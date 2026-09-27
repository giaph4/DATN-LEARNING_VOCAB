package com.team.vocab_learn.shared.outbox;

/** Reacts to one event type after commit (at-least-once → must be idempotent, e.g. dedupe by eventId). */
public interface OutboxEventHandler {

	String eventType();

	void handle(OutboxEvent event) throws Exception;
}
