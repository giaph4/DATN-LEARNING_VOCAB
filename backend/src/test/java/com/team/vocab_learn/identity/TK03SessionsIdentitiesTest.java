package com.team.vocab_learn.identity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.servlet.http.Cookie;

import com.team.vocab_learn.AbstractIntegrationTest;
import com.team.vocab_learn.identity.api.GoogleLoginHandler;
import com.team.vocab_learn.identity.application.AccountSecurityService;
import com.team.vocab_learn.identity.application.GoogleLoginService;
import com.team.vocab_learn.identity.domain.NhaCungCapDangNhap;
import com.team.vocab_learn.shared.errors.ErrorCode;

/** B1.5 (TK-03/05): phiên đăng nhập, xác thực lại, liên kết/gỡ Google. */
class TK03SessionsIdentitiesTest extends AbstractIntegrationTest {

	private static final String CSRF = "b15-csrf-token";
	private static final String PASSWORD = "DemoHoc!2026";

	@Autowired
	GoogleLoginHandler googleHandler;

	@Autowired
	GoogleLoginService googleLogin;

	@Autowired
	AccountSecurityService accountSecurity;

	@Test
	void tc01_listShowsOnlyMySessionsWithHashedIds_revokeOtherDevice() throws Exception {
		String email = activeAccount();
		Cookie laptop = login(email, "Mozilla/5.0 (Windows NT 10.0) Chrome/130");
		Cookie phone = login(email, "Mozilla/5.0 (iPhone; CPU iPhone OS 18_0) Safari/604");
		login(activeAccount(), "Nguoi khac");

		var body = call(HttpMethod.GET, "/api/v1/auth/sessions", null, laptop)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[?(@.current == true)].userAgent").value("Mozilla/5.0 (Windows NT 10.0) Chrome/130"))
				.andReturn().getResponse().getContentAsString();
		String phoneId = com.jayway.jsonpath.JsonPath.<List<String>>read(body, "$[?(@.current == false)].id").get(0);
		assertThat(phoneId).matches("[0-9a-f]{16}");
		assertThat(body).doesNotContain(phone.getValue());

		call(HttpMethod.DELETE, "/api/v1/auth/sessions/" + phoneId, null, laptop).andExpect(status().isNoContent());
		call(HttpMethod.GET, "/api/v1/auth/sessions", null, phone).andExpect(status().isUnauthorized());
		call(HttpMethod.GET, "/api/v1/auth/sessions", null, laptop).andExpect(jsonPath("$.length()").value(1));
		call(HttpMethod.DELETE, "/api/v1/auth/sessions/" + phoneId, null, laptop).andExpect(status().isNotFound());
	}

	@Test
	void tc02_reauthThenLinkAndUnlinkGoogle() throws Exception {
		String email = activeAccount();
		Cookie session = login(email, "Chrome");
		long userId = jdbc.queryForObject("SELECT id FROM nguoi_dung WHERE email_chuan_hoa = ?", Long.class, email);

		call(HttpMethod.GET, "/api/v1/auth/identities", null, session)
				.andExpect(jsonPath("$.hasPassword").value(true)).andExpect(jsonPath("$.identities.length()").value(0));
		call(HttpMethod.POST, "/api/v1/auth/identities/google/link", null, session)
				.andExpect(status().isForbidden()).andExpect(jsonPath("$.code").value("REAUTH_REQUIRED"));
		call(HttpMethod.POST, "/api/v1/auth/reauthenticate", "{\"password\":\"sai-mat-khau\"}", session)
				.andExpect(status().isUnauthorized()).andExpect(jsonPath("$.code").value("INVALID_CREDENTIALS"));
		call(HttpMethod.POST, "/api/v1/auth/reauthenticate", "{\"password\":\"%s\"}".formatted(PASSWORD), session)
				.andExpect(status().isOk()).andExpect(jsonPath("$.reauthenticatedUntil").isString());
		call(HttpMethod.POST, "/api/v1/auth/identities/google/link", null, session)
				.andExpect(status().isOk()).andExpect(jsonPath("$.redirectUrl").value("/oauth2/authorization/google"));
		call(HttpMethod.POST, "/api/v1/auth/identities/facebook/link", null, session).andExpect(status().isNotFound());

		// Google gọi lại với ý định liên kết trong phiên: gắn vào tài khoản đang đăng nhập, email Google được phép khác
		String sub = "google-" + UUID.randomUUID();
		var response = googleCallback(sub, "khac-" + UUID.randomUUID() + "@gmail.test", userId);
		assertThat(response.getRedirectedUrl()).isEqualTo("http://localhost:3000/cai-dat?lien-ket=google");
		call(HttpMethod.GET, "/api/v1/auth/identities", null, session)
				.andExpect(jsonPath("$.identities[0].provider").value("google"));

		call(HttpMethod.DELETE, "/api/v1/auth/identities/google", null, session).andExpect(status().isNoContent());
		assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM danh_tinh_dang_nhap WHERE nguoi_dung_id = ?", Integer.class, userId))
				.isZero();
	}

