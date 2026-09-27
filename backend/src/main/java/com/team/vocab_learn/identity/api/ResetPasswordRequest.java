package com.team.vocab_learn.identity.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Body POST /auth/reset-password (TK-05). "Nhập lại mật khẩu" chỉ kiểm ở FE. */
public record ResetPasswordRequest(
		@NotBlank(message = "Thiếu mã đặt lại mật khẩu.")
		@Size(max = 128, message = "Mã đặt lại mật khẩu không hợp lệ.")
		String token,

		@NotBlank(message = "Vui lòng nhập mật khẩu mới.")
		@Size(min = 10, max = 128, message = "Mật khẩu phải từ 10 đến 128 ký tự.")
		String newPassword) {

	@Override
	public String toString() {
		return "ResetPasswordRequest[token=***, newPassword=***]";
	}
}
