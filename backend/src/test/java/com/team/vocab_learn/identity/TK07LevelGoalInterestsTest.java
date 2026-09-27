package com.team.vocab_learn.identity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.JsonPath;
import com.team.vocab_learn.AbstractIntegrationTest;

import jakarta.servlet.http.Cookie;

/** B1.7 (TK-07): trình độ tự đánh giá, mục tiêu học, chủ đề quan tâm. */
class TK07LevelGoalInterestsTest extends AbstractIntegrationTest {

	private static final String CSRF = "b17-csrf-token";
	private static final String PASSWORD = "DemoHoc!2026";

	@Test
	void tc01_saveLevelGoalAndInterests() throws Exception {
		Cookie a = login(activeAccount());
		String body = call(HttpMethod.PATCH, "/api/v1/users/me/profile", """
				{"level":"B2","learningGoal":"  Đạt IELTS 6.5 trước tháng 6  ","completeOnboarding":true}""", a)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.level").value("B2"))
				.andExpect(jsonPath("$.learningGoal").value("Đạt IELTS 6.5 trước tháng 6"))
				.andExpect(jsonPath("$.onboardingCompletedAt").isString())
				.andReturn().getResponse().getContentAsString();
		String completedAt = JsonPath.read(body, "$.onboardingCompletedAt");
		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"completeOnboarding\":true,\"learningGoal\":\"\"}", a)
				.andExpect(jsonPath("$.onboardingCompletedAt").value(completedAt))
				.andExpect(jsonPath("$.learningGoal").doesNotExist())
				.andExpect(jsonPath("$.level").value("B2"));

		String duLich = topicId("du-lich");
		String sanBay = topicId("san-bay");
		String put = interests(duLich, sanBay, duLich);
		call(HttpMethod.PUT, "/api/v1/users/me/interests", put, a)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.topics.length()").value(2))
				.andExpect(jsonPath("$.topics[?(@.slug == 'san-bay')].id").value(sanBay));
		call(HttpMethod.PUT, "/api/v1/users/me/interests", put, a).andExpect(jsonPath("$.topics.length()").value(2));
		call(HttpMethod.GET, "/api/v1/users/me", null, a).andExpect(jsonPath("$.interests.length()").value(2));

