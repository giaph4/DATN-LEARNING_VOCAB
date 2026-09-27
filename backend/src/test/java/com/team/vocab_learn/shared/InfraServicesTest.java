package com.team.vocab_learn.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.team.vocab_learn.AbstractIntegrationTest;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.jobs.JobQueue;
import com.team.vocab_learn.shared.jobs.JobWorker;
import com.team.vocab_learn.shared.mail.MailService;
import com.team.vocab_learn.shared.mail.SendEmailJobHandler;
import com.team.vocab_learn.shared.security.RateLimiter;
import com.team.vocab_learn.shared.storage.StorageService;

/** B0.5/B0.6: S3 presigned upload/download on MinIO, mail via job → Mailpit, Redis rate limit. */
class InfraServicesTest extends AbstractIntegrationTest {

	private final HttpClient http = HttpClient.newHttpClient();

	@Autowired
	StorageService storage;

	@Autowired
	MailService mail;

	@Autowired
	JobQueue queue;

	@Autowired
	SendEmailJobHandler mailHandler;

	@Autowired
	RateLimiter rateLimiter;

	@Test
	void storage_presignedPutThenGet() throws Exception {
		String key = "test/" + UUID.randomUUID() + ".txt";
		byte[] body = "hello vocab".getBytes();
		var upload = storage.presignUpload(key, "text/plain", body.length);

		var put = http.send(HttpRequest.newBuilder(upload.url()).header("Content-Type", "text/plain")
				.PUT(HttpRequest.BodyPublishers.ofByteArray(body)).build(), HttpResponse.BodyHandlers.ofString());
		assertThat(put.statusCode()).isEqualTo(200);
		assertThat(storage.head(key)).hasValueSatisfying(o -> assertThat(o.size()).isEqualTo(body.length));

		var get = http.send(HttpRequest.newBuilder(storage.presignDownload(key)).build(), HttpResponse.BodyHandlers.ofString());
		assertThat(get.body()).isEqualTo("hello vocab");

		storage.delete(key);
		assertThat(storage.head(key)).isEmpty();
	}

	@Test
	void mail_queuedJobArrivesInMailpit() throws Exception {
		String to = "learner-" + UUID.randomUUID() + "@test.local";
		long jobId = mail.send("verify-email", to, Map.of("displayName", "Lan", "link", "http://localhost:3000/xac-thuc?t=abc"));

		// Hàng đợi có thể còn email do test đăng ký tạo ra: poll đến khi tới lượt job này
		JobWorker worker = new JobWorker(queue, List.of(mailHandler));
		for (int i = 0; i < 20 && "QUEUED".equals(
				jdbc.queryForObject("SELECT trang_thai FROM tac_vu_nen WHERE id = ?", String.class, jobId)); i++) {
			worker.poll();
		}

		var res = http.send(HttpRequest.newBuilder(URI.create(mailpitApi() + "/search?query=to:" + to)).build(),
				HttpResponse.BodyHandlers.ofString());
		assertThat(res.body()).contains("\"messages_count\":1").contains("Xác thực email VocabLearning");
	}

	@Test
	void rateLimit_sixthLoginAttemptPerAccountIsRejected() {
		String subject = UUID.randomUUID() + "@test.local";
		for (int i = 0; i < 5; i++) {
			rateLimiter.check(RateLimiter.Policy.LOGIN_ACCOUNT, subject);
		}
		assertThatThrownBy(() -> rateLimiter.check(RateLimiter.Policy.LOGIN_ACCOUNT, subject))
				.extracting("code").isEqualTo(ErrorCode.RATE_LIMITED);
	}
}
