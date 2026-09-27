package com.team.vocab_learn.shared.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Forwards security-filter errors to GlobalExceptionHandler so 401/403 share the ProblemDetail format. */
@Component
public class ProblemSecurityHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

	private final HandlerExceptionResolver resolver;

	public ProblemSecurityHandler(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
		resolver.resolveException(request, response, null, e);
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
		resolver.resolveException(request, response, null, e);
	}
}
