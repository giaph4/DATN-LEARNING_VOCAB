package com.team.vocab_learn.shared;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.team.vocab_learn.AbstractIntegrationTest;
import com.team.vocab_learn.shared.jobs.Job;
import com.team.vocab_learn.shared.jobs.JobHandler;
import com.team.vocab_learn.shared.jobs.JobQueue;
import com.team.vocab_learn.shared.jobs.JobWorker;

/** B0.5 Jobs: dedup on enqueue, two workers never claim the same job, retry then FAILED. */
class JobQueueTest extends AbstractIntegrationTest {

	@Autowired
	JobQueue queue;

	@BeforeEach
	void clean() {
		jdbc.update("DELETE FROM tac_vu_nen");
	}

	@Test
	void enqueueIsDeduplicated() {
		long a = queue.enqueue("DEMO", null, "demo:1", Map.of("x", 1), Instant.now());
		long b = queue.enqueue("DEMO", null, "demo:1", Map.of("x", 1), Instant.now());
		assertThat(b).isEqualTo(a);
		assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM tac_vu_nen", Integer.class)).isEqualTo(1);
	}

	@Test
	void twoWorkers_neverClaimSameJob() {
		for (int i = 0; i < 20; i++) {
			queue.enqueue("DEMO", null, "demo:" + i, Map.of("i", i), Instant.now().minusSeconds(1));
		}
		var w1 = CompletableFuture.supplyAsync(() -> queue.claim("w1", 15));
		var w2 = CompletableFuture.supplyAsync(() -> queue.claim("w2", 15));
		List<Job> all = new java.util.ArrayList<>(w1.join());
		all.addAll(w2.join());
		all.addAll(queue.claim("w3", 20)); // whatever is left

		Set<Long> ids = new HashSet<>();
		all.forEach(j -> ids.add(j.id()));
		assertThat(all).hasSize(20);
		assertThat(ids).hasSize(20); // no job claimed twice
	}

	@Test
	void worker_runsHandler_andRetriesFailures() {
		queue.enqueue("OK", null, "ok:1", Map.of(), Instant.now().minusSeconds(1));
		queue.enqueue("BOOM", null, "boom:1", Map.of(), Instant.now().minusSeconds(1));
		JobWorker worker = new JobWorker(queue, List.of(handler("OK", false), handler("BOOM", true)));

		worker.poll();

		assertThat(status("ok:1")).isEqualTo("SUCCEEDED");
		assertThat(status("boom:1")).isEqualTo("QUEUED"); // attempt 1 of 3 → retry later
		assertThat(jdbc.queryForObject("SELECT ma_loi_gan_nhat FROM tac_vu_nen WHERE khoa_loai_trung = 'boom:1'", String.class))
				.isEqualTo("IllegalStateException");
	}

	private String status(String key) {
		return jdbc.queryForObject("SELECT trang_thai FROM tac_vu_nen WHERE khoa_loai_trung = ?", String.class, key);
	}

	private static JobHandler handler(String type, boolean fail) {
		return new JobHandler() {
			public String type() {
				return type;
			}

			public void handle(Job job) {
				if (fail) {
					throw new IllegalStateException("boom");
				}
			}
		};
	}
}
