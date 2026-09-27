package com.team.vocab_learn.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.jayway.jsonpath.JsonPath;
import com.team.vocab_learn.AbstractIntegrationTest;

import jakarta.servlet.http.Cookie;

/** B0.6: 401/403 as ProblemDetail, CSRF double-submit, admin role. */
class SecurityBaselineTest extends AbstractIntegrationTest {

	@Test
	void noSession_returns401Problem() throws Exception {
		mvc.perform(get("/api/v1/me"))
				.andExpect(status().isUnauthorized())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
				.andExpect(jsonPath("$.code").value("UNAUTHENTICATED"))
				.andExpect(jsonPath("$.type").value("urn:vocablearning:error:unauthenticated"))
				.andExpect(jsonPath("$.traceId").isNotEmpty())
				.andExpect(jsonPath("$.retryable").value(false))
				.andExpect(header().exists("X-Trace-Id"));
	}

	@Test
	void postWithoutCsrf_returns403() throws Exception {
		mvc.perform(post("/api/v1/decks").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isForbidden())
				.andExpect(jsonPath("$.code").value("CSRF_INVALID"));
	}

	@Test
	void csrfEndpoint_tokenPassesCsrfFilter() throws Exception {
		var res = mvc.perform(get("/api/v1/auth/csrf"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.headerName").value("X-CSRF-TOKEN"))
				.andReturn().getResponse();
		String token = JsonPath.read(res.getContentAsString(), "$.token");
		assertThat(res.getCookie("XSRF-TOKEN").getValue()).isEqualTo(token);

		// CSRF ok → next check is authentication → 401, not 403.
		mvc.perform(post("/api/v1/decks").cookie(new Cookie("XSRF-TOKEN", token)).header("X-CSRF-TOKEN", token)
				.contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void learnerOnAdminApi_returns403() throws Exception {
		mvc.perform(get("/api/v1/admin/users").with(user("a@b.c").roles("LEARNER")))
				.andExpect(status().isForbidden())
				.andExpect(jsonPath("$.code").value("FORBIDDEN"));
	}

	@Test
	void unknownRoute_returns404Problem() throws Exception {
		mvc.perform(get("/api/v1/khong-ton-tai").with(user("a@b.c").roles("LEARNER")))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code").value("NOT_FOUND"));
	}
}
