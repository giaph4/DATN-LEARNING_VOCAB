# ROADMAP FRONTEND — VocabLearning (Next.js, JavaScript)

> Đối chiếu: `docs/SCI_SE_5` (42 màn hình UI01–UI42, trường, trạng thái), `SCI_SE_3` (tiêu chí từng ID), `THIET_KE_HE_THONG_HOC_TU_VUNG.md` mục 5 (tương tác) và 11 (API), `SCI_SE_2` (lịch).
> Phạm vi: từ khởi tạo đến **bản demo/staging** (chưa production). Làm song song với `ROADMAP_BE.md`: dùng **API giả (MSW)** khi BE chưa xong, chuyển sang API thật ở cuối mỗi đợt.
> Cách đọc: giai đoạn → bước (F) → màn hình (UI), đường dẫn, API, trạng thái cần có, "xong khi".

---

## 0. Tổng quan một trang

| Giai đoạn | Thời gian | Màn hình | Yêu cầu |
|---|---|---|---|
| **GĐ0 — Nền tảng** | 28/09 – 11/10 | Khung ứng dụng, bộ thành phần UI, lớp gọi API, xác thực | PC01, PC02, PC04 |
| **Đợt 1** | 12/10 – 25/10 | UI01–UI14 | TK, BT, ND |
| **Đợt 2** | 26/10 – 08/11 | UI15–UI22, UI27–UI31, phần phân quyền UI37 | ON, LT, ST, TKH, QT-02 |
| **Đợt 3** | 09/11 – 29/11 | UI23–UI26, UI32–UI42 | AI, DL, PA, QT |
| **GĐ4 — Kiểm thử** | 30/11 – 06/12 | Toàn bộ, 5 kích thước màn hình, E2E | TR16, PC01, PC02 |
| **GĐ5 — Hoàn thiện** | 07/12 – 14/12 | Build staging, hướng dẫn | PC10 |

### Công nghệ chốt

| Thành phần | Lựa chọn | Ghi chú |
|---|---|---|
| Khung | Next.js App Router, **JavaScript** (không TypeScript) | `jsconfig.json` alias `@/*`; kiểu mô tả bằng JSDoc khi cần |
| Giao diện | Tailwind CSS + bộ thành phần tự viết trong `src/components/ui` | Nền sáng, tương phản WCAG AA |
| Dữ liệu máy chủ | TanStack Query | Không đưa dữ liệu server vào store toàn cục |
| Biểu mẫu | React Hook Form + Zod | Server vẫn kiểm tra lại |
| Biểu đồ | Recharts (hoặc tương đương) | Có bảng dữ liệu thay thế cho trình đọc màn hình |
| API giả | MSW (Mock Service Worker) | Theo đúng hợp đồng mục 11 |
| Kiểm thử | Vitest + Testing Library (đơn vị), Playwright (E2E) | Kích thước 360/390/768/1280/1440 px |

### Quy ước bắt buộc

1. **Đường dẫn trang tiếng Việt không dấu** đúng bảng UI của `SCI_SE_5` (`/dang-ky`, `/thu-vien`, `/hoc`…). API giữ tiếng Anh dưới `/api/v1`.
2. Gọi API **cùng tên miền**: `next.config.mjs` rewrite `/api/:path*` → `BACKEND_INTERNAL_URL` (dev `http://localhost:8080`) để cookie phiên và CSRF hoạt động.
3. Mọi thao tác ghi gửi `X-CSRF-TOKEN` (lấy từ `GET /api/v1/auth/csrf`); thao tác quan trọng gửi `Idempotency-Key` (UUID, giữ nguyên khi thử lại).
4. ID là **chuỗi**; thời điểm UTC, hiển thị theo múi giờ người dùng.
5. Mỗi màn hình đủ 4 trạng thái: **tải / rỗng / lỗi / thành công**; xử lý 401 (về đăng nhập, giữ đường dẫn quay lại), 403/404 (không lộ tài nguyên), 409/412 (tải phiên bản mới), 429 (nêu thời điểm thử lại), 503.
6. Ẩn nút theo quyền chỉ để trải nghiệm; quyền thật do BE kiểm.
7. Không có khóa bí mật trong mã FE; không dùng biến `NEXT_PUBLIC_*` cho khóa.

