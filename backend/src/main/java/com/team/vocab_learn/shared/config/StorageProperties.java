package com.team.vocab_learn.shared.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * S3-compatible storage (MinIO in dev).
 * {@code publicEndpoint} is the host the browser can reach; it signs presigned URLs.
 */
@ConfigurationProperties("app.storage")
public record StorageProperties(
		String endpoint,
		String publicEndpoint,
		@DefaultValue("us-east-1") String region,
		@DefaultValue("vocab-media") String bucket,
		String accessKey,
		String secretKey,
		@DefaultValue("PT5M") Duration urlTtl,
		@DefaultValue("false") boolean createBucket) {
}
