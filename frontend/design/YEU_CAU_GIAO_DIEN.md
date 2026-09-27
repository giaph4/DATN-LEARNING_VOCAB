# Yêu cầu giao diện — VocabLearning

> Nguồn chuẩn cho mọi giao diện (mockup HTML và Next.js). Skill `ui-brief` nạp tài liệu này trước khi thiết kế/code giao diện.
> Mã hóa thành biến CSS tại `mockups/shared/tokens.css` — khi đổi màu/cỡ chữ ở đây phải đổi cả ở đó.

**Công nghệ:** Next.js; nghiệp vụ và quyền truy cập vẫn do Spring Boot xử lý.
**Định hướng:** Gọn, rõ, tươi sáng và đáng tin: nền trung tính, xanh dương làm màu chính, xanh lá cho tiến bộ, cam làm điểm nhấn kêu gọi học; chuyển động có mục đích.

## 1. Mục tiêu thiết kế

Giao diện phải tạo cảm giác dễ bắt đầu học và dễ duy trì sự tập trung trong phiên học dài. Sự ấn tượng đến từ cách trình bày từ vựng, khoảng trắng, chữ rõ, thao tác chính xác và phản hồi tinh tế. Người học nhận ra ngay việc cần làm tiếp theo, hiểu trạng thái học và ít phải tìm nút.

Yêu cầu “không giống giao diện AI tạo đại trà” được hiểu là tránh bố cục và trang trí lặp theo mẫu: không dùng nền tím–xanh chuyển màu, khối kính trong suốt, các ô thông tin giống hệt nhau trên mọi trang, biểu tượng AI phát sáng, tiêu đề phóng đại hoặc hình trang trí không phục vụ học tập. Không đánh giá thiết kế dựa trên việc có hay không sử dụng AI trong quá trình làm; đánh giá bằng chất lượng sản phẩm và các tiêu chí cụ thể dưới đây.

Trang học là trung tâm sản phẩm. Trang danh mục cần giúp chọn bộ; trình soạn thẻ cần giúp nhập nội dung; thống kê cần giúp hiểu tiến độ; vùng quản trị cần giúp xử lý công việc. Không ép tất cả các trang dùng cùng một bố cục thẻ trang trí.

## 2. Bảng màu và cách phân bổ (60 – 30 – 10)

Theo nguyên tắc phối màu 60 – 30 – 10 của web giáo dục:
- **60 % trung tính** (trắng, xám sáng): nền và bề mặt thoáng, chữ và hình nổi rõ.
- **30 % xanh dương**, kèm xanh lá: xanh dương gợi tin cậy và tập trung, dùng cho hành động chính và điều hướng. Xanh lá gợi sự phát triển, dùng cho tiến độ và hoàn thành.
- **10 % cam**: điểm nhấn kích thích hành động học, dùng rất ít.

