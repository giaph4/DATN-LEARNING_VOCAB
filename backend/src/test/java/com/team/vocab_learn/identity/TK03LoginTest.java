package com.team.vocab_learn.identity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.servlet.http.Cookie;

import com.team.vocab_learn.AbstractIntegrationTest;
import com.team.vocab_learn.identity.api.GoogleLoginHandler;
import com.team.vocab_learn.identity.application.GoogleLoginService;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.security.AuthUser;

/** TC-TK-03: đăng nhập email/mật khẩu, Google, đăng xuất. */
class TK03LoginTest extends AbstractIntegrationTest {

	private static final String CSRF = "tk03-csrf-token";
	private static final String PASSWORD = "DemoHoc!2026";
	// Đường dẫn cần đăng nhập nhưng không có controller: có phiên → 404, không phiên → 401
	private static final String PROTECTED = "/api/v1/__probe";

	@Autowired
	GoogleLoginService googleLogin;

	@Autowired
	GoogleLoginHandler googleHandler;

	@Test
	void tc01_loginCreatesSession_logoutRevokesIt() throws Exception {
		String email = account("ACTIVE");

		MvcResult res = login(email.toUpperCase(), PASSWORD)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isString())
				.andExpect(jsonPath("$.email").value(email))
				.andExpect(jsonPath("$.displayName").value("Học A"))
				.andExpect(jsonPath("$.roles[0]").value("LEARNER"))
				.andExpect(jsonPath("$.status").value("ACTIVE"))
				.andReturn();
		Cookie session = res.getResponse().getCookie("VOCAB_SESSION");
		assertThat(session).isNotNull();
		assertThat(session.isHttpOnly()).isTrue();
		assertThat(jdbc.queryForObject("SELECT thoi_diem_dang_nhap_gan_nhat FROM nguoi_dung WHERE email_chuan_hoa = ?",
				java.sql.Timestamp.class, email)).isNotNull();

