package com.team.vocab_learn.shared.security;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import org.springframework.stereotype.Component;

import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/** Mốc xác thực lại lưu trong phiên; thao tác nhạy cảm (liên kết/gỡ Google, xóa tài khoản…) cần mốc còn hạn 5 phút. */
@Component
@RequiredArgsConstructor
public class Reauthentication {

	public static final Duration WINDOW = Duration.ofMinutes(5);
	static final String ATTR = "vl.reauthAt";

	private final Clock clock;

	/** Gọi sau khi đã kiểm mật khẩu; trả về hạn cuối. */
	public Instant mark(HttpServletRequest request) {
		Instant now = clock.instant();
		request.getSession().setAttribute(ATTR, now.toEpochMilli());
		return now.plus(WINDOW);
	}

	public void require(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Object at = session == null ? null : session.getAttribute(ATTR);
		if (!(at instanceof Long millis) || clock.instant().isAfter(Instant.ofEpochMilli(millis).plus(WINDOW))) {
			throw new ApiException(ErrorCode.REAUTH_REQUIRED, "Hãy nhập lại mật khẩu để tiếp tục thao tác này.");
		}
	}
}
