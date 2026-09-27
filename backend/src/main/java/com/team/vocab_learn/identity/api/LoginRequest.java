package com.team.vocab_learn.identity.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Body POST /auth/login (TK-03). Không kiểm độ dài tối thiểu mật khẩu để không lộ chính sách qua lỗi đăng nhập. */
public record LoginRequest(
		@NotBlank(message = "Vui lòng nhập email.")
		@Email(message = "Email không đúng định dạng.")
		@Size(max = 254, message = "Email tối đa 254 ký tự.")
		String email,

		@NotBlank(message = "Vui lòng nhập mật khẩu.")
		@Size(max = 128, message = "Mật khẩu tối đa 128 ký tự.")
		String password) {

	public LoginRequest {
		email = email == null ? null : email.strip();
	}

	@Override
	public String toString() {
		return "LoginRequest[email=" + email + "]";
	}
}
