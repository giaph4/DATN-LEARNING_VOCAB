package com.team.vocab_learn.identity.application;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.vocab_learn.identity.application.OneTimeCodeService.Code;
import com.team.vocab_learn.identity.application.OneTimeCodeService.Purpose;
import com.team.vocab_learn.identity.domain.NguoiDung;
import com.team.vocab_learn.identity.domain.TrangThaiNguoiDung;
import com.team.vocab_learn.identity.infrastructure.HoSoNguoiDungRepository;
import com.team.vocab_learn.identity.infrastructure.NguoiDungRepository;
import com.team.vocab_learn.shared.config.AppProperties;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.mail.MailService;
import com.team.vocab_learn.shared.security.RateLimiter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

	static final Duration VERIFY_TTL = Duration.ofHours(24);

	private final OneTimeCodeService codes;
	private final NguoiDungRepository nguoiDungRepo;
	private final HoSoNguoiDungRepository hoSoRepo;
	private final RateLimiter rateLimiter;
	private final MailService mail;
	private final AppProperties app;
	private final Clock clock;

	/** Phát mã mới (mã cũ mất hiệu lực) và xếp email vào hàng đợi tác vụ nền. */
	@Transactional
	public void sendCode(long nguoiDungId, String email, String displayName) {
		String code = codes.issue(nguoiDungId, Purpose.VERIFY_EMAIL, VERIFY_TTL);
		mail.send("verify-email", email, Map.of(
				"displayName", displayName,
				"link", app.publicBaseUrl() + "/xac-thuc-email?token=" + code));
	}

	/**
	 * TK-02. Mã đã dùng nhưng tài khoản đã xác thực thì coi là thành công (bấm link 2 lần, 2 tab),
	 * còn lại mã sai/đã dùng/hết hạn không kích hoạt tài khoản.
	 */
	@Transactional
	public void verify(String token) {
		Code code = codes.findForUpdate(token, Purpose.VERIFY_EMAIL)
				.orElseThrow(() -> new ApiException(ErrorCode.TOKEN_INVALID,
						"Liên kết xác thực không hợp lệ. Hãy yêu cầu gửi lại email xác thực."));
		NguoiDung nguoiDung = nguoiDungRepo.findById(code.nguoiDungId()).orElseThrow();
		if (code.daSuDung() != null) {
			if (nguoiDung.getThoiDiemXacThucEmail() != null) {
				return;
			}
			throw new ApiException(ErrorCode.TOKEN_INVALID,
					"Liên kết này đã được thay bằng liên kết mới hơn. Hãy mở email xác thực gần nhất.");
		}
		Instant now = clock.instant();
		if (!now.isBefore(code.hetHan())) {
			throw new ApiException(ErrorCode.TOKEN_EXPIRED,
					"Liên kết xác thực đã hết hạn sau 24 giờ. Hãy yêu cầu gửi lại email xác thực.");
		}
		codes.markUsed(code.id());
		nguoiDung.verifyEmail(now);
	}

	/** TK-02: luôn trả lời như nhau để không lộ email nào đã đăng ký; chỉ gửi khi tài khoản còn chờ xác thực. */
	@Transactional
	public void resend(String email) {
		String emailChuanHoa = RegistrationService.normalizeEmail(email);
		rateLimiter.check(RateLimiter.Policy.RESEND_VERIFY_ACCOUNT, emailChuanHoa);
		nguoiDungRepo.findByEmailChuanHoa(emailChuanHoa)
				.filter(u -> u.getTrangThai() == TrangThaiNguoiDung.PENDING_VERIFICATION)
				.ifPresent(u -> sendCode(u.getId(), u.getEmail(),
						hoSoRepo.findById(u.getId()).map(h -> h.getTenHienThi()).orElse(u.getEmail())));
	}
}
