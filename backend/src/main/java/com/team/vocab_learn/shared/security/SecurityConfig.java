package com.team.vocab_learn.shared.security;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.team.vocab_learn.shared.config.AppProperties;
import com.team.vocab_learn.shared.web.TraceIdFilter;

/**
 * Session cookie (stored in Redis) + CSRF double-submit: cookie XSRF-TOKEN, header X-CSRF-TOKEN.
 * 401/403 are written as ProblemDetail by {@link ProblemSecurityHandler}.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	public static final String CSRF_HEADER = "X-CSRF-TOKEN";

	@Bean
	SecurityFilterChain apiSecurity(HttpSecurity http, ProblemSecurityHandler problems, AppProperties app,
			ObjectProvider<ClientRegistrationRepository> oauthClients,
			ObjectProvider<AuthenticationSuccessHandler> oauthSuccess,
			ObjectProvider<AuthenticationFailureHandler> oauthFailure) throws Exception {
		CookieCsrfTokenRepository csrfRepo = CookieCsrfTokenRepository.withHttpOnlyFalse();
		csrfRepo.setHeaderName(CSRF_HEADER);
		csrfRepo.setCookieCustomizer(c -> c.sameSite("Lax").secure(app.cookieSecure()).path("/"));

		http
			.cors(c -> {})
			// Plain handler (no XOR masking) so the SPA can echo the cookie value in the header.
			.csrf(c -> c.csrfTokenRepository(csrfRepo).csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
			.sessionManagement(s -> s.sessionFixation(f -> f.changeSessionId()))
			.authorizeHttpRequests(a -> a
				.requestMatchers("/api/v1/auth/sessions/**", "/api/v1/auth/identities/**", "/api/v1/auth/reauthenticate").authenticated()
				.requestMatchers("/api/v1/auth/**", "/api/v1/public/**", "/oauth2/**", "/login/oauth2/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/topics", "/api/v1/topics/**").permitAll()
				.requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
				.requestMatchers("/actuator/health/**", "/actuator/info", "/error").permitAll()
				.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
				.anyRequest().authenticated())
			.exceptionHandling(e -> e.authenticationEntryPoint(problems).accessDeniedHandler(problems))
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.logout(AbstractHttpConfigurer::disable)
			.requestCache(AbstractHttpConfigurer::disable);
		// Google: /oauth2/authorization/google → Google → /login/oauth2/code/google (handler ở module identity)
		if (oauthClients.getIfAvailable() != null) {
			http.oauth2Login(o -> o.successHandler(oauthSuccess.getObject()).failureHandler(oauthFailure.getObject()));
		}
		return http.build();
	}

	/**
	 * Cookie phiên của Spring Session. Khai báo tường minh vì server.servlet.session.cookie.* không áp vào
	 * Spring Session (cookie ra "SESSION", thiếu HttpOnly/SameSite).
	 */
	@Bean
	CookieSerializer cookieSerializer(AppProperties app) {
		DefaultCookieSerializer cookie = new DefaultCookieSerializer();
		cookie.setCookieName("VOCAB_SESSION");
		cookie.setCookiePath("/");
		cookie.setUseHttpOnlyCookie(true);
		cookie.setSameSite("Lax");
		cookie.setUseSecureCookie(app.cookieSecure());
		return cookie;
	}

	/** Cost 11 ≈ 100–300 ms per hash on a dev laptop. */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	/** Only needed when the browser calls the API cross-origin; Next.js rewrites keep it same-origin. */
	@Bean
	CorsConfigurationSource corsConfigurationSource(AppProperties app) {
		CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowedOrigins(app.corsAllowedOrigins());
		cors.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		cors.setAllowedHeaders(List.of("*"));
		cors.setExposedHeaders(List.of("ETag", "Location", TraceIdFilter.HEADER));
		cors.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/**", cors);
		return source;
	}
}