		mvc.perform(get(PROTECTED).cookie(session)).andExpect(status().isNotFound());
		post("/api/v1/auth/logout", null, session).andExpect(status().isNoContent());
		mvc.perform(get(PROTECTED).cookie(session)).andExpect(status().isUnauthorized());
		post("/api/v1/auth/logout", null, session).andExpect(status().isNoContent());
	}

	@Test
	void tc02_wrongPasswordUnknownEmailPendingLockedCreateNoSession() throws Exception {
		String active = account("ACTIVE");
		expectNoSession(login(active, "sai-mat-khau-1").andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.code").value("INVALID_CREDENTIALS")));
		expectNoSession(login("khong-co-" + UUID.randomUUID() + "@test.local", PASSWORD)
				.andExpect(status().isUnauthorized()).andExpect(jsonPath("$.code").value("INVALID_CREDENTIALS")));
		expectNoSession(login(account("PENDING_VERIFICATION"), PASSWORD)
				.andExpect(status().isForbidden()).andExpect(jsonPath("$.code").value("EMAIL_NOT_VERIFIED")));
		expectNoSession(login(account("LOCKED"), PASSWORD)
				.andExpect(status().isForbidden()).andExpect(jsonPath("$.code").value("ACCOUNT_LOCKED")));
		// Mật khẩu sai thì không lộ trạng thái tài khoản
		login(account("LOCKED"), "sai-mat-khau-1").andExpect(jsonPath("$.code").value("INVALID_CREDENTIALS"));
		login("khong-phai-email", "").andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value("VALIDATION_FAILED"));
	}

	@Test
	void tc03_sixthAttemptPerAccountIs429_andGoogleNeverMergesByEmail() throws Exception {
		String email = account("ACTIVE");
		for (int i = 0; i < 5; i++) {
			login(email, "sai-mat-khau-" + i).andExpect(status().isUnauthorized());
		}
		login(email, PASSWORD).andExpect(status().isTooManyRequests()).andExpect(jsonPath("$.code").value("RATE_LIMITED"));

		// Bắt đầu Google: chuyển tới Google kèm state và nonce
		String location = mvc.perform(get("/oauth2/authorization/google"))
				.andExpect(status().isFound()).andReturn().getResponse().getRedirectedUrl();
		assertThat(location).startsWith("https://accounts.google.com/").contains("state=").contains("nonce=");

		// Google lần đầu tạo tài khoản ACTIVE + danh tính; lần sau cùng "sub" vào lại đúng tài khoản
		String sub = "google-" + UUID.randomUUID();
		String googleEmail = "gg-" + UUID.randomUUID() + "@gmail.test";
		Long first = googleLogin.signIn(sub, googleEmail, true, "Lan Google").authUser().id();
		assertThat(googleLogin.signIn(sub, googleEmail, true, "Lan Google").authUser().id()).isEqualTo(first);
		assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM danh_tinh_dang_nhap WHERE dinh_danh_nha_cung_cap = ?",
				Integer.class, sub)).isEqualTo(1);

		// Email đã có tài khoản mật khẩu: không tự ghép
		String otherSub = "google-" + UUID.randomUUID();
		assertThatThrownBy(() -> googleLogin.signIn(otherSub, email, true, "Ke gia mao"))
				.extracting("code").isEqualTo(ErrorCode.GOOGLE_EMAIL_IN_USE);
		assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM danh_tinh_dang_nhap WHERE dinh_danh_nha_cung_cap = ?",
				Integer.class, otherSub)).isZero();
		assertThatThrownBy(() -> googleLogin.signIn("google-" + UUID.randomUUID(), "x-" + UUID.randomUUID() + "@gmail.test",
				false, "Chua xac thuc")).isInstanceOf(ApiException.class).extracting("code").isEqualTo(ErrorCode.EMAIL_NOT_VERIFIED);
	}

	@Test
	void tc03_googleCallbackStoresAuthUserInSession_andCancelRedirectsToLogin() throws Exception {
		String sub = "google-" + UUID.randomUUID();
		var idToken = new OidcIdToken("id-token", Instant.now(), Instant.now().plusSeconds(60),
				Map.of("sub", sub, "email", "cb-" + UUID.randomUUID() + "@gmail.test", "email_verified", true, "name", "Minh"));
		var oidcUser = new DefaultOidcUser(java.util.List.of(), idToken);
		var request = new MockHttpServletRequest();
		var response = new MockHttpServletResponse();

		googleHandler.onAuthenticationSuccess(request, response,
				new OAuth2AuthenticationToken(oidcUser, java.util.List.of(), "google"));
		assertThat(response.getRedirectedUrl()).isEqualTo("http://localhost:3000/hoc");
		var context = (SecurityContext) request.getSession()
				.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		assertThat(context.getAuthentication().getPrincipal()).isInstanceOf(AuthUser.class);

		var cancelled = new MockHttpServletResponse();
		googleHandler.onAuthenticationFailure(new MockHttpServletRequest(), cancelled,
				new OAuth2AuthenticationException(new OAuth2Error("access_denied")));
		assertThat(cancelled.getRedirectedUrl()).isEqualTo("http://localhost:3000/dang-nhap?loi=GOOGLE_CANCELLED");
	}

	private void expectNoSession(ResultActions result) {
		assertThat(result.andReturn().getResponse().getCookie("VOCAB_SESSION")).isNull();
	}

	/** Tạo tài khoản qua API đăng ký rồi đặt trạng thái mong muốn. */
	private String account(String trangThai) throws Exception {
		String email = "tk03-" + UUID.randomUUID() + "@test.local";
		post("/api/v1/auth/register", """
				{"email":"%s","password":"%s","displayName":"Học A"}""".formatted(email, PASSWORD), null)
				.andExpect(status().isAccepted());
		jdbc.update("UPDATE nguoi_dung SET trang_thai = ? WHERE email_chuan_hoa = ?", trangThai, email);
		return email;
	}

	private ResultActions login(String email, String password) throws Exception {
		return post("/api/v1/auth/login", """
				{"email":"%s","password":"%s"}""".formatted(email, password), null);
	}

	private ResultActions post(String path, String body, Cookie session) throws Exception {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		String ip = "10." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(1, 255);
		var req = MockMvcRequestBuilders.post(path)
				.cookie(new Cookie("XSRF-TOKEN", CSRF)).header("X-CSRF-TOKEN", CSRF)
				.with(x -> { x.setRemoteAddr(ip); return x; });
		if (session != null) {
			req.cookie(new Cookie("XSRF-TOKEN", CSRF), session);
		}
		if (body != null) {
			req.contentType(MediaType.APPLICATION_JSON).content(body);
		}
		return mvc.perform(req);
	}
}