---

## 1. Cấu trúc thư mục đích

```text
frontend/
├─ next.config.mjs            # rewrites /api → backend, headers bảo mật
├─ jsconfig.json              # alias @/*
├─ .env.example               # BACKEND_INTERNAL_URL, NEXT_PUBLIC_API_BASE=/api/v1
├─ src/
│  ├─ app/
│  │  ├─ layout.js, globals.css, not-found.js, error.js
│  │  ├─ (public)/            # /, /kham-pha, /bo-the/[id] (xem trước)
│  │  ├─ (auth)/              # /dang-ky, /dang-nhap, /xac-thuc-email, /quen-mat-khau, /dat-lai-mat-khau
│  │  ├─ (learner)/           # /hoc, /thu-vien, /bo-the/..., /luyen-tap/..., /ai/..., /so-tay, /diem-yeu,
│  │  │                       # /goi-y, /thong-ke, /bao-cao-tuan, /thanh-tich, /thu-thach, /thong-bao,
│  │  │                       # /phat-am/..., /ho-so, /cai-dat/..., /tien-do
│  │  └─ quan-tri/            # /quan-tri/tai-khoan, noi-dung, bao-cao, ai, tong-quan, nhat-ky
│  ├─ features/               # auth, decks, study, practice, ai, notebook, speaking, stats, engagement, admin
│  │  └─ <feature>/api.js, hooks.js, schemas.js, components/
│  ├─ components/ui/          # Button, Input, Select, Dialog, Toast, Table, Pagination, EmptyState, ErrorState, Skeleton…
│  ├─ lib/
│  │  ├─ api-client.js        # fetch + CSRF + Idempotency-Key + chuyển ProblemDetail thành lỗi
│  │  ├─ csrf.js, date-time.js, query-client.js
│  │  └─ schemas/             # lược đồ Zod dùng chung
│  └─ mocks/                  # MSW handlers theo từng feature
└─ tests/
   ├─ unit/                   # Vitest
   └─ e2e/                    # Playwright
```

---

## 2. GĐ0 — Nền tảng (28/09 – 11/10)

| Bước | Việc | Xong khi |
|---|---|---|
| F0.1 Khởi tạo | `npx create-next-app@latest frontend --js --app --src-dir --tailwind --eslint --import-alias "@/*"`; khóa phiên bản Node LTS trong `.nvmrc` | `npm run dev` mở được trang |
| F0.2 Proxy API | `next.config.mjs` rewrites `/api/:path*`; header `Referrer-Policy`, `X-Content-Type-Options` | Gọi `/api/v1/auth/csrf` qua Next tới BE |
| F0.3 Lớp API | `api-client.js`: `apiGet/apiPost/apiPatch/apiPut/apiDelete`; tự lấy/gắn CSRF; `credentials: 'include'`; chuyển lỗi ProblemDetail thành `ApiError{status, code, detail, fieldErrors, retryable}` | Test đơn vị cho 401/409/422/429 |
| F0.4 TanStack Query | `QueryClientProvider`; khóa cache gồm mã người dùng; xóa cache khi đăng xuất | Đăng xuất không còn dữ liệu người cũ |
| F0.5 MSW | Bật khi `NEXT_PUBLIC_API_MOCK=1`; handlers trả đúng mẫu API mục 11 | Có dữ liệu giả cho từng đợt |
| F0.6 Bộ UI | Button, Input, Textarea, Select, Checkbox, Toggle, Dialog (giữ focus), Toast, Table→thẻ trên điện thoại, Pagination, Skeleton, EmptyState, ErrorState (nút thử lại), Badge | Trang `/_ui` (chỉ dev) hiển thị đủ |
| F0.7 Bố cục | 2 bố cục (mốc 1024 px): máy tính header + menu trái; điện thoại thanh trên + thanh điều hướng dưới 5 mục (`mockups/gd0/bo-cuc.html`); layout `(auth)` 2 cột/1 cột, vùng thông báo `aria-live`; route group `(learner)` kiểm phiên bằng `GET /users/me` | Chưa đăng nhập vào `/hoc` → chuyển `/dang-nhap?quay-lai=/hoc` |
| F0.8 Kiểm thử | Vitest + Testing Library; Playwright với 5 viewport | `npm test` và `npx playwright test` chạy |

