# Assets — logo và ảnh

FE chép cả thư mục này sang `frontend/public/` (giữ nguyên tên file).

## Logo (đã có, SVG)

| File | Dùng ở đâu |
|---|---|
| `logo-mark.svg` | Biểu tượng 32×32 cạnh chữ "VocabLearning" trên nền sáng (header, trang đăng ký trên điện thoại) |
| `logo-mark-inverse.svg` | Biểu tượng trên nền xanh đậm (khối thương hiệu trang đăng ký/đăng nhập) |
| `logo.svg` / `logo-inverse.svg` | Logo đầy đủ (biểu tượng + chữ) cho email, tài liệu, trang giới thiệu |
| `favicon.svg` | Biểu tượng tab trình duyệt (`<link rel="icon">`, Next.js `app/icon.svg`) |

Ý nghĩa: 2 thẻ từ xếp chồng (học bằng thẻ), chữ **V** (Vocab), chấm cam (khoảnh khắc "nhớ ra rồi"). Màu: `#1D5FD1`, `#FFFFFF`, `#F59E0B`. Chữ: Be Vietnam Pro 600, "Vocab" màu chữ chính + "Learning" xanh dương.

## Ảnh cần bổ sung → `images/`

Mockup hiện một **khung nét đứt kèm mô tả** (`.img-slot`) ở mỗi chỗ cần ảnh. Thả file đúng tên vào `images/` và tải lại trang: ảnh tự thay khung.

| File | Kích thước | Trang | Nội dung cần có |
|---|---|---|---|
| `images/auth-hero.webp` | 960×720 (4:3), nền trong suốt nếu được | `dot1/dang-ky.html`, sau này đăng nhập/xác thực — khối xanh bên trái (chỉ máy tính) | Người học trẻ cầm điện thoại, quanh là vài thẻ từ tiếng Anh bay nhẹ ("travel", "boarding pass"); phong cách phẳng, trắng + xanh dương nhạt + chấm cam, hợp nền `#16489E` |
| `images/empty-topics.webp` | 480×320 (3:2) | `gd0/chu-de.html` — trạng thái Rỗng | Vài thư mục/nhãn chủ đề trống xếp gọn; nét phẳng, xám + xanh dương nhạt; không có chữ |
| `images/card-serendipity.webp` | 640×360 (16:9) | `gd0/ui-kit.html` — mặt sau thẻ học mẫu | Hai người bạn bất ngờ gặp nhau trong quán cà phê (minh họa từ "serendipity"). Thẻ thật lấy ảnh từ API |

Quy tắc ảnh (theo `frontend/design/YEU_CAU_GIAO_DIEN.md`):
- Ảnh chỉ dùng khi giúp hiểu nội dung. Không có ảnh thì bố cục vẫn phải đẹp.
- Không dùng gradient tím–xanh, hiệu ứng phát sáng hay ảnh stock có chữ.
- Dùng WebP dưới 150 KB.
- Ảnh trang trí thì để `alt=""`; ảnh mang nghĩa (ảnh của thẻ) thì phải có `alt`.
