package com.team.vocab_learn.identity.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Body POST /auth/resend-verification (TK-02). */
public record ResendVerificationRequest(
		@NotBlank(message = "Vui lòng nhập email.")
		@Email(message = "Email không đúng định dạng.")
		@Size(max = 254, message = "Email tối đa 254 ký tự.")
		String email) {

	public ResendVerificationRequest {
		email = email == null ? null : email.strip();
	}
}