**Mốc GĐ0:** khung chạy, gọi được BE thật (CSRF) hoặc MSW, bộ UI sẵn.

---

## 3. Đợt 1 — Tài khoản & nội dung (12/10 – 25/10)

### Tuần 1: xác thực & cài đặt

| Bước | UI | Đường dẫn | API chính | Nội dung / trạng thái | ID |
|---|---|---|---|---|---|
| F1.1 | UI01 Đăng ký | `/dang-ky` | `POST /auth/register` | email, tên hiển thị 2–100, mật khẩu 10–128, xác nhận, đồng ý điều khoản; lỗi theo trường; thành công → màn chờ xác thực | TK-01 |
| F1.2 | UI02 Xác thực email | `/xac-thuc-email?token=` | `POST /auth/verify-email`, `POST /auth/resend-verification` | Tự gửi mã từ liên kết; nút gửi lại có đếm ngược; mã hết hạn/đã dùng | TK-02 |
| F1.3 | UI03 Đăng nhập | `/dang-nhap` | `POST /auth/login`, `/oauth2/authorization/google` | Nút Google; Facebook ẩn (mở rộng); lỗi khóa/chưa xác thực | TK-03 |
| F1.4 | UI04 Quên/đặt lại | `/quen-mat-khau`, `/dat-lai-mat-khau` | `POST /auth/forgot-password`, `POST /auth/reset-password` | Thông báo chung; đổi xong về đăng nhập | TK-05 |
| F1.5 | UI05 Hồ sơ | `/ho-so` | `GET /users/me`, `PATCH /users/me/profile`, `/media/upload-intents`, `GET /users/me/streak`, `GET /users/me/activity`, `POST /auth/logout` | Tên, giới thiệu ≤500, ảnh ≤5 MB (xem trước), **chuỗi ngày + lịch sử hoạt động** (rỗng: "Chưa có dữ liệu") | TK-06, TK-10 |
| F1.6 | UI06 Thiết lập học và nhắc | `/cai-dat` | `PATCH /users/me/profile`, `PUT /users/me/interests`, `GET/PUT /users/me/preferences`, `GET /topics` | Lần đầu chạy **theo bước**: trình độ A1–C2 → mục tiêu/chủ đề → từ mới 0–100, phút 1–240 → giờ nhắc/múi giờ IANA/email | TK-07, TK-08, TK-09 |
| F1.6b | UI06 Bảo mật (tab trong Cài đặt) | `/cai-dat/bao-mat` | `GET /auth/sessions`, `DELETE /auth/sessions/{id}`, `POST /auth/reauthenticate`, `GET /auth/identities`, `POST /auth/identities/google/link`, `DELETE /auth/identities/google` | Phương thức đăng nhập (liên kết/gỡ Google, chặn gỡ cách cuối), thiết bị đang đăng nhập, hộp thoại xác thực lại 5 phút; đọc `?lien-ket=google`/`?loi=` | TK-03, TK-05 |
| F1.7 | UI07 Xóa tài khoản | `/cai-dat/xoa-tai-khoan` | `POST /auth/reauthenticate`, `POST /users/me/deletion-requests`, `GET …/current`, `POST …/{id}/cancel` | Xác thực lại, hộp thoại xác nhận hậu quả, mốc 7 ngày, nút hủy | TK-11 |

### Tuần 2: bộ thẻ, thẻ, CSV, danh mục

