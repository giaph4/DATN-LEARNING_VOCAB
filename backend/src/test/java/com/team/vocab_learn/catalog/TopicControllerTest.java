package com.team.vocab_learn.catalog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.team.vocab_learn.AbstractIntegrationTest;

/** Public topic tree; also proves JPA validate + MapStruct + Long-as-string JSON. */
class TopicControllerTest extends AbstractIntegrationTest {

	@Test
	void guestGetsTopicTreeWithStringIds() throws Exception {
		mvc.perform(get("/api/v1/topics"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(7))
				.andExpect(jsonPath("$[0].id").isString())
				.andExpect(jsonPath("$[?(@.slug=='du-lich')].children[0].slug").value("san-bay"));
	}
}
