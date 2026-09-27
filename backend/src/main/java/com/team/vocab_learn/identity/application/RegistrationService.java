package com.team.vocab_learn.identity.application;

import java.text.Normalizer;
import java.util.Locale;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.team.vocab_learn.identity.api.RegisterRequest;
import com.team.vocab_learn.identity.domain.HoSoNguoiDung;
import com.team.vocab_learn.identity.domain.NguoiDung;
import com.team.vocab_learn.identity.domain.TuyChonHoc;
import com.team.vocab_learn.identity.infrastructure.HoSoNguoiDungRepository;
import com.team.vocab_learn.identity.infrastructure.NguoiDungRepository;
import com.team.vocab_learn.identity.infrastructure.TuyChonHocRepository;
import com.team.vocab_learn.shared.security.RateLimiter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {

	private final NguoiDungRepository nguoiDungRepo;
	private final HoSoNguoiDungRepository hoSoRepo;
	private final TuyChonHocRepository tuyChonRepo;
	private final EmailVerificationService verification;
	private final PasswordEncoder passwordEncoder;
	private final RateLimiter rateLimiter;
	private final JdbcTemplate jdbc;

	/**
	 * TK-01. Email đã tồn tại vẫn trả về bình thường để không lộ email nào đã đăng ký.
	 * Luôn băm mật khẩu trước để thời gian phản hồi hai trường hợp như nhau.
	 */
	@Transactional
	public void register(RegisterRequest req, String clientIp) {
		rateLimiter.check(RateLimiter.Policy.REGISTER_IP, clientIp);
		String passwordHash = passwordEncoder.encode(req.password());
		String emailChuanHoa = normalizeEmail(req.email());
		if (nguoiDungRepo.existsByEmailChuanHoa(emailChuanHoa)) {
			return;
		}

		NguoiDung nguoiDung = nguoiDungRepo.save(NguoiDung.registerWithEmail(req.email(), emailChuanHoa, passwordHash));
		Long id = nguoiDung.getId();
		createProfile(id, req.displayName());

		verification.sendCode(id, req.email(), req.displayName());
	}

	/** Hồ sơ, tùy chọn học mặc định và vai trò LEARNER cho tài khoản mới (email hoặc Google). */
	@Transactional(propagation = Propagation.MANDATORY)
	public void createProfile(Long nguoiDungId, String tenHienThi) {
		hoSoRepo.save(HoSoNguoiDung.create(nguoiDungId, tenHienThi));
		tuyChonRepo.save(TuyChonHoc.defaultsFor(nguoiDungId));
		jdbc.update("INSERT INTO nguoi_dung_vai_tro (nguoi_dung_id, vai_tro_id) SELECT ?, id FROM vai_tro WHERE ma = 'LEARNER'",
				nguoiDungId);
	}

	public static String normalizeEmail(String email) {
		return Normalizer.normalize(email.strip(), Normalizer.Form.NFC).toLowerCase(Locale.ROOT);
	}
}