| Bước | UI | Đường dẫn | API chính | Nội dung / trạng thái | ID |
|---|---|---|---|---|---|
| F1.8 | UI08 Danh mục và thư viện | `/thu-vien` | `GET /library/decks`, `PUT/DELETE /decks/{id}/favorite` | Tìm, lọc chủ đề/trình độ/nguồn, lọc yêu thích, sắp xếp, phân trang ≤100 | BT-06, BT-10 |
| F1.9 | UI09 Chi tiết/xem trước bộ | `/bo-the/[id]` | `GET /decks/{id}` hoặc `GET /catalog/decks/{id}`, `POST /decks/{id}/copy`, `POST /reports` | Nhãn nguồn ADMIN/USER, số thẻ, ≤10 thẻ cho khách; nút Học/Sao chép/Báo cáo theo quyền | BT-07, BT-12, ND-04…06 |
| F1.10 | UI10 Tạo và sửa bộ | `/bo-the/moi`, `/bo-the/[id]/sua` | `POST /decks`, `PATCH /decks/{id}` (If-Match), `POST /decks/{id}/publication` | Tên 1–150, mô tả, chủ đề, trình độ, công khai → "Chờ kiểm duyệt"; 412 → tải bản mới | BT-01, BT-04, BT-05 |
| F1.11 | UI11 Danh sách thẻ | `/bo-the/[id]/the` | `GET /decks/{id}/cards`, `DELETE /cards/{id}` | Tìm thẻ, lọc nhãn, xóa có xác nhận | BT-02 |
| F1.12 | UI12 Tạo và sửa thẻ | `/bo-the/[id]/the/moi`, `/the/[id]/sua` | `POST /decks/{id}/cards`, `PATCH /cards/{id}`, `POST /decks/{id}/duplicate-check`, `PUT /cards/{id}/tags`, upload tệp | Từ, từ loại, nghĩa, IPA, nhiều ví dụ + bản dịch, ảnh, audio từ/ví dụ, độ khó 1–5, nhãn; cảnh báo trùng khi rời ô | BT-02, BT-03, BT-04, BT-09 |
| F1.13 | UI13 Nhập/xuất CSV và chia sẻ | `/bo-the/[id]/du-lieu` | `POST /decks/{id}/imports`, `GET /imports/{id}`, `GET /imports/{id}/rows`, `POST /imports/{id}/commit`, `GET /decks/{id}/export`, share-links | Tải tệp → xem trước lỗi theo dòng → chọn dòng → xác nhận; cảnh báo CSV V1 chỉ xuất ví dụ đầu; tạo/thu hồi liên kết (mã hiện 1 lần) | BT-08, BT-11 |
| F1.14 | UI14 Bộ nội dung theo chủ đề | `/kham-pha` | `GET /catalog/decks`, `GET /topics` | Giao tiếp/TOEIC theo trình độ; mô tả, số thẻ; khách xem được | ND-01…03, ND-05 |
| F1.15 | Trang chủ | `/` | `GET /catalog/decks` | Giới thiệu + bộ nổi bật + nút đăng ký | — |

**Mốc Đợt 1:** luồng khách → đăng ký → xác thực → thiết lập → tạo bộ/thẻ → nhập CSV → sao chép bộ mẫu chạy với **API thật**.

---

## 4. Đợt 2 — Học & luyện tập (26/10 – 08/11)

