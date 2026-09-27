package com.team.vocab_learn.identity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

import jakarta.servlet.http.Cookie;

import com.team.vocab_learn.AbstractIntegrationTest;

/** TC-TK-01: đăng ký tài khoản bằng email. */
class TK01RegisterTest extends AbstractIntegrationTest {

	private static final String CSRF = "tk01-csrf-token";

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void tc01_happyPath_createsPendingUserProfilePrefsRoleCodeAndMailJob() throws Exception {
		String email = "new-" + UUID.randomUUID() + "@test.local";
		register(randomIp(), email, "matkhau-dai-1", "Lan Anh")
				.andExpect(status().isAccepted())
				.andExpect(jsonPath("$.message").isString());

		var user = jdbc.queryForMap("SELECT id, trang_thai, password_hash FROM nguoi_dung WHERE email_chuan_hoa = ?", email);
		long id = ((Number) user.get("id")).longValue();
		assertThat(user.get("trang_thai")).isEqualTo("PENDING_VERIFICATION");
		assertThat(passwordEncoder.matches("matkhau-dai-1", (String) user.get("password_hash"))).isTrue();

		assertThat(jdbc.queryForObject("SELECT ten_hien_thi FROM ho_so_nguoi_dung WHERE nguoi_dung_id = ?", String.class, id))
				.isEqualTo("Lan Anh");
		assertThat(jdbc.queryForObject("SELECT so_the_moi_moi_ngay FROM tuy_chon_hoc WHERE nguoi_dung_id = ?", Integer.class, id))
				.isEqualTo(10);
		assertThat(jdbc.queryForObject("""
				SELECT v.ma FROM nguoi_dung_vai_tro nv JOIN vai_tro v ON v.id = nv.vai_tro_id
				WHERE nv.nguoi_dung_id = ?""", String.class, id)).isEqualTo("LEARNER");
		assertThat(jdbc.queryForObject(
				"SELECT COUNT(*) FROM ma_xac_thuc WHERE nguoi_dung_id = ? AND muc_dich = 'VERIFY_EMAIL'", Integer.class, id))
				.isEqualTo(1);
		assertThat(jdbc.queryForObject(
				"SELECT COUNT(*) FROM tac_vu_nen WHERE loai_tac_vu = 'SEND_EMAIL' AND JSON_UNQUOTE(du_lieu->'$.to') = ?",
				Integer.class, email)).isEqualTo(1);
	}

	@Test
	void tc02_invalidInput400_duplicateEmailStill202WithoutSecondUser() throws Exception {
		register(randomIp(), "khong-phai-email", "ngan", " ")
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
				.andExpect(jsonPath("$.fieldErrors[?(@.field=='email')]").exists())
				.andExpect(jsonPath("$.fieldErrors[?(@.field=='password')]").exists())
				.andExpect(jsonPath("$.fieldErrors[?(@.field=='displayName')]").exists());

		String email = "dup-" + UUID.randomUUID() + "@test.local";
		register(randomIp(), email, "matkhau-dai-1", "Minh").andExpect(status().isAccepted());
		register(randomIp(), "  " + email.toUpperCase() + " ", "matkhau-khac-2", "Minh 2").andExpect(status().isAccepted());

		assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM nguoi_dung WHERE email_chuan_hoa = ?", Integer.class, email))
				.isEqualTo(1);
	}

	@Test
	void tc03_concurrentSameEmailCreatesOneUser_andSixthRequestFromIpIs429() throws Exception {
		String email = "race-" + UUID.randomUUID() + "@test.local";
		List<CompletableFuture<Integer>> calls = List.of(1, 2, 3).stream()
				.map(i -> CompletableFuture.supplyAsync(() -> statusOf(randomIp(), email)))
				.toList();
		calls.forEach(c -> assertThat(c.join()).isEqualTo(202));
		assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM nguoi_dung WHERE email_chuan_hoa = ?", Integer.class, email))
				.isEqualTo(1);

		String ip = randomIp();
		for (int i = 0; i < 5; i++) {
			register(ip, "rl-" + UUID.randomUUID() + "@test.local", "matkhau-dai-1", "Hoa").andExpect(status().isAccepted());
		}
		register(ip, "rl-" + UUID.randomUUID() + "@test.local", "matkhau-dai-1", "Hoa")
				.andExpect(status().isTooManyRequests())
				.andExpect(jsonPath("$.code").value("RATE_LIMITED"));
	}

	private ResultActions register(String ip, String email, String password, String displayName) throws Exception {
		String body = """
				{"email":"%s","password":"%s","displayName":"%s"}""".formatted(email, password, displayName);
		// Cookie + header CSRF như trình duyệt; không dùng with(csrf()) vì nó thay CsrfTokenRepository của cả context test
		return mvc.perform(post("/api/v1/auth/register")
				.cookie(new Cookie("XSRF-TOKEN", CSRF)).header("X-CSRF-TOKEN", CSRF)
				.with(r -> { r.setRemoteAddr(ip); return r; })
				.contentType(MediaType.APPLICATION_JSON).content(body));
	}

	private int statusOf(String ip, String email) {
		try {
			return register(ip, email, "matkhau-dai-1", "Race").andReturn().getResponse().getStatus();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	// Mỗi test một IP riêng để không dính giới hạn 5 lần/giờ của test khác
	private static String randomIp() {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		return "10." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(1, 255);
	}
}
