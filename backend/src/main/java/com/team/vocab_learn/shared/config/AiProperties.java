package com.team.vocab_learn.shared.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/** LLM provider settings; {@code fake=true} uses the offline fake client (dev/test). */
@ConfigurationProperties("app.ai")
public record AiProperties(
		@DefaultValue("true") boolean fake,
		@DefaultValue("https://api.openai.com/v1") String baseUrl,
		String apiKey,
		@DefaultValue("gpt-4.1-mini") String model,
		@DefaultValue("PT30S") Duration timeout) {
}
