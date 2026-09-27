package com.team.vocab_learn.identity.application;

import com.team.vocab_learn.identity.domain.TrangThaiNguoiDung;
import com.team.vocab_learn.shared.security.AuthUser;

/** Kết quả đăng nhập: principal lưu vào phiên + thông tin trả cho FE. */
public record SignedInUser(AuthUser authUser, String displayName, TrangThaiNguoiDung status) {
}
