package com.team.vocab_learn.shared.storage;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.team.vocab_learn.shared.config.StorageProperties;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

	@Bean(destroyMethod = "close")
	S3Client s3Client(StorageProperties p) {
		return S3Client.builder()
				.endpointOverride(URI.create(p.endpoint()))
				.region(Region.of(p.region()))
				.credentialsProvider(credentials(p))
				.forcePathStyle(true)
				.build();
	}

	/** Signs with the browser-reachable endpoint (in Docker the API sees "minio:9000", the browser "localhost:9000"). */
	@Bean(destroyMethod = "close")
	S3Presigner s3Presigner(StorageProperties p) {
		String endpoint = p.publicEndpoint() == null || p.publicEndpoint().isBlank() ? p.endpoint() : p.publicEndpoint();
		return S3Presigner.builder()
				.endpointOverride(URI.create(endpoint))
				.region(Region.of(p.region()))
				.credentialsProvider(credentials(p))
				.serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
				.build();
	}

	private static StaticCredentialsProvider credentials(StorageProperties p) {
		return StaticCredentialsProvider.create(AwsBasicCredentials.create(p.accessKey(), p.secretKey()));
	}
}
