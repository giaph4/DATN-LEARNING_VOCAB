package com.team.vocab_learn.shared.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/** Azure Speech settings; {@code fake=true} uses the offline fake client. */
@ConfigurationProperties("app.speech")
public record SpeechProperties(
		@DefaultValue("true") boolean fake,
		String key,
		@DefaultValue("southeastasia") String region) {
}