		call(HttpMethod.PUT, "/api/v1/users/me/interests", interests(), a).andExpect(jsonPath("$.topics.length()").value(0));
		call(HttpMethod.GET, "/api/v1/users/me", null, a).andExpect(jsonPath("$.interests.length()").value(0));
	}

	@Test
	void tc02_invalidLevelLongGoalHiddenTopics() throws Exception {
		Cookie a = login(activeAccount());
		for (String level : List.of("Z9", "b2", "")) {
			call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"level\":\"%s\"}".formatted(level), a)
					.andExpect(status().isBadRequest()).andExpect(jsonPath("$.fieldErrors[0].field").value("level"));
		}
		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"learningGoal\":\"%s\"}".formatted("x".repeat(501)), a)
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.fieldErrors[0].field").value("learningGoal"));
		call(HttpMethod.GET, "/api/v1/users/me", null, a).andExpect(jsonPath("$.level").value("A1"));

		String duLich = topicId("du-lich");
		call(HttpMethod.PUT, "/api/v1/users/me/interests", interests(duLich), a).andExpect(status().isOk());

		String hidden = newTopic(null, false);
		String childOfHidden = newTopic(hidden, true);
		for (String bad : List.of(hidden, childOfHidden, "999999999")) {
			call(HttpMethod.PUT, "/api/v1/users/me/interests", interests(duLich, bad), a)
					.andExpect(status().isUnprocessableEntity())
					.andExpect(jsonPath("$.fieldErrors[0].field").value("topicIds"));
		}
		call(HttpMethod.PUT, "/api/v1/users/me/interests", interests("abc"), a).andExpect(status().isBadRequest());
		call(HttpMethod.PUT, "/api/v1/users/me/interests", "{}", a).andExpect(status().isBadRequest());
		// Lỗi không làm mất lựa chọn cũ
		call(HttpMethod.GET, "/api/v1/users/me", null, a).andExpect(jsonPath("$.interests[0].id").value(duLich));

		// Chủ đề bị ẩn sau khi đã chọn: không còn hiện ra
		String later = newTopic(null, true);
		call(HttpMethod.PUT, "/api/v1/users/me/interests", interests(duLich, later), a).andExpect(status().isOk());
		jdbc.update("UPDATE chu_de SET dang_su_dung = FALSE WHERE id = ?", Long.valueOf(later));
		call(HttpMethod.GET, "/api/v1/users/me", null, a)
				.andExpect(jsonPath("$.interests.length()").value(1)).andExpect(jsonPath("$.interests[0].id").value(duLich));
	}

	@Test
	void tc03_concurrentReplaceAndAnonymous() throws Exception {
		Cookie a = login(activeAccount());
		String duLich = topicId("du-lich");
		String sanBay = topicId("san-bay");
		String lapTrinh = topicId("lap-trinh");
		var first = CompletableFuture.supplyAsync(() -> putStatus(interests(duLich, sanBay), a));
		var second = CompletableFuture.supplyAsync(() -> putStatus(interests(lapTrinh), a));
		assertThat(List.of(first.get(), second.get())).containsOnly(200);
		long userId = jdbc.queryForObject("SELECT h.nguoi_dung_id FROM ho_so_nguoi_dung h JOIN nguoi_dung n ON n.id = h.nguoi_dung_id "
				+ "WHERE n.email_chuan_hoa = ?", Long.class, emailOf(a));
		List<Long> saved = jdbc.queryForList("SELECT chu_de_id FROM chu_de_quan_tam WHERE nguoi_dung_id = ? ORDER BY chu_de_id",
				Long.class, userId);
		// Một trong hai lần ghi thắng trọn vẹn, không trộn lẫn
		List<Long> pair = java.util.stream.Stream.of(duLich, sanBay).map(Long::valueOf).sorted().toList();
		assertThat(saved).isIn(pair, List.of(Long.valueOf(lapTrinh)));

		call(HttpMethod.PUT, "/api/v1/users/me/interests", interests(duLich), null).andExpect(status().isUnauthorized());
		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"level\":\"C1\"}", null).andExpect(status().isUnauthorized());
	}

	private final java.util.Map<String, String> emails = new java.util.concurrent.ConcurrentHashMap<>();

	private String emailOf(Cookie session) {
		return emails.get(session.getValue());
	}

	private int putStatus(String body, Cookie session) {
		try {
			return call(HttpMethod.PUT, "/api/v1/users/me/interests", body, session).andReturn().getResponse().getStatus();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private String topicId(String slug) {
		return String.valueOf(jdbc.queryForObject("SELECT id FROM chu_de WHERE slug = ?", Long.class, slug));
	}

	private String newTopic(String parentId, boolean active) {
		String slug = "b17-" + UUID.randomUUID();
		jdbc.update("INSERT INTO chu_de (cha_id, slug, ten, dang_su_dung) VALUES (?, ?, ?, ?)",
				parentId == null ? null : Long.valueOf(parentId), slug, "Chủ đề thử " + slug, active);
		return topicId(slug);
	}

	private static String interests(String... ids) {
		return "{\"topicIds\":[" + String.join(",", java.util.Arrays.stream(ids).map(i -> "\"" + i + "\"").toList()) + "]}";
	}

	private String activeAccount() throws Exception {
		String email = "b17-" + UUID.randomUUID() + "@test.local";
		call(HttpMethod.POST, "/api/v1/auth/register", """
				{"email":"%s","password":"%s","displayName":"Học Thiết Lập"}""".formatted(email, PASSWORD), null)
				.andExpect(status().isAccepted());
		jdbc.update("UPDATE nguoi_dung SET trang_thai = 'ACTIVE', thoi_diem_xac_thuc_email = NOW(3) WHERE email_chuan_hoa = ?",
				email);
		return email;
	}

	private Cookie login(String email) throws Exception {
		Cookie session = call(HttpMethod.POST, "/api/v1/auth/login",
				"{\"email\":\"%s\",\"password\":\"%s\"}".formatted(email, PASSWORD), null)
				.andExpect(status().isOk()).andReturn().getResponse().getCookie("VOCAB_SESSION");
		emails.put(session.getValue(), email);
		return session;
	}

	private ResultActions call(HttpMethod method, String path, String body, Cookie session) throws Exception {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		String ip = "10." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(1, 255);
		var req = MockMvcRequestBuilders.request(method, path)
				.header("X-CSRF-TOKEN", CSRF)
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
