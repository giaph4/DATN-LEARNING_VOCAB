package com.team.vocab_learn.shared.idempotency;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.json.JsonMapper;

/**
 * Idempotency-Key support (table ban_ghi_chong_trung, kept 24 h).
 * Same key + same body → stored result; same key + other body → 409.
 * Runs in the caller's transaction: a concurrent duplicate blocks on the row lock,
 * and if the first request rolls back the record disappears with it.
 */
@Service
@RequiredArgsConstructor
public class IdempotencyService {

	public static final String HEADER = "Idempotency-Key";
	private static final Duration TTL = Duration.ofHours(24);
	private static final Pattern KEY = Pattern.compile("[A-Za-z0-9_.:-]{8,80}");

	private final JdbcTemplate jdbc;
	private final JsonMapper json;
	private final Clock clock;

	private record Stored(byte[] hash, String status, String response, Instant expiresAt) {
	}

	@Transactional
	public <T> T execute(long userId, String operation, String key, Object request, Class<T> responseType,
			Supplier<T> action) {
		if (key == null || !KEY.matcher(key).matches()) {
			throw new ApiException(ErrorCode.VALIDATION_FAILED, "Header Idempotency-Key thiếu hoặc không hợp lệ (8–80 ký tự).");
		}
		byte[] hash = sha256(json.writeValueAsBytes(request));
		Instant now = clock.instant();
		try {
			jdbc.update("""
					INSERT INTO ban_ghi_chong_trung
					  (nguoi_dung_id, thao_tac, khoa_chong_ghi_trung, ma_bam_yeu_cau, trang_thai, created_at, thoi_diem_het_han)
					VALUES (?, ?, ?, ?, 'IN_PROGRESS', ?, ?)""",
					userId, operation, key, hash, Timestamp.from(now), Timestamp.from(now.plus(TTL)));
		} catch (DuplicateKeyException e) {
			Stored stored = load(userId, operation, key);
			if (stored.expiresAt().isAfter(now)) {
				return replay(stored, hash, responseType);
			}
			// Expired record: reuse the row for this new request.
			jdbc.update("""
					UPDATE ban_ghi_chong_trung SET ma_bam_yeu_cau = ?, trang_thai = 'IN_PROGRESS', du_lieu_phan_hoi = NULL,
					  created_at = ?, thoi_diem_het_han = ?
					WHERE nguoi_dung_id = ? AND thao_tac = ? AND khoa_chong_ghi_trung = ?""",
					hash, Timestamp.from(now), Timestamp.from(now.plus(TTL)), userId, operation, key);
		}
		T result = action.get();
		jdbc.update("""
				UPDATE ban_ghi_chong_trung SET trang_thai = 'COMPLETED', du_lieu_phan_hoi = ?
				WHERE nguoi_dung_id = ? AND thao_tac = ? AND khoa_chong_ghi_trung = ?""",
				json.writeValueAsString(result), userId, operation, key);
		return result;
	}

	private Stored load(long userId, String operation, String key) {
		List<Stored> rows = jdbc.query("""
				SELECT ma_bam_yeu_cau, trang_thai, du_lieu_phan_hoi, thoi_diem_het_han FROM ban_ghi_chong_trung
				WHERE nguoi_dung_id = ? AND thao_tac = ? AND khoa_chong_ghi_trung = ? FOR UPDATE""",
				(rs, i) -> new Stored(rs.getBytes(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4).toInstant()),
				userId, operation, key);
		return rows.getFirst();
	}

	private <T> T replay(Stored stored, byte[] hash, Class<T> responseType) {
		if (!Arrays.equals(stored.hash(), hash)) {
			throw new ApiException(ErrorCode.IDEMPOTENCY_KEY_REUSED, "Khóa này đã được dùng cho một yêu cầu khác.");
		}
		if (!"COMPLETED".equals(stored.status())) {
			throw new ApiException(ErrorCode.REQUEST_IN_PROGRESS, "Yêu cầu trước vẫn đang xử lý, hãy thử lại sau.");
		}
		return json.readValue(stored.response(), responseType);
	}

	private static byte[] sha256(byte[] data) {
		try {
			return MessageDigest.getInstance("SHA-256").digest(data);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}
}
