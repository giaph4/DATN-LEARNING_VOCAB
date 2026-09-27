package com.team.vocab_learn.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.support.TransactionTemplate;

import com.team.vocab_learn.AbstractIntegrationTest;
import com.team.vocab_learn.shared.outbox.OutboxEvent;
import com.team.vocab_learn.shared.outbox.OutboxEventHandler;
import com.team.vocab_learn.shared.outbox.OutboxPublisher;
import com.team.vocab_learn.shared.outbox.OutboxRelay;

/** B0.5 Outbox: rollback → no event; committed event is delivered once and marked sent. */
class OutboxTest extends AbstractIntegrationTest {

	@Autowired
	OutboxPublisher publisher;

	@Autowired
	TransactionTemplate tx;

	@Autowired
	Clock clock;

	@BeforeEach
	void clean() {
		jdbc.update("DELETE FROM su_kien_cho_gui");
	}

	@Test
	void rollback_leavesNoEvent() {
		tx.executeWithoutResult(s -> {
			publisher.publish("DECK", "1", "DECK_CREATED", Map.of("id", 1));
			s.setRollbackOnly();
		});
		assertThat(count()).isZero();
	}

	@Test
	void publishOutsideTransaction_isRejected() {
		assertThatThrownBy(() -> publisher.publish("DECK", "1", "DECK_CREATED", Map.of()))
				.isInstanceOf(IllegalTransactionStateException.class);
	}

	@Test
	void relay_deliversCommittedEvent() {
		tx.executeWithoutResult(s -> publisher.publish("DECK", "7", "DECK_CREATED", Map.of("id", 7)));
		List<OutboxEvent> received = new ArrayList<>();
		OutboxEventHandler handler = new OutboxEventHandler() {
			public String eventType() {
				return "DECK_CREATED";
			}

			public void handle(OutboxEvent event) {
				received.add(event);
			}
		};
		OutboxRelay relay = new OutboxRelay(jdbc, tx, clock, List.of(handler));

		relay.poll();
		relay.poll();

		assertThat(received).hasSize(1);
		assertThat(received.getFirst().aggregateId()).isEqualTo("7");
		assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM su_kien_cho_gui WHERE thoi_diem_da_gui IS NOT NULL", Integer.class))
				.isEqualTo(1);
	}

	private int count() {
		return jdbc.queryForObject("SELECT COUNT(*) FROM su_kien_cho_gui", Integer.class);
	}
}
