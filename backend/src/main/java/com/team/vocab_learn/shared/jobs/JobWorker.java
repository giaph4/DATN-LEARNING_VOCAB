package com.team.vocab_learn.shared.jobs;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.team.vocab_learn.shared.errors.ApiException;

import lombok.extern.slf4j.Slf4j;

/** Polls tac_vu_nen and dispatches to {@link JobHandler}s. Active only with profile {@code worker}. */
@Slf4j
@Component
@EnableScheduling
@ConditionalOnProperty(name = "app.worker.enabled", havingValue = "true")
public class JobWorker {

	private static final int BATCH = 10;

	private final JobQueue queue;
	private final Map<String, JobHandler> handlers;
	private final String workerId = "worker-" + UUID.randomUUID().toString().substring(0, 8);

	public JobWorker(JobQueue queue, List<JobHandler> handlers) {
		this.queue = queue;
		this.handlers = handlers.stream().collect(Collectors.toMap(JobHandler::type, Function.identity()));
	}

	@Scheduled(fixedDelayString = "${app.worker.poll-delay:PT2S}")
	public void poll() {
		queue.recoverExpiredLeases();
		queue.claim(workerId, BATCH).forEach(this::run);
	}

	private void run(Job job) {
		JobHandler handler = handlers.get(job.type());
		if (handler == null) {
			queue.markFailed(job, "NO_HANDLER", false);
			return;
		}
		try {
			handler.handle(job);
			queue.markSucceeded(job);
		} catch (Exception e) {
			log.warn("Job {} ({}) attempt {} failed: {}", job.id(), job.type(), job.attempts(), e.toString());
			boolean retryable = !(e instanceof ApiException api) || api.getCode().isRetryable();
			queue.markFailed(job, e.getClass().getSimpleName(), retryable);
		}
	}
}
