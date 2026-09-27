package com.team.vocab_learn.identity.api;

import java.util.Set;

import com.team.vocab_learn.identity.application.SignedInUser;

/** Người dùng của phiên vừa tạo (TK-03). */
public record SessionUserResponse(Long id, String email, String displayName, Set<String> roles, String status) {

	static SessionUserResponse from(SignedInUser u) {
		return new SessionUserResponse(u.authUser().id(), u.authUser().email(), u.displayName(), u.authUser().roles(),
				u.status().name());
	}
}
