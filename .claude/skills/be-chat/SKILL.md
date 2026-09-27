---
name: be-chat
description: Teach and show VocabLearning backend (Spring Boot/Java) code directly in the chat without writing files. Use when the user wants to learn, understand, or hand-type the code themselves — e.g. "explain and code in chat", "hướng dẫn code", "giải thích rồi code", or asks how a backend step/requirement works. Detailed explanation first, then compact code blocks, token-efficient.
argument-hint: "<topic, roadmap step or requirement ID>"
---

# be-chat — explain in depth, then code in chat

The user types the code themselves. Do not create or edit files. Answer in Vietnamese; code and identifiers in English/Java; DB names Vietnamese (per SQL).

**No trial builds.** Never copy the project to a scratch/temp folder, never write trial files, never run Maven or tests to "check" the code before answering. They cost too many tokens.
Get correctness by reading instead:
- read the exact SQL of the touched tables (column names, types, lengths, CHECK values);
- read the existing shared classes you call (signatures);
- follow the known pitfalls in `references/pitfalls.md`.
The user compiles and runs the tests; if something fails, they paste the error and you fix it.

## Before answering
- Read `.claude/skills/be-code/references/conventions.md` once per session.
- Grep only the needed doc lines: requirement in `docs/SCI_SE_3*.md`, tables in `docs/SCI_SE_6*.md`, API in `docs/THIET_KE_HE_THONG_HOC_TU_VUNG.md` §11. If the user has existing code, read just the files involved.

## Answer structure (in this order, no extra sections)

1. **Mục tiêu** — 1–2 lines: what we build and which ID/roadmap step it satisfies.
2. **Giải thích** — as detailed as needed, but dense:
   - Concepts the user must understand (e.g. "What is @Transactional", "why optimistic locking"), each in 1–3 sentences.
   - Flow as a numbered list or a small table (request → controller → service → repository → DB).
   - Business rules from the docs that the code enforces, each tagged with its ID.
   - Pitfalls (1 line each).
3. **Code** — one fenced block per file, preceded by its path in bold:
   **`backend/src/main/java/com/team/vocab_learn/identity/api/AuthController.java`**
   - Complete and compilable for new files; for existing files show only the changed method/section with `// ...` around it.
   - Comments only where they teach something non-obvious (short, Vietnamese allowed).
   - Order files bottom-up: migration → entity → repository → service → DTO → controller → test.
4. **Chạy thử** — 1–3 commands (e.g. `./mvnw -q test -Dtest=...`, a `curl`), plus expected result in 1 line.
5. **Bạn tự làm tiếp** (optional) — 1–2 small exercises or next roadmap step.

## Token discipline
- Do not restate the question, do not summarize at the end, no filler ("Great question", "Hope this helps").
- Never repeat a code block already shown in this conversation; reference it by path.
- Prefer tables over long paragraphs; skip explanations of trivial Java syntax unless asked.
- If the topic is large (> ~200 lines of code), split into parts and deliver part 1, ending with one line: "Gõ 'tiếp' để sang phần 2: <name>".