| Bước | UI | Đường dẫn | API chính | Nội dung / tương tác | ID |
|---|---|---|---|---|---|
| F2.1 | UI15 Trang học và hàng đợi | `/hoc` | `GET /study/overview`, `GET /recommendations`, `GET /users/me/streak`, `POST /study/sessions` | Trang chính sau đăng nhập: số đến hạn/quá hạn/mới, hạn mức còn lại, chuỗi ngày, gợi ý có lý do; chọn bộ, chiều EN_VI/VI_EN, quỹ thời gian 5/10/15/30 hoặc 1–240 phút | ON-03, ON-08, ON-13 |
| F2.2 | UI16 Phiên lật thẻ | `/hoc/phien/[id]` | `GET /study/sessions/{id}`, `POST …/items/{itemId}/reveal`, `POST …/reviews`, `POST …/heartbeat` | **Space** lật, **1–4** Quên/Khó/Nhớ/Dễ (chỉ sau khi lật), **Enter** tiếp; tắt phím khi đang nhập/hộp thoại; nút nghe từ/ví dụ (không tự phát chồng); gửi kèm `eventKey` + `expectedProgressVersion`; mất mạng → "Chưa lưu" + thử lại cùng khóa; heartbeat khi tab hiển thị; `prefers-reduced-motion` | ON-01, ON-02, ON-04, ON-05, ON-09 |
| F2.3 | UI17 Tổng kết phiên | `/hoc/phien/[id]/tong-ket` | `GET …/summary`, `POST …/complete` | Số thẻ ôn, từ mới, mức nhớ, thời gian hoạt động | ON-11 |
| F2.4 | UI18 Tiến độ & học lại sau nghỉ | `/tien-do` | `GET /reviews`, `PUT /progress/{id}/suspension`, `POST /progress/{id}/reset`, `GET /study/recovery-plan` | Tạm ngưng/khôi phục, đặt lại có xác nhận, kế hoạch chia nợ 7–14 ngày | ON-06, ON-07, ON-12, ON-14 |
| F2.5 | UI19 Chọn bài luyện | `/luyen-tap` | `POST /practice/sets`, `POST /practice/sets/{id}/attempts` | Chọn bộ, dạng (9 dạng), số câu, nguồn bộ/lỗi/đến hạn; báo số câu khả dụng | LT-01…07, LT-09 |
| F2.6 | UI20 Làm bài | `/luyen-tap/lan-lam/[id]` | `GET /practice/attempts/{id}`, `PUT …/answers/{questionId}`, `POST …/submit` | Thành phần cho từng dạng: trắc nghiệm, nghe viết (audio), điền chỗ trống, **ghép cặp (nhấn chọn + bàn phím, không bắt buộc kéo thả)**, nhập từ, cặp dễ nhầm, viết câu; tự lưu nháp; khóa sau nộp | LT-01…10 |
| F2.7 | UI21 Kết quả | `/luyen-tap/lan-lam/[id]/ket-qua` | `GET …/results`, `POST …/retry-wrong` | Đáp án đúng, giải thích; bài viết AI hiện trạng thái "Chưa đánh giá" riêng | LT-11 |
| F2.8 | UI22 Lịch sử bài làm | `/luyen-tap/lich-su` | `GET /practice/attempts` | Điểm, thời gian, luyện lại câu sai | LT-12 |
| F2.9 | UI27 Sổ tay từ khó | `/so-tay` | `GET/POST /notebook/entries`, `PATCH/DELETE …`, `…/accept`, `…/dismiss` | Lọc theo nhóm lỗi (nghĩa/chính tả/nghe/sử dụng), đề xuất chờ chấp nhận, tạo bài từ từ đã chọn | ST-01…03, ST-05 |
| F2.10 | UI28 Điểm yếu & làm chủ | `/diem-yeu` | `GET /weaknesses`, `GET /cards/{id}/mastery` | Điểm theo kỹ năng + số mẫu; <5 mẫu hiện "Chưa đủ dữ liệu" | ST-04, ST-06 |
| F2.11 | UI29 Cặp nhầm & gợi ý | `/goi-y` | `GET /confused-pairs`, `GET /recommendations`, `POST …/dismiss` | Lý do dạng "Bạn viết sai từ này 3 lần trong 7 ngày" | ST-07…09 |
| F2.12 | UI30 Thống kê | `/thong-ke` | `GET /statistics/summary`, `/activity`, `/skills`, `/forgotten-words`, `/comparison` | Biểu đồ + bảng dữ liệu thay thế; bộ lọc kỳ/bộ/chủ đề; mẫu 0 → "Chưa có dữ liệu" | TKH-01…08, TKH-10 |
| F2.13 | UI31 Báo cáo tuần | `/bao-cao-tuan` | `GET /statistics/weekly-reports` | Danh sách tuần, chi tiết | TKH-05, TKH-09 |
| F2.14 | UI37 (phần quyền) | `/quan-tri/tai-khoan` | `GET /admin/users`, `PUT /admin/users/{id}/roles` | Cấp/thu hồi vai trò, yêu cầu xác thực lại | QT-02 |

