# VocabLearning — project guide for Codex

Vocabulary-learning website: `backend/` Spring Boot 4 (Java 21, MySQL 8.4, Redis, S3) · `frontend/` Next.js (JavaScript, Vietnamese routes).
Specs in `docs/` (SCI_SE_01…7, THIET_KE). Plans in `roadmap/ROADMAP_BE.md`, `roadmap/ROADMAP_FE.md`. Run guides in `huong-dan/` (Docker, FE↔BE). Requirement IDs: TK, BT, ND, ON, LT, AI, ST, TKH, DL, PA, QT.

## Response rules (save tokens)
- Reply in Vietnamese; code, identifiers, commit messages in English.
- Answer first, no preamble, no restating the question, no closing summary or offers.
- Only what the user needs: result, key reason, next step. Bullets/tables over paragraphs.
- Do not paste code you already wrote to files; list files instead.
- Ask a question only when blocked; otherwise pick the sensible default and state it in one line.

## Reading rules (save tokens)
- Never read a whole `docs/*.md` (they are 50–350 KB). Grep by ID or heading, then read the matching range.
- Prefer `graphify query "<question>"` when `graphify-out/graph.json` exists.
- Skip build output: `backend/target`, `frontend/.next`, `node_modules`.

## Skills
- `/be-code <step|ID>` — write backend code into files, verify, list files, stop.
- `/be-chat <topic|ID>` — explain in depth and show code in chat (no file writes).
- `/ui-brief` — load UI requirements (`frontend/design/YEU_CAU_GIAO_DIEN.md` + `mockups/shared/tokens.css`) before any UI work.
- `/ui-mockup <step|UI id|phase>` — HTML/CSS/JS mockup in `mockups/<phase>/`, wired to the real API.
- `/fe-from-mockup <mockup path>` — Next.js page/components identical to the mockup.
- `frontend-design` — Anthropic's design craft skill (brief in `design/` always wins).
- `/postman-sync <step|ID|all>` — add/update endpoints in Postman collection "VocabLearning API" (IDs in `postman/postman.json`).
- `/fe-report <PHASE>` — after a BE phase: verified API handoff report for FE in `report/<PHASE>_BAO_CAO_FE.md`.
- `/graphify` — build/query the knowledge graph of this repo (`graphify-out/`).

## Backend conventions
See `.Codex/skills/be-code/references/conventions.md` (load only for backend work).
