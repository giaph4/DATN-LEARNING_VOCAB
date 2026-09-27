package com.team.vocab_learn.identity.application;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * Bảng ma_xac_thuc: mã gốc chỉ gửi qua email, DB giữ SHA-256.
 * Dùng JdbcTemplate vì cột BINARY(32) map sang byte[] sẽ làm ddl-auto=validate báo sai kiểu.
 */
@Service
@RequiredArgsConstructor
public class OneTimeCodeService {

	public enum Purpose {
		VERIFY_EMAIL, RESET_PASSWORD, LINK_IDENTITY, DELETE_CONFIRM
	}

	/** Một dòng ma_xac_thuc; daSuDung khác null nghĩa là đã dùng hoặc đã bị mã mới thay thế. */
	public record Code(long id, long nguoiDungId, Instant hetHan, Instant daSuDung) {
	}

	private static final SecureRandom RANDOM = new SecureRandom();

	private final JdbcTemplate jdbc;
	private final Clock clock;

	/** TK-02: phát mã mới thì vô hiệu các mã chưa dùng cùng mục đích. Trả về mã gốc. */
	@Transactional(propagation = Propagation.MANDATORY)
	public String issue(long nguoiDungId, Purpose purpose, Duration ttl) {
		Instant now = clock.instant();
		jdbc.update("""
				UPDATE ma_xac_thuc SET thoi_diem_da_su_dung = ?
				WHERE nguoi_dung_id = ? AND muc_dich = ? AND thoi_diem_da_su_dung IS NULL""",
				Timestamp.from(now), nguoiDungId, purpose.name());

		byte[] random = new byte[32];
		RANDOM.nextBytes(random);
		String code = Base64.getUrlEncoder().withoutPadding().encodeToString(random);
		jdbc.update("""
				INSERT INTO ma_xac_thuc (nguoi_dung_id, muc_dich, ma_xac_thuc_bam, thoi_diem_het_han, created_at)
				VALUES (?, ?, ?, ?, ?)""",
				nguoiDungId, purpose.name(), sha256(code), Timestamp.from(now.plus(ttl)), Timestamp.from(now));
		return code;
	}

	/** Khóa dòng để hai request cùng mã không cùng dùng được. */
	@Transactional(propagation = Propagation.MANDATORY)
	public Optional<Code> findForUpdate(String code, Purpose purpose) {
		return jdbc.query("""
				SELECT id, nguoi_dung_id, thoi_diem_het_han, thoi_diem_da_su_dung FROM ma_xac_thuc
				WHERE ma_xac_thuc_bam = ? AND muc_dich = ? FOR UPDATE""",
				(rs, i) -> new Code(rs.getLong(1), rs.getLong(2), rs.getTimestamp(3).toInstant(),
						rs.getTimestamp(4) == null ? null : rs.getTimestamp(4).toInstant()),
				sha256(code), purpose.name()).stream().findFirst();
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void markUsed(long codeId) {
		jdbc.update("UPDATE ma_xac_thuc SET thoi_diem_da_su_dung = ? WHERE id = ?", Timestamp.from(clock.instant()), codeId);
	}

	public static byte[] sha256(String code) {
		try {
			return MessageDigest.getInstance("SHA-256").digest(code.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}
}