| Vai trò | Mã màu | Biến CSS | Cách sử dụng |
| --- | --- | --- | --- |
| Nền trang | `#F5F7FB` | `--bg` | Nền chung, xám xanh rất nhạt; không nhuộm màu toàn màn hình |
| Bề mặt chính | `#FFFFFF` | `--surface` | Thẻ học, biểu mẫu, bảng, hộp thoại, nửa biểu mẫu của trang đăng nhập |
| Chữ chính | `#1B2536` | `--text` | Từ mục tiêu, tiêu đề, nội dung cần đọc (14:1 trên nền) |
| Chữ phụ | `#536075` | `--text-muted` | Phiên âm, mô tả, chú thích (≥ 5,9:1); không giảm độ rõ bằng độ trong suốt |
| Đường chia | `#E2E7EF` | `--divider` | Phân nhóm nội dung; không dùng làm dấu hiệu duy nhất nhận diện ô nhập |
| Viền điều khiển | `#8391A3` | `--control-border` | Ranh giới ô nhập, nút phụ (≥ 3:1 với nền trắng) |
| **Xanh dương** — hành động chính | `#1D5FD1` (hover `#184FB0`, nhấn `#13408F`) | `--primary` | Lưu, xác nhận, tạo tài khoản, liên kết, mục điều hướng đang chọn; chữ trắng trên nền này (5,8:1) |
| Khối thương hiệu | `#16489E` | `--primary-strong` | Nửa trái trang đăng ký/đăng nhập trên máy tính; không dùng cho nút |
| Nền mục được chọn | `#E9F0FD` | `--selected-bg` | Điều hướng đang mở; kèm chữ xanh đậm và vạch trái |
| **Xanh lá** — tiến bộ | `#1E7F55`, nền `#E6F4EC` | `--secondary`, `--secondary-bg` | Thanh tiến độ, chuỗi mục tiêu đạt, trạng thái hoàn thành |
| **Cam** — điểm nhấn | `#F59E0B` (hover `#E08A00`), chữ `#1B2536` | `--accent`, `--on-accent` | **Chỉ một** lời kêu gọi học mỗi màn: “Bắt đầu học”, “Làm bài luyện”. Chữ tối trên nền cam (7:1), không dùng chữ trắng |
| Nền/chữ nhấn cam | nền `#FFF4E0`, chữ `#9A5200` | `--accent-bg`, `--accent-text` | Chuỗi ngày học, nhãn “Mới”, điểm nhấn nhỏ |
| Quên / lỗi | Chữ `#B3261E`, nền `#FDEEEC` | `--danger`, `--danger-bg` | Nút Quên và lỗi nhập; lỗi phải có thông báo chữ |
| Khó / cảnh báo | Chữ `#8F5A00`, nền `#FFF4DE` | `--warning`, `--warning-bg` | Nút Khó; cảnh báo cần chú ý |
| Nhớ / thành công | Chữ `#1C7249`, nền `#E6F4EC` | `--success`, `--success-bg` | Nút Nhớ; xác nhận thành công |
| Dễ / thông tin | Chữ `#1D5FD1`, nền `#E9F0FD` | `--info`, `--info-bg` | Nút Dễ; thông tin hỗ trợ |

Quy tắc phân vai:
- **Xanh dương** là màu hành động chính mặc định (Lưu thẻ, Tạo tài khoản, Đăng nhập).
- **Cam** chỉ dành cho hành động học quan trọng nhất trên màn hình, ví dụ “Bắt đầu học” ở trang Học hôm nay. Mỗi vùng thao tác không có cả nút cam và nút xanh đậm cạnh nhau cùng độ nhấn.
- **Xanh lá** không dùng cho nút; chỉ dùng để thể hiện tiến bộ.
- Tỷ lệ 60 – 30 – 10 là hướng cân bằng thị giác, không yêu cầu đếm điểm ảnh.

Không đặt nhiều hơn một hành động chính được tô đậm trong cùng vùng thao tác. Các nút Quên/Khó/Nhớ/Dễ là một nhóm lựa chọn ngang hàng nên có cùng kích thước và cùng độ nhấn. Không làm Dễ nổi bật hơn để thúc đẩy người học tự đánh giá cao.

Không dùng màu như cách duy nhất truyền đạt ý nghĩa. Bốn mức nhớ luôn có nhãn đầy đủ và số phím 1–4 trên máy tính. Biểu đồ có chú giải và số liệu; dữ liệu thiếu khác với điểm bằng 0. Màu đỏ được giới hạn trong lỗi và Quên, không phủ cả nền trang khi người học trả lời sai.

## 3. Chữ, khoảng trắng và thành phần

