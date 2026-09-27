# Known pitfalls (Spring Boot 4.1 / Hibernate 7 / MySQL 8.4) — apply without test-building

| Situation | Do this |
|---|---|
| `@Enumerated(EnumType.STRING)` on a VARCHAR column | Add `@JdbcTypeCode(SqlTypes.VARCHAR)`; otherwise `ddl-auto=validate` expects a MySQL ENUM and fails |
| Entity with assigned `@Id` (1:1 tables keyed by `nguoi_dung_id`) | Use a `Long` (wrapper) `@Version` so a null version means "new" (no extra SELECT). Without a version column, accept the merge SELECT |
| Map only some columns of a table | Allowed: unmapped columns take DB defaults on INSERT; `validate` checks only mapped columns |
| `TEXT` column ↔ `String` | Add `columnDefinition = "TEXT"` |
| `Instant` ↔ `DATETIME(3)` | Works as is (`hibernate.jdbc.time_zone=UTC` is set) |
| Record DTO with a password | Override `toString()` to hide it |
| Trim before validation | Record compact constructor (runs before `@Valid`) |
| Duplicate insert race | Unique key + catch `DataIntegrityViolationException`, check the constraint name (`uk_...`) |
| JDBC + JPA in one method | Same transaction and connection; JPA `IDENTITY` inserts immediately, so FKs from JDBC work |
| Jackson | Boot 4 = Jackson 3 (`tools.jackson.*`), exceptions unchecked; annotations still `com.fasterxml.jackson.annotation.*` |
| Tests on `/auth/register`-like endpoints | Random `setRemoteAddr` per test (IP rate limit lives in Redis across tests); CSRF via `.cookie(new Cookie("XSRF-TOKEN", t)).header("X-CSRF-TOKEN", t)`. **Never** `.with(csrf())`: it swaps the CsrfTokenRepository of the shared test context and breaks `SecurityBaselineTest` |
| Netty/Redis `Unable to establish loopback connection` in tests (Claude's shell only) | Run Maven with `JAVA_TOOL_OPTIONS='-Djdk.net.unixdomain.tmpdir=C:\Users\Public'` (AF_UNIX fails under the short-name temp path) |
| MockMvc JSON filter | `jsonPath("$.fieldErrors[?(@.field == 'x')]").exists()` |
| Time in services | Inject `Clock`, never `Instant.now()` |
| Network in transaction | Never; enqueue via `JobQueue`/`MailService` or `OutboxPublisher` |
- Session cookie: `server.servlet.session.cookie.*` is NOT applied by Spring Session (cookie came out as `SESSION` without HttpOnly). Name/flags live in the `CookieSerializer` bean in `SecurityConfig`.
- `jdbc.queryForObject(..., Instant.class)` fails on DATETIME (returns LocalDateTime); read `java.sql.Timestamp` in tests.
- Boot 4 renamed `spring.session.redis.*` → `spring.session.data.redis.*` (old names are silently ignored → no `FindByIndexNameSessionRepository` bean).