**Mốc Đợt 2:** học một phiên hoàn chỉnh bằng bàn phím, làm 9 dạng bài, xem sổ tay/thống kê — tất cả với API thật; tắt AI vẫn dùng được.

---

## 5. Đợt 3 — AI, phát âm, động lực, quản trị (09/11 – 29/11)

| Bước | UI | Đường dẫn | API chính | Nội dung / tương tác | ID |
|---|---|---|---|---|---|
| F3.1 | UI23 Tạo thẻ AI | `/ai/tao-the` | `GET /ai/usage`, `POST /ai/requests` (202), `GET /ai/requests/{id}` | Đoạn 50–10 000 ký tự, số thẻ ≤30, chọn bộ đích, đồng ý gửi AI; hỏi trạng thái sau 2 giây rồi 5 giây; lỗi giữ nguyên đoạn văn | AI-01, AI-12 |
| F3.2 | UI24 Duyệt & lưu thẻ AI | `/ai/yeu-cau/[id]/the-nhap` | `PATCH /ai/candidates/{id}`, `POST /ai/requests/{id}/commit-cards` | Ô chọn + sửa từng trường, cảnh báo trùng/nguồn cần kiểm tra, nút "Lưu thẻ đã chọn" (Idempotency-Key) | AI-03 |
| F3.3 | UI25 Trợ lý ngữ cảnh & luyện AI | `/ai/tro-ly` | `POST /ai/requests` (feature khác nhau) | Nghĩa ngữ cảnh/ví dụ/mẹo, phân biệt 2–5 từ, sửa câu (câu gốc và câu gợi ý tách biệt), tình huống 1–20 từ, phản hồi nhiệm vụ viết, tạo bài từ lỗi | AI-02, AI-04…08 |
| F3.4 | UI26 Lịch sử AI & hội thoại | `/ai/lich-su`, `/ai/hoi-thoai` | `GET /ai/requests`, `DELETE /ai/requests/{id}`, `POST /ai/requests/{id}/feedback` | Lọc loại/ngày; báo cáo AI sai; nội dung hết hạn nêu rõ; hội thoại **ẩn** (mở rộng) | AI-10, AI-11, AI-09* |
| F3.5 | UI35 Ghi âm phát âm | `/phat-am` | `/media/upload-intents`, `POST /pronunciation/attempts` | Nghe mẫu; xin quyền micro khi bấm; đồng hồ, dừng, ghi lại, nghe lại **trước khi gửi**; ≤30 giây; dò MIME `MediaRecorder` hỗ trợ; đồng ý gửi dịch vụ | PA-01…04 |
| F3.6 | UI36 Kết quả & lịch sử phát âm | `/phat-am/lich-su` | `GET /pronunciation/attempts`, `GET/DELETE …/{id}`, `DELETE …/{id}/recording` | Chỉ hiện chỉ số API trả; điểm theo từ; lọc điểm thấp → luyện lại; xóa audio/xóa cả kết quả | PA-05…08 |
| F3.7 | UI32 Điểm & huy hiệu | `/thanh-tich` | `GET /achievements`, `GET /users/me/streak` | XP hôm nay (trần 200), huy hiệu, chuỗi ngày | DL-01, DL-02, DL-04 |
| F3.8 | UI33 Thử thách | `/thu-thach` | `GET /challenges` | Tiến độ ngày/tuần | DL-03 |
| F3.9 | UI34 Thông báo | `/thong-bao` + chuông ở header | `GET /notifications`, `GET /announcements`, `PATCH /notifications/{id}/read`, `POST /notifications/read-all` | Số chưa đọc, mở hàng đợi thẻ đến hạn | DL-05…07 |
| F3.10 | UI37 Tài khoản & quyền | `/quan-tri/tai-khoan` | `GET /admin/users`, `PUT /admin/users/{id}/lock` | Tìm/lọc, khóa có lý do | QT-01 |
| F3.11 | UI38 Nội dung mẫu | `/quan-tri/noi-dung` | `/admin/topics…`, `/admin/decks…`, `/admin/decks/{id}/cards…` | Quản lý chủ đề, bộ và thẻ chính thức | QT-03 |
| F3.12 | UI39 Kiểm duyệt | `/quan-tri/bao-cao` | `GET /admin/reports`, `POST /admin/reports/{id}/resolve`, `PUT /admin/decks/{id}/moderation` | Hàng đợi, minh chứng, duyệt/từ chối/ẩn + lý do; 409 khi người khác đã xử lý | QT-04 |
| F3.13 | UI40 Hạn mức & chi phí | `/quan-tri/ai` | `GET/PUT /admin/ai-policies…`, `GET /admin/api-calls` | Có phiên bản/ngày hiệu lực; không hiển thị khóa | QT-06, QT-07 |
| F3.14 | UI41 Thống kê & thông báo hệ thống | `/quan-tri/tong-quan` | `GET /admin/usage`, `/admin/announcements…` | Người hoạt động thực, soạn/hẹn/phát hành thông báo | QT-05, QT-08 |
| F3.15 | UI42 Nhật ký quản trị | `/quan-tri/nhat-ky` | `GET /admin/audit-logs` | Chỉ đọc, lọc, phân trang | QT-09 |

