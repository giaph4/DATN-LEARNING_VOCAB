package com.team.vocab_learn.media.application;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.team.vocab_learn.media.domain.LoaiTep;
import com.team.vocab_learn.media.domain.TepDaPhuongTien;
import com.team.vocab_learn.media.domain.TrangThaiTep;
import com.team.vocab_learn.media.infrastructure.TepDaPhuongTienRepository;
import com.team.vocab_learn.shared.config.StorageProperties;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.security.RateLimiter;
import com.team.vocab_learn.shared.storage.StorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Tệp không đi qua API: trình duyệt PUT thẳng lên kho bằng URL ký, rồi gọi complete để máy chủ kiểm tra.
 * Không gọi kho (mạng) bên trong giao dịch DB.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MediaService {

	public enum Purpose {
		AVATAR
	}

	public record UploadIntent(Long mediaId, URI uploadUrl, String method, Map<String, String> headers, Instant expiresAt) {
	}

	public record MediaView(Long id, String mimeType, long sizeBytes, TrangThaiTep status) {
		static MediaView of(TepDaPhuongTien t) {
			return new MediaView(t.getId(), t.getMimeType(), t.getKichThuocByte(), t.getTrangThai());
		}
	}

	public record Access(URI url, Instant expiresAt) {
	}

	/** TK-06: ảnh JPEG/PNG/WebP tối đa 5 MB. */
	public static final long MAX_IMAGE_BYTES = 5L * 1024 * 1024;
	private static final Map<String, String> IMAGE_TYPES = Map.of("image/jpeg", "jpg", "image/png", "png", "image/webp", "webp");

	private final TepDaPhuongTienRepository repo;
	private final StorageService storage;
	private final StorageProperties storageProps;
	private final RateLimiter rateLimiter;
	private final TransactionTemplate tx;
	private final Clock clock;

	public UploadIntent createUploadIntent(long userId, Purpose purpose, String contentType, long sizeBytes) {
		String ext = IMAGE_TYPES.get(contentType);
		if (ext == null) {
			throw new ApiException(ErrorCode.UNSUPPORTED_MEDIA_TYPE, "Chỉ nhận ảnh JPEG, PNG hoặc WebP.");
		}
		if (sizeBytes < 1 || sizeBytes > MAX_IMAGE_BYTES) {
			throw new ApiException(ErrorCode.BUSINESS_RULE_VIOLATED, "Ảnh tối đa 5 MB.");
		}
		rateLimiter.check(RateLimiter.Policy.UPLOAD_INTENT_ACCOUNT, String.valueOf(userId));
		String key = "users/%d/%s/%s.%s".formatted(userId, purpose.name().toLowerCase(), UUID.randomUUID(), ext);
		TepDaPhuongTien tep = repo.save(TepDaPhuongTien.startUpload(userId, key, contentType, sizeBytes, clock.instant()));
		var signed = storage.presignUpload(key, contentType, sizeBytes);
		return new UploadIntent(tep.getId(), signed.url(), signed.method(), Map.of("Content-Type", contentType),
				signed.expiresAt());
	}

	/**
	 * Đọc tệp thật trên kho, kiểm chữ ký byte, bỏ siêu dữ liệu, ghi đè bản sạch rồi mới chuyển READY.
	 * Gọi lại khi đã READY trả kết quả cũ (an toàn khi mạng chập chờn).
	 */
	public MediaView complete(long userId, long mediaId) {
		TepDaPhuongTien tep = requireOwned(userId, mediaId);
		if (tep.getTrangThai() == TrangThaiTep.READY) {
			return MediaView.of(tep);
		}
		if (tep.getTrangThai() != TrangThaiTep.UPLOADING) {
			throw new ApiException(ErrorCode.CONFLICT, "Tệp này đã bị từ chối hoặc đã xóa. Hãy tải ảnh khác.");
		}
		String key = tep.getKhoaDoiTuongLuuTru();
		var stored = storage.head(key).orElseThrow(() -> new ApiException(ErrorCode.BUSINESS_RULE_VIOLATED,
				"Máy chủ chưa nhận được tệp. Hãy tải lên trước khi xác nhận."));
		ImageSanitizer.CleanImage clean;
		try {
			if (stored.size() > MAX_IMAGE_BYTES) {
				throw new ApiException(ErrorCode.BUSINESS_RULE_VIOLATED, "Ảnh tối đa 5 MB.");
			}
			clean = ImageSanitizer.sanitize(storage.read(key));
		} catch (ApiException e) {
			reject(mediaId, key);
			throw e;
		}
		storage.write(key, clean.bytes(), clean.mimeType());
		return tx.execute(s -> {
			TepDaPhuongTien locked = repo.findForUpdate(mediaId).orElseThrow();
			if (locked.getTrangThai() == TrangThaiTep.UPLOADING) {
				locked.markReady(clean.mimeType(), clean.bytes().length, sha256(clean.bytes()));
			}
			return MediaView.of(locked);
		});
	}

	public Access access(long userId, long mediaId) {
		TepDaPhuongTien tep = requireOwned(userId, mediaId);
		if (tep.getTrangThai() != TrangThaiTep.READY) {
			throw ApiException.notFound("tệp");
		}
		return new Access(storage.presignDownload(tep.getKhoaDoiTuongLuuTru()), clock.instant().plus(storageProps.urlTtl()));
	}

	/** Cho module khác gắn ảnh: chỉ ảnh READY của chính người dùng; tệp người khác trả 404. */
	@Transactional(propagation = Propagation.MANDATORY)
	public void requireUsableImage(long userId, long mediaId) {
		TepDaPhuongTien tep = requireOwned(userId, mediaId);
		if (tep.getLoaiTep() != LoaiTep.IMAGE || tep.getTrangThai() != TrangThaiTep.READY) {
			throw new ApiException(ErrorCode.BUSINESS_RULE_VIOLATED, "Ảnh chưa sẵn sàng. Hãy tải lên và xác nhận trước.");
		}
	}

	/**
	 * TK-06: thay ảnh thì tệp cũ chỉ bị xóa khi không còn chỗ nào tham chiếu. Gọi sau khi đã gỡ tham chiếu
	 * trong cùng giao dịch; xóa trên kho chạy sau commit.
	 */
	@Transactional(propagation = Propagation.MANDATORY)
	public void releaseIfUnused(long mediaId) {
		TepDaPhuongTien tep = repo.findForUpdate(mediaId).orElse(null);
		if (tep == null || tep.getTrangThai() == TrangThaiTep.DELETED || repo.countReferences(mediaId) > 0) {
			return;
		}
		tep.markDeleted(clock.instant());
		String key = tep.getKhoaDoiTuongLuuTru();
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
			@Override
			public void afterCommit() {
				deleteQuietly(key);
			}
		});
	}

	public Access accessOrNull(Long mediaId) {
		if (mediaId == null) {
			return null;
		}
		return repo.findById(mediaId).filter(t -> t.getTrangThai() == TrangThaiTep.READY)
				.map(t -> new Access(storage.presignDownload(t.getKhoaDoiTuongLuuTru()),
						clock.instant().plus(storageProps.urlTtl())))
				.orElse(null);
	}

	private TepDaPhuongTien requireOwned(long userId, long mediaId) {
		return repo.findById(mediaId).filter(t -> t.ownedBy(userId)).orElseThrow(() -> ApiException.notFound("tệp"));
	}

	private void reject(long mediaId, String key) {
		tx.executeWithoutResult(s -> repo.findForUpdate(mediaId).ifPresent(TepDaPhuongTien::reject));
		deleteQuietly(key);
	}

	private static byte[] sha256(byte[] bytes) {
		try {
			return MessageDigest.getInstance("SHA-256").digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}

	private void deleteQuietly(String key) {
		try {
			storage.delete(key);
		} catch (RuntimeException e) {
			// Tệp mồ côi trên kho không ảnh hưởng người dùng; tác vụ dọn định kỳ sẽ xử lý
			log.warn("Could not delete object {}: {}", key, e.getMessage());
		}
	}
}
