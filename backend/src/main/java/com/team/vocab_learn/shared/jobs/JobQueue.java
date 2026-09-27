package com.team.vocab_learn.shared.jobs;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.json.JsonMapper;

/**
 * Background job queue on table tac_vu_nen.
 * Claim uses SELECT … FOR UPDATE SKIP LOCKED + a 120 s lease, so two workers never take the same job.
 */
@Repository
@RequiredArgsConstructor
public class JobQueue {

	static final Duration LEASE = Duration.ofSeconds(120);
	private static final Duration BASE_BACKOFF = Duration.ofSeconds(30);

	private final JdbcTemplate jdbc;
	private final JsonMapper json;
	private final Clock clock;

	/** Enqueue once per {@code dedupKey}; returns the job id (existing id if already queued). */
	@Transactional
	public long enqueue(String type, Long ownerId, String dedupKey, Object payload, Instant runAt) {
		GeneratedKeyHolder keys = new GeneratedKeyHolder();
		jdbc.update(con -> {
			PreparedStatement ps = con.prepareStatement("""
					INSERT INTO tac_vu_nen (loai_tac_vu, chu_so_huu_id, khoa_loai_trung, du_lieu, thoi_diem_co_the_xu_ly)
					VALUES (?, ?, ?, ?, ?)
					ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id)""", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, type);
			ps.setObject(2, ownerId);
			ps.setString(3, dedupKey);
			ps.setString(4, json.writeValueAsString(payload));
			ps.setTimestamp(5, Timestamp.from(runAt));
			return ps;
		}, keys);
		return keys.getKey().longValue();
	}

	@Transactional
	public List<Job> claim(String workerId, int limit) {
		Instant now = clock.instant();
		// Range scan on ix_tac_vu_nhan → only the returned rows get locked, other workers skip them.
		List<Long> ids = jdbc.queryForList("""
				SELECT id FROM tac_vu_nen
				WHERE trang_thai = 'QUEUED' AND thoi_diem_co_the_xu_ly <= ?
				ORDER BY thoi_diem_co_the_xu_ly
				LIMIT ?
				FOR UPDATE SKIP LOCKED""", Long.class, Timestamp.from(now), limit);
		List<Job> jobs = new ArrayList<>(ids.size());
		for (Long id : ids) {
			String token = UUID.randomUUID().toString();
			jdbc.update("""
					UPDATE tac_vu_nen SET trang_thai = 'RUNNING', ma_quyen_xu_ly = ?, quyen_xu_ly_den_luc = ?,
					  tien_trinh_nhan_xu_ly = ?, so_lan_thu = so_lan_thu + 1
					WHERE id = ?""", token, Timestamp.from(now.plus(LEASE)), workerId, id);
			jobs.add(jdbc.queryForObject("""
					SELECT id, loai_tac_vu, chu_so_huu_id, du_lieu, so_lan_thu, so_lan_thu_toi_da, ma_quyen_xu_ly
					FROM tac_vu_nen WHERE id = ?""", (rs, i) -> Job.builder()
					.id(rs.getLong(1))
					.type(rs.getString(2))
					.ownerId(rs.getObject(3, Long.class))
					.payload(rs.getString(4))
					.attempts(rs.getInt(5))
					.maxAttempts(rs.getInt(6))
					.claimToken(rs.getString(7))
					.build(), id));
		}
		return jobs;
	}

	public void markSucceeded(Job job) {
		jdbc.update("""
				UPDATE tac_vu_nen SET trang_thai = 'SUCCEEDED', thoi_diem_hoan_thanh = ?, ma_quyen_xu_ly = NULL,
				  quyen_xu_ly_den_luc = NULL, ma_loi_gan_nhat = NULL
				WHERE id = ? AND ma_quyen_xu_ly = ?""", Timestamp.from(clock.instant()), job.id(), job.claimToken());
	}

	/** Retry with exponential backoff (30 s, 60 s, 120 s…) until so_lan_thu_toi_da, then FAILED. */
	public void markFailed(Job job, String errorCode, boolean retryable) {
		Instant now = clock.instant();
		String code = errorCode.length() > 100 ? errorCode.substring(0, 100) : errorCode;
		if (!retryable || job.attempts() >= job.maxAttempts()) {
			jdbc.update("""
					UPDATE tac_vu_nen SET trang_thai = 'FAILED', ma_loi_gan_nhat = ?, thoi_diem_hoan_thanh = ?,
					  ma_quyen_xu_ly = NULL, quyen_xu_ly_den_luc = NULL
					WHERE id = ? AND ma_quyen_xu_ly = ?""", code, Timestamp.from(now), job.id(), job.claimToken());
			return;
		}
		Instant retryAt = now.plus(BASE_BACKOFF.multipliedBy(1L << (job.attempts() - 1)));
		jdbc.update("""
				UPDATE tac_vu_nen SET trang_thai = 'QUEUED', ma_loi_gan_nhat = ?, thoi_diem_co_the_xu_ly = ?,
				  ma_quyen_xu_ly = NULL, quyen_xu_ly_den_luc = NULL
				WHERE id = ? AND ma_quyen_xu_ly = ?""", code, Timestamp.from(retryAt), job.id(), job.claimToken());
	}

	/** Worker died mid-job (lease expired): re-queue it, or FAILED if no attempts left. */
	public void recoverExpiredLeases() {
		Timestamp now = Timestamp.from(clock.instant());
		jdbc.update("""
				UPDATE tac_vu_nen SET trang_thai = 'FAILED', ma_loi_gan_nhat = 'LEASE_EXPIRED', thoi_diem_hoan_thanh = ?,
				  ma_quyen_xu_ly = NULL, quyen_xu_ly_den_luc = NULL
				WHERE trang_thai = 'RUNNING' AND quyen_xu_ly_den_luc < ? AND so_lan_thu >= so_lan_thu_toi_da""", now, now);
		jdbc.update("""
				UPDATE tac_vu_nen SET trang_thai = 'QUEUED', ma_loi_gan_nhat = 'LEASE_EXPIRED', thoi_diem_co_the_xu_ly = ?,
				  ma_quyen_xu_ly = NULL, quyen_xu_ly_den_luc = NULL
				WHERE trang_thai = 'RUNNING' AND quyen_xu_ly_den_luc < ?""", now, now);
	}
}