- Dùng **Be Vietnam Pro** nếu nhóm đưa được phông vào dự án và tự phục vụ tệp; phương án dự phòng là Segoe UI hoặc phông hệ thống hỗ trợ tiếng Việt. Chỉ dùng một họ phông chính, ba mức đậm 400/500/600; không phối nhiều phông để tạo khác biệt giả.
- Nội dung và biểu mẫu 16 px, giãn dòng khoảng 1,5–1,65. Chú thích 14 px. Không thu nhỏ chữ để nhét dữ liệu vào màn hình.
- Từ/cụm từ mục tiêu trong phiên học 32–44 px ở máy tính, 28–36 px trên điện thoại; từ dài được xuống dòng, không cắt. Nghĩa 20–24 px; ví dụ 16–18 px. Phiên âm nằm gần từ và có nút nghe dễ nhận ra.
- Tiêu đề trang 24–32 px; tiêu đề nhóm 18–22 px. Tên bộ dài, nghĩa nhiều dòng và tiếng Việt có dấu phải được thử với dữ liệu thật.
- Khoảng cách theo nhịp 4/8/12/16/24/32 px. Các vùng lớn có khoảng trắng 24–40 px; vùng liên quan được đặt gần nhau. Tránh cả dồn chật và khoảng trống quá lớn buộc cuộn vô ích.
- Bo góc 8–12 px cho biểu mẫu/bộ thẻ, 12–16 px cho thẻ học chính. Không biến mọi khối thành viên thuốc hoặc dùng góc bo quá lớn cho bảng.
- Dùng viền nhẹ làm cách phân nhóm chính. Bóng đổ chỉ nhẹ ở lớp nổi như hộp thoại và menu; không đặt bóng đổ nặng trên mọi ô.
- Biểu tượng nét thống nhất, kích thước 18–20 px; nút quan trọng có chữ. Không dùng emoji làm biểu tượng điều hướng chính. Ảnh minh họa chỉ xuất hiện khi giúp hiểu nội dung; thiếu ảnh vẫn phải có bố cục tốt.

## 4. Bố cục giúp học dễ

### 4.0. Hai bố cục: máy tính và điện thoại

Mỗi màn hình có **hai bố cục riêng**, không chỉ co giãn một bố cục. Mốc chuyển là **1024 px**.

| | Máy tính (≥ 1024 px) | Điện thoại / máy tính bảng (< 1024 px) |
| --- | --- | --- |
| Khung người học | Header 64 px (logo, chuỗi ngày, ảnh đại diện) + menu trái 248 px (Học hôm nay, Thư viện, Luyện tập, Sổ tay, Thống kê; nhóm Tài khoản) + nội dung tối đa 1040 px, canh giữa | Thanh trên 56 px (logo, chuỗi ngày, ảnh đại diện) + **thanh điều hướng dưới 5 mục** (Học, Thư viện, Luyện tập, Sổ tay, Tôi), cao 64 px cộng vùng an toàn; không có menu trái hay ngăn kéo |
| Trang tổng quan (Học hôm nay) | 2 cột: cột chính là hành động học; cột phụ 320 px cho từ mới và kế hoạch | 1 cột: hành động học trước, nút cam rộng hết chiều ngang, thông tin phụ ở dưới |
| Trang đăng ký / đăng nhập / xác thực | 2 cột: trái là khối thương hiệu `--primary-strong` (câu giới thiệu, 3 lợi ích, một từ mẫu); phải là biểu mẫu tối đa 420 px trên nền trắng | 1 cột: logo trên, biểu mẫu rộng hết màn hình (lề 16 px), không có khối thương hiệu |
| Danh sách / bảng | Bảng nhiều cột, bộ lọc nằm ngang trên bảng | Mỗi dòng thành một thẻ xếp dọc (< 640 px), bộ lọc gọn trong một nút |
| Phiên học | Thẻ 640–760 px canh giữa, 4 nút đánh giá một hàng, phím 1–4 | Thẻ rộng hết màn hình; 4 nút xếp 2 × 2 (< 520 px) ở dưới, **ẩn thanh điều hướng dưới** để tập trung |

Nội dung cố định ở đáy (thanh chọn, nút lưu) phải nằm **trên** thanh điều hướng dưới, không bị che. Mockup HTML đặt cả `.sidebar` và `.bottom-nav` trong trang; CSS hiện một trong hai. Next.js làm tương tự với `lg:`.

