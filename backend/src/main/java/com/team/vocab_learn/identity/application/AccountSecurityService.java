package com.team.vocab_learn.identity.application;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.vocab_learn.identity.domain.DanhTinhDangNhap;
import com.team.vocab_learn.identity.domain.NguoiDung;
import com.team.vocab_learn.identity.domain.NhaCungCapDangNhap;
import com.team.vocab_learn.identity.infrastructure.DanhTinhDangNhapRepository;
import com.team.vocab_learn.identity.infrastructure.NguoiDungRepository;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.security.RateLimiter;

import lombok.RequiredArgsConstructor;

/** Xác thực lại và các phương thức đăng nhập đã liên kết của người dùng hiện tại. */
@Service
@RequiredArgsConstructor
public class AccountSecurityService {

	public record LoginMethods(boolean hasPassword, List<DanhTinhDangNhap> identities) {
	}

	private final NguoiDungRepository nguoiDungRepo;
	private final DanhTinhDangNhapRepository danhTinhRepo;
	private final PasswordEncoder passwordEncoder;
	private final RateLimiter rateLimiter;

	/** Dùng chung giới hạn đăng nhập theo tài khoản để không dò mật khẩu qua đây. */
	@Transactional(readOnly = true)
	public void verifyPassword(long nguoiDungId, String password) {
		NguoiDung nguoiDung = nguoiDungRepo.findById(nguoiDungId).orElseThrow();
		rateLimiter.check(RateLimiter.Policy.LOGIN_ACCOUNT, nguoiDung.getEmailChuanHoa());
		if (nguoiDung.getPasswordHash() == null) {
			throw new ApiException(ErrorCode.BUSINESS_RULE_VIOLATED,
					"Tài khoản chưa có mật khẩu. Hãy tạo mật khẩu bằng chức năng Quên mật khẩu rồi thử lại.");
		}
		if (!passwordEncoder.matches(password, nguoiDung.getPasswordHash())) {
			throw new ApiException(ErrorCode.INVALID_CREDENTIALS, "Mật khẩu không đúng.");
		}
	}

	@Transactional(readOnly = true)
	public LoginMethods loginMethods(long nguoiDungId) {
		NguoiDung nguoiDung = nguoiDungRepo.findById(nguoiDungId).orElseThrow();
		return new LoginMethods(nguoiDung.getPasswordHash() != null,
				danhTinhRepo.findByNguoiDungIdOrderByThoiDiemLienKet(nguoiDungId));
	}

	/** Không gỡ phương thức đăng nhập cuối cùng (THIET_KE 3.1). */
	@Transactional
	public void unlink(long nguoiDungId, NhaCungCapDangNhap nhaCungCap) {
		LoginMethods methods = loginMethods(nguoiDungId);
		DanhTinhDangNhap identity = methods.identities().stream().filter(d -> d.getNhaCungCap() == nhaCungCap).findFirst()
				.orElseThrow(() -> ApiException.notFound("phương thức đăng nhập này"));
		if (!methods.hasPassword() && methods.identities().size() == 1) {
			throw new ApiException(ErrorCode.LAST_LOGIN_METHOD,
					"Đây là cách đăng nhập duy nhất của bạn. Hãy tạo mật khẩu trước khi gỡ Google.");
		}
		danhTinhRepo.delete(identity);
	}

	public static NhaCungCapDangNhap provider(String path) {
		// Facebook chưa bật (mở rộng): coi như không tồn tại
		if (!"google".equals(path)) {
			throw ApiException.notFound("phương thức đăng nhập này");
		}
		return NhaCungCapDangNhap.GOOGLE;
	}
}
