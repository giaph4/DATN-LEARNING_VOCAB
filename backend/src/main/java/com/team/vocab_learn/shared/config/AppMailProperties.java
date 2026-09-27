package com.team.vocab_learn.shared.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/** Sender identity for outgoing mail (SMTP itself is {@code spring.mail.*}). */
@ConfigurationProperties("app.mail")
public record AppMailProperties(
		@DefaultValue("no-reply@vocablearning.local") String from,
		@DefaultValue("VocabLearning") String fromName) {
}