### 4.1. Trang học và phiên thẻ

Trang học ưu tiên số thẻ đến hạn, thời gian học đã chọn và nút Bắt đầu. Hạn mức từ mới, tiến độ mục tiêu và kế hoạch học lại đặt ở vị trí phụ nhưng dễ tìm. Không xếp hàng dài thẻ thống kê trước hành động học.

Trong phiên học, thẻ từ là điểm tập trung, rộng khoảng 640–760 px trên máy tính. Thanh tiến độ nhỏ ở trên; từ, phiên âm và audio ở vùng chính; nghĩa/ví dụ ở mặt sau; các mức nhớ thành một nhóm ở dưới. Trước khi lật không hiển thị đáp án; sau lật không thay đổi bố cục bất ngờ. Không hiển thị bảng xếp hạng, quảng bá AI, huy hiệu nhấp nháy hoặc biểu đồ lớn trong phiên.

Trên điện thoại, nút đánh giá xếp 2 × 2 nếu hàng bốn nút quá hẹp; vùng chạm tối thiểu 44 × 44 px, ưu tiên chiều cao 48 px. Có khoảng cách đủ tránh bấm nhầm. Thanh hành động dưới cùng không che nội dung và có khoảng an toàn ở mép thiết bị.

### 4.2. Thư viện và danh mục

Mỗi bộ thể hiện rõ tên, mô tả ngắn, trình độ, số thẻ và nguồn quản trị/người dùng. Ưu tiên đọc nội dung trước hình trang trí. Bộ chưa duyệt, bị ẩn hoặc riêng tư có nhãn chữ tương ứng. Tìm kiếm và bộ lọc gần danh sách; bộ lọc đang áp dụng có thể bỏ từng mục. Yêu thích là hành động phụ, không tranh vị trí với Học hoặc Sao chép.

### 4.3. Soạn thẻ, luyện tập và AI

Biểu mẫu thẻ chia nhóm: từ và nghĩa; ví dụ và bản dịch; audio/ảnh; phân loại. Trường bắt buộc rõ, trường tùy chọn không tạo áp lực phải nhập. Cảnh báo trùng dẫn được tới thẻ liên quan. Nháp AI có nhãn nguồn và lựa chọn từng thẻ; nút lưu chỉ rõ “Lưu các thẻ đã chọn”, không gây hiểu nhầm AI đã tự lưu.

Trong bài luyện, câu hỏi và vùng trả lời được đặt trước điểm thưởng. Sau nộp, phần giải thích trả lời trực tiếp vì sao đúng/sai. Phản hồi AI dùng cùng hệ thiết kế, không có màu neon hoặc biểu tượng lấp lánh riêng. Điểm cố định và phản hồi AI phân biệt bằng nhãn/chú thích, không chỉ khác màu.

### 4.4. Thống kê và quản trị

Thống kê dùng ít biểu đồ nhưng đúng mục đích: hoạt động theo thời gian, tỷ lệ đúng và kỹ năng. Luôn có khoảng thời gian, đơn vị, số mẫu và thời điểm cập nhật. Không dùng biểu đồ tròn/đồng hồ chỉ để lấp chỗ. Vùng quản trị thiên về bảng, bộ lọc và trạng thái; dùng chung màu/phông nhưng tăng mật độ dữ liệu hợp lý, không áp dụng bố cục thẻ học cho bảng công việc.

## 5. Chuyển động mượt và vừa đủ

Chuyển động phải giúp nhận ra phản hồi hoặc sự chuyển trạng thái. Thao tác vẫn hoạt động ngay, không chờ hiệu ứng kết thúc mới gửi/lưu. Không đưa độ trễ cố định vào API để làm hiệu ứng trông đẹp.

