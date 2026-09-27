---
name: be-code
description: Implement VocabLearning backend (Spring Boot) features by writing real files into backend/. Use when the user asks to code, build, implement or fix a backend step, endpoint, entity, migration or requirement ID (TK-, BT-, ND-, ON-, LT-, AI-, ST-, TKH-, DL-, PA-, QT-) or a roadmap step (B0.x, B1.x...). Writes code, verifies it compiles/tests, then lists changed files and stops.
argument-hint: "<roadmap step or requirement ID, e.g. B1.1 or TK-01>"
---

# be-code — write runnable backend code into files

Goal: smallest correct code that compiles and passes tests, following the project conventions. Minimal talk.

## Steps

1. **Scope** — Map the request to a roadmap step in `roadmap/ROADMAP_BE.md` and requirement IDs. Read only what is needed:
   - `references/conventions.md` (this skill) — always, once per session.
   - Grep the requirement: `grep -n "<ID>" docs/SCI_SE_3*.md` and the SQL of the touched tables in `docs/SCI_SE_6*.md`.
   - Read existing files you will modify; do not re-read files already in context.
   If a prerequisite step is missing (e.g. `shared/errors` before a controller), build the minimal prerequisite first and say so in one line.

2. **Explain (≤ 5 lines, Vietnamese)** — what is being built, where it sits (module/layer), and the key rule(s) from the docs.

3. **Code**
   - Follow `references/conventions.md` exactly (package layout, Vietnamese DB names ↔ English Java names, ProblemDetail errors, ownership checks, idempotency/version, UTC + Clock).
   - Optimal length: no dead code, no speculative abstractions, no boilerplate comments; comment only non-obvious rules (cite ID, e.g. `// TK-05: token valid 15 min`).
   - Prefer records for DTOs, constructor injection, one responsibility per class.
   - Add or update the test for the requirement (`TC-<ID>-01/02/03` shape) when behavior is added.

4. **Verify** in the real `backend/` only; never copy the project to a scratch/temp folder. Run only the commands below (no full `verify` unless asked):
   - `./mvnw -q -DskipTests compile` must pass.
   - Run the related tests: `./mvnw -q test -Dtest=<TestClass>`. In Codex's shell prefix `JAVA_TOOL_OPTIONS='-Djdk.net.unixdomain.tmpdir=C:\Users\Public'`, else Netty/Redis fails with "Unable to establish loopback connection".
   - Test pitfalls: `.Codex/skills/be-chat/references/pitfalls.md`.
   - If Docker/Testcontainers is unavailable, run unit tests only and state that integration tests were not run.
   - Fix failures before reporting. Never claim success without a passing command.

4a. **UI** — if the step's endpoints feed a screen (check the matching F-step/UI ID in `roadmap/ROADMAP_FE.md`), run the `ui-mockup` skill for those screens. Add one line to the report: `**Mockup:** <paths>`.

4b. **Postman** — if this step added or changed a controller endpoint, run the `postman-sync` skill for this step (registry `postman/postman.json`). Add one line to the report: `**Postman:** +n request (<folder>)`.

5. **Report and stop** — output exactly this, nothing else:

   ```
   **Kết quả:** <1 line: compiled / tests passed (n) / what was not run>

   | File | Vai trò |
   |---|---|
   | backend/src/.../X.java | <what it does, ≤ 12 words> |

   **Tiếp theo:** <next roadmap step, 1 line>
   ```
   Then tick the finished checkbox in `roadmap/ROADMAP_BE.md` if a whole step is done.
   If that was the **last step of a phase** (GĐ0, Đợt 1/2/3…), make the `Tiếp theo` line:
   `Chạy /fe-report <PHASE> để bàn giao cho FE`. End the turn.

## Do not
- Do not paste the written code back into chat.
- Do not refactor unrelated code or add features outside the requested step.
- Do not add dependencies without saying which and why (1 line).
- Do not use `ddl-auto=update`; schema changes go into a new Flyway `V<n>__*.sql`.