	@Test
	void tc03_cannotStealLinkedGoogle_cannotRemoveLastMethod_anonymousGets401() throws Exception {
		String ownerSub = "google-" + UUID.randomUUID();
		Long googleOnly = googleLogin.signIn(ownerSub, "gg-" + UUID.randomUUID() + "@gmail.test", true, "Chu Google")
				.authUser().id();
		assertThatThrownBy(() -> accountSecurity.unlink(googleOnly, NhaCungCapDangNhap.GOOGLE))
				.extracting("code").isEqualTo(ErrorCode.LAST_LOGIN_METHOD);

		String email = activeAccount();
		long attacker = jdbc.queryForObject("SELECT id FROM nguoi_dung WHERE email_chuan_hoa = ?", Long.class, email);
		var response = googleCallback(ownerSub, "gg@gmail.test", attacker);
		assertThat(response.getRedirectedUrl()).isEqualTo("http://localhost:3000/cai-dat?loi=GOOGLE_ACCOUNT_IN_USE");
		assertThat(jdbc.queryForObject("SELECT nguoi_dung_id FROM danh_tinh_dang_nhap WHERE dinh_danh_nha_cung_cap = ?",
				Long.class, ownerSub)).isEqualTo(googleOnly);

		call(HttpMethod.GET, "/api/v1/auth/sessions", null, null).andExpect(status().isUnauthorized());
		call(HttpMethod.GET, "/api/v1/auth/identities", null, null).andExpect(status().isUnauthorized());
	}

	private MockHttpServletResponse googleCallback(String sub, String googleEmail, long linkUserId) throws Exception {
		var idToken = new OidcIdToken("id-token", Instant.now(), Instant.now().plusSeconds(60),
				Map.of("sub", sub, "email", googleEmail, "email_verified", true, "name", "Google"));
		var request = new MockHttpServletRequest();
		request.getSession().setAttribute("vl.linkGoogle", linkUserId + ":" + Instant.now().toEpochMilli());
		var response = new MockHttpServletResponse();
		googleHandler.onAuthenticationSuccess(request, response,
				new OAuth2AuthenticationToken(new DefaultOidcUser(List.of(), idToken), List.of(), "google"));
		return response;
	}

	private String activeAccount() throws Exception {
		String email = "b15-" + UUID.randomUUID() + "@test.local";
		call(HttpMethod.POST, "/api/v1/auth/register", """
				{"email":"%s","password":"%s","displayName":"Học Phiên"}""".formatted(email, PASSWORD), null)
				.andExpect(status().isAccepted());
		jdbc.update("UPDATE nguoi_dung SET trang_thai = 'ACTIVE' WHERE email_chuan_hoa = ?", email);
		return email;
	}

	private Cookie login(String email, String userAgent) throws Exception {
		return call(HttpMethod.POST, "/api/v1/auth/login", "{\"email\":\"%s\",\"password\":\"%s\"}".formatted(email, PASSWORD),
				null, userAgent).andExpect(status().isOk()).andReturn().getResponse().getCookie("VOCAB_SESSION");
	}

	private ResultActions call(HttpMethod method, String path, String body, Cookie session) throws Exception {
		return call(method, path, body, session, "JUnit");
	}

	private ResultActions call(HttpMethod method, String path, String body, Cookie session, String userAgent) throws Exception {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		String ip = "10." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(1, 255);
		var req = MockMvcRequestBuilders.request(method, path)
				.header("X-CSRF-TOKEN", CSRF).header("User-Agent", userAgent)
				.with(x -> { x.setRemoteAddr(ip); return x; });
		if (session != null) {
			req.cookie(new Cookie("XSRF-TOKEN", CSRF), session);
		} else {
			req.cookie(new Cookie("XSRF-TOKEN", CSRF));
		}
		if (body != null) {
			req.contentType(MediaType.APPLICATION_JSON).content(body);
		}
		return mvc.perform(req);
	}
}
