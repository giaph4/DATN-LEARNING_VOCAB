package com.team.vocab_learn.identity.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Body POST /auth/forgot-password (TK-05). */
public record ForgotPasswordRequest(
		@NotBlank(message = "Vui lòng nhập email.")
		@Email(message = "Email không đúng định dạng.")
		@Size(max = 254, message = "Email tối đa 254 ký tự.")
		String email) {

	public ForgotPasswordRequest {
		email = email == null ? null : email.strip();
	}
}