| Tương tác | Thời lượng đề xuất | Biến CSS | Cách thể hiện |
| --- | --- | --- | --- |
| Di chuột, nhấn và lấy focus | 120–160 ms | `--dur-hover` | Đổi màu/viền; nhấn dịch tối đa 1 px, không phóng to mạnh |
| Mở menu/bộ lọc nhỏ | 140–180 ms | `--dur-menu` | Hiện dần, dịch 4–6 px; focus đúng vị trí |
| Mở/đóng hộp thoại | 180–220 ms | `--dur-dialog` | Hiện dần và dịch tối đa 8 px; nền tối nhẹ, không làm mờ nặng toàn trang |
| Lật thẻ | 260–320 ms | `--dur-flip` | Một lần quay theo trục Y hoặc chuyển mặt rõ; chữ không méo, không có nhiều lớp hiệu ứng |
| Chuyển sang thẻ tiếp | 160–220 ms | `--dur-next` | Thẻ cũ mờ/dịch 8–12 px, thẻ mới vào cùng vùng; không kéo dài hành trình |
| Lưu hoặc trả lời xong | 160–220 ms | `--dur-next` | Thay trạng thái nút và xác nhận ngắn; không rung toàn màn hình |
| Thanh tiến độ | 200–280 ms | `--dur-progress` | Đi tới giá trị máy chủ xác nhận; không chạy số lặp khi quay lại trang |
| Thông báo ngắn | 180–220 ms | `--dur-toast` | Hiện dần; lỗi cần xử lý vẫn có nội dung tại trường, không chỉ thông báo tự biến mất |

Dùng đường chuyển động giảm tốc nhẹ, ví dụ `cubic-bezier(0.2, 0.8, 0.2, 1)` (`--ease`). Chủ yếu chuyển `opacity` và `transform`; tránh thay `width`, `height`, vị trí bố cục hoặc bóng đổ lớn liên tục. Có thể dùng CSS cho hiệu ứng nhỏ; chỉ thêm thư viện chuyển động khi thật sự cần quản lý lật thẻ/chuyển trạng thái phức tạp.

Không dùng hiệu ứng nảy đàn hồi, hạt bay, ánh sáng quét, cuộn tự chạy, nền chuyển động, trỏ chuột trang trí hay pháo giấy sau mọi lượt học. Thẻ đến hạn không tự rung. Khi đạt huy hiệu/mục tiêu, chỉ dùng xác nhận ngắn, yên tĩnh; không buộc người dùng xem hiệu ứng mới được tiếp tục học.

Khi người dùng bật `prefers-reduced-motion`, bỏ quay 3D và dịch chuyển; đổi mặt thẻ tức thời hoặc mờ nhẹ tối đa 100 ms. Nội dung, trạng thái và quyền thao tác giữ nguyên. Trên thiết bị yếu hoặc khi có nhiều dữ liệu, bỏ chuyển động phụ trước; mục tiêu là không mất phản hồi và không giật bố cục.

## 6. Khả năng đọc và tiêu chí nghiệm thu

