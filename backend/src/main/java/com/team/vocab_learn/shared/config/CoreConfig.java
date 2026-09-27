package com.team.vocab_learn.shared.config;

import java.time.Clock;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Configuration
public class CoreConfig {

	/** Single time source; tests replace it with {@code Clock.fixed(...)}. */
	@Bean
	Clock clock() {
		return Clock.systemUTC();
	}

	/** IDs are {@code Long} → JSON strings (JS loses precision above 2^53). Primitive long/int counts stay numbers. */
	@Bean
	JsonMapperBuilderCustomizer longAsString() {
		SimpleModule module = new SimpleModule("long-as-string");
		module.addSerializer(Long.class, ToStringSerializer.instance);
		return builder -> builder.addModule(module);
	}
}
