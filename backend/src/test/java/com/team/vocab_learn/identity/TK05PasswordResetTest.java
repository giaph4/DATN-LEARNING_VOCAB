package com.team.vocab_learn.identity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.servlet.http.Cookie;

import com.team.vocab_learn.AbstractIntegrationTest;
import com.team.vocab_learn.identity.application.OneTimeCodeService;

/** TC-TK-05: quên mật khẩu, đặt lại mật khẩu, thu hồi phiên. */
class TK05PasswordResetTest extends AbstractIntegrationTest {

	private static final String CSRF = "tk05-csrf-token";
	private static final String OLD = "DemoHoc!2026";
	private static final String NEW = "MatKhauMoi!2026";
	private static final String PROTECTED = "/api/v1/__probe";

	@Test
	void tc01_resetChangesPassword_bumpsAuthVersion_revokesEverySession() throws Exception {
		String email = activeAccount();
		Cookie phone = sessionOf(email, OLD);
		Cookie laptop = sessionOf(email, OLD);

		send("/api/v1/auth/forgot-password", "{\"email\":\"%s\"}".formatted(email.toUpperCase()), null)
				.andExpect(status().isAccepted()).andExpect(jsonPath("$.message").isString());
		String token = latestToken(email);
		assertThat(jdbc.queryForObject("""
				SELECT TIMESTAMPDIFF(MINUTE, created_at, thoi_diem_het_han) FROM ma_xac_thuc WHERE ma_xac_thuc_bam = ?""",
				Integer.class, (Object) OneTimeCodeService.sha256(token))).isEqualTo(15);

		reset(token, NEW).andExpect(status().isOk()).andExpect(jsonPath("$.message").isString());

		assertThat(jdbc.queryForObject("SELECT phien_ban_xac_thuc FROM nguoi_dung WHERE email_chuan_hoa = ?", Long.class, email))
				.isEqualTo(1L);
		mvc.perform(get(PROTECTED).cookie(phone)).andExpect(status().isUnauthorized());
		mvc.perform(get(PROTECTED).cookie(laptop)).andExpect(status().isUnauthorized());
		login(email, OLD).andExpect(status().isUnauthorized());
		login(email, NEW).andExpect(status().isOk());
	}

	@Test
	void tc02_usedExpiredWrongTokenAndShortPasswordAreRejected() throws Exception {
		String email = activeAccount();
		send("/api/v1/auth/forgot-password", "{\"email\":\"%s\"}".formatted(email), null).andExpect(status().isAccepted());
		String token = latestToken(email);

		reset(token, "ngan").andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.fieldErrors[?(@.field=='newPassword')]").exists());
		reset("khong-ton-tai", NEW).andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("TOKEN_INVALID"));

		jdbc.update("UPDATE ma_xac_thuc SET thoi_diem_het_han = NOW(3) - INTERVAL 1 SECOND WHERE ma_xac_thuc_bam = ?",
				(Object) OneTimeCodeService.sha256(token));
		reset(token, NEW).andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("TOKEN_EXPIRED"));
		login(email, OLD).andExpect(status().isOk());

		send("/api/v1/auth/forgot-password", "{\"email\":\"%s\"}".formatted(email), null).andExpect(status().isAccepted());
		String fresh = latestToken(email);
		reset(fresh, NEW).andExpect(status().isOk());
		reset(fresh, "MatKhauKhac!2026").andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("TOKEN_INVALID"));
		login(email, NEW).andExpect(status().isOk());
	}

	@Test
	void tc03_sameAnswerForUnknownAndPendingEmail_andFourthRequestPerAccountIs429() throws Exception {
		String unknown = "khong-co-" + UUID.randomUUID() + "@test.local";
		send("/api/v1/auth/forgot-password", "{\"email\":\"%s\"}".formatted(unknown), null).andExpect(status().isAccepted());
		assertThat(resetMails(unknown)).isZero();

		String pending = register();
		send("/api/v1/auth/forgot-password", "{\"email\":\"%s\"}".formatted(pending), null).andExpect(status().isAccepted());
		assertThat(resetMails(pending)).isZero();

		String email = activeAccount();
		for (int i = 0; i < 3; i++) {
			send("/api/v1/auth/forgot-password", "{\"email\":\"%s\"}".formatted(email), null).andExpect(status().isAccepted());
		}
		send("/api/v1/auth/forgot-password", "{\"email\":\"%s\"}".formatted(email), null)
				.andExpect(status().isTooManyRequests()).andExpect(jsonPath("$.code").value("RATE_LIMITED"));
		// Mỗi lần gửi lại vô hiệu mã trước: chỉ còn 1 mã chưa dùng
		assertThat(jdbc.queryForObject("""
				SELECT COUNT(*) FROM ma_xac_thuc m JOIN nguoi_dung u ON u.id = m.nguoi_dung_id
				WHERE u.email_chuan_hoa = ? AND m.muc_dich = 'RESET_PASSWORD' AND m.thoi_diem_da_su_dung IS NULL""",
				Integer.class, email)).isEqualTo(1);
	}

	private String register() throws Exception {
		String email = "tk05-" + UUID.randomUUID() + "@test.local";
		send("/api/v1/auth/register", """
				{"email":"%s","password":"%s","displayName":"Học Năm"}""".formatted(email, OLD), null)
				.andExpect(status().isAccepted());
		return email;
	}

	private String activeAccount() throws Exception {
		String email = register();
		jdbc.update("UPDATE nguoi_dung SET trang_thai = 'ACTIVE' WHERE email_chuan_hoa = ?", email);
		return email;
	}

	private Cookie sessionOf(String email, String password) throws Exception {
		return login(email, password).andExpect(status().isOk()).andReturn().getResponse().getCookie("VOCAB_SESSION");
	}

	private String latestToken(String email) {
		String link = jdbc.queryForObject("""
				SELECT JSON_UNQUOTE(du_lieu->'$.model.link') FROM tac_vu_nen
				WHERE JSON_UNQUOTE(du_lieu->'$.template') = 'reset-password' AND JSON_UNQUOTE(du_lieu->'$.to') = ?
				ORDER BY id DESC LIMIT 1""", String.class, email);
		assertThat(link).contains("/dat-lai-mat-khau?token=");
		return link.substring(link.indexOf("token=") + 6);
	}

	private int resetMails(String email) {
		return jdbc.queryForObject("""
				SELECT COUNT(*) FROM tac_vu_nen WHERE JSON_UNQUOTE(du_lieu->'$.template') = 'reset-password'
				AND LOWER(JSON_UNQUOTE(du_lieu->'$.to')) = ?""", Integer.class, email);
	}

	private ResultActions login(String email, String password) throws Exception {
		return send("/api/v1/auth/login", "{\"email\":\"%s\",\"password\":\"%s\"}".formatted(email, password), null);
	}

	private ResultActions reset(String token, String password) throws Exception {
		return send("/api/v1/auth/reset-password", "{\"token\":\"%s\",\"newPassword\":\"%s\"}".formatted(token, password), null);
	}

	private ResultActions send(String path, String body, Cookie session) throws Exception {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		String ip = "10." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(1, 255);
		var req = MockMvcRequestBuilders.post(path)
				.cookie(new Cookie("XSRF-TOKEN", CSRF)).header("X-CSRF-TOKEN", CSRF)
				.with(x -> { x.setRemoteAddr(ip); return x; })
				.contentType(MediaType.APPLICATION_JSON).content(body);
		if (session != null) {
			req.cookie(new Cookie("XSRF-TOKEN", CSRF), session);
		}
		return mvc.perform(req);
	}
}
