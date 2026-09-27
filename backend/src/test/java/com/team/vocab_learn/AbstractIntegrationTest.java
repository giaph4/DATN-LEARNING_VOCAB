package com.team.vocab_learn;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.mysql.MySQLContainer;

/**
 * Base for API/DB tests: one Spring context + real MySQL 8.4, Redis, MinIO, Mailpit (started once per JVM).
 * Needs Docker running.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

	protected static final MySQLContainer MYSQL = new MySQLContainer("mysql:8.4")
			.withDatabaseName("vocab_learning")
			.withUrlParam("connectionTimeZone", "UTC")
			.withUrlParam("characterEncoding", "UTF-8");

	protected static final GenericContainer<?> REDIS = new GenericContainer<>("redis:7-alpine")
			.withExposedPorts(6379);

	protected static final GenericContainer<?> MINIO = new GenericContainer<>("minio/minio:latest")
			.withCommand("server", "/data")
			.withEnv("MINIO_ROOT_USER", "minioadmin")
			.withEnv("MINIO_ROOT_PASSWORD", "minioadmin")
			.withExposedPorts(9000)
			.waitingFor(Wait.forHttp("/minio/health/live").forPort(9000));

	protected static final GenericContainer<?> MAILPIT = new GenericContainer<>("axllent/mailpit:latest")
			.withExposedPorts(1025, 8025)
			.waitingFor(Wait.forHttp("/api/v1/messages").forPort(8025));

	static {
		MYSQL.start();
		REDIS.start();
		MINIO.start();
		MAILPIT.start();
	}

	@DynamicPropertySource
	static void containerProperties(DynamicPropertyRegistry r) {
		r.add("spring.datasource.url", MYSQL::getJdbcUrl);
		r.add("spring.datasource.username", MYSQL::getUsername);
		r.add("spring.datasource.password", MYSQL::getPassword);
		r.add("spring.data.redis.host", REDIS::getHost);
		r.add("spring.data.redis.port", () -> REDIS.getMappedPort(6379));
		String minio = "http://" + MINIO.getHost() + ":" + MINIO.getMappedPort(9000);
		r.add("app.storage.endpoint", () -> minio);
		r.add("app.storage.public-endpoint", () -> minio);
		r.add("spring.mail.host", MAILPIT::getHost);
		r.add("spring.mail.port", () -> MAILPIT.getMappedPort(1025));
	}

	@Autowired
	protected MockMvc mvc;

	@Autowired
	protected JdbcTemplate jdbc;

	protected static String mailpitApi() {
		return "http://" + MAILPIT.getHost() + ":" + MAILPIT.getMappedPort(8025) + "/api/v1";
	}

	/** Inserts an ACTIVE user (needed by FKs) and returns its id. */
	protected long createUser() {
		String email = "u-" + UUID.randomUUID() + "@test.local";
		GeneratedKeyHolder keys = new GeneratedKeyHolder();
		jdbc.update(con -> {
			var ps = con.prepareStatement(
					"INSERT INTO nguoi_dung (email, email_chuan_hoa, trang_thai, created_at) VALUES (?, ?, 'ACTIVE', ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, email);
			ps.setString(2, email);
			ps.setTimestamp(3, Timestamp.from(Instant.now()));
			return ps;
		}, keys);
		return keys.getKey().longValue();
	}
}