**Mốc Đợt 3:** toàn bộ UI01–UI42 (trừ phần mở rộng) nối API thật; AI/phát âm hiển thị đúng trạng thái chờ/chưa rõ/lỗi.

---

## 6. GĐ4 — Kiểm thử (30/11 – 06/12)

| Việc | Công cụ | Xong khi |
|---|---|---|
| E2E luồng chính (đăng ký → học → luyện → AI → phát âm → quản trị) | Playwright | Xanh ở Chrome; thử Edge/Firefox |
| 5 kích thước 360/390/768/1280/1440 px, không tràn ngang | Playwright + chụp màn hình | TR16 đạt |
| Bàn phím & trình đọc màn hình (Tab, focus lỗi, hộp thoại) | Thủ công + axe | Không lỗi nghiêm trọng |
| Trạng thái tải/rỗng/lỗi/thành công từng UI | Danh sách kiểm tra theo `SCI_SE_5` | PC02 đạt |
| Mất mạng giữa phiên học, gửi lại | Playwright (offline) | Không ghi đôi |
| Không lộ bí mật trong bundle | Tìm chuỗi trong `.next/` | PC04 đạt |

## 7. GĐ5 — Hoàn thiện (07/12 – 14/12, chưa production)

- [ ] `npm run build` + `output: 'standalone'`, `Dockerfile` cho staging; Nginx cùng tên miền với BE.
- [ ] `frontend/README.md`: cài đặt, biến môi trường, chạy MSW/API thật, chạy test, tài khoản demo.
- [ ] Rà nội dung tiếng Việt, thông báo lỗi, ảnh chụp màn hình cho báo cáo.

## 8. Checklist "xong một màn hình"

- [ ] Đúng đường dẫn và trường theo `SCI_SE_5`; kiểm tra nhập bằng Zod khớp giới hạn BE.
- [ ] Đủ 4 trạng thái; xử lý 401/403/404/409/412/422/429/503.
- [ ] Dùng được bằng bàn phím, nhãn cho ô nhập, không chỉ dùng màu.
- [ ] Hiển thị tốt ở 360 px và 1440 px.
- [ ] Không gửi đôi khi bấm nhiều lần (khóa nút + Idempotency-Key).
- [ ] Có test đơn vị cho logic, E2E cho luồng chính.
