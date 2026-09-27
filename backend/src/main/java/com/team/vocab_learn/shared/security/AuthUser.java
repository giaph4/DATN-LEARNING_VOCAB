package com.team.vocab_learn.shared.security;

import java.io.Serializable;
import java.util.Set;

/** Principal stored in the session (serialized to Redis). Built at login in the identity module. */
public record AuthUser(Long id, String email, Set<String> roles, long authVersion) implements Serializable {

	public boolean isAdmin() {
		return roles.contains("ADMIN");
	}
}
