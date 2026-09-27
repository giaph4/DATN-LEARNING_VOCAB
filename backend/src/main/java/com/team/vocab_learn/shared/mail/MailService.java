package com.team.vocab_learn.shared.mail;

import java.time.Clock;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.team.vocab_learn.shared.jobs.JobQueue;

import lombok.RequiredArgsConstructor;

/** Queues an email as a SEND_EMAIL job (no SMTP call inside the caller's DB transaction). */
@Service
@RequiredArgsConstructor
public class MailService {

	public static final String JOB_TYPE = "SEND_EMAIL";

	public record MailPayload(String template, String to, Map<String, String> model) {
	}

	private final JobQueue jobs;
	private final Clock clock;

	/** @param template file name in {@code templates/mail/} without ".html" */
	public long send(String template, String to, Map<String, String> model) {
		return jobs.enqueue(JOB_TYPE, null, "mail:" + UUID.randomUUID(), new MailPayload(template, to, model),
				clock.instant());
	}
}
