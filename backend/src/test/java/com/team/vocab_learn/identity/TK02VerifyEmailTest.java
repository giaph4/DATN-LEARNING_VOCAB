package com.team.vocab_learn.identity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.servlet.http.Cookie;

import com.team.vocab_learn.AbstractIntegrationTest;
import com.team.vocab_learn.identity.application.OneTimeCodeService;

/** TC-TK-02: xác thực email và gửi lại mã. */
class TK02VerifyEmailTest extends AbstractIntegrationTest {

	private static final String CSRF = "tk02-csrf-token";

	@Test
	void tc01_happyPath_activatesAccount_storesOnlyHash_secondClickStillOk() throws Exception {
		String email = registerNew();
		String token = latestToken(email);

		assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM ma_xac_thuc WHERE ma_xac_thuc_bam = ?", Integer.class,
				(Object) OneTimeCodeService.sha256(token))).isEqualTo(1);

		verify(token).andExpect(status().isOk()).andExpect(jsonPath("$.message").isString());
		var user = jdbc.queryForMap("SELECT id, trang_thai, thoi_diem_xac_thuc_email FROM nguoi_dung WHERE email_chuan_hoa = ?", email);
		assertThat(user.get("trang_thai")).isEqualTo("ACTIVE");
		assertThat(user.get("thoi_diem_xac_thuc_email")).isNotNull();
		assertThat(jdbc.queryForObject("""
				SELECT COUNT(*) FROM ma_xac_thuc WHERE nguoi_dung_id = ? AND thoi_diem_da_su_dung IS NULL""",
				Integer.class, user.get("id"))).isZero();

		verify(token).andExpect(status().isOk());
	}

	@Test
	void tc02_invalidExpiredAndReplacedTokensDoNotActivate() throws Exception {
		verify("khong-ton-tai").andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("TOKEN_INVALID"));
		verify(" ").andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("VALIDATION_FAILED"));

		String email = registerNew();
		String expired = latestToken(email);
		jdbc.update("UPDATE ma_xac_thuc SET thoi_diem_het_han = NOW(3) - INTERVAL 1 SECOND WHERE ma_xac_thuc_bam = ?",
				(Object) OneTimeCodeService.sha256(expired));
		verify(expired).andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("TOKEN_EXPIRED"));
		assertThat(statusOf(email)).isEqualTo("PENDING_VERIFICATION");

		resend(email.toUpperCase()).andExpect(status().isAccepted());
		String fresh = latestToken(email);
		assertThat(fresh).isNotEqualTo(expired);
		verify(expired).andExpect(jsonPath("$.code").value("TOKEN_INVALID"));
		assertThat(statusOf(email)).isEqualTo("PENDING_VERIFICATION");

		verify(fresh).andExpect(status().isOk());
		assertThat(statusOf(email)).isEqualTo("ACTIVE");
	}

	@Test
	void tc03_resendIsUniformAndLimited_concurrentVerifyActivatesOnce() throws Exception {
		String unknown = "khong-co-" + UUID.randomUUID() + "@test.local";
		resend(unknown).andExpect(status().isAccepted());
		assertThat(mailJobs(unknown)).isZero();

		String email = registerNew();
		String token = latestToken(email);
		List<CompletableFuture<Integer>> calls = List.of(1, 2, 3).stream()
				.map(i -> CompletableFuture.supplyAsync(() -> verifyStatus(token)))
				.toList();
		calls.forEach(c -> assertThat(c.join()).isEqualTo(200));
		assertThat(statusOf(email)).isEqualTo("ACTIVE");

		// Đã ACTIVE: vẫn 202 nhưng không gửi thêm email
		resend(email).andExpect(status().isAccepted());
		resend(email).andExpect(status().isAccepted());
		resend(email).andExpect(status().isAccepted());
		assertThat(mailJobs(email)).isEqualTo(1);
		resend(email).andExpect(status().isTooManyRequests()).andExpect(jsonPath("$.code").value("RATE_LIMITED"));
	}

	private String registerNew() throws Exception {
		String email = "tk02-" + UUID.randomUUID() + "@test.local";
		send("/api/v1/auth/register", """
				{"email":"%s","password":"matkhau-dai-1","displayName":"Học A"}""".formatted(email))
				.andExpect(status().isAccepted());
		return email;
	}

	private String latestToken(String email) {
		String link = jdbc.queryForObject("""
				SELECT JSON_UNQUOTE(du_lieu->'$.model.link') FROM tac_vu_nen
				WHERE loai_tac_vu = 'SEND_EMAIL' AND JSON_UNQUOTE(du_lieu->'$.to') = ? ORDER BY id DESC LIMIT 1""",
				String.class, email);
		return link.substring(link.indexOf("token=") + 6);
	}

	private String statusOf(String email) {
		return jdbc.queryForObject("SELECT trang_thai FROM nguoi_dung WHERE email_chuan_hoa = ?", String.class, email);
	}

	private int mailJobs(String email) {
		return jdbc.queryForObject("""
				SELECT COUNT(*) FROM tac_vu_nen WHERE loai_tac_vu = 'SEND_EMAIL' AND LOWER(JSON_UNQUOTE(du_lieu->'$.to')) = ?""",
				Integer.class, email);
	}

	private ResultActions verify(String token) throws Exception {
		return send("/api/v1/auth/verify-email", "{\"token\":\"%s\"}".formatted(token));
	}

	private int verifyStatus(String token) {
		try {
			return verify(token).andReturn().getResponse().getStatus();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private ResultActions resend(String email) throws Exception {
		return send("/api/v1/auth/resend-verification", "{\"email\":\"%s\"}".formatted(email));
	}

	private ResultActions send(String path, String body) throws Exception {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		String ip = "10." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(1, 255);
		return mvc.perform(MockMvcRequestBuilders.post(path)
				.cookie(new Cookie("XSRF-TOKEN", CSRF)).header("X-CSRF-TOKEN", CSRF)
				.with(req -> { req.setRemoteAddr(ip); return req; })
				.contentType(MediaType.APPLICATION_JSON).content(body));
	}
}
