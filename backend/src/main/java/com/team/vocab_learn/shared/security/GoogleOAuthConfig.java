package com.team.vocab_learn.shared.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

/** TK-03: bật đăng nhập Google chỉ khi có GOOGLE_CLIENT_ID; để trống thì API vẫn chạy, chỉ thiếu nút Google. */
@Configuration
@ConditionalOnExpression("!'${app.google.client-id:}'.isBlank()")
public class GoogleOAuthConfig {

	@Bean
	ClientRegistrationRepository clientRegistrationRepository(
			@Value("${app.google.client-id}") String clientId, @Value("${app.google.client-secret:}") String clientSecret) {
		// Scope openid → Spring gửi và kiểm tra nonce; state luôn được kiểm tra ở callback
		return new InMemoryClientRegistrationRepository(CommonOAuth2Provider.GOOGLE.getBuilder("google")
				.clientId(clientId).clientSecret(clientSecret).build());
	}
}
