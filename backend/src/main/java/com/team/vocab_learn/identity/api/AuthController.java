package com.team.vocab_learn.identity.api;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.team.vocab_learn.identity.application.EmailVerificationService;
import com.team.vocab_learn.identity.application.LoginService;
import com.team.vocab_learn.identity.application.PasswordResetService;
import com.team.vocab_learn.identity.application.RegistrationService;
import com.team.vocab_learn.shared.security.SessionAuthenticator;
import com.team.vocab_learn.shared.web.MessageResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	static final MessageResponse REGISTER_ACCEPTED =
			new MessageResponse("Nếu email hợp lệ, hãy kiểm tra hộp thư để xác thực tài khoản.");

	static final MessageResponse EMAIL_VERIFIED =
			new MessageResponse("Email đã được xác thực. Bạn có thể đăng nhập.");

	static final MessageResponse RESEND_ACCEPTED =
			new MessageResponse("Nếu tài khoản đang chờ xác thực, email mới sẽ đến trong vài phút. Liên kết cũ không còn dùng được.");

	static final MessageResponse FORGOT_ACCEPTED =
			new MessageResponse("Nếu email thuộc một tài khoản đang hoạt động, liên kết đặt lại mật khẩu sẽ đến trong vài phút.");

	static final MessageResponse PASSWORD_RESET =
			new MessageResponse("Đã đặt lại mật khẩu. Hãy đăng nhập bằng mật khẩu mới.");

	private final RegistrationService registration;
	private final EmailVerificationService verification;
	private final LoginService login;
	private final SessionAuthenticator sessions;
	private final PasswordResetService passwordReset;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public MessageResponse register(@Valid @RequestBody RegisterRequest req, HttpServletRequest http) {
		try {
			registration.register(req, http.getRemoteAddr());
		} catch (DataIntegrityViolationException e) {
			// Hai request cùng email chạy song song: bên thua trả lời giống hệt để không lộ email
			if (!String.valueOf(e.getMostSpecificCause().getMessage()).contains("uk_nguoi_dung_email")) {
				throw e;
			}
		}
		return REGISTER_ACCEPTED;
	}

	@PostMapping("/verify-email")
	public MessageResponse verifyEmail(@Valid @RequestBody VerifyEmailRequest req) {
		verification.verify(req.token());
		return EMAIL_VERIFIED;
	}

	@PostMapping("/resend-verification")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public MessageResponse resendVerification(@Valid @RequestBody ResendVerificationRequest req) {
		verification.resend(req.email());
		return RESEND_ACCEPTED;
	}

	@PostMapping("/login")
	public SessionUserResponse login(@Valid @RequestBody LoginRequest req, HttpServletRequest http,
			HttpServletResponse res) {
		var user = login.login(req, http.getRemoteAddr());
		sessions.signIn(user.authUser(), http, res);
		return SessionUserResponse.from(user);
	}

	// TK-03: gọi khi chưa đăng nhập hoặc gọi lại vẫn 204
	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(HttpServletRequest http) {
		sessions.signOut(http);
	}

	@PostMapping("/forgot-password")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public MessageResponse forgotPassword(@Valid @RequestBody ForgotPasswordRequest req, HttpServletRequest http) {
		passwordReset.requestReset(req.email(), http.getRemoteAddr());
		return FORGOT_ACCEPTED;
	}

	@PostMapping("/reset-password")
	public MessageResponse resetPassword(@Valid @RequestBody ResetPasswordRequest req) {
		passwordReset.reset(req.token(), req.newPassword());
		return PASSWORD_RESET;
	}
}
