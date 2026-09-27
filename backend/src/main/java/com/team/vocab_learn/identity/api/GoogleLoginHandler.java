package com.team.vocab_learn.identity.api;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.team.vocab_learn.identity.application.GoogleLoginService;
import com.team.vocab_learn.identity.application.SignedInUser;
import com.team.vocab_learn.shared.config.AppProperties;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.security.Reauthentication;
import com.team.vocab_learn.shared.security.SessionAuthenticator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * TK-03: Spring đã kiểm state, nonce và chữ ký id_token trước khi gọi handler này.
 * Thay principal Google bằng AuthUser của hệ thống rồi chuyển trình duyệt về FE.
 * Nếu phiên có "ý định liên kết" (B1.5) thì liên kết Google vào tài khoản đang đăng nhập thay vì đăng nhập.
 */
@Component
@RequiredArgsConstructor
public class GoogleLoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

	static final String SUCCESS_PATH = "/hoc";
	static final String LOGIN_PATH = "/dang-nhap?loi=";
	static final String SETTINGS_PATH = "/cai-dat";
	static final String LINK_INTENT = "vl.linkGoogle";

	private final GoogleLoginService googleLogin;
	private final SessionAuthenticator sessions;
	private final AppProperties app;
	private final Clock clock;

	/** Ghi nhớ người dùng muốn liên kết; hết hạn cùng cửa sổ xác thực lại. */
	static void rememberLinkIntent(HttpSession session, long userId, Instant now) {
		session.setAttribute(LINK_INTENT, userId + ":" + now.toEpochMilli());
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException {
		OidcUser google = (OidcUser) auth.getPrincipal();
		Long linkUserId = takeLinkIntent(request.getSession(false));
		if (linkUserId != null) {
			link(linkUserId, google, request, response);
			return;
		}
		try {
			SignedInUser user = googleLogin.signIn(google.getSubject(), google.getEmail(),
					Boolean.TRUE.equals(google.getEmailVerified()), google.getFullName());
			sessions.signIn(user.authUser(), request, response);
			response.sendRedirect(app.publicBaseUrl() + SUCCESS_PATH);
		} catch (ApiException e) {
			// Không giữ phiên Google khi tài khoản không được phép vào (khóa, email trùng...)
			sessions.signOut(request);
			response.sendRedirect(app.publicBaseUrl() + LOGIN_PATH + e.getCode().name());
		}
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException {
		boolean cancelled = e instanceof OAuth2AuthenticationException o && "access_denied".equals(o.getError().getErrorCode());
		String code = cancelled ? "GOOGLE_CANCELLED" : "GOOGLE_FAILED";
		// Hủy giữa chừng khi đang liên kết: phiên cũ vẫn còn, quay về trang cài đặt
		boolean linking = takeLinkIntent(request.getSession(false)) != null;
		response.sendRedirect(app.publicBaseUrl() + (linking ? SETTINGS_PATH + "?loi=" : LOGIN_PATH) + code);
	}

	private void link(long userId, OidcUser google, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String target;
		SignedInUser user;
		try {
			user = googleLogin.link(userId, google.getSubject(), google.getEmail());
			target = SETTINGS_PATH + "?lien-ket=google";
		} catch (ApiException e) {
			user = googleLogin.reload(userId);
			target = SETTINGS_PATH + "?loi=" + e.getCode().name();
		}
		// Spring đã đặt principal Google vào phiên: trả lại đúng người dùng của hệ thống
		sessions.signIn(user.authUser(), request, response);
		response.sendRedirect(app.publicBaseUrl() + target);
	}

	private Long takeLinkIntent(HttpSession session) {
		Object value = session == null ? null : session.getAttribute(LINK_INTENT);
		if (!(value instanceof String s)) {
			return null;
		}
		session.removeAttribute(LINK_INTENT);
		String[] parts = s.split(":");
		Instant at = Instant.ofEpochMilli(Long.parseLong(parts[1]));
		return clock.instant().isAfter(at.plus(Reauthentication.WINDOW)) ? null : Long.valueOf(parts[0]);
	}
}
