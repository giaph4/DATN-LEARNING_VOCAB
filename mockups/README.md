# Mockups — VocabLearning

Giao diện mẫu bằng HTML/CSS/JS thuần, chia theo giai đoạn. Là **bản chuẩn để FE dựng Next.js giống hệt**.

## Mở xem

Cần phục vụ qua HTTP ở cổng **5173** (đã cho phép CORS ở BE) để trang gọi được API thật:

```bash
npx serve mockups -l 5173
```
Mở http://localhost:5173. Không có Node: dùng extension Live Server của VS Code, đặt cổng 5173.

Backend phải chạy (`cd backend && docker compose --profile app up -d`) cho các trang có nhãn “API thật”; nếu không, bấm trạng thái “Thành công” trên thanh Mockup để xem dữ liệu mẫu.

## Cấu trúc

| Đường dẫn | Nội dung |
|---|---|
| `shared/tokens.css` | Biến màu, chữ, khoảng cách, bo góc, chuyển động — lấy từ `frontend/design/YEU_CAU_GIAO_DIEN.md` |
| `shared/base.css` | Reset, kiểu chữ, **2 bố cục** (≥ 1024 px: header + menu trái; < 1024 px: thanh trên + thanh điều hướng dưới), khung trang xác thực 2 cột / 1 cột |
| `shared/components.css` | Nút, ô nhập, nhãn, bảng, phân trang, skeleton, trạng thái, hộp thoại, toast, nhóm Quên/Khó/Nhớ/Dễ, thẻ học |
| `shared/app.js` | Biểu tượng (tên theo Lucide), `VL.api`/`VL.send` (tự lấy CSRF), hộp thoại, toast, thanh trạng thái mockup, gọi API, lật thẻ + phím 1–4 |
| `gd0/`, `dot1/`, `dot2/`, `dot3/` | Trang theo giai đoạn; tên tệp theo đường dẫn tiếng Việt của trang thật |
| `index.html` | Mục lục mọi trang và trạng thái |

## Quy ước

- Mỗi trang có đủ 4 trạng thái tải/rỗng/lỗi/thành công (thanh **Mockup** góc dưới — không thuộc sản phẩm, FE không dựng).
- Dữ liệu mẫu = phản hồi thật trong `report/*_BAO_CAO_FE.md`; trang “API thật” gọi `http://localhost:8080/api/v1` (đổi bằng `localStorage['vl.apiBase']`).
- Tên class ↔ component React: `.btn-primary` → `<Button variant="primary">`, `.field` → `<Field>`, `.state` → `<EmptyState>/<ErrorState>`, `.rating` → `<RatingGroup>`, `.card` → `<StudyCard>`.
- Tạo trang mới: skill `/ui-mockup`. Dựng Next.js theo mockup: skill `/fe-from-mockup`.

## Website demo (không cần backend)

- Chạy `npx serve mockups -l 5173` → mở `http://localhost:5173/` → **Mở website demo** (Trang chủ). Tài khoản demo: `huynhgiapho@gmail.com` / `huynhgiapho1234`.
- `shared/demo.js` là backend giả chạy trong trình duyệt (dữ liệu trong `localStorage`, khóa `vl.demo.v2`): trả lời `VL.api`/`VL.send`/`VL.put` theo hợp đồng ở `report/DOT1_BAO_CAO_FE.md`, và giữ dữ liệu học mẫu (5 bộ thẻ, lịch sử 2 tuần, sổ tay, bài luyện) cho các trang Đợt 2.
- Khung người học dựng từ MỘT cấu hình trong `app.js`: trang chỉ cần `<body data-nav="hoc" data-auth="required">` + `<header class="app-header"></header>`, `<nav class="sidebar"></nav>`, `<nav class="bottom-nav"></nav>` rỗng. `data-auth="required"` chưa đăng nhập thì chuyển `/dang-nhap?quay-lai=…`.
- Email hệ thống (xác thực, đặt lại mật khẩu) hiện ở **Hộp thư** trên thanh demo; **Làm lại** xóa dữ liệu demo; **Mục lục** về trang này.
- Trang mới: nạp `../shared/demo.js` trước `app.js`; endpoint Đợt 1 thêm vào `routes` trong `demo.js`; không để `href="#"` cho màn đã có.
- Chỉ dành cho mockup: FE không chép `demo.js` hay thanh demo vào sản phẩm (dùng MSW nếu cần giả lập).
