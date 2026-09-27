package com.team.vocab_learn.identity.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Body POST /auth/register (TK-01). */
public record RegisterRequest(
		@NotBlank(message = "Vui lòng nhập email.")
		@Email(message = "Email không đúng định dạng.")
		@Size(max = 254, message = "Email tối đa 254 ký tự.")
		String email,

		@NotBlank(message = "Vui lòng nhập mật khẩu.")
		@Size(min = 10, max = 128, message = "Mật khẩu phải từ 10 đến 128 ký tự.")
		String password,

		@NotBlank(message = "Vui lòng nhập tên hiển thị.")
		@Size(min = 2, max = 100, message = "Tên hiển thị phải từ 2 đến 100 ký tự.")
		String displayName) {

	// Cắt khoảng trắng trước khi Bean Validation chạy; không trim mật khẩu
	public RegisterRequest {
		email = email == null ? null : email.strip();
		displayName = displayName == null ? null : displayName.strip();
	}

	// Không để mật khẩu lọt vào log
	@Override
	public String toString() {
		return "RegisterRequest[email=" + email + ", displayName=" + displayName + "]";
	}
}
