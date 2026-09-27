package com.team.vocab_learn.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.team.vocab_learn.AbstractIntegrationTest;

/** B0.4 + B0.7: Flyway builds 67 tables + history, seed exists, readiness is UP. */
class PlatformTest extends AbstractIntegrationTest {

	@Test
	void flywayCreates67TablesPlusHistory() {
		Integer tables = jdbc.queryForObject(
				"SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'vocab_learning' AND table_type = 'BASE TABLE'",
				Integer.class);
		assertThat(tables).isEqualTo(68);
	}

	@Test
	void coreSeedLoaded() {
		assertThat(jdbc.queryForList("SELECT ma FROM vai_tro ORDER BY ma", String.class)).containsExactly("ADMIN", "LEARNER");
		assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM chinh_sach_ai", Integer.class)).isEqualTo(5);
	}

	@Test
	void healthProbesUp() throws Exception {
		mvc.perform(get("/actuator/health/liveness")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("UP"));
		mvc.perform(get("/actuator/health/readiness")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("UP"));
	}
}
