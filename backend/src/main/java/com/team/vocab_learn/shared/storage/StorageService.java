package com.team.vocab_learn.shared.storage;

import java.net.URI;
import java.time.Instant;
import java.util.Optional;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.team.vocab_learn.shared.config.StorageProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

/**
 * Files never pass through the API: the browser PUTs to a presigned URL, then calls "complete",
 * and reads via a short-lived presigned GET (≤ 5 min).
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService {

	public record PresignedUpload(URI url, String method, String contentType, long contentLength, Instant expiresAt) {
	}

	public record StoredObject(long size, String contentType) {
	}

	private final S3Client s3;
	private final S3Presigner presigner;
	private final StorageProperties props;

	public PresignedUpload presignUpload(String objectKey, String contentType, long contentLength) {
		var presigned = presigner.presignPutObject(r -> r.signatureDuration(props.urlTtl())
				.putObjectRequest(p -> p.bucket(props.bucket()).key(objectKey)
						.contentType(contentType).contentLength(contentLength)));
		return new PresignedUpload(URI.create(presigned.url().toString()), "PUT", contentType, contentLength,
				presigned.expiration());
	}

	public URI presignDownload(String objectKey) {
		var presigned = presigner.presignGetObject(r -> r.signatureDuration(props.urlTtl())
				.getObjectRequest(g -> g.bucket(props.bucket()).key(objectKey)));
		return URI.create(presigned.url().toString());
	}

	/** Real size/MIME after upload — never trust what the client declared. */
	public Optional<StoredObject> head(String objectKey) {
		try {
			var head = s3.headObject(h -> h.bucket(props.bucket()).key(objectKey));
			return Optional.of(new StoredObject(head.contentLength(), head.contentType()));
		} catch (NoSuchKeyException e) {
			return Optional.empty();
		} catch (S3Exception e) {
			if (e.statusCode() == 404) {
				return Optional.empty();
			}
			throw e;
		}
	}

	/** Chỉ dùng cho tệp nhỏ đã kiểm kích thước bằng head() (ảnh ≤ 5 MB). */
	public byte[] read(String objectKey) {
		return s3.getObjectAsBytes(g -> g.bucket(props.bucket()).key(objectKey)).asByteArray();
	}

	public void write(String objectKey, byte[] bytes, String contentType) {
		s3.putObject(p -> p.bucket(props.bucket()).key(objectKey).contentType(contentType),
				RequestBody.fromBytes(bytes));
	}

	public void delete(String objectKey) {
		s3.deleteObject(d -> d.bucket(props.bucket()).key(objectKey));
	}

	/** Dev/test convenience: create the bucket if missing (app.storage.create-bucket=true). */
	@EventListener(ApplicationReadyEvent.class)
	public void ensureBucket() {
		if (!props.createBucket()) {
			return;
		}
		try {
			try {
				s3.headBucket(b -> b.bucket(props.bucket()));
			} catch (NoSuchBucketException notFound) {
				s3.createBucket(b -> b.bucket(props.bucket()));
				log.info("Created bucket {}", props.bucket());
			}
		} catch (SdkException e) {
			log.warn("Storage not reachable ({}); file features will fail until it is up", e.getMessage());
		}
	}
}
