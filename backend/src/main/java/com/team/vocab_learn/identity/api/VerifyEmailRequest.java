package com.team.vocab_learn.identity.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Body POST /auth/verify-email (TK-02); token lấy từ ?token= của liên kết trong email. */
public record VerifyEmailRequest(
		@NotBlank(message = "Thiếu mã xác thực.")
		@Size(max = 128, message = "Mã xác thực không hợp lệ.")
		String token) {

	@Override
	public String toString() {
		return "VerifyEmailRequest[token=***]";
	}
}
