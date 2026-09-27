package com.team.vocab_learn.identity.application;

import java.time.Clock;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.vocab_learn.identity.api.LoginRequest;
import com.team.vocab_learn.identity.domain.HoSoNguoiDung;
import com.team.vocab_learn.identity.domain.NguoiDung;
import com.team.vocab_learn.identity.infrastructure.HoSoNguoiDungRepository;
import com.team.vocab_learn.identity.infrastructure.NguoiDungRepository;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.security.AuthUser;
import com.team.vocab_learn.shared.security.RateLimiter;

@Service
public class LoginService {

	private final NguoiDungRepository nguoiDungRepo;
	private final HoSoNguoiDungRepository hoSoRepo;
	private final PasswordEncoder passwordEncoder;
	private final RateLimiter rateLimiter;
	private final Clock clock;
	// Email không tồn tại vẫn chạy BCrypt với băm giả để thời gian phản hồi không lộ email nào có thật
	private final String dummyHash;

	public LoginService(NguoiDungRepository nguoiDungRepo, HoSoNguoiDungRepository hoSoRepo,
			PasswordEncoder passwordEncoder, RateLimiter rateLimiter, Clock clock) {
		this.nguoiDungRepo = nguoiDungRepo;
		this.hoSoRepo = hoSoRepo;
		this.passwordEncoder = passwordEncoder;
		this.rateLimiter = rateLimiter;
		this.clock = clock;
		this.dummyHash = passwordEncoder.encode("dummy-password-for-timing");
	}

	/** TK-03. Sai email hay sai mật khẩu đều trả cùng một lỗi; trạng thái tài khoản chỉ báo khi mật khẩu đúng. */
	@Transactional
	public SignedInUser login(LoginRequest req, String clientIp) {
		rateLimiter.check(RateLimiter.Policy.LOGIN_IP, clientIp);
		String emailChuanHoa = RegistrationService.normalizeEmail(req.email());
		rateLimiter.check(RateLimiter.Policy.LOGIN_ACCOUNT, emailChuanHoa);

		NguoiDung nguoiDung = nguoiDungRepo.findByEmailChuanHoa(emailChuanHoa).orElse(null);
		boolean hasPassword = nguoiDung != null && nguoiDung.getPasswordHash() != null;
		boolean matches = passwordEncoder.matches(req.password(), hasPassword ? nguoiDung.getPasswordHash() : dummyHash);
		if (!hasPassword || !matches) {
			throw new ApiException(ErrorCode.INVALID_CREDENTIALS, "Email hoặc mật khẩu không đúng.");
		}
		return signIn(nguoiDung);
	}

	/** Kiểm tra trạng thái, ghi thời điểm đăng nhập và dựng principal cho phiên. Dùng chung cho Google. */
	@Transactional
	public SignedInUser signIn(NguoiDung nguoiDung) {
		switch (nguoiDung.getTrangThai()) {
			case PENDING_VERIFICATION -> throw new ApiException(ErrorCode.EMAIL_NOT_VERIFIED,
					"Email chưa được xác thực. Hãy mở liên kết trong email hoặc yêu cầu gửi lại.");
			case LOCKED -> throw new ApiException(ErrorCode.ACCOUNT_LOCKED,
					"Tài khoản đã bị khóa. Hãy liên hệ quản trị viên nếu bạn cho rằng đây là nhầm lẫn.");
			default -> {
			}
		}
		nguoiDung.recordLogin(clock.instant());
		Long id = nguoiDung.getId();
		AuthUser authUser = new AuthUser(id, nguoiDung.getEmail(), Set.copyOf(nguoiDungRepo.findRoleCodes(id)),
				nguoiDung.getPhienBanXacThuc());
		String displayName = hoSoRepo.findById(id).map(HoSoNguoiDung::getTenHienThi).orElse(nguoiDung.getEmail());
		return new SignedInUser(authUser, displayName, nguoiDung.getTrangThai());
	}
}
