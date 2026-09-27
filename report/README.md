# Báo cáo bàn giao BE → FE

Mỗi khi BE xong một giai đoạn, chạy `/fe-report <PHASE>` để tạo báo cáo mới. FE đọc báo cáo **mới nhất** + phần hợp đồng chung ở GĐ0.

| Giai đoạn | Ngày | Báo cáo | Trạng thái |
|---|---|---|---|
| GĐ0 — Nền tảng | 26/09/2026 | [GD0_BAO_CAO_FE.md](GD0_BAO_CAO_FE.md) | ✅ Đã bàn giao |
| Đợt 1 — Tài khoản & nội dung | 27/09/2026 (B1.1) → 25/10 | [DOT1_BAO_CAO_FE.md](DOT1_BAO_CAO_FE.md) | 🟡 Đang bàn giao dần (B1.1 xong) |
| Đợt 2 — Học & luyện tập | dự kiến 08/11 | `DOT2_BAO_CAO_FE.md` | ⏳ |
| Đợt 3 — AI, phát âm, động lực, quản trị | dự kiến 29/11 | `DOT3_BAO_CAO_FE.md` | ⏳ |
| GĐ4 — Kiểm thử | dự kiến 06/12 | `GD4_BAO_CAO_FE.md` | ⏳ |
| GĐ5 — Hoàn thiện | dự kiến 14/12 | `GD5_BAO_CAO_FE.md` | ⏳ |

Postman: workspace **VocabLearning** → collection **VocabLearning API**, environment `VocabLearning - Local (8080)` hoặc `Qua Next.js (3000)`. Nhờ BE mời vào workspace; ID lưu ở `postman/postman.json`.

Hướng dẫn chạy BE: [`huong-dan/02_FE_CHAY_BACKEND.md`](../huong-dan/02_FE_CHAY_BACKEND.md).