| ID | Tiêu chí | Cách kiểm chứng |
| --- | --- | --- |
| GT01 | Bảng màu cân đối và nhất quán | Tỷ lệ 60 – 30 – 10; xanh dương là hành động chính và điều hướng, xanh lá chỉ cho tiến độ, cam tối đa một lời kêu gọi học mỗi màn; không có màu ngoài bảng màu |
| GT02 | Chữ đủ tương phản | Chữ thông thường tối thiểu 4,5:1; chữ lớn tối thiểu 3:1; kiểm tra cặp màu thực tế cả bình thường, hover, focus và lỗi |
| GT03 | Điều khiển và focus dễ thấy | Thành phần cần nhận diện và vòng focus đạt tương phản tối thiểu 3:1 với nền liền kề; Tab thấy vị trí hiện tại, không bị lớp khác che |
| GT04 | Phân biệt bốn mức nhớ | Nhãn đầy đủ và thứ tự Quên–Khó–Nhớ–Dễ; cùng kích thước/độ nhấn; vẫn hiểu khi xem ảnh thang xám |
| GT05 | Trang học có điểm tập trung rõ | Từ và thao tác học nổi bật nhất; không bị trang trí/AI/XP/biểu đồ tranh sự chú ý; nghĩa không lộ trước lật |
| GT06 | Không có hình thức trang trí đại trà | Không gradient tím–xanh, kính mờ, phát sáng, khối số liệu lặp vô nghĩa, emoji điều hướng hoặc tiêu đề quảng cáo trong màn hình học |
| GT07 | Dữ liệu thật vẫn đẹp và đọc được | Thử tên bộ 150 ký tự, từ dài, nghĩa nhiều dòng, tiếng Việt đầy dấu, thẻ thiếu ảnh/audio và danh sách nhiều mục; không cắt/đè chữ |
| GT08 | Hai bố cục đúng | Máy tính 1280 px dùng menu trái và bố cục nhiều cột; 360/390 px dùng thanh điều hướng dưới, 1 cột; không tràn ngang; vùng chạm ≥ 44 px; thanh dưới và nút cố định không che nội dung |
| GT09 | Chuyển động đúng mục đích | Lật/chuyển thẻ và hộp thoại nằm trong khoảng thời lượng; không nảy/nhấp nháy hay tự chạy khi chỉ đọc |
| GT10 | Hiệu ứng không làm sai nghiệp vụ | Bấm liên tục, mất mạng hoặc chuyển trang giữa hiệu ứng không ghi thêm lượt, XP hay kết quả; chỉ tăng tiến độ sau xác nhận |
| GT11 | Giảm chuyển động hoạt động | Bật thiết lập giảm chuyển động của hệ điều hành; thẻ không quay/dịch, thao tác và phản hồi vẫn đầy đủ |
| GT12 | Mượt trên thiết bị thử và đầy đủ trạng thái | Quan sát/đo trên máy tính và điện thoại thực tế; mục tiêu gần 60 khung hình/giây khi thiết bị đáp ứng, không giật hoặc gây dịch bố cục; mọi màn hình có tải/rỗng/lỗi/thành công |

Đánh giá độ mượt phải ghi thiết bị, trình duyệt và bản ứng dụng đã thử; không khẳng định đạt 60 khung hình/giây trên mọi thiết bị. Đây là yêu cầu thiết kế và nghiệm thu, chưa phải kết quả kiểm thử thực tế.

## 7. Đoạn yêu cầu có thể giao cho người thiết kế

Thiết kế website học từ vựng tiếng Anh có giao diện tiếng Việt, tạo cảm giác tươi sáng, đáng tin và dễ tập trung. Phối màu 60 – 30 – 10: nền trắng/xám sáng; xanh dương cho hành động chính và điều hướng, xanh lá cho tiến độ; cam làm điểm nhấn duy nhất cho nút “Bắt đầu học”. Máy tính dùng menu trái, điện thoại dùng thanh điều hướng dưới; trang đăng nhập chia 2 cột trên máy tính và 1 cột trên điện thoại. Từ vựng, phiên âm, ví dụ và hành động học phải rõ hơn các yếu tố trang trí. Phân biệt Quên, Khó, Nhớ, Dễ bằng nhãn và màu dịu, cùng độ nhấn. Chữ dễ đọc, khoảng trắng hợp lý, biểu tượng thống nhất, dùng tốt trên điện thoại. Có chuyển động ngắn và mượt khi lật/chuyển thẻ, mở hộp thoại và nhận phản hồi; hỗ trợ giảm chuyển động. Tránh gradient tím–xanh, kính mờ, phát sáng, bố cục thẻ lặp vô nghĩa, hiệu ứng nảy và pháo giấy thường xuyên. Giao diện phải có đầy đủ trạng thái tải, rỗng, lỗi, thành công và được kiểm tra với dữ liệu thật.
