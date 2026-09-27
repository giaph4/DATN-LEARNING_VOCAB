package com.team.vocab_learn.shared.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** GET /api/v1/auth/csrf — sets cookie XSRF-TOKEN and returns the value to put in header X-CSRF-TOKEN. */
@RestController
public class CsrfController {

	public record CsrfResponse(String headerName, String token) {
	}

	@GetMapping("/api/v1/auth/csrf")
	public CsrfResponse csrf(CsrfToken token) {
		return new CsrfResponse(token.getHeaderName(), token.getToken());
	}
}
