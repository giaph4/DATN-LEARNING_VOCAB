package com.team.vocab_learn.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.team.vocab_learn.AbstractIntegrationTest;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.idempotency.IdempotencyService;

/** B0.5 Idempotency: send twice → one effect; same key other body → 409; concurrent duplicates → one effect. */
class IdempotencyServiceTest extends AbstractIntegrationTest {

	record Result(String value) {
	}

	@Autowired
	IdempotencyService idempotency;

	@Test
	void sameKeySameBody_runsOnceAndReplays() {
		long user = createUser();
		AtomicInteger runs = new AtomicInteger();
		Result first = idempotency.execute(user, "REVIEW", "key-00000001", Map.of("grade", 3), Result.class,
				() -> new Result("r" + runs.incrementAndGet()));
		Result second = idempotency.execute(user, "REVIEW", "key-00000001", Map.of("grade", 3), Result.class,
				() -> new Result("r" + runs.incrementAndGet()));

		assertThat(runs).hasValue(1);
		assertThat(second).isEqualTo(first);
	}

	@Test
	void sameKeyOtherBody_conflict() {
		long user = createUser();
		idempotency.execute(user, "REVIEW", "key-00000002", Map.of("grade", 3), Result.class, () -> new Result("ok"));

		assertThatThrownBy(() -> idempotency.execute(user, "REVIEW", "key-00000002", Map.of("grade", 1), Result.class,
				() -> new Result("x")))
				.isInstanceOf(ApiException.class)
				.extracting("code").isEqualTo(ErrorCode.IDEMPOTENCY_KEY_REUSED);
	}

	@Test
	void concurrentDuplicates_runOnce() {
		long user = createUser();
		AtomicInteger runs = new AtomicInteger();
		Runnable call = () -> idempotency.execute(user, "SUBMIT", "key-00000003", Map.of("a", 1), Result.class, () -> {
			runs.incrementAndGet();
			sleep(300);
			return new Result("done");
		});
		CompletableFuture.allOf(CompletableFuture.runAsync(call), CompletableFuture.runAsync(call)).join();

		assertThat(runs).hasValue(1);
	}

	@Test
	void missingKey_validationError() {
		assertThatThrownBy(() -> idempotency.execute(1, "X", null, Map.of(), Result.class, () -> null))
				.extracting("code").isEqualTo(ErrorCode.VALIDATION_FAILED);
	}

	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
