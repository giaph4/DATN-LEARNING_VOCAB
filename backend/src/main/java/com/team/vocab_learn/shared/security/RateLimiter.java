package com.team.vocab_learn.shared.security;

import java.time.Duration;
import java.util.Locale;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Fixed-window counter in Redis: INCR key, set TTL on first hit, reject above the limit with 429. */
@Component
@RequiredArgsConstructor
public class RateLimiter {

	@Getter
	@RequiredArgsConstructor
	public enum Policy {
		LOGIN_IP(10, Duration.ofMinutes(1)),
		LOGIN_ACCOUNT(5, Duration.ofMinutes(1)),
		REGISTER_IP(5, Duration.ofHours(1)),
		RESEND_VERIFY_ACCOUNT(3, Duration.ofHours(1)),
		PASSWORD_RESET_IP(5, Duration.ofHours(1)),
		PASSWORD_RESET_ACCOUNT(3, Duration.ofHours(1)),
		UPLOAD_INTENT_ACCOUNT(30, Duration.ofHours(1));

		private final int limit;
		private final Duration window;
	}

	private final StringRedisTemplate redis;

	/** @param subject IP address or normalized email */
	public void check(Policy policy, String subject) {
		String key = "rl:" + policy.name().toLowerCase(Locale.ROOT) + ":" + subject;
		Long count = redis.opsForValue().increment(key);
		if (count != null && count == 1) {
			redis.expire(key, policy.getWindow());
		}
		if (count != null && count > policy.getLimit()) {
			Long ttl = redis.getExpire(key);
			throw new ApiException(ErrorCode.RATE_LIMITED,
					"Bạn thao tác quá nhanh, hãy thử lại sau %d giây.".formatted(ttl == null || ttl < 0 ? 60 : ttl));
		}
	}
}
