package com.team.vocab_learn.shared.security;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;

/** The only way services learn who is calling — never trust a userId sent by the client. */
@Component
public class CurrentUser {

	public Optional<AuthUser> find() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.getPrincipal() instanceof AuthUser user ? Optional.of(user) : Optional.empty();
	}

	public AuthUser require() {
		return find().orElseThrow(() -> new ApiException(ErrorCode.UNAUTHENTICATED, "Bạn cần đăng nhập để tiếp tục."));
	}

	public long id() {
		return require().id();
	}
}
