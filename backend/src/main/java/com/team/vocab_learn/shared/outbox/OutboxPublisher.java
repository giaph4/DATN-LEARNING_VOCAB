package com.team.vocab_learn.shared.outbox;

import java.sql.Timestamp;
import java.time.Clock;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.json.JsonMapper;

/** Writes an event into su_kien_cho_gui inside the business transaction: rollback → no event. */
@Component
@RequiredArgsConstructor
public class OutboxPublisher {

	private final JdbcTemplate jdbc;
	private final JsonMapper json;
	private final Clock clock;

	@Transactional(propagation = Propagation.MANDATORY)
	public String publish(String aggregateType, String aggregateId, String eventType, Object payload) {
		String eventId = UUID.randomUUID().toString();
		Timestamp now = Timestamp.from(clock.instant());
		jdbc.update("""
				INSERT INTO su_kien_cho_gui (su_kien_id, loai_nhom_du_lieu, tham_chieu_nhom_du_lieu, loai_su_kien,
				  phien_ban_cau_truc, du_lieu, thoi_diem_phat_sinh, thoi_diem_co_the_xu_ly)
				VALUES (?, ?, ?, ?, 1, ?, ?, ?)""",
				eventId, aggregateType, aggregateId, eventType, json.writeValueAsString(payload), now, now);
		return eventId;
	}
}
