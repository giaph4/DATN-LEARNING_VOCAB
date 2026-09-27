package com.team.vocab_learn.identity.application;

import java.time.Clock;
import java.time.Duration;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.vocab_learn.identity.application.OneTimeCodeService.Code;
import com.team.vocab_learn.identity.application.OneTimeCodeService.Purpose;
import com.team.vocab_learn.identity.domain.HoSoNguoiDung;
import com.team.vocab_learn.identity.domain.NguoiDung;
import com.team.vocab_learn.identity.domain.TrangThaiNguoiDung;
import com.team.vocab_learn.identity.infrastructure.HoSoNguoiDungRepository;
import com.team.vocab_learn.identity.infrastructure.NguoiDungRepository;
import com.team.vocab_learn.shared.config.AppProperties;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.mail.MailService;
import com.team.vocab_learn.shared.security.RateLimiter;
import com.team.vocab_learn.shared.security.SessionAuthenticator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

	static final Duration RESET_TTL = Duration.ofMinutes(15);

	private final NguoiDungRepository nguoiDungRepo;
	private final HoSoNguoiDungRepository hoSoRepo;
	private final OneTimeCodeService codes;
	private final PasswordEncoder passwordEncoder;
	private final RateLimiter rateLimiter;
	private final MailService mail;
	private final SessionAuthenticator sessions;
	private final AppProperties app;
	private final Clock clock;

	/**
	 * TK-05: luôn trả lời như nhau. Chỉ tài khoản ACTIVE (email đã xác minh, kể cả tài khoản chỉ có Google)
	 * nhận liên kết; tài khoản chưa xác thực phải xác thực email trước.
	 */
	@Transactional
	public void requestReset(String email, String clientIp) {
		rateLimiter.check(RateLimiter.Policy.PASSWORD_RESET_IP, clientIp);
		String emailChuanHoa = RegistrationService.normalizeEmail(email);
		rateLimiter.check(RateLimiter.Policy.PASSWORD_RESET_ACCOUNT, emailChuanHoa);
		nguoiDungRepo.findByEmailChuanHoa(emailChuanHoa)
				.filter(u -> u.getTrangThai() == TrangThaiNguoiDung.ACTIVE)
				.ifPresent(u -> {
					String code = codes.issue(u.getId(), Purpose.RESET_PASSWORD, RESET_TTL);
					String name = hoSoRepo.findById(u.getId()).map(HoSoNguoiDung::getTenHienThi).orElse(u.getEmail());
					mail.send("reset-password", u.getEmail(), Map.of(
							"displayName", name,
							"link", app.publicBaseUrl() + "/dat-lai-mat-khau?token=" + code));
				});
	}

	/** TK-05: mã dùng một lần; đổi xong thu hồi mọi phiên, người dùng đăng nhập lại bằng mật khẩu mới. */
	@Transactional
	public void reset(String token, String newPassword) {
		Code code = codes.findForUpdate(token, Purpose.RESET_PASSWORD)
				.filter(c -> c.daSuDung() == null)
				.orElseThrow(() -> new ApiException(ErrorCode.TOKEN_INVALID,
						"Liên kết đặt lại mật khẩu không hợp lệ hoặc đã được dùng. Hãy yêu cầu liên kết mới."));
		if (!clock.instant().isBefore(code.hetHan())) {
			throw new ApiException(ErrorCode.TOKEN_EXPIRED,
					"Liên kết đặt lại mật khẩu đã hết hạn sau 15 phút. Hãy yêu cầu liên kết mới.");
		}
		codes.markUsed(code.id());
		NguoiDung nguoiDung = nguoiDungRepo.findById(code.nguoiDungId()).orElseThrow();
		nguoiDung.changePassword(passwordEncoder.encode(newPassword));
		sessions.revokeAll(nguoiDung.getId());
	}
}
