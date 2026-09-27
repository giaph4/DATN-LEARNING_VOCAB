package com.team.vocab_learn.shared.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/** General app settings (prefix {@code app}). */
@ConfigurationProperties("app")
public record AppProperties(
		@DefaultValue("http://localhost:3000") String publicBaseUrl,
		@DefaultValue("false") boolean cookieSecure,
		@DefaultValue("http://localhost:3000") List<String> corsAllowedOrigins) {
}
