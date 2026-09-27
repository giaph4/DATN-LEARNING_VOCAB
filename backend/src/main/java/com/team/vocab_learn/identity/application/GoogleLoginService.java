package com.team.vocab_learn.identity.application;

import java.time.Clock;
import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.vocab_learn.identity.domain.DanhTinhDangNhap;
import com.team.vocab_learn.identity.domain.NguoiDung;
import com.team.vocab_learn.identity.domain.NhaCungCapDangNhap;
import com.team.vocab_learn.identity.infrastructure.DanhTinhDangNhapRepository;
import com.team.vocab_learn.identity.infrastructure.NguoiDungRepository;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleLoginService {

	private final DanhTinhDangNhapRepository danhTinhRepo;
	private final NguoiDungRepository nguoiDungRepo;
	private final RegistrationService registration;
	private final LoginService login;
	private final Clock clock;

	/**
	 * TK-03. Tìm theo "sub" của Google, không theo email. Email đã có tài khoản thì không tự ghép:
	 * người dùng đăng nhập bằng mật khẩu rồi liên kết Google trong cài đặt (B1.5).
	 */
	@Transactional
	public SignedInUser signIn(String subject, String email, boolean emailVerified, String fullName) {
		NguoiDung nguoiDung = danhTinhRepo.findByNhaCungCapAndDinhDanhNhaCungCap(NhaCungCapDangNhap.GOOGLE, subject)
				.map(d -> nguoiDungRepo.findById(d.getNguoiDungId()).orElseThrow())
				.orElseGet(() -> createAccount(subject, email, emailVerified, fullName));
		return login.signIn(nguoiDung);
	}

	/**
	 * B1.5: người dùng đã đăng nhập + xác thực lại rồi mới đi qua Google. "sub" đang thuộc người khác thì từ chối;
	 * email Google được phép khác email tài khoản vì người dùng đã tự xác nhận.
	 */
	@Transactional
	public SignedInUser link(long nguoiDungId, String subject, String email) {
		danhTinhRepo.findByNhaCungCapAndDinhDanhNhaCungCap(NhaCungCapDangNhap.GOOGLE, subject).ifPresent(d -> {
			if (!d.getNguoiDungId().equals(nguoiDungId)) {
				throw new ApiException(ErrorCode.GOOGLE_ACCOUNT_IN_USE,
						"Tài khoản Google này đã liên kết với một người dùng khác.");
			}
		});
		if (danhTinhRepo.findByNguoiDungIdAndNhaCungCap(nguoiDungId, NhaCungCapDangNhap.GOOGLE).isEmpty()) {
			danhTinhRepo.save(DanhTinhDangNhap.link(nguoiDungId, NhaCungCapDangNhap.GOOGLE, subject, email, clock.instant()));
		} else if (danhTinhRepo.findByNhaCungCapAndDinhDanhNhaCungCap(NhaCungCapDangNhap.GOOGLE, subject).isEmpty()) {
			throw new ApiException(ErrorCode.CONFLICT, "Tài khoản đã liên kết một Google khác. Hãy gỡ liên kết cũ trước.");
		}
		return reload(nguoiDungId);
	}

	/** Dựng lại phiên cho người dùng gốc khi liên kết thất bại. */
	@Transactional
	public SignedInUser reload(long nguoiDungId) {
		return login.signIn(nguoiDungRepo.findById(nguoiDungId).orElseThrow());
	}

	private NguoiDung createAccount(String subject, String email, boolean emailVerified, String fullName) {
		if (email == null || !emailVerified) {
			throw new ApiException(ErrorCode.EMAIL_NOT_VERIFIED, "Google chưa xác thực email của tài khoản này.");
		}
		String emailChuanHoa = RegistrationService.normalizeEmail(email);
		if (nguoiDungRepo.existsByEmailChuanHoa(emailChuanHoa)) {
			throw new ApiException(ErrorCode.GOOGLE_EMAIL_IN_USE,
					"Email này đã có tài khoản. Hãy đăng nhập bằng mật khẩu, sau đó liên kết Google trong Cài đặt.");
		}
		Instant now = clock.instant();
		NguoiDung nguoiDung = nguoiDungRepo.save(NguoiDung.registerWithGoogle(email, emailChuanHoa, now));
		String name = fullName == null || fullName.strip().length() < 2 ? "Người học" : fullName.strip();
		registration.createProfile(nguoiDung.getId(), name.length() > 100 ? name.substring(0, 100) : name);
		danhTinhRepo.save(DanhTinhDangNhap.link(nguoiDung.getId(), NhaCungCapDangNhap.GOOGLE, subject, email, now));
		return nguoiDung;
	}
}
