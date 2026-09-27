package com.team.vocab_learn.shared.outbox;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.extern.slf4j.Slf4j;

/** Delivers committed outbox events to handlers; retries with backoff, max 10 attempts. Profile {@code worker}. */
@Slf4j
@Component
@ConditionalOnProperty(name = "app.worker.enabled", havingValue = "true")
public class OutboxRelay {

	private static final int BATCH = 50;
	private static final int MAX_ATTEMPTS = 10;
	private static final Duration LEASE = Duration.ofSeconds(120);

	private final JdbcTemplate jdbc;
	private final TransactionTemplate tx;
	private final Clock clock;
	private final Map<String, List<OutboxEventHandler>> handlers;

	public OutboxRelay(JdbcTemplate jdbc, TransactionTemplate tx, Clock clock, List<OutboxEventHandler> handlers) {
		this.jdbc = jdbc;
		this.tx = tx;
		this.clock = clock;
		this.handlers = handlers.stream().collect(Collectors.groupingBy(OutboxEventHandler::eventType));
	}

	@Scheduled(fixedDelayString = "${app.worker.poll-delay:PT2S}")
	public void poll() {
		List<OutboxEvent> events = tx.execute(s -> claim());
		events.forEach(this::deliver);
	}

	private List<OutboxEvent> claim() {
		Instant now = clock.instant();
		String token = UUID.randomUUID().toString();
		List<Long> ids = jdbc.queryForList("""
				SELECT id FROM su_kien_cho_gui
				WHERE thoi_diem_da_gui IS NULL AND thoi_diem_co_the_xu_ly <= ? AND so_lan_thu < ?
				  AND (quyen_xu_ly_den_luc IS NULL OR quyen_xu_ly_den_luc < ?)
				ORDER BY id LIMIT ?
				FOR UPDATE SKIP LOCKED""", Long.class, Timestamp.from(now), MAX_ATTEMPTS, Timestamp.from(now), BATCH);
		if (ids.isEmpty()) {
			return List.of();
		}
		String in = ids.stream().map(String::valueOf).collect(Collectors.joining(","));
		jdbc.update("UPDATE su_kien_cho_gui SET ma_quyen_xu_ly = ?, quyen_xu_ly_den_luc = ?, so_lan_thu = so_lan_thu + 1 WHERE id IN (" + in + ")",
				token, Timestamp.from(now.plus(LEASE)));
		return jdbc.query("""
				SELECT id, su_kien_id, loai_nhom_du_lieu, tham_chieu_nhom_du_lieu, loai_su_kien, phien_ban_cau_truc,
				  du_lieu, thoi_diem_phat_sinh, ma_quyen_xu_ly, so_lan_thu
				FROM su_kien_cho_gui WHERE id IN (""" + in + ") ORDER BY id",
				(rs, i) -> new OutboxEvent(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getString(7), rs.getTimestamp(8).toInstant(),
						rs.getString(9), rs.getInt(10)));
	}

	private void deliver(OutboxEvent event) {
		Instant now = clock.instant();
		try {
			for (OutboxEventHandler handler : handlers.getOrDefault(event.eventType(), List.of())) {
				handler.handle(event);
			}
			jdbc.update("UPDATE su_kien_cho_gui SET thoi_diem_da_gui = ?, ma_quyen_xu_ly = NULL, quyen_xu_ly_den_luc = NULL WHERE id = ? AND ma_quyen_xu_ly = ?",
					Timestamp.from(now), event.id(), event.claimToken());
		} catch (Exception e) {
			log.warn("Outbox event {} ({}) failed: {}", event.eventId(), event.eventType(), e.toString());
			Instant retryAt = now.plusSeconds(30L << Math.min(event.attempts() - 1, 6));
			jdbc.update("UPDATE su_kien_cho_gui SET thoi_diem_co_the_xu_ly = ?, ma_quyen_xu_ly = NULL, quyen_xu_ly_den_luc = NULL WHERE id = ? AND ma_quyen_xu_ly = ?",
					Timestamp.from(retryAt), event.id(), event.claimToken());
		}
	}
}
