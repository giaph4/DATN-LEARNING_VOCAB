# VocabLearning backend conventions (shared by `be-code` and `be-chat`)

Source of truth: `docs/` (SCI_SE_3 requirements, SCI_SE_6 SQL, THIET_KE sections 11–13) and `roadmap/ROADMAP_BE.md`.
Read only the section you need: `grep -n "<ID>" docs/SCI_SE_3*.md` — never load whole docs.

## Stack
Java 21 (`release 21`), Spring Boot 4.1.x, Spring MVC, Spring Security 7, Spring Data JPA (Hibernate 7) + JdbcTemplate for heavy SQL,
Flyway, MySQL 8.4, Redis (Spring Session, rate limit), S3/MinIO (AWS SDK v2), JUnit 5 + Testcontainers.
Boot 4 ships Jackson 3 (`tools.jackson.*`); annotations stay `com.fasterxml.jackson.annotation.*`. Use `jakarta.*`, never `javax.*`.

## Layout
`backend/src/main/java/com/team/vocab_learn/<module>/{api,application,domain,infrastructure}`
Modules: identity, content, catalog, learning, practice, personalization, stats, ai, speaking, engagement, administration.
`shared/`: config, security, errors, web, idempotency, outbox, jobs, storage, mail, time.
Rule: a module calls another module only through its `application` service; never another module's repository.

## Naming
- DB tables/columns: Vietnamese without accents, exactly as in SCI_SE_6 SQL (`bo_the`, `chu_so_huu_id`, `thoi_diem_xoa`).
- **Entity class/field = DB name in camelCase** (`BoThe` ↔ `bo_the`, `chuSoHuuId` ↔ `chu_so_huu_id`, `createdAt` ↔ `created_at`). Spring's snake-case naming strategy maps them automatically: no `@Table`/`@Column(name=…)`. `ddl-auto=validate` catches typos at startup. Repository = `<Entity>Repository` (`NguoiDungRepository`). Enum type of an entity field is named like the column concept (`TrangThaiNguoiDung`); enum values stay English = SQL CHECK lists.
- DTO/JSON = API contract in English camelCase (THIET_KE §11, e.g. `displayName`). Where DTO and entity names differ, map in MapStruct with `@Mapping(target="tenHienThi", source="displayName")`; name DTO fields like the entity only when the contract allows it.
- Services/controllers/packages stay English (`RegistrationService`, `identity`).
- GĐ0 legacy: `catalog.domain.Topic` still uses the old English style; rename to `ChuDe` when that module is next touched.
- API: `/api/v1/...` English paths (THIET_KE §11). IDs serialized as strings.
- Enums stored as `VARCHAR` with `@Enumerated(EnumType.STRING)`, values identical to SQL CHECK lists.

## Code shape (keep it short)
- Entity: fields + `@Version` on `phien_ban` when the table has it. Lombok `@Getter` + `@NoArgsConstructor(access = PROTECTED)`; no `@Data`/`@Setter` on entities (state changes via named methods). IDs are boxed `Long` (serialized as JSON strings); counts use primitive `int`/`long` (stay numbers).
- DTO: Java `record` with Bean Validation (`@NotBlank @Size(max=150)`), `@Builder` when it has > 4 fields; one file per request/response or nested in controller when tiny.
- Mapping: MapStruct interface `<Entity>Mapper` in `api/` with plain `@Mapper` (componentModel=spring and unmappedTargetPolicy=ERROR are set in pom); use `@Mapping(target=..., ignore=true|expression=...)` for fields not on the entity. No hand-written mapping loops.
- Lombok: `@RequiredArgsConstructor` for injection, `@Slf4j` for logs, `@Getter` on enums/exceptions.
- Shared building blocks (reuse, don't re-implement): `ApiException`/`ErrorCode`, `CurrentUser`, `RateLimiter.Policy`, `IdempotencyService`, `OutboxPublisher`, `JobQueue`/`JobHandler`, `StorageService`, `MailService`, `LocalDayService`, `PageQuery`/`PageResponse`/`CursorResponse`, `ETags`.
- Controller: thin — validate, call service, map to DTO. No business logic, no repositories.
- Service: `@Transactional` boundaries; ownership check first (`requireOwner(deck, currentUser)`) → 404 for other users' private data.
- Repository: Spring Data interface; custom SQL via `@Query` or JdbcTemplate for queues/aggregates (`FOR UPDATE SKIP LOCKED`).
- Time: inject `Clock`; store UTC `Instant` in `DATETIME(3)`; local day via `LocalDayService`.
- No network calls (AI, mail, S3, Speech) inside a DB transaction → enqueue a `tac_vu_nen` job or publish outbox event.

## Errors
Throw `ApiException(ErrorCode.X, "Vietnamese detail")`; `GlobalExceptionHandler` returns `application/problem+json`:
`{type, title, status, code, detail, traceId, fieldErrors, retryable}`.
Status map: 400/422 validation, 401 unauthenticated, 403 forbidden, 404 not found (also for foreign private resources),
409 conflict/duplicate/idempotency mismatch, 412 stale version, 429 quota/rate limit, 503 dependency down.
User-facing `detail` in Vietnamese; codes in English UPPER_SNAKE.

## Write safety
- Important writes accept `Idempotency-Key` (reviews, submit, copy, CSV commit, AI create/commit): use `IdempotencyService`.
- Updates take `If-Match` / `expectedVersion`; mismatch → 412.
- Lock order for review writes: user → daily quota → session/item → progress → XP ledger.

## Security
Session cookie (Redis), CSRF header `X-CSRF-TOKEN`, BCrypt, `@PreAuthorize("hasRole('ADMIN')")` for `/admin/**`.
Never trust `userId`, dates, scores or XP from the client. Never log passwords, tokens, API keys, signed URLs.

## Tests
- Pure logic (SRS, grading, normalization, streak): plain JUnit with fixed `Clock`.
- API/DB: `@SpringBootTest` + MockMvc on Testcontainers MySQL/Redis (`AbstractIntegrationTest`).
- Name tests after requirement IDs: `TK01RegisterTest` with methods `tc01_happyPath`, `tc02_invalidAndEdge`, `tc03_permissionAndConcurrency`.

## Commands (run from `backend/`)
- Compile fast: `./mvnw -q -DskipTests compile`
- One test: `./mvnw -q test -Dtest=ClassName`
- All: `./mvnw -q verify`
- Dev services: `docker compose up -d` (MySQL on host port 3307, Redis 6379, MinIO 9000/9001, Mailpit 1025/8025)
- Full stack incl. API container: `docker compose --profile app up -d --build`
- Tests extend `AbstractIntegrationTest` (Testcontainers: MySQL, Redis, MinIO, Mailpit — Docker must run).
On Windows PowerShell use `.\mvnw.cmd`. Guides: `huong-dan/01_DOCKER_BACKEND.md`, `huong-dan/02_FE_CHAY_BACKEND.md`.
