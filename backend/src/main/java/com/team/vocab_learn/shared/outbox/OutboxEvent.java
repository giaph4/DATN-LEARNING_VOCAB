package com.team.vocab_learn.shared.outbox;

import java.time.Instant;

/** A row of su_kien_cho_gui handed to {@link OutboxEventHandler}s. */
public record OutboxEvent(long id, String eventId, String aggregateType, String aggregateId, String eventType,
		int schemaVersion, String payload, Instant occurredAt, String claimToken, int attempts) {
}
