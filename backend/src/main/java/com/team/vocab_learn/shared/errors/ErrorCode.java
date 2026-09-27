package com.team.vocab_learn.shared.errors;

import java.util.Locale;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Stable error codes returned in ProblemDetail {@code code} (THIET_KE §11.1). */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	VALIDATION_FAILED(400, "Dữ liệu không hợp lệ", false),
	UNAUTHENTICATED(401, "Chưa đăng nhập", false),
	TOKEN_INVALID(400, "Mã xác thực không hợp lệ", false),
	TOKEN_EXPIRED(400, "Mã xác thực đã hết hạn", false),
	INVALID_CREDENTIALS(401, "Sai thông tin đăng nhập", false),
	FORBIDDEN(403, "Không có quyền", false),
	EMAIL_NOT_VERIFIED(403, "Email chưa được xác thực", false),
	ACCOUNT_LOCKED(403, "Tài khoản đã bị khóa", false),
	REAUTH_REQUIRED(403, "Cần xác thực lại", false),
	CSRF_INVALID(403, "Thiếu hoặc sai mã chống giả mạo", false),
	NOT_FOUND(404, "Không tìm thấy", false),
	METHOD_NOT_ALLOWED(405, "Phương thức không được hỗ trợ", false),
	CONFLICT(409, "Xung đột dữ liệu", false),
	GOOGLE_EMAIL_IN_USE(409, "Email đã thuộc tài khoản khác", false),
	GOOGLE_ACCOUNT_IN_USE(409, "Tài khoản Google đã liên kết với người dùng khác", false),
	IDEMPOTENCY_KEY_REUSED(409, "Khóa chống gửi trùng đã dùng cho nội dung khác", false),
	REQUEST_IN_PROGRESS(409, "Yêu cầu đang được xử lý", true),
	STALE_VERSION(412, "Dữ liệu đã thay đổi", false),
	UNSUPPORTED_MEDIA_TYPE(415, "Định dạng không được hỗ trợ", false),
	BUSINESS_RULE_VIOLATED(422, "Không thể thực hiện", false),
	LAST_LOGIN_METHOD(422, "Không thể gỡ phương thức đăng nhập cuối cùng", false),
	RATE_LIMITED(429, "Thao tác quá nhanh", true),
	QUOTA_EXCEEDED(429, "Đã hết hạn mức", true),
	INTERNAL_ERROR(500, "Lỗi hệ thống", true),
	DEPENDENCY_UNAVAILABLE(503, "Dịch vụ phụ thuộc tạm thời không khả dụng", true);

	private final int status;
	private final String title;
	private final boolean retryable;

	public String type() {
		return "urn:vocablearning:error:" + name().toLowerCase(Locale.ROOT).replace('_', '-');
	}
}
