# PHÂN TÍCH VÀ THIẾT KẾ HỆ THỐNG HỌC TỪ VỰNG TIẾNG ANH

**Công nghệ:** Next.js · Spring Boot · MySQL · Redis · kho lưu trữ tương thích S3  
**Ngày:** 26/09/2026 · **Phiên bản:** 1.2 — Tên bảng và cột tiếng Việt không dấu  
**Tên quy ước:** VocabLearning

> Tài liệu mô tả thiết kế để xây dựng hệ thống, chưa phải mã nguồn hay website đã triển khai. AI sử dụng API có sẵn, không huấn luyện mô hình riêng. Các hạn mức, thời hạn lưu dữ liệu và chỉ tiêu hiệu năng là đề xuất ban đầu, cần điều chỉnh sau kiểm thử.

**Quy ước ngôn ngữ:** Tên bảng và tên cột dùng tiếng Việt không dấu, phân tách bằng gạch dưới, ví dụ `nguoi_dung`, `bo_the`, `trang_thai`. Giữ các tên kỹ thuật quen thuộc `id`, `email`, `password_hash`, `created_at`, `updated_at`, `ipa`, `mime_type`, `slug`; giữ tên công nghệ, kiểu dữ liệu SQL, giá trị trạng thái và đường dẫn API. Tên trường JSON của API giữ nguyên để không đổi giao tiếp; cần ánh xạ sang tên cột tại máy chủ.

## Mục lục

1. [Phạm vi và giả định](#1-phạm-vi-và-giả-định)
2. [Yêu cầu và trường hợp sử dụng](#2-yêu-cầu-và-trường-hợp-sử-dụng)
3. [Quy tắc nghiệp vụ](#3-quy-tắc-nghiệp-vụ)
4. [Kiến trúc hệ thống](#4-kiến-trúc-hệ-thống)
5. [Thiết kế giao diện người dùng](#5-thiết-kế-giao-diện-người-dùng)
6. [Thiết kế phía máy chủ](#6-thiết-kế-phía-máy-chủ)
7. [Thiết kế cơ sở dữ liệu](#7-thiết-kế-cơ-sở-dữ-liệu)
8. [Mã SQL tạo cơ sở dữ liệu](#8-mã-sql-tạo-cơ-sở-dữ-liệu)
9. [Thuật toán ôn tập và cá nhân hóa](#9-thuật-toán-ôn-tập-và-cá-nhân-hóa)
10. [Tích hợp API AI và đánh giá phát âm](#10-tích-hợp-api-ai-và-đánh-giá-phát-âm)
11. [Đặc tả API](#11-đặc-tả-api)
12. [Luồng xử lý và giao dịch](#12-luồng-xử-lý-và-giao-dịch)
13. [Bảo mật và quyền riêng tư](#13-bảo-mật-và-quyền-riêng-tư)
14. [Hiệu năng và vận hành](#14-hiệu-năng-và-vận-hành)
15. [Triển khai và cấu hình](#15-triển-khai-và-cấu-hình)
16. [Kiểm thử và nghiệm thu](#16-kiểm-thử-và-nghiệm-thu)
17. [Lộ trình và rủi ro](#17-lộ-trình-và-rủi-ro)
18. [Thuật ngữ và tài liệu tham khảo](#18-thuật-ngữ-và-tài-liệu-tham-khảo)

## 1. Phạm vi và giả định

### 1.1 Mục tiêu

Hệ thống giúp người học quản lý bộ thẻ từ vựng, khám phá nội dung có sẵn, học theo lịch ôn, luyện nhiều kỹ năng và theo dõi tiến độ. AI hỗ trợ tạo nội dung, giải thích và phản hồi; các chức năng học cốt lõi vẫn sử dụng được khi AI bị lỗi hoặc hết hạn mức.

### 1.2 Các lựa chọn thiết kế

| Hạng mục | Lựa chọn | Lý do và ảnh hưởng |
|---|---|---|
| Kiến trúc | Một ứng dụng máy chủ chia thành các mô-đun nghiệp vụ; tác vụ nền chạy riêng | Phù hợp nhóm nhỏ, dễ triển khai, có thể tách dịch vụ khi cần |
| Xác thực | Spring Security; phiên đăng nhập lưu trong Redis; trình duyệt giữ cookie định danh | Dễ thu hồi phiên khi đăng xuất, khóa tài khoản hoặc đổi mật khẩu |
| Công nghệ | Next.js, Spring Boot, MySQL | Giữ đúng yêu cầu của đề bài |
| Ôn tập | Biến thể thuật toán SM-2 có bước học mới và học lại | Công thức rõ ràng, dễ kiểm thử, không phụ thuộc AI |
| Tiến độ | Theo người dùng + thẻ + chiều học | Nhớ Anh–Việt không mặc nhiên đồng nghĩa nhớ Việt–Anh |
| Nội dung thẻ | Một thẻ ứng với một nghĩa mục tiêu | Tránh mơ hồ khi chấm bài; cùng từ khác nghĩa được phép tồn tại |
| Bộ thẻ công khai | Người học sao chép thành bộ riêng độc lập | Sửa hoặc xóa bộ gốc không phá bản sao |
| AI | Lớp tích hợp nhà cung cấp API, có kiểm tra cấu trúc đầu ra | Có thể thay nhà cung cấp và mô hình |
| Phát âm | API đánh giá phát âm chuyên dụng, mặc định Azure Speech | Chuyển giọng nói thành văn bản không thay thế chấm phát âm |
| Ảnh và âm thanh | Lưu tệp ở kho đối tượng; MySQL giữ thông tin tệp | Tránh lưu tệp nhị phân lớn trong cơ sở dữ liệu |
| “Số từ” | Tính theo số thẻ chứa từ/cụm từ và nghĩa | Không tuyên bố đây là số từ gốc khác nhau |
| Xóa tài khoản | Khóa sử dụng ngay; xóa hoàn toàn sau 7 ngày | Người dùng có thể hủy yêu cầu trước khi bắt đầu xóa |
| Mở rộng | Facebook, IELTS, tiếng Anh trẻ em, hội thoại AI bằng văn bản | Có thiết kế dữ liệu nhưng triển khai sau chức năng chính |

Thanh toán, gọi video, mạng xã hội và đồng bộ ngoại tuyến đầy đủ nằm ngoài phạm vi. Đánh giá phát âm nằm trong phạm vi chính, không được tự chuyển thành chức năng mở rộng.

### 1.3 Tác nhân

| Tác nhân | Quyền chính |
|---|---|
| Khách | Xem, tìm kiếm và xem trước bộ công khai đã duyệt; đăng ký, đăng nhập |
| Người học | Quản lý dữ liệu của mình; học, luyện tập, dùng AI theo hạn mức; báo cáo nội dung |
| Quản trị viên | Quản lý tài khoản, nội dung mẫu, kiểm duyệt, hạn mức, thông báo và nhật ký quản trị |
| Tiến trình xử lý nền | Gửi email, nhắc học, gọi API, tổng hợp thống kê, xử lý tệp và xóa dữ liệu |
| Dịch vụ bên ngoài | Đăng nhập Google/Facebook, gửi email, AI, tạo giọng đọc và đánh giá phát âm |

## 2. Yêu cầu và trường hợp sử dụng

Người học phải có tài khoản `ACTIVE`, đã xác thực email và có quyền với dữ liệu đang thao tác. Khách chỉ xem nội dung công khai đã duyệt. Quyền luôn được kiểm tra tại Spring Boot.

| Mã yêu cầu (SCI_SE_3) | Tác nhân | Chức năng | Điều kiện và kết quả |
|---|---|---|---|
| TK-01, TK-02 | Khách | Đăng ký email, xác thực và gửi lại email | Email hợp lệ; tạo tài khoản chờ xác thực; mã dùng một lần, có thời hạn |
| TK-03, TK-04 | Khách | Đăng nhập email/Google; Facebook mở rộng | Kiểm tra thông tin đăng nhập; tạo phiên mới; không tự liên kết tài khoản chỉ vì trùng email |
| TK-05 | Người học/Khách | Đăng xuất, quên và đặt lại mật khẩu | Mã đặt lại hợp lệ; thu hồi phiên sau đổi mật khẩu; thông báo chung để tránh lộ email tồn tại |
| TK-06 | Người học | Hồ sơ và ảnh đại diện | Kiểm tra tệp tải lên; cập nhật thông tin và xử lý ảnh cũ |
| TK-07 | Người học | Trình độ tự đánh giá, mục tiêu, chủ đề quan tâm | Trình độ CEFR hợp lệ; lưu thiết lập ban đầu |
| TK-08, TK-09 | Người học | Số từ mới, phút học, giờ nhắc, múi giờ và thông báo | Kiểm tra giới hạn; lưu tùy chọn; tính lại lịch nhắc tương lai |
| TK-10 | Người học | Chuỗi ngày học và lịch sử hoạt động | Thống kê theo ngày địa phương; hiển thị rõ khi chưa có dữ liệu |
| TK-11 | Người học | Yêu cầu hoặc hủy xóa tài khoản | Xác thực lại; khóa sử dụng; tác vụ xóa có trạng thái và mốc xử lý |
| BT-01, BT-02 | Người học | Tạo, xem, sửa, xóa bộ thẻ và thẻ | Chủ sở hữu; kiểm tra phiên bản khi sửa; thẻ đã xóa không tiếp tục đưa vào học |
| BT-03, BT-09 | Người học | Từ, từ loại, nghĩa, IPA, ví dụ, bản dịch, ảnh và âm thanh | Một nghĩa mục tiêu; nhiều ví dụ/tệp; kiểm tra dữ liệu và cảnh báo trùng |
| BT-04, BT-05 | Người học | Chủ đề, nhãn, độ khó, công khai/riêng tư | Nhãn thuộc đúng bộ; công khai phải qua kiểm duyệt |
| BT-06, BT-10, BT-11 | Khách/Người học | Tìm, lọc, sắp xếp, yêu thích và chia sẻ liên kết | Phân trang; liên kết bộ riêng tư không cấp quyền cho khách |
| BT-07 | Người học | Sao chép bộ công khai | Bộ đã duyệt; bản sao riêng tư; không sao chép tiến độ học |
| BT-08 | Người học | Nhập và xuất CSV | Xem trước lỗi theo dòng; xác nhận trước khi ghi; chống nhập trùng khi thử lại |
| BT-12 | Người học | Báo cáo nội dung sai hoặc không phù hợp | Lưu phiếu kiểm duyệt; không khóa nội dung chỉ vì một báo cáo |
| ND-01…ND-07 | Khách/Người học | Bộ giao tiếp, TOEIC; phân trình độ; xem trước | Có mô tả, số thẻ, nguồn quản trị/người dùng; IELTS và trẻ em mở rộng |
| ON-01…ON-04 | Người học | Lật thẻ, phím tắt, chọn chiều học, nghe từ và ví dụ | Lưu nội dung tại lúc tạo phiên; không kích hoạt phím tắt khi đang nhập liệu |
| ON-05…ON-09 | Người học | Quên/Khó/Nhớ/Dễ; lịch ôn, trạng thái và lịch sử | Mỗi kết quả ghi một lần; phân biệt từ mới, đến hạn và quá hạn |
| ON-10…ON-12 | Người học | Giới hạn từ mới, tổng kết, tạm ngưng và đặt lại tiến độ | Giữ lịch sử; đặt lại tạo giai đoạn tiến độ mới |
| ON-13, ON-14 | Người học | Học theo quỹ thời gian và quay lại sau nghỉ | Ưu tiên ôn nợ; chia khối lượng học mà không sửa giả ngày đến hạn |
| LT-01…LT-06, LT-10, LT-11 | Người học | Chọn nghĩa/từ, nghe chép, điền chỗ trống, ghép, nhập từ | Chấm đáp án cố định trên máy chủ; giải thích kết quả |
| LT-07…LT-09 | Người học | Từ dễ nhầm, viết câu, kiểm tra tổng hợp | Điểm AI cho bài viết tách khỏi điểm đáp án cố định |
| LT-12 | Người học | Lịch sử và luyện lại câu sai | Tạo lần làm mới; giữ nguyên lần làm cũ |
| AI-01…AI-03 | Người học | Tạo thẻ từ đoạn văn; nghĩa ngữ cảnh, ví dụ, mẹo nhớ | Có sự đồng ý và hạn mức; xem, sửa, chọn rồi mới lưu |
| AI-04…AI-07 | Người học | Bài từ các từ sai/đến hạn; giải thích; sửa câu; tình huống | Tác vụ nền; ghi phiên bản chỉ dẫn, mô hình, mức sử dụng |
| AI-08…AI-12 | Người học | Phản hồi sử dụng từ, báo cáo AI, lịch sử, hội thoại mở rộng | Kiểm tra quyền; thử lại có giới hạn; lỗi AI không làm hỏng học cốt lõi |
| ST-01…ST-03 | Người học | Sổ tay từ khó, đề xuất tự động, nhóm lỗi | Phân loại nghĩa/chính tả/nghe/sử dụng; người học quyết định chấp nhận đề xuất |
| ST-04…ST-09 | Người học | Điểm yếu, mức làm chủ theo kỹ năng, cặp nhầm, gợi ý | Hiển thị số mẫu và lý do; ít dữ liệu thì chưa kết luận |
| TKH-01…TKH-04 | Người học | Từ mới/đang học/ghi nhớ; ôn; tỷ lệ đúng; phút và mục tiêu | Chỉ số có định nghĩa rõ và bộ lọc thời gian |
| TKH-05…TKH-10 | Người học | Biểu đồ, chuỗi ngày, chủ đề/bộ, từ hay quên, so sánh, tuần, kỹ năng | Lưu báo cáo tuần; không chia cho 0 khi so sánh |
| DL-01…DL-04 | Người học | Điểm kinh nghiệm, huy hiệu, thử thách ngày/tuần | Giới hạn thưởng; không tăng điểm bằng lặp bài quá dễ |
| DL-05…DL-07 | Người học | Thẻ đến hạn trong ứng dụng, email nhắc, hoàn thành mục tiêu | Gửi theo lựa chọn; chống gửi trùng; tôn trọng múi giờ |
| PA-01…PA-08 | Người học | Nghe mẫu, ghi âm, phát lại, chấm phát âm, luyện lại, xóa | Có sự đồng ý gửi API; chỉ hiển thị điểm mà nhà cung cấp hỗ trợ |
| QT-01, QT-02 | Quản trị viên | Khóa/mở khóa tài khoản, phân quyền | Thu hồi phiên khi khóa; không bỏ quản trị viên cuối cùng |
| QT-03, QT-04 | Quản trị viên | Chủ đề, bộ/thẻ mẫu, kiểm duyệt | Duyệt/từ chối/ẩn; kiểm tra phiên bản; lưu nhật ký |
| QT-05…QT-09 | Quản trị viên | Thống kê, hạn mức AI, lượt gọi/lỗi/chi phí, thông báo, nhật ký | Cấu hình có phiên bản; không hiển thị khóa API |

### 2.1 Yêu cầu phi chức năng

| Nhóm | Mục tiêu ban đầu | Cách kiểm tra |
|---|---|---|
| Giao diện | Tiếng Việt; dùng tốt từ 360px; hỗ trợ bàn phím và khả năng tiếp cận | Kiểm tra máy tính/điện thoại; nhãn cho trình đọc màn hình; WCAG 2.2 AA ở luồng chính |
| Trạng thái | Đang tải, rỗng, lỗi và thành công rõ ràng | Danh sách kiểm tra từng màn hình |
| Hiệu năng | p95 thao tác dữ liệu <500ms; gửi ôn tập <300ms; tìm kiếm <800ms | Tải 100 người đồng thời, 1 triệu bản ghi tiến độ, 5 triệu lượt ôn; không tính dịch vụ ngoài |
| AI | Tạo tác vụ <500ms; thời hạn xử lý mặc định 90 giây | Giả lập lỗi, hết thời gian chờ và tạm ngắt nhà cung cấp |
| Nhất quán | Không ghi trùng lượt ôn, hạn mức hoặc điểm thưởng khi thử lại | Kiểm thử xử lý đồng thời trên MySQL thật |
| Sẵn sàng | Mục tiêu 99,5%/tháng cho học cốt lõi | Giám sát; đây là mục tiêu thiết kế, chưa phải cam kết dịch vụ |
| Khôi phục | Mất tối đa 24 giờ dữ liệu; khôi phục trong 4 giờ ở bản triển khai nhỏ | Sao lưu mỗi ngày; diễn tập khôi phục; bổ sung nhật ký nhị phân để giảm mất dữ liệu |
| Bảo mật | Không lộ mật khẩu, khóa API, đáp án hoặc dữ liệu riêng tư | Kiểm thử từ chối truy cập; quét thư viện và khóa bí mật |
| Vận hành | Có mã truy vết, chỉ số, nhật ký và hướng dẫn xử lý sự cố | Kiểm tra giám sát và quy trình khôi phục |

## 3. Quy tắc nghiệp vụ

### 3.1 Tài khoản

- Email có bản hiển thị và bản chuẩn hóa bằng bỏ khoảng trắng hai đầu, chuyển chữ thường. Không bỏ dấu chấm hay phần sau dấu `+` để suy đoán cùng người.
- Tài khoản chưa xác thực chỉ dùng chức năng xác thực/gửi lại/đăng xuất. Google phải được kiểm tra chữ ký, bên phát hành, đối tượng nhận, mã ngẫu nhiên và thông tin xác thực email khi có.
- Không tự liên kết Google với tài khoản email trùng. Người dùng đăng nhập tài khoản hiện có, xác thực lại và xác nhận liên kết.
- `password_hash` có thể `NULL` với người chỉ dùng Google. Không cho gỡ phương thức đăng nhập cuối cùng.
- Khóa, đặt lại mật khẩu, thay đổi quyền hoặc xóa tài khoản phải thu hồi phiên; kiểm tra trạng thái tài khoản khi xử lý yêu cầu nhạy cảm.

### 3.2 Bộ thẻ và quyền nội dung

- Bộ xuất hiện công khai khi đồng thời có `che_do_hien_thi=PUBLIC`, `vong_doi=PUBLISHED`, `trang_thai_kiem_duyet=APPROVED` và chưa bị xóa.
- Sửa nội dung bộ công khai đưa về chờ duyệt, tạm ẩn khỏi danh mục. Chủ bộ vẫn học được. Giữ bản công bố cũ là cải tiến sau này nếu bổ sung bảng phiên bản nội dung.
- Nguồn `ADMIN` chỉ do quản trị viên tạo; sao chép thành nguồn `USER` và giữ `bo_the_nguon_id` để ghi nhận nguồn gốc.
- Thư viện gồm bộ sở hữu và bản sao. Yêu thích chỉ đánh dấu bộ công khai, không tự tạo tiến độ học.
- Liên kết chia sẻ luôn kiểm tra quyền hiện tại; liên kết bộ riêng chỉ điều hướng sau khi chủ sở hữu đăng nhập.
- Trùng hoàn toàn: cùng từ đã chuẩn hóa, từ loại và nghĩa đã chuẩn hóa trong một bộ. Cùng từ nhưng khác nghĩa chỉ cảnh báo.
- Chuẩn hóa Unicode NFC, bỏ khoảng trắng thừa, chuyển chữ thường bằng `Locale.ROOT`; không bỏ dấu nghĩa tiếng Việt. `khoa_kiem_tra_trung` là SHA-256 của từ + ký tự phân cách + từ loại + ký tự phân cách + nghĩa.
- Xóa mềm nội dung để giữ lịch sử. Xóa vật lý theo thời hạn lưu và quy trình kiểm tra tham chiếu; xóa tài khoản có quy trình riêng.

### 3.3 Thời gian và chuỗi ngày học

- Máy chủ/cơ sở dữ liệu dùng UTC với `DATETIME(3)`; API trả ISO-8601 có `Z`. Giờ nhắc học là giờ địa phương, không ghi giờ UTC vào trường đó.
- Múi giờ theo IANA, ví dụ `Asia/Ho_Chi_Minh`. Sự kiện lưu `ngay_dia_phuong` và múi giờ lúc phát sinh; đổi múi giờ không viết lại lịch sử.
- Ngày đủ điều kiện chuỗi học khi có ít nhất một lượt ôn hợp lệ hoặc một bài luyện đã nộp. Đăng nhập, lật thẻ, nghe âm thanh hoặc gọi AI không tự tạo ngày học.
- Chuỗi hiện tại kết thúc hôm nay; nếu hôm nay chưa học có thể tính chuỗi kết thúc hôm qua. Cũ hơn thì bằng 0. Chuỗi dài nhất tính từ các ngày học không trùng nhau.
- Đổi múi giờ tối đa một lần/24 giờ; không cấp thưởng lần hai cho cùng cửa sổ thưởng. Thời gian học hợp nhất các khoảng hoạt động giữa nhiều tab/phiên để không đếm đôi.
- Chỉ tính thời gian khi trang hiển thị và có tương tác gần đây; mỗi khoảng tối đa 60 giây. Không tính toàn bộ thời gian mở tab.
- Thẻ đạt tiêu chí ghi nhớ khi ở `REVIEW`, khoảng ôn ≥21 ngày, nhớ thành công ≥3 lần liên tiếp và không tạm ngưng. Đây là chỉ báo ôn tập, chưa phải làm chủ mọi kỹ năng.

### 3.4 Hạn mức và điểm thưởng

- Từ mới mặc định 10/ngày, cho chọn 0–100; học mặc định 15 phút/ngày, cho chọn 1–240 phút.
- Một thẻ chỉ tiêu thụ một suất từ mới khi giới thiệu lần đầu, không đếm hai lần vì học hai chiều.
- Giữ trước hạn mức AI trước khi gọi dịch vụ; giới hạn theo tính năng và tổng chi phí. Hết thời gian chờ vẫn có thể phát sinh phí, nên giữ trạng thái chưa xác định để đối soát.
- Thưởng ôn tối đa một lần/thẻ/ngày; thưởng luyện tối đa một lần/thẻ/dạng bài/ngày. Lặp bước học hoặc bài dễ không tạo điểm mới.
- Đề xuất: ôn hợp lệ 5 XP, bài cố định đúng 3 XP, tổng tối đa 200 XP/ngày gồm cả thử thách. Chỉ cấp khi có kết quả, không cấp vì mở đáp án.
- Điểm bài viết AI hoặc phát âm không tự thay lịch SRS. Lịch ôn chỉ đổi khi người học đánh giá thẻ trong phiên học.

## 4. Kiến trúc hệ thống

### 4.1 Thành phần

```text
Trình duyệt máy tính / điện thoại
              |
            HTTPS
              v
     Nginx — máy chủ trung gian
        | /                    | /api, /oauth2, /login/oauth2
        v                      v
     Next.js              Spring Boot API
        |                      |-- MySQL: dữ liệu, tác vụ, sự kiện
        +-- API nội bộ --------|-- Redis: phiên, bộ nhớ đệm, giới hạn tần suất
                               |-- Kho S3: ảnh và âm thanh
                               |
                   Tiến trình xử lý nền Spring Boot
                               |-- Dịch vụ email
                               |-- API AI / tạo giọng đọc
                               +-- API đánh giá phát âm
```

Next.js dùng App Router và JavaScript; dựng trang danh mục ở máy chủ, xử lý lật thẻ/âm thanh/ghi âm ở trình duyệt. Spring Boot là nơi duy nhất xử lý nghiệp vụ và truy cập MySQL. Giao diện không giữ khóa AI.

Dùng cùng tên miền qua Nginx để đơn giản hóa cookie và chống giả mạo yêu cầu. Nội dung cá nhân trả `Cache-Control: private, no-store`; không lưu chung phản hồi chứa dữ liệu người dùng. Tham khảo [hướng dẫn tự triển khai Next.js](https://nextjs.org/docs/app/guides/self-hosting).

### 4.2 Công nghệ và lựa chọn thay thế

| Thành phần | Lựa chọn | Lý do / phương án khác |
|---|---|---|
| Giao diện | Next.js App Router, JavaScript | Thuận tiện dựng trang danh mục và tối ưu tìm kiếm; ứng dụng một trang là lựa chọn đơn giản hơn |
| Trạng thái giao diện | TanStack Query; trạng thái cục bộ cho lật thẻ | Không đưa toàn bộ dữ liệu máy chủ vào kho trạng thái chung |
| Biểu mẫu | React Hook Form, Zod | Máy chủ vẫn kiểm tra dữ liệu bằng Bean Validation |
| Máy chủ | Spring Boot, Spring MVC, Spring Security, Spring Data JPA | Chưa cần WebFlux; tách xử lý chậm sang tiến trình nền |
| Java | Java 21 LTS; Spring Boot bản chính thức còn được hỗ trợ | Khóa phiên bản cụ thể và kiểm tra tương thích thư viện lúc khởi tạo dự án |
| Cơ sở dữ liệu | MySQL 8.4 LTS, InnoDB, utf8mb4 | Đúng yêu cầu; chưa cần đổi sang PostgreSQL |
| Cập nhật cấu trúc | Flyway; Hibernate `ddl-auto=validate` | Không tự sửa cấu trúc dữ liệu trong môi trường thật |
| Phiên và bộ nhớ đệm | Redis, Spring Session | Lưu phiên trong bộ nhớ ứng dụng không phù hợp nhiều bản chạy |
| Tác vụ nền | Hàng đợi bền vững MySQL; quyền xử lý có thời hạn; bảng sự kiện sau giao dịch | Chỉ thêm Kafka/RabbitMQ khi tải và số thành phần nhận sự kiện tăng |
| Tệp | Kho tương thích S3; MinIO ở máy cá nhân | Kho đối tượng quản lý ảnh/âm thanh tốt hơn cơ sở dữ liệu |
| Tìm kiếm | MySQL FULLTEXT và chỉ mục lọc | Elasticsearch dành cho giai đoạn cần chất lượng tìm kiếm cao hơn |
| Triển khai | Docker Compose cho bản minh họa | Kubernetes chưa cần với nhóm nhỏ |

Tài liệu Spring Boot được đối chiếu khi thiết kế ghi bản 4.1.1 yêu cầu Java từ 17. Khóa bản vá cụ thể khi tạo dự án; kiểm tra Node LTS tương thích với Next.js, SDK của dịch vụ ngoài và chính sách hỗ trợ. Tài liệu này không thay tệp khóa phiên bản thư viện.

### 4.3 Phân chia nghiệp vụ

| Mô-đun | Dữ liệu chính | Trách nhiệm |
|---|---|---|
| Tài khoản | User, AuthIdentity, AuthToken | Đăng nhập, phân quyền, liên kết và xóa tài khoản |
| Nội dung | Deck, Card, Example, MediaAsset | Bộ thẻ, ví dụ, tệp, quyền, sao chép, CSV |
| Danh mục | Topic, Tag, Deck | Tìm kiếm, xem trước, trình độ và nguồn |
| Học | UserCardProgress, StudySession, ReviewLog | SRS, hạn mức từ mới, lịch sử |
| Luyện tập | PracticeSet, Question, Attempt, Answer | Tạo đề, chấm cố định, lịch sử |
| Cá nhân hóa | Notebook, SkillMastery, Recommendation | Lỗi, cặp nhầm, mức làm chủ, lý do gợi ý |
| AI | AiRequest, Candidate, UsageBucket | Nhà cung cấp, chỉ dẫn, hạn mức, đầu ra |
| Phát âm | PronunciationAttempt | Bản ghi, điểm và phản hồi |
| Duy trì học | DailyActivity, XpLedger, Challenge, Notification | Thống kê, chuỗi ngày, thưởng và nhắc |
| Quản trị | Report, AuditLog, SystemSetting | Kiểm duyệt, cấu hình và nhật ký |

Mô-đun gọi nhau qua dịch vụ, không trực tiếp sửa lớp truy cập dữ liệu của mô-đun khác. Dữ liệu nghiệp vụ chính ghi nhất quán trong giao dịch MySQL; email, AI và thống kê có thể hoàn tất sau. Bảng `su_kien_cho_gui` ghi sự kiện cùng giao dịch để không mất tác vụ sau khi dữ liệu đã lưu.

## 5. Thiết kế giao diện người dùng

### 5.1 Các trang

| Đường dẫn | Nội dung |
|---|---|
| `/`, `/kham-pha`, `/bo-the/[id]` | Giới thiệu; danh mục bộ công khai (UI14); xem trước bộ (UI09) |
| `/dang-ky`, `/dang-nhap`, `/xac-thuc-email` | Đăng ký, đăng nhập, xác thực và gửi lại email (UI01–UI03) |
| `/quen-mat-khau`, `/dat-lai-mat-khau` | Quên/đặt lại mật khẩu; không ghi mã xác thực vào dữ liệu theo dõi (UI04) |
| `/cai-dat` | Thiết lập học và nhắc (UI06); lần đầu chạy theo từng bước: trình độ → mục tiêu/chủ đề → kế hoạch → giờ nhắc |
| `/hoc` | Trang chính sau đăng nhập (UI15): thẻ đến hạn, mục tiêu, chuỗi ngày và gợi ý có lý do |
| `/thu-vien`, `/bo-the/moi`, `/bo-the/[id]/sua`, `/bo-the/[id]/the`, `/bo-the/[id]/the/moi`, `/the/[id]/sua` | Quản lý bộ/thẻ, yêu thích, sao chép, nhãn (UI08, UI10–UI12) |
| `/bo-the/[id]/du-lieu` | Nhập/xuất CSV, xem trước lỗi, xác nhận, kết quả và chia sẻ (UI13) |
| `/hoc/phien/[id]`, `/hoc/phien/[id]/tong-ket`, `/tien-do` | Học; tổng kết; quản lý tiến độ và học lại sau nghỉ (UI16–UI18) |
| `/luyen-tap`, `/luyen-tap/lan-lam/[id]`, `/luyen-tap/lan-lam/[id]/ket-qua`, `/luyen-tap/lich-su` | Chọn dạng bài, làm bài, kết quả, lịch sử, luyện câu sai (UI19–UI22) |
| `/ai/tao-the`, `/ai/yeu-cau/[id]/the-nhap`, `/ai/tro-ly`, `/ai/lich-su` | Tạo thẻ nháp, chọn/sửa/lưu, trợ lý ngữ cảnh và lịch sử AI (UI23–UI26) |
| `/so-tay`, `/diem-yeu`, `/goi-y` | Từ khó, lỗi, kỹ năng, cặp nhầm và lý do gợi ý (UI27–UI29) |
| `/phat-am`, `/phat-am/lich-su` | Ghi âm, nghe lại, điểm, lịch sử và xóa (UI35–UI36) |
| `/thong-ke`, `/bao-cao-tuan`, `/thanh-tich`, `/thu-thach`, `/thong-bao` | Biểu đồ, báo cáo tuần, XP, huy hiệu, thử thách và thông báo (UI30–UI34) |
| `/ho-so`, `/cai-dat`, `/cai-dat/xoa-tai-khoan` | Hồ sơ và chuỗi ngày, kế hoạch/thông báo, xóa dữ liệu (UI05–UI07) |
| `/quan-tri/*` | Tài khoản, nội dung, báo cáo, hạn mức, chi phí, thông báo và nhật ký (UI37–UI42) |
| `/ai/hoi-thoai` | Hội thoại AI (thuộc UI26); chỉ mở ở giai đoạn mở rộng |

Đường dẫn trang dùng tiếng Việt không dấu và khớp bảng mã UI trong SCI_SE_5; đường dẫn API vẫn giữ tiếng Anh dưới tiền tố `/api/v1`.

### 5.2 Tương tác

- Phím Space lật thẻ; phím 1–4 đánh giá sau khi lật. Không tự gửi kết quả bằng phím mũi tên; tắt phím tắt khi đang nhập liệu hoặc mở hộp thoại. Tôn trọng `prefers-reduced-motion`.
- Mặt trước hiện từ hoặc nghĩa theo chiều học; mặt sau có IPA, nghĩa, ví dụ và bản dịch. Âm thanh chỉ phát khi người dùng thao tác; lỗi âm thanh không chặn đánh giá thẻ.
- Mất mạng giữa phiên: giữ kết quả chưa gửi trong bộ nhớ, hiện “Chưa lưu”, thử lại cùng khóa chống trùng. Không coi là hỗ trợ đồng bộ ngoại tuyến nhiều ngày.
- Thẻ AI có ô chọn, ô sửa từng trường, cảnh báo và nút “Lưu thẻ đã chọn”. Lỗi AI giữ lại đoạn văn để người dùng chủ động thử lại.
- Ghép từ hỗ trợ nhấn chọn/bàn phím ngoài kéo thả. Ghi âm xin quyền micrô khi bấm, có đồng hồ, dừng và ghi lại; chỉ gửi API khi người dùng đồng ý.
- Đổi trang không mất tác vụ đang chạy trên máy chủ; mở lại trong lịch sử. Tài khoản đang xóa không được tạo tác vụ mới.

### 5.3 Cấu trúc mã nguồn

```text
frontend/
  src/app/(public)/, (auth)/, (learner)/, admin/
  src/features/auth|decks|study|practice|ai|notebook|speaking|stats/
  src/components/ui/
  src/lib/api-client.js, csrf.js, date-time.js
  src/lib/schemas/        # lược đồ Zod dùng chung; mô tả kiểu bằng JSDoc
  tests/e2e/
```

Khi dựng trang tại máy chủ, Next.js gọi API nội bộ và chuyển tiếp cookie đúng đích; không chuyển cookie tới URL do người dùng nhập. Chặn trang ở giao diện chỉ cải thiện trải nghiệm, không thay kiểm tra quyền tại máy chủ. Khóa bộ nhớ đệm có mã người dùng để tránh hiện dữ liệu của tài khoản trước sau đăng xuất.

## 6. Thiết kế phía máy chủ

```text
backend/src/main/java/.../
  identity/{api,application,domain,infrastructure}
  content/{api,application,domain,infrastructure}
  learning/{api,application,domain,infrastructure}
  practice/{api,application,domain,infrastructure}
  ai/{api,application,domain,infrastructure}
  personalization/, speaking/, engagement/, administration/
  shared/{security,errors,storage,outbox,idempotency,clock}
backend/src/main/resources/db/migration/
```

- Lớp tiếp nhận yêu cầu nhận DTO, không trả trực tiếp đối tượng JPA. Dịch vụ nghiệp vụ quản lý giao dịch; phần tính SRS và chấm bài không gọi HTTP.
- JPA dùng cho thao tác dữ liệu thông thường; SQL trực tiếp dùng cho chọn thẻ đến hạn, khóa dữ liệu, tổng hợp và nhận tác vụ. Tránh truy vấn lặp N+1; không tải toàn bộ thẻ khi chỉ cần thông tin bộ.
- `@Version` trên bộ/thẻ/tiến độ. Khi sửa, gửi `If-Match` hoặc `expectedVersion`; lệch phiên bản trả 409/412, không ghi đè âm thầm.
- Quyền nhận tác vụ nền có thời hạn 120 giây, được gia hạn định kỳ. Chỉ tiến trình còn giữ quyền hợp lệ được ghi hoàn tất; dùng `ma_quyen_xu_ly` để loại kết quả từ tiến trình đã mất quyền.
- Không giữ giao dịch cơ sở dữ liệu trong lúc gọi AI, email, kho tệp hoặc API phát âm.
- Bộ nhớ đệm truy vấn Redis có thể bỏ qua khi lỗi; Redis lưu phiên bị lỗi thì trả 503, không tự cho truy cập bằng phiên không kiểm soát.

### 6.1 Chuyển trạng thái

| Đối tượng | Trạng thái và ý nghĩa |
|---|---|
| Người dùng | `PENDING_VERIFICATION` chờ xác thực → `ACTIVE` hoạt động; có thể `LOCKED` bị khóa; yêu cầu xóa → `PENDING_DELETION` |
| Bộ thẻ | `DRAFT` nháp → `PUBLISHED` công bố → `ARCHIVED` lưu trữ; `thoi_diem_xoa` ghi thời điểm xóa mềm |
| Kiểm duyệt | `NOT_REQUIRED` không cần; `PENDING` chờ; `APPROVED` duyệt; `REJECTED` từ chối; `HIDDEN` ẩn |
| Tiến độ | `NEW` mới → `LEARNING` học → `REVIEW` ôn → `RELEARNING` học lại; tạm ngưng là cờ riêng |
| Phiên học | `ACTIVE` đang học → `COMPLETED` hoàn thành hoặc `ABANDONED` dừng |
| Tác vụ | `QUEUED` chờ → `RUNNING` chạy → `SUCCEEDED` thành công / `FAILED` lỗi / `CANCELLED` hủy |
| Thẻ nháp AI | `PENDING` chờ chọn → `ACCEPTED` lưu hoặc `REJECTED` bỏ |
| Yêu cầu xóa | `REQUESTED` đã yêu cầu → `PROCESSING` đang xóa → `COMPLETED` xong / `FAILED` lỗi; chỉ hủy khi chưa xử lý |

## 7. Thiết kế cơ sở dữ liệu

### 7.1 Quy ước

- Dùng InnoDB và `utf8mb4_0900_ai_ci` cho nội dung. Khóa email/từ đã chuẩn hóa dùng so sánh nhị phân hoặc giá trị băm để so khớp rõ ràng.
- Khóa chính nội bộ là `BIGINT UNSIGNED`. Trả ID dạng **chuỗi** trong JSON vì JavaScript không biểu diễn chính xác mọi giá trị BIGINT bằng Number.
- Thời điểm `created_at`, `updated_at` dùng UTC; `DATE` là ngày địa phương đã chốt khi phát sinh sự kiện.
- Trạng thái quan trọng có ràng buộc `CHECK`; giá trị mở rộng còn lại kiểm tra bằng kiểu liệt kê Java và Bean Validation. `CHECK` không thay kiểm tra quyền sở hữu liên bảng.
- JSON chỉ dùng cho nội dung lưu tại một thời điểm, cấu hình, câu hỏi có cấu trúc, kết quả dịch vụ và dữ liệu sự kiện. Quan hệ người/bộ/thẻ và chỉ số cần tìm kiếm vẫn có cột riêng.
- Khóa ngoại mặc định ngăn xóa khi còn tham chiếu. Xóa dây chuyền chỉ áp dụng bảng con/bảng liên kết phù hợp; không dùng một lệnh xóa dây chuyền thay quy trình xóa tài khoản.
- Báo cáo có các khóa đích riêng và chỉ cho đúng một đích. Nhật ký/sự kiện có định danh lịch sử có chủ ý, không giả làm khóa ngoại đa loại đối tượng.

### 7.2 Quan hệ chính

```text
nguoi_dung 1--1 ho_so_nguoi_dung, tuy_chon_hoc
nguoi_dung N--N vai_tro qua nguoi_dung_vai_tro; nguoi_dung N--N chu_de qua chu_de_quan_tam
nguoi_dung 1--N danh_tinh_dang_nhap, ma_xac_thuc, yeu_cau_xoa_tai_khoan
nguoi_dung 1--N bo_the 1--N the 1--N vi_du_cua_the
bo_the N--N chu_de qua bo_the_chu_de; the N--N nhan qua the_nhan
the N--N tep_da_phuong_tien qua tep_cua_the
vi_du_cua_the N--N tep_da_phuong_tien qua tep_cua_vi_du
nguoi_dung N--N bo_the qua bo_the_yeu_thich; bo_the 1--N lien_ket_chia_se_bo_the
bo_the 1--N lan_nhap_du_lieu 1--N dong_nhap_du_lieu
nguoi_dung + the + chieu_hoc -> tien_do_hoc_the
phien_hoc 1--N muc_the_trong_phien 1--1 lich_su_on_tap
tien_do_hoc_the 1--N lich_su_on_tap
de_luyen_tap 1--N cau_hoi_luyen_tap; de_luyen_tap 1--N lan_lam_bai
lan_lam_bai 1--N cau_tra_loi N--1 cau_hoi_luyen_tap
nguoi_dung + the -> muc_so_tay; nguoi_dung + the + ky_nang -> muc_do_lam_chu_tu
nguoi_dung 1--N loi_hoc_tap, cap_tu_de_nham, goi_y_hoc_tap
nguoi_dung 1--N yeu_cau_ai 1--N the_nhap_ai
yeu_cau_ai 1--1 han_muc_ai_giu_truoc N--1 han_muc_su_dung_ai
yeu_cau_ai 1--N luot_goi_api; yeu_cau_ai 1--1 lan_danh_gia_phat_am
lan_danh_gia_phat_am 1--N ket_qua_phat_am_tung_tu
nguoi_dung 1--N cuoc_hoi_thoai_ai 1--N tin_nhan_ai
nguoi_dung 1--N su_kien_hoat_dong, so_diem_kinh_nghiem, thong_bao, bao_cao_hang_tuan
nguoi_dung + ngay_dia_phuong -> thong_ke_hang_ngay, han_muc_hoc_hang_ngay
nguoi_dung N--N huy_hieu qua huy_hieu_nguoi_dung
nguoi_dung N--N ky_thu_thach qua tien_do_thu_thach
```

Mô hình có **67 bảng**. Không cần triển khai tất cả màn hình cùng lúc; chia theo giai đoạn nhưng giữ quan hệ dữ liệu nhất quán.

### 7.3 Từ điển dữ liệu

Mục 8 định nghĩa từng cột, kiểu, giá trị mặc định, khả năng nhận NULL, khóa chính/ngoại, ràng buộc và chỉ mục.

| Nhóm | Tên bảng | Ý nghĩa |
|---|---|---|
| Tài khoản | `nguoi_dung`, `vai_tro`, `nguoi_dung_vai_tro` | Người dùng, thông tin đăng nhập trực tiếp, trạng thái và phân quyền |
| Xác thực | `danh_tinh_dang_nhap`, `ma_xac_thuc` | Danh tính Google/Facebook và mã xác thực/đặt lại đã băm |
| Hồ sơ | `ho_so_nguoi_dung`, `tuy_chon_hoc`, `chu_de_quan_tam` | Hồ sơ, trình độ, kế hoạch, thông báo, chủ đề |
| Quyền riêng tư | `yeu_cau_xoa_tai_khoan` | Yêu cầu xóa, thời hạn, trạng thái, mốc xử lý |
| Tệp | `tep_da_phuong_tien` | Khóa tệp, loại, kích thước, nguồn gốc, quyền truy cập |
| Nội dung | `chu_de`, `bo_the`, `bo_the_chu_de`, `nhan`, `the`, `the_nhan` | Bộ/thẻ, phân loại, nguồn, độ khó, chống trùng |
| Ví dụ và tệp | `vi_du_cua_the`, `tep_cua_the`, `tep_cua_vi_du` | Câu ví dụ, bản dịch, ảnh, âm thanh từ/câu |
| Thư viện | `bo_the_yeu_thich`, `lien_ket_chia_se_bo_the` | Yêu thích và liên kết có thể thu hồi |
| CSV | `lan_nhap_du_lieu`, `dong_nhap_du_lieu` | Dữ liệu nhập tạm và lỗi từng dòng |
| Hạn mức học | `han_muc_hoc_hang_ngay`, `the_da_gioi_thieu` | Suất từ mới và thẻ đã giới thiệu, tránh đếm hai chiều |
| Phiên học | `phien_hoc`, `muc_the_trong_phien` | Hàng đợi thẻ, nội dung lúc học, suất giữ trước |
| Ôn tập | `tien_do_hoc_the`, `lich_su_on_tap` | Lịch ôn hiện tại và lịch sử trước/sau mỗi lượt |
| Đề luyện | `de_luyen_tap`, `cau_hoi_luyen_tap` | Đề/câu hỏi đã cố định, đáp án chấm giữ ở máy chủ |
| Bài làm | `lan_lam_bai`, `cau_tra_loi` | Lần làm, câu trả lời, điểm, phản hồi, trạng thái chấm |
| Lỗi và sổ tay | `muc_so_tay`, `loi_hoc_tap` | Từ khó và minh chứng lỗi |
| Cá nhân hóa | `muc_do_lam_chu_tu`, `cap_tu_de_nham`, `goi_y_hoc_tap` | Kỹ năng, cặp nhầm, lý do đề xuất |
| Hạn mức AI | `chinh_sach_ai`, `han_muc_su_dung_ai`, `han_muc_ai_giu_truoc` | Chính sách, số lượt/đơn vị văn bản/giây âm thanh/chi phí |
| Nội dung AI | `yeu_cau_ai`, `the_nhap_ai`, `phan_hoi_ai` | Tác vụ, đầu vào/đầu ra, nháp, phản hồi |
| Hội thoại | `cuoc_hoi_thoai_ai`, `tin_nhan_ai` | Hội thoại văn bản mở rộng |
| Dịch vụ ngoài | `luot_goi_api` | Lượt gọi, độ trễ, lỗi, mức dùng và chi phí; không chứa khóa API |
| Phát âm | `lan_danh_gia_phat_am`, `ket_qua_phat_am_tung_tu` | Văn bản mẫu, bản ghi, điểm tổng/từng từ/âm vị |
| Thống kê | `su_kien_hoat_dong`, `thong_ke_hang_ngay`, `bao_cao_hang_tuan` | Minh chứng, tổng hợp ngày và báo cáo tuần |
| Thành tích | `so_diem_kinh_nghiem`, `huy_hieu`, `huy_hieu_nguoi_dung` | Sổ thưởng, huy hiệu và lần cấp duy nhất |
| Thử thách | `mau_thu_thach`, `ky_thu_thach`, `tien_do_thu_thach` | Mẫu, kỳ áp dụng và tiến độ |
| Thông báo | `thong_bao`, `luot_gui_thong_bao`, `thong_bao_he_thong` | Hộp thông báo, gửi ngoài và thông báo hệ thống |
| Quản trị | `bao_cao_noi_dung`, `nhat_ky_quan_tri`, `cai_dat_he_thong` | Kiểm duyệt, nhật ký và cấu hình có phiên bản |
| Hạ tầng | `tac_vu_nen`, `su_kien_cho_gui`, `ban_ghi_chong_trung` | Tác vụ bền vững, sự kiện sau giao dịch, chống ghi trùng |

### 7.4 Chỉ mục và nhất quán

- Hàng đợi ôn dùng `(nguoi_dung_id,chieu_hoc,tam_ngung,trang_thai_hoc,thoi_diem_den_han)`; lọc bộ qua thẻ chưa xóa.
- Thư viện dùng chủ sở hữu/thời điểm sửa; danh mục dùng quyền hiển thị/kiểm duyệt/trình độ. FULLTEXT tìm tên/mô tả; từ ngắn dùng tìm tiền tố có giới hạn vì FULLTEXT có từ bị loại và độ dài tối thiểu.
- Lịch sử dùng người/thời điểm/ID; phân trang theo mốc bản ghi. Mặc định 20, tối đa 100 mục/lần.
- Tác vụ dùng trạng thái/thời điểm chạy/quyền xử lý. Nhận bằng `SELECT ... FOR UPDATE SKIP LOCKED`, giữ giao dịch ngắn.
- Chưa phân vùng bảng lịch sử ngay bản đầu vì cần cân nhắc hạn chế khóa ngoại MySQL; lưu trữ dài hạn sau khi đo tải.
- Dịch vụ phải kiểm tra thẻ thuộc bộ, nhãn thuộc cùng bộ, tiến độ/mục phiên cùng người và chiều, câu hỏi thuộc đề của lần làm, quyền tệp và quyền với thẻ AI. Khóa ngoại đơn lẻ không thay kiểm tra này; có thể bổ sung khóa ngoại nhiều cột khi cần.

## 8. Mã SQL tạo cơ sở dữ liệu

Khối SQL dưới đây giữ nguyên cấu trúc đã kiểm tra, tạo **67 bảng** theo thứ tự phụ thuộc. Dùng làm nền cho Flyway V1; tách các lần cập nhật sau cho dữ liệu mẫu và chỉ mục bổ sung. Nếu cơ sở dữ liệu được cấp sẵn, bỏ `CREATE DATABASE` và điều chỉnh `USE`. Không tắt kiểm tra khóa ngoại.

```sql
CREATE DATABASE IF NOT EXISTS vocab_learning
  CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE vocab_learning;
SET time_zone = '+00:00';

CREATE TABLE nguoi_dung (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(254) NOT NULL,
  email_chuan_hoa VARCHAR(254) COLLATE utf8mb4_bin NOT NULL,
  password_hash VARCHAR(255) NULL,
  trang_thai VARCHAR(30) NOT NULL DEFAULT 'PENDING_VERIFICATION',
  thoi_diem_xac_thuc_email DATETIME(3) NULL,
  thoi_diem_dang_nhap_gan_nhat DATETIME(3) NULL,
  phien_ban_xac_thuc BIGINT NOT NULL DEFAULT 0,
  ly_do_khoa VARCHAR(500) NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  UNIQUE KEY uk_nguoi_dung_email (email_chuan_hoa),
  CHECK (trang_thai IN ('PENDING_VERIFICATION','ACTIVE','LOCKED','PENDING_DELETION'))
) ENGINE=InnoDB;

CREATE TABLE vai_tro (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  ma VARCHAR(30) NOT NULL UNIQUE,
  mo_ta VARCHAR(255) NULL
) ENGINE=InnoDB;
CREATE TABLE nguoi_dung_vai_tro (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  vai_tro_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (nguoi_dung_id, vai_tro_id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  FOREIGN KEY (vai_tro_id) REFERENCES vai_tro(id)
) ENGINE=InnoDB;
CREATE TABLE danh_tinh_dang_nhap (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  nha_cung_cap VARCHAR(20) NOT NULL,
  dinh_danh_nha_cung_cap VARCHAR(255) COLLATE utf8mb4_bin NOT NULL,
  email_nha_cung_cap VARCHAR(254) NULL,
  thoi_diem_lien_ket DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  UNIQUE KEY uk_danh_tinh (nha_cung_cap,dinh_danh_nha_cung_cap),
  UNIQUE KEY uk_nguoi_dung_nha_cung_cap (nguoi_dung_id,nha_cung_cap),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  CHECK (nha_cung_cap IN ('GOOGLE','FACEBOOK'))
) ENGINE=InnoDB;
CREATE TABLE ma_xac_thuc (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  muc_dich VARCHAR(30) NOT NULL,
  ma_xac_thuc_bam BINARY(32) NOT NULL UNIQUE,
  thoi_diem_het_han DATETIME(3) NOT NULL,
  thoi_diem_da_su_dung DATETIME(3) NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  KEY ix_ma_xac_thuc_nguoi_dung (nguoi_dung_id,muc_dich,thoi_diem_het_han),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  CHECK (muc_dich IN ('VERIFY_EMAIL','RESET_PASSWORD','LINK_IDENTITY','DELETE_CONFIRM'))
) ENGINE=InnoDB;

CREATE TABLE tep_da_phuong_tien (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  chu_so_huu_id BIGINT UNSIGNED NULL,
  khoa_doi_tuong_luu_tru VARCHAR(512) COLLATE utf8mb4_bin NOT NULL UNIQUE,
  mime_type VARCHAR(100) NOT NULL,
  loai_tep VARCHAR(20) NOT NULL,
  kich_thuoc_byte BIGINT UNSIGNED NOT NULL,
  thoi_luong_ms INT UNSIGNED NULL,
  ma_bam_noi_dung BINARY(32) NOT NULL,
  pham_vi_truy_cap VARCHAR(20) NOT NULL DEFAULT 'PRIVATE',
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'UPLOADING',
  nguon_goc_tep VARCHAR(30) NOT NULL DEFAULT 'USER_UPLOAD',
  ghi_chu_quyen_su_dung VARCHAR(500) NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_xoa DATETIME(3) NULL,
  KEY ix_tep_chu_so_huu (chu_so_huu_id,trang_thai),
  KEY ix_tep_bam (ma_bam_noi_dung),
  FOREIGN KEY (chu_so_huu_id) REFERENCES nguoi_dung(id) ON DELETE SET NULL,
  CHECK (loai_tep IN ('IMAGE','AUDIO')),
  CHECK (pham_vi_truy_cap IN ('PRIVATE','PUBLIC')),
  CHECK (trang_thai IN ('UPLOADING','PROCESSING','READY','REJECTED','DELETED'))
) ENGINE=InnoDB;
CREATE TABLE ho_so_nguoi_dung (
  nguoi_dung_id BIGINT UNSIGNED PRIMARY KEY,
  ten_hien_thi VARCHAR(100) NOT NULL,
  gioi_thieu VARCHAR(500) NULL,
  tep_anh_dai_dien_id BIGINT UNSIGNED NULL,
  trinh_do_tu_danh_gia VARCHAR(10) NOT NULL DEFAULT 'A1',
  muc_tieu_hoc VARCHAR(500) NULL,
  thoi_diem_xong_thiet_lap DATETIME(3) NULL,
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  FOREIGN KEY (tep_anh_dai_dien_id) REFERENCES tep_da_phuong_tien(id) ON DELETE SET NULL,
  CHECK (trinh_do_tu_danh_gia IN ('A1','A2','B1','B2','C1','C2'))
) ENGINE=InnoDB;
CREATE TABLE tuy_chon_hoc (
  nguoi_dung_id BIGINT UNSIGNED PRIMARY KEY,
  so_the_moi_moi_ngay SMALLINT UNSIGNED NOT NULL DEFAULT 10,
  muc_tieu_phut_moi_ngay SMALLINT UNSIGNED NOT NULL DEFAULT 15,
  mui_gio VARCHAR(64) NOT NULL DEFAULT 'Asia/Ho_Chi_Minh',
  thoi_diem_doi_mui_gio DATETIME(3) NULL,
  gio_nhac_dia_phuong TIME NOT NULL DEFAULT '20:00:00',
  bat_nhac_hoc BOOLEAN NOT NULL DEFAULT TRUE,
  dong_y_email_nhac_hoc BOOLEAN NOT NULL DEFAULT FALSE,
  thong_bao_trong_ung_dung BOOLEAN NOT NULL DEFAULT TRUE,
  thong_bao_muc_tieu BOOLEAN NOT NULL DEFAULT TRUE,
  thoi_diem_dong_y_ai DATETIME(3) NULL,
  chieu_hoc_mac_dinh VARCHAR(10) NOT NULL DEFAULT 'EN_VI',
  phien_ban BIGINT NOT NULL DEFAULT 0,
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  CHECK (so_the_moi_moi_ngay <= 100),
  CHECK (muc_tieu_phut_moi_ngay BETWEEN 1 AND 240),
  CHECK (chieu_hoc_mac_dinh IN ('EN_VI','VI_EN'))
) ENGINE=InnoDB;
CREATE TABLE yeu_cau_xoa_tai_khoan (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NULL,
  tham_chieu_doi_tuong BINARY(32) NOT NULL,
  trang_thai_tai_khoan_truoc VARCHAR(30) NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'REQUESTED',
  thoi_diem_yeu_cau DATETIME(3) NOT NULL,
  thoi_diem_duoc_phep_xoa DATETIME(3) NOT NULL,
  thoi_diem_hoan_thanh DATETIME(3) NULL,
  ma_loi_xu_ly VARCHAR(100) NULL,
  moc_xu_ly JSON NULL,
  KEY ix_delete_hang_doi (trang_thai,thoi_diem_duoc_phep_xoa),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE SET NULL,
  CHECK (trang_thai IN ('REQUESTED','PROCESSING','COMPLETED','FAILED','CANCELLED'))
) ENGINE=InnoDB;

CREATE TABLE chu_de (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  cha_id BIGINT UNSIGNED NULL,
  slug VARCHAR(120) NOT NULL UNIQUE,
  ten VARCHAR(150) NOT NULL,
  mo_ta TEXT NULL,
  dang_su_dung BOOLEAN NOT NULL DEFAULT TRUE,
  FOREIGN KEY (cha_id) REFERENCES chu_de(id)
) ENGINE=InnoDB;
CREATE TABLE chu_de_quan_tam (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  chu_de_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (nguoi_dung_id,chu_de_id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  FOREIGN KEY (chu_de_id) REFERENCES chu_de(id)
) ENGINE=InnoDB;
CREATE TABLE bo_the (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  chu_so_huu_id BIGINT UNSIGNED NOT NULL,
  bo_the_nguon_id BIGINT UNSIGNED NULL,
  tieu_de VARCHAR(150) NOT NULL,
  mo_ta TEXT NULL,
  loai_nguon VARCHAR(10) NOT NULL DEFAULT 'USER',
  phan_loai VARCHAR(20) NOT NULL DEFAULT 'GENERAL',
  trinh_do VARCHAR(10) NOT NULL DEFAULT 'A1',
  che_do_hien_thi VARCHAR(10) NOT NULL DEFAULT 'PRIVATE',
  vong_doi VARCHAR(15) NOT NULL DEFAULT 'DRAFT',
  trang_thai_kiem_duyet VARCHAR(20) NOT NULL DEFAULT 'NOT_REQUIRED',
  tep_anh_bia_id BIGINT UNSIGNED NULL,
  so_the INT UNSIGNED NOT NULL DEFAULT 0,
  phien_ban BIGINT NOT NULL DEFAULT 0,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  thoi_diem_xoa DATETIME(3) NULL,
  KEY ix_bo_the_chu_so_huu (chu_so_huu_id,thoi_diem_xoa,updated_at),
  KEY ix_bo_the_danh_muc (che_do_hien_thi,trang_thai_kiem_duyet,vong_doi,trinh_do,thoi_diem_xoa),
  FULLTEXT KEY ft_bo_the (tieu_de,mo_ta),
  FOREIGN KEY (chu_so_huu_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (bo_the_nguon_id) REFERENCES bo_the(id) ON DELETE SET NULL,
  FOREIGN KEY (tep_anh_bia_id) REFERENCES tep_da_phuong_tien(id) ON DELETE SET NULL,
  CHECK (loai_nguon IN ('USER','ADMIN')),
  CHECK (che_do_hien_thi IN ('PRIVATE','PUBLIC')),
  CHECK (vong_doi IN ('DRAFT','PUBLISHED','ARCHIVED')),
  CHECK (trang_thai_kiem_duyet IN ('NOT_REQUIRED','PENDING','APPROVED','REJECTED','HIDDEN')),
  CHECK (trinh_do IN ('A1','A2','B1','B2','C1','C2')),
  CHECK (phan_loai IN ('GENERAL','COMMUNICATION','TOEIC','IELTS','CHILDREN'))
) ENGINE=InnoDB;
CREATE TABLE bo_the_chu_de (
  bo_the_id BIGINT UNSIGNED NOT NULL,
  chu_de_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (bo_the_id,chu_de_id),
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id) ON DELETE CASCADE,
  FOREIGN KEY (chu_de_id) REFERENCES chu_de(id)
) ENGINE=InnoDB;
CREATE TABLE nhan (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  bo_the_id BIGINT UNSIGNED NOT NULL,
  ten VARCHAR(80) NOT NULL,
  ten_chuan_hoa VARCHAR(80) COLLATE utf8mb4_bin NOT NULL,
  UNIQUE KEY uk_bo_the_nhan (bo_the_id,ten_chuan_hoa),
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE the (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  bo_the_id BIGINT UNSIGNED NOT NULL,
  the_nguon_id BIGINT UNSIGNED NULL,
  tu_cum_tu VARCHAR(255) NOT NULL,
  tu_cum_tu_chuan_hoa VARCHAR(255) COLLATE utf8mb4_bin NOT NULL,
  tu_loai VARCHAR(30) NOT NULL DEFAULT 'OTHER',
  nghia_tieng_viet VARCHAR(1000) NOT NULL,
  ipa VARCHAR(255) NULL,
  meo_ghi_nho TEXT NULL,
  do_kho TINYINT UNSIGNED NOT NULL DEFAULT 1,
  thu_tu INT UNSIGNED NOT NULL DEFAULT 0,
  nguon_tao VARCHAR(20) NOT NULL DEFAULT 'MANUAL',
  khoa_kiem_tra_trung BINARY(32) NOT NULL,
  thoi_diem_xoa DATETIME(3) NULL,
  khoa_trung_dang_su_dung BINARY(32) GENERATED ALWAYS AS
    (CASE WHEN thoi_diem_xoa IS NULL THEN khoa_kiem_tra_trung ELSE NULL END) STORED,
  phien_ban BIGINT NOT NULL DEFAULT 0,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  UNIQUE KEY uk_the_trung (bo_the_id,khoa_trung_dang_su_dung),
  KEY ix_the_bo_the (bo_the_id,thoi_diem_xoa,thu_tu),
  KEY ix_the_tu (bo_the_id,tu_cum_tu_chuan_hoa),
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id),
  FOREIGN KEY (the_nguon_id) REFERENCES the(id) ON DELETE SET NULL,
  CHECK (do_kho BETWEEN 1 AND 5),
  CHECK (nguon_tao IN ('MANUAL','CSV','AI','COPY','ADMIN'))
) ENGINE=InnoDB;
CREATE TABLE the_nhan (
  the_id BIGINT UNSIGNED NOT NULL,
  nhan_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (the_id,nhan_id),
  FOREIGN KEY (the_id) REFERENCES the(id) ON DELETE CASCADE,
  FOREIGN KEY (nhan_id) REFERENCES nhan(id) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE vi_du_cua_the (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  the_id BIGINT UNSIGNED NOT NULL,
  cau_tieng_anh TEXT NOT NULL,
  ban_dich_tieng_viet TEXT NOT NULL,
  vi_tri_bat_dau_cho_trong INT UNSIGNED NULL,
  vi_tri_ket_thuc_cho_trong INT UNSIGNED NULL,
  thu_tu INT UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (the_id) REFERENCES the(id) ON DELETE CASCADE,
  CHECK ((vi_tri_bat_dau_cho_trong IS NULL AND vi_tri_ket_thuc_cho_trong IS NULL) OR
    (vi_tri_bat_dau_cho_trong IS NOT NULL AND vi_tri_ket_thuc_cho_trong IS NOT NULL AND vi_tri_ket_thuc_cho_trong > vi_tri_bat_dau_cho_trong))
) ENGINE=InnoDB;
CREATE TABLE tep_cua_the (
  the_id BIGINT UNSIGNED NOT NULL,
  tep_id BIGINT UNSIGNED NOT NULL,
  muc_dich_su_dung VARCHAR(20) NOT NULL,
  giong_doc VARCHAR(20) NULL,
  PRIMARY KEY (the_id,tep_id,muc_dich_su_dung),
  FOREIGN KEY (the_id) REFERENCES the(id) ON DELETE CASCADE,
  FOREIGN KEY (tep_id) REFERENCES tep_da_phuong_tien(id),
  CHECK (muc_dich_su_dung IN ('IMAGE','WORD_AUDIO'))
) ENGINE=InnoDB;
CREATE TABLE tep_cua_vi_du (
  vi_du_id BIGINT UNSIGNED NOT NULL,
  tep_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (vi_du_id,tep_id),
  FOREIGN KEY (vi_du_id) REFERENCES vi_du_cua_the(id) ON DELETE CASCADE,
  FOREIGN KEY (tep_id) REFERENCES tep_da_phuong_tien(id)
) ENGINE=InnoDB;
CREATE TABLE bo_the_yeu_thich (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  bo_the_id BIGINT UNSIGNED NOT NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (nguoi_dung_id,bo_the_id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE lien_ket_chia_se_bo_the (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  bo_the_id BIGINT UNSIGNED NOT NULL,
  ma_xac_thuc_bam BINARY(32) NOT NULL UNIQUE,
  thoi_diem_het_han DATETIME(3) NULL,
  thoi_diem_thu_hoi DATETIME(3) NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE lan_nhap_du_lieu (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  bo_the_id BIGINT UNSIGNED NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'UPLOADED',
  khoa_tep_luu_tru VARCHAR(512) NOT NULL,
  ma_bam_tep BINARY(32) NOT NULL,
  phien_ban_bo_the_mong_doi BIGINT NOT NULL,
  tong_so_dong INT UNSIGNED NOT NULL DEFAULT 0,
  so_dong_hop_le INT UNSIGNED NOT NULL DEFAULT 0,
  so_dong_loi INT UNSIGNED NOT NULL DEFAULT 0,
  so_dong_da_nhap INT UNSIGNED NOT NULL DEFAULT 0,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_het_han DATETIME(3) NOT NULL,
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id)
) ENGINE=InnoDB;
CREATE TABLE dong_nhap_du_lieu (
  lan_nhap_id BIGINT UNSIGNED NOT NULL,
  so_dong INT UNSIGNED NOT NULL,
  du_lieu JSON NOT NULL,
  khoa_kiem_tra_trung BINARY(32) NULL,
  trang_thai VARCHAR(20) NOT NULL,
  danh_sach_loi JSON NULL,
  PRIMARY KEY (lan_nhap_id,so_dong),
  FOREIGN KEY (lan_nhap_id) REFERENCES lan_nhap_du_lieu(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE han_muc_hoc_hang_ngay (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  ngay_dia_phuong DATE NOT NULL,
  mui_gio VARCHAR(64) NOT NULL,
  gioi_han_chot_dau_ngay SMALLINT UNSIGNED NOT NULL,
  so_the_da_gioi_thieu SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  so_suat_giu_truoc SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  phien_ban BIGINT NOT NULL DEFAULT 0,
  PRIMARY KEY (nguoi_dung_id,ngay_dia_phuong),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE the_da_gioi_thieu (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  the_id BIGINT UNSIGNED NOT NULL,
  thoi_diem_gioi_thieu_dau DATETIME(3) NOT NULL,
  ngay_dia_phuong DATE NOT NULL,
  PRIMARY KEY (nguoi_dung_id,the_id),
  KEY ix_gioi_thieu_ngay (nguoi_dung_id,ngay_dia_phuong),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  FOREIGN KEY (the_id) REFERENCES the(id)
) ENGINE=InnoDB;
CREATE TABLE phien_hoc (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  bo_the_id BIGINT UNSIGNED NULL,
  chieu_hoc VARCHAR(10) NOT NULL,
  che_do VARCHAR(20) NOT NULL DEFAULT 'DAILY',
  quy_thoi_gian_giay INT UNSIGNED NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
  mui_gio VARCHAR(64) NOT NULL,
  ngay_dia_phuong DATE NOT NULL,
  so_suat_the_moi_giu_truoc SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  thoi_diem_het_giu_suat DATETIME(3) NULL,
  thoi_diem_bat_dau DATETIME(3) NOT NULL,
  thoi_diem_hoan_thanh DATETIME(3) NULL,
  so_giay_hoat_dong INT UNSIGNED NOT NULL DEFAULT 0,
  KEY ix_phien_nguoi_dung (nguoi_dung_id,thoi_diem_bat_dau),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id) ON DELETE SET NULL,
  CHECK (chieu_hoc IN ('EN_VI','VI_EN')),
  CHECK (trang_thai IN ('ACTIVE','COMPLETED','ABANDONED'))
) ENGINE=InnoDB;
CREATE TABLE tien_do_hoc_the (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  the_id BIGINT UNSIGNED NOT NULL,
  chieu_hoc VARCHAR(10) NOT NULL,
  trang_thai_hoc VARCHAR(15) NOT NULL DEFAULT 'NEW',
  thoi_diem_den_han DATETIME(3) NULL,
  khoang_on_ngay INT UNSIGNED NOT NULL DEFAULT 0,
  he_so_de_nho DECIMAL(5,3) NOT NULL DEFAULT 2.500,
  so_lan_on_thanh_cong INT UNSIGNED NOT NULL DEFAULT 0,
  so_lan_nho_lien_tiep INT UNSIGNED NOT NULL DEFAULT 0,
  so_lan_quen INT UNSIGNED NOT NULL DEFAULT 0,
  buoc_hoc SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  tam_ngung BOOLEAN NOT NULL DEFAULT FALSE,
  phien_ban_lap_lich VARCHAR(30) NOT NULL DEFAULT 'SM2_V1',
  giai_doan_tien_do INT UNSIGNED NOT NULL DEFAULT 0,
  thoi_diem_hoc_dau DATETIME(3) NULL,
  thoi_diem_on_gan_nhat DATETIME(3) NULL,
  phien_ban BIGINT NOT NULL DEFAULT 0,
  UNIQUE KEY uk_tien_do (nguoi_dung_id,the_id,chieu_hoc),
  KEY ix_tien_do_den_han (nguoi_dung_id,chieu_hoc,tam_ngung,trang_thai_hoc,thoi_diem_den_han),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (the_id) REFERENCES the(id),
  CHECK (chieu_hoc IN ('EN_VI','VI_EN')),
  CHECK (trang_thai_hoc IN ('NEW','LEARNING','REVIEW','RELEARNING')),
  CHECK (he_so_de_nho >= 1.300)
) ENGINE=InnoDB;
CREATE TABLE muc_the_trong_phien (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  phien_hoc_id BIGINT UNSIGNED NOT NULL,
  tien_do_id BIGINT UNSIGNED NOT NULL,
  thu_tu_hang_doi INT UNSIGNED NOT NULL,
  phien_ban_the BIGINT NOT NULL,
  noi_dung_luc_hoc JSON NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'PENDING',
  giu_suat_the_moi BOOLEAN NOT NULL DEFAULT FALSE,
  thoi_diem_tra_suat DATETIME(3) NULL,
  UNIQUE KEY uk_phien_thu_tu (phien_hoc_id,thu_tu_hang_doi),
  KEY ix_phien_tien_do (phien_hoc_id,tien_do_id),
  FOREIGN KEY (phien_hoc_id) REFERENCES phien_hoc(id) ON DELETE CASCADE,
  FOREIGN KEY (tien_do_id) REFERENCES tien_do_hoc_the(id)
) ENGINE=InnoDB;
CREATE TABLE lich_su_on_tap (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  tien_do_id BIGINT UNSIGNED NOT NULL,
  muc_the_trong_phien_id BIGINT UNSIGNED NOT NULL,
  khoa_su_kien VARCHAR(80) COLLATE utf8mb4_bin NOT NULL,
  muc_danh_gia TINYINT UNSIGNED NOT NULL,
  trang_thai_truoc JSON NOT NULL,
  trang_thai_sau JSON NOT NULL,
  phien_ban_lap_lich VARCHAR(30) NOT NULL,
  giai_doan_tien_do INT UNSIGNED NOT NULL,
  thoi_diem_on DATETIME(3) NOT NULL,
  ngay_dia_phuong DATE NOT NULL,
  mui_gio VARCHAR(64) NOT NULL,
  thoi_gian_tra_loi_ms INT UNSIGNED NOT NULL,
  UNIQUE KEY uk_on_su_kien (nguoi_dung_id,khoa_su_kien),
  UNIQUE KEY uk_on_item (muc_the_trong_phien_id),
  KEY ix_on_nguoi_dung_thoi_gian (nguoi_dung_id,thoi_diem_on,id),
  KEY ix_on_tien_do (tien_do_id,thoi_diem_on),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (tien_do_id) REFERENCES tien_do_hoc_the(id),
  FOREIGN KEY (muc_the_trong_phien_id) REFERENCES muc_the_trong_phien(id),
  CHECK (muc_danh_gia BETWEEN 1 AND 4)
) ENGINE=InnoDB;

CREATE TABLE de_luyen_tap (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  bo_the_id BIGINT UNSIGNED NULL,
  tieu_de VARCHAR(200) NOT NULL,
  loai_nguon VARCHAR(20) NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
  phien_ban_tao_de VARCHAR(30) NOT NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_xoa DATETIME(3) NULL,
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id) ON DELETE SET NULL
) ENGINE=InnoDB;
CREATE TABLE cau_hoi_luyen_tap (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  de_luyen_tap_id BIGINT UNSIGNED NOT NULL,
  the_id BIGINT UNSIGNED NULL,
  loai_cau_hoi VARCHAR(30) NOT NULL,
  ky_nang VARCHAR(20) NOT NULL,
  noi_dung_cau_hoi JSON NOT NULL,
  dap_an_cham JSON NOT NULL,
  giai_thich_tieng_viet TEXT NULL,
  cach_cham VARCHAR(10) NOT NULL DEFAULT 'FIXED',
  diem_toi_da DECIMAL(6,2) NOT NULL DEFAULT 1.00,
  thu_tu INT UNSIGNED NOT NULL,
  UNIQUE KEY uk_cau_hoi_thu_tu (de_luyen_tap_id,thu_tu),
  FOREIGN KEY (de_luyen_tap_id) REFERENCES de_luyen_tap(id) ON DELETE CASCADE,
  FOREIGN KEY (the_id) REFERENCES the(id) ON DELETE SET NULL,
  CHECK (cach_cham IN ('FIXED','AI')),
  CHECK (diem_toi_da > 0)
) ENGINE=InnoDB;
CREATE TABLE lan_lam_bai (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  de_luyen_tap_id BIGINT UNSIGNED NOT NULL,
  lan_lam_bai_goc_id BIGINT UNSIGNED NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'IN_PROGRESS',
  diem_co_dinh DECIMAL(8,2) NULL,
  diem_co_dinh_toi_da DECIMAL(8,2) NULL,
  diem_ai DECIMAL(8,2) NULL,
  thoi_diem_bat_dau DATETIME(3) NOT NULL,
  thoi_diem_nop DATETIME(3) NULL,
  so_giay_hoat_dong INT UNSIGNED NOT NULL DEFAULT 0,
  phien_ban BIGINT NOT NULL DEFAULT 0,
  KEY ix_lan_lam_lich_su (nguoi_dung_id,thoi_diem_bat_dau,id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (de_luyen_tap_id) REFERENCES de_luyen_tap(id),
  FOREIGN KEY (lan_lam_bai_goc_id) REFERENCES lan_lam_bai(id) ON DELETE SET NULL
) ENGINE=InnoDB;
CREATE TABLE cau_tra_loi (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  lan_thuc_hien_id BIGINT UNSIGNED NOT NULL,
  cau_hoi_id BIGINT UNSIGNED NOT NULL,
  noi_dung_tra_loi JSON NOT NULL,
  tra_loi_dung BOOLEAN NULL,
  diem DECIMAL(6,2) NULL,
  phan_hoi_tieng_viet TEXT NULL,
  trang_thai_cham VARCHAR(20) NOT NULL DEFAULT 'PENDING',
  phien_ban_cham VARCHAR(30) NULL,
  yeu_cau_ai_id BIGINT UNSIGNED NULL,
  thoi_diem_tra_loi DATETIME(3) NOT NULL,
  thoi_gian_tra_loi_ms INT UNSIGNED NOT NULL,
  UNIQUE KEY uk_lan_lam_cau_hoi (lan_thuc_hien_id,cau_hoi_id),
  FOREIGN KEY (lan_thuc_hien_id) REFERENCES lan_lam_bai(id) ON DELETE CASCADE,
  FOREIGN KEY (cau_hoi_id) REFERENCES cau_hoi_luyen_tap(id)
) ENGINE=InnoDB;
CREATE TABLE muc_so_tay (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  the_id BIGINT UNSIGNED NOT NULL,
  loai_nguon VARCHAR(20) NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
  ghi_chu TEXT NULL,
  nhom_loi JSON NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  UNIQUE KEY uk_so_tay (nguoi_dung_id,the_id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (the_id) REFERENCES the(id)
) ENGINE=InnoDB;
CREATE TABLE loi_hoc_tap (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  the_id BIGINT UNSIGNED NOT NULL,
  phan_loai VARCHAR(20) NOT NULL,
  cau_tra_loi_id BIGINT UNSIGNED NULL,
  lich_su_on_id BIGINT UNSIGNED NULL,
  khoa_loai_trung VARCHAR(100) COLLATE utf8mb4_bin NOT NULL,
  minh_chung JSON NOT NULL,
  thoi_diem_phat_sinh DATETIME(3) NOT NULL,
  UNIQUE KEY uk_loi_su_kien (nguoi_dung_id,khoa_loai_trung),
  KEY ix_loi_nguoi_dung (nguoi_dung_id,phan_loai,thoi_diem_phat_sinh),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (the_id) REFERENCES the(id),
  FOREIGN KEY (cau_tra_loi_id) REFERENCES cau_tra_loi(id) ON DELETE SET NULL,
  FOREIGN KEY (lich_su_on_id) REFERENCES lich_su_on_tap(id) ON DELETE SET NULL,
  CHECK (phan_loai IN ('MEANING','SPELLING','LISTENING','USAGE'))
) ENGINE=InnoDB;
CREATE TABLE muc_do_lam_chu_tu (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  the_id BIGINT UNSIGNED NOT NULL,
  ky_nang VARCHAR(20) NOT NULL,
  so_mau_minh_chung INT UNSIGNED NOT NULL DEFAULT 0,
  trong_so_thanh_cong DECIMAL(12,4) NOT NULL DEFAULT 0,
  trong_so_that_bai DECIMAL(12,4) NOT NULL DEFAULT 0,
  diem_lam_chu DECIMAL(5,2) NULL,
  thoi_diem_tinh_toan DATETIME(3) NOT NULL,
  phien_ban_thuat_toan VARCHAR(30) NOT NULL,
  PRIMARY KEY (nguoi_dung_id,the_id,ky_nang),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (the_id) REFERENCES the(id),
  CHECK (diem_lam_chu IS NULL OR diem_lam_chu BETWEEN 0 AND 100)
) ENGINE=InnoDB;
CREATE TABLE cap_tu_de_nham (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  the_a_id BIGINT UNSIGNED NOT NULL,
  the_b_id BIGINT UNSIGNED NOT NULL,
  so_lan_nham INT UNSIGNED NOT NULL DEFAULT 1,
  thoi_diem_nham_gan_nhat DATETIME(3) NOT NULL,
  giai_thich_tieng_viet TEXT NULL,
  PRIMARY KEY (nguoi_dung_id,the_a_id,the_b_id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (the_a_id) REFERENCES the(id),
  FOREIGN KEY (the_b_id) REFERENCES the(id),
  CHECK (the_a_id < the_b_id)
) ENGINE=InnoDB;
CREATE TABLE goi_y_hoc_tap (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  the_id BIGINT UNSIGNED NULL,
  bo_the_id BIGINT UNSIGNED NULL,
  loai_goi_y VARCHAR(30) NOT NULL,
  do_uu_tien DECIMAL(8,3) NOT NULL,
  ma_ly_do VARCHAR(50) NOT NULL,
  du_lieu_ly_do JSON NOT NULL,
  phien_ban_thuat_toan VARCHAR(30) NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_het_han DATETIME(3) NOT NULL,
  KEY ix_goi_y (nguoi_dung_id,trang_thai,do_uu_tien),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (the_id) REFERENCES the(id) ON DELETE SET NULL,
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE chinh_sach_ai (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  tinh_nang VARCHAR(40) NOT NULL UNIQUE,
  duoc_bat BOOLEAN NOT NULL DEFAULT TRUE,
  nha_cung_cap VARCHAR(40) NOT NULL,
  ten_mo_hinh VARCHAR(120) NOT NULL,
  gioi_han_luot_moi_ngay INT UNSIGNED NOT NULL,
  gioi_han_token_moi_ngay INT UNSIGNED NOT NULL,
  gioi_han_giay_am_thanh_moi_ngay INT UNSIGNED NULL,
  gioi_han_chi_phi_usd_moi_ngay DECIMAL(12,6) NOT NULL,
  so_ky_tu_dau_vao_toi_da INT UNSIGNED NOT NULL,
  so_token_dau_ra_toi_da INT UNSIGNED NOT NULL,
  so_giay_am_thanh_toi_da SMALLINT UNSIGNED NULL,
  thoi_gian_cho_giay SMALLINT UNSIGNED NOT NULL,
  phien_ban_chi_dan_ai VARCHAR(40) NOT NULL,
  phien_ban_cau_truc VARCHAR(40) NOT NULL,
  phien_ban BIGINT NOT NULL DEFAULT 0,
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3)
) ENGINE=InnoDB;
CREATE TABLE han_muc_su_dung_ai (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  tinh_nang VARCHAR(40) NOT NULL,
  ngay_tinh_han_muc DATE NOT NULL,
  so_luot_da_dung INT UNSIGNED NOT NULL DEFAULT 0,
  so_luot_giu_truoc INT UNSIGNED NOT NULL DEFAULT 0,
  so_token_da_dung BIGINT UNSIGNED NOT NULL DEFAULT 0,
  so_token_giu_truoc BIGINT UNSIGNED NOT NULL DEFAULT 0,
  so_giay_am_thanh_da_dung DECIMAL(12,3) NOT NULL DEFAULT 0,
  so_giay_am_thanh_giu_truoc DECIMAL(12,3) NOT NULL DEFAULT 0,
  chi_phi_usd_da_dung DECIMAL(12,6) NOT NULL DEFAULT 0,
  chi_phi_usd_giu_truoc DECIMAL(12,6) NOT NULL DEFAULT 0,
  phien_ban BIGINT NOT NULL DEFAULT 0,
  UNIQUE KEY uk_ai_han_muc (nguoi_dung_id,tinh_nang,ngay_tinh_han_muc),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id)
) ENGINE=InnoDB;
CREATE TABLE yeu_cau_ai (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  bo_the_id BIGINT UNSIGNED NULL,
  tinh_nang VARCHAR(40) NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'QUEUED',
  du_lieu_dau_vao JSON NOT NULL,
  ma_bam_dau_vao BINARY(32) NOT NULL,
  du_lieu_dau_ra JSON NULL,
  chinh_sach_luc_tao JSON NOT NULL,
  nha_cung_cap VARCHAR(40) NOT NULL,
  ten_mo_hinh VARCHAR(120) NOT NULL,
  phien_ban_chi_dan_ai VARCHAR(40) NOT NULL,
  phien_ban_cau_truc VARCHAR(40) NOT NULL,
  ma_loi_xu_ly VARCHAR(80) NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_hoan_thanh DATETIME(3) NULL,
  thoi_diem_het_han DATETIME(3) NULL,
  KEY ix_ai_lich_su (nguoi_dung_id,created_at,id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id) ON DELETE SET NULL,
  CHECK (trang_thai IN ('QUEUED','RUNNING','SUCCEEDED','FAILED','CANCELLED'))
) ENGINE=InnoDB;
ALTER TABLE cau_tra_loi ADD CONSTRAINT fk_tra_loi_ai
  FOREIGN KEY (yeu_cau_ai_id) REFERENCES yeu_cau_ai(id) ON DELETE SET NULL;
CREATE TABLE han_muc_ai_giu_truoc (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  yeu_cau_ai_id BIGINT UNSIGNED NOT NULL UNIQUE,
  han_muc_id BIGINT UNSIGNED NOT NULL,
  so_token_giu_truoc INT UNSIGNED NOT NULL,
  so_giay_am_thanh_giu_truoc DECIMAL(12,3) NOT NULL DEFAULT 0,
  chi_phi_usd_giu_truoc DECIMAL(12,6) NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'RESERVED',
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_doi_soat DATETIME(3) NULL,
  FOREIGN KEY (yeu_cau_ai_id) REFERENCES yeu_cau_ai(id),
  FOREIGN KEY (han_muc_id) REFERENCES han_muc_su_dung_ai(id),
  CHECK (trang_thai IN ('RESERVED','SETTLED','RELEASED','UNCERTAIN'))
) ENGINE=InnoDB;
CREATE TABLE the_nhap_ai (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  yeu_cau_ai_id BIGINT UNSIGNED NOT NULL,
  thu_tu INT UNSIGNED NOT NULL,
  noi_dung_the_nhap JSON NOT NULL,
  noi_dung_da_sua JSON NULL,
  loi_kiem_tra JSON NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'PENDING',
  the_da_luu_id BIGINT UNSIGNED NULL,
  phien_ban BIGINT NOT NULL DEFAULT 0,
  UNIQUE KEY uk_the_nhap_thu_tu (yeu_cau_ai_id,thu_tu),
  FOREIGN KEY (yeu_cau_ai_id) REFERENCES yeu_cau_ai(id) ON DELETE CASCADE,
  FOREIGN KEY (the_da_luu_id) REFERENCES the(id) ON DELETE SET NULL,
  CHECK (trang_thai IN ('PENDING','ACCEPTED','REJECTED'))
) ENGINE=InnoDB;
CREATE TABLE phan_hoi_ai (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  yeu_cau_ai_id BIGINT UNSIGNED NOT NULL,
  loai_phan_hoi VARCHAR(30) NOT NULL,
  chi_tiet TEXT NULL,
  trang_thai_kiem_duyet VARCHAR(20) NOT NULL DEFAULT 'OPEN',
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  UNIQUE KEY uk_ai_phan_hoi (nguoi_dung_id,yeu_cau_ai_id,loai_phan_hoi),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (yeu_cau_ai_id) REFERENCES yeu_cau_ai(id) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE cuoc_hoi_thoai_ai (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  chu_de_id BIGINT UNSIGNED NULL,
  tieu_de VARCHAR(200) NOT NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_xoa DATETIME(3) NULL,
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (chu_de_id) REFERENCES chu_de(id) ON DELETE SET NULL
) ENGINE=InnoDB;
CREATE TABLE tin_nhan_ai (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  cuoc_hoi_thoai_id BIGINT UNSIGNED NOT NULL,
  yeu_cau_ai_id BIGINT UNSIGNED NULL,
  vai_tro VARCHAR(20) NOT NULL,
  noi_dung TEXT NOT NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  FOREIGN KEY (cuoc_hoi_thoai_id) REFERENCES cuoc_hoi_thoai_ai(id) ON DELETE CASCADE,
  FOREIGN KEY (yeu_cau_ai_id) REFERENCES yeu_cau_ai(id) ON DELETE SET NULL,
  CHECK (vai_tro IN ('USER','ASSISTANT'))
) ENGINE=InnoDB;
CREATE TABLE luot_goi_api (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NULL,
  yeu_cau_ai_id BIGINT UNSIGNED NULL,
  loai_dich_vu VARCHAR(30) NOT NULL,
  tham_chieu_thao_tac VARCHAR(100) NULL,
  nha_cung_cap VARCHAR(40) NOT NULL,
  ten_mo_hinh VARCHAR(120) NULL,
  yeu_cau_nha_cung_cap_id VARCHAR(200) NULL,
  thu_tu_lan_goi SMALLINT UNSIGNED NOT NULL,
  trang_thai VARCHAR(20) NOT NULL,
  so_token_dau_vao INT UNSIGNED NULL,
  so_token_dau_ra INT UNSIGNED NULL,
  so_giay_am_thanh DECIMAL(10,3) NULL,
  chi_phi_uoc_tinh_usd DECIMAL(12,6) NULL,
  chi_phi_thuc_tra_usd DECIMAL(12,6) NULL,
  don_gia_luc_goi JSON NULL,
  ma_loi VARCHAR(100) NULL,
  do_tre_ms INT UNSIGNED NULL,
  thoi_diem_bat_dau DATETIME(3) NOT NULL,
  thoi_diem_hoan_thanh DATETIME(3) NULL,
  KEY ix_api_chi_so (loai_dich_vu,thoi_diem_bat_dau,trang_thai),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE SET NULL,
  FOREIGN KEY (yeu_cau_ai_id) REFERENCES yeu_cau_ai(id) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE lan_danh_gia_phat_am (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  yeu_cau_ai_id BIGINT UNSIGNED NOT NULL UNIQUE,
  the_id BIGINT UNSIGNED NULL,
  tep_ghi_am_id BIGINT UNSIGNED NULL,
  van_ban_mau TEXT NOT NULL,
  ma_ngon_ngu_vung VARCHAR(20) NOT NULL DEFAULT 'en-US',
  nha_cung_cap VARCHAR(40) NOT NULL,
  phien_ban_cau_hinh_dich_vu VARCHAR(40) NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'QUEUED',
  diem_phat_am DECIMAL(5,2) NULL,
  diem_chinh_xac DECIMAL(5,2) NULL,
  diem_troi_chay DECIMAL(5,2) NULL,
  diem_day_du DECIMAL(5,2) NULL,
  diem_ngu_dieu DECIMAL(5,2) NULL,
  van_ban_nhan_dang TEXT NULL,
  phan_hoi_tieng_viet TEXT NULL,
  ket_qua_goc JSON NULL,
  ma_loi_xu_ly VARCHAR(100) NULL,
  thoi_diem_xoa_ghi_am DATETIME(3) NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_hoan_thanh DATETIME(3) NULL,
  KEY ix_phat_am_lich_su (nguoi_dung_id,created_at,id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (yeu_cau_ai_id) REFERENCES yeu_cau_ai(id),
  FOREIGN KEY (the_id) REFERENCES the(id) ON DELETE SET NULL,
  FOREIGN KEY (tep_ghi_am_id) REFERENCES tep_da_phuong_tien(id) ON DELETE SET NULL,
  CHECK (diem_phat_am IS NULL OR diem_phat_am BETWEEN 0 AND 100)
) ENGINE=InnoDB;
CREATE TABLE ket_qua_phat_am_tung_tu (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  lan_thuc_hien_id BIGINT UNSIGNED NOT NULL,
  thu_tu INT UNSIGNED NOT NULL,
  tu VARCHAR(255) NOT NULL,
  diem_chinh_xac DECIMAL(5,2) NULL,
  loai_loi VARCHAR(50) NULL,
  vi_tri_bat_dau_ms INT UNSIGNED NULL,
  thoi_luong_ms INT UNSIGNED NULL,
  ket_qua_am_vi JSON NULL,
  UNIQUE KEY uk_phat_am_tu (lan_thuc_hien_id,thu_tu),
  FOREIGN KEY (lan_thuc_hien_id) REFERENCES lan_danh_gia_phat_am(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE su_kien_hoat_dong (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  loai_su_kien VARCHAR(40) NOT NULL,
  khoa_loai_trung VARCHAR(120) COLLATE utf8mb4_bin NOT NULL,
  bo_the_id BIGINT UNSIGNED NULL,
  the_id BIGINT UNSIGNED NULL,
  thoi_diem_phat_sinh DATETIME(3) NOT NULL,
  ngay_dia_phuong DATE NOT NULL,
  mui_gio VARCHAR(64) NOT NULL,
  du_lieu JSON NOT NULL,
  UNIQUE KEY uk_hoat_dong (nguoi_dung_id,khoa_loai_trung),
  KEY ix_hoat_dong_nguoi_dung_ngay (nguoi_dung_id,ngay_dia_phuong,loai_su_kien),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id) ON DELETE SET NULL,
  FOREIGN KEY (the_id) REFERENCES the(id) ON DELETE SET NULL
) ENGINE=InnoDB;
CREATE TABLE thong_ke_hang_ngay (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  ngay_dia_phuong DATE NOT NULL,
  so_the_moi INT UNSIGNED NOT NULL DEFAULT 0,
  so_luot_on INT UNSIGNED NOT NULL DEFAULT 0,
  so_cau_dung_co_dinh INT UNSIGNED NOT NULL DEFAULT 0,
  so_cau_cham_co_dinh INT UNSIGNED NOT NULL DEFAULT 0,
  so_giay_hoat_dong INT UNSIGNED NOT NULL DEFAULT 0,
  diem_kinh_nghiem_da_nhan INT UNSIGNED NOT NULL DEFAULT 0,
  muc_tieu_the_moi_luc_chot SMALLINT UNSIGNED NOT NULL,
  muc_tieu_phut_luc_chot SMALLINT UNSIGNED NOT NULL,
  du_dieu_kien_chuoi_ngay BOOLEAN NOT NULL DEFAULT FALSE,
  da_hoan_thanh_muc_tieu BOOLEAN NOT NULL DEFAULT FALSE,
  su_kien_gan_nhat_id BIGINT UNSIGNED NULL,
  phien_ban_tong_hop VARCHAR(30) NOT NULL,
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (nguoi_dung_id,ngay_dia_phuong),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE bao_cao_hang_tuan (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  ngay_dau_tuan DATE NOT NULL,
  mui_gio VARCHAR(64) NOT NULL,
  noi_dung_bao_cao JSON NOT NULL,
  thoi_diem_tao_bao_cao DATETIME(3) NOT NULL,
  UNIQUE KEY uk_hang_tuan_bao_cao (nguoi_dung_id,ngay_dau_tuan),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE so_diem_kinh_nghiem (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  khoa_thuong VARCHAR(150) COLLATE utf8mb4_bin NOT NULL,
  loai_nguon VARCHAR(30) NOT NULL,
  tham_chieu_nguon VARCHAR(100) NOT NULL,
  diem SMALLINT NOT NULL,
  ngay_dia_phuong DATE NOT NULL,
  phien_ban_chinh_sach VARCHAR(30) NOT NULL,
  thoi_diem_nhan_thuong DATETIME(3) NOT NULL,
  UNIQUE KEY uk_kinh_nghiem_thuong (nguoi_dung_id,khoa_thuong),
  KEY ix_kinh_nghiem_ngay (nguoi_dung_id,ngay_dia_phuong),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id)
) ENGINE=InnoDB;
CREATE TABLE huy_hieu (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  ma VARCHAR(60) NOT NULL UNIQUE,
  tieu_de VARCHAR(100) NOT NULL,
  mo_ta VARCHAR(500) NOT NULL,
  quy_tac JSON NOT NULL,
  dang_su_dung BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE=InnoDB;
CREATE TABLE huy_hieu_nguoi_dung (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  huy_hieu_id BIGINT UNSIGNED NOT NULL,
  thoi_diem_nhan_thuong DATETIME(3) NOT NULL,
  minh_chung JSON NOT NULL,
  PRIMARY KEY (nguoi_dung_id,huy_hieu_id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  FOREIGN KEY (huy_hieu_id) REFERENCES huy_hieu(id)
) ENGINE=InnoDB;
CREATE TABLE mau_thu_thach (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  ma VARCHAR(60) NOT NULL UNIQUE,
  tieu_de VARCHAR(150) NOT NULL,
  chu_ky VARCHAR(10) NOT NULL,
  chi_so VARCHAR(40) NOT NULL,
  gia_tri_muc_tieu INT UNSIGNED NOT NULL,
  diem_kinh_nghiem_thuong SMALLINT UNSIGNED NOT NULL,
  dang_su_dung BOOLEAN NOT NULL DEFAULT TRUE,
  CHECK (chu_ky IN ('DAILY','WEEKLY'))
) ENGINE=InnoDB;
CREATE TABLE ky_thu_thach (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  mau_thu_thach_id BIGINT UNSIGNED NOT NULL,
  ngay_bat_dau_ky DATE NOT NULL,
  ngay_ket_thuc_ky DATE NOT NULL,
  quy_tac_luc_tao JSON NOT NULL,
  UNIQUE KEY uk_thu_thach_ky (mau_thu_thach_id,ngay_bat_dau_ky),
  FOREIGN KEY (mau_thu_thach_id) REFERENCES mau_thu_thach(id),
  CHECK (ngay_ket_thuc_ky >= ngay_bat_dau_ky)
) ENGINE=InnoDB;
CREATE TABLE tien_do_thu_thach (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  ky_thu_thach_id BIGINT UNSIGNED NOT NULL,
  gia_tri_hien_tai INT UNSIGNED NOT NULL DEFAULT 0,
  thoi_diem_hoan_thanh DATETIME(3) NULL,
  thoi_diem_cap_thuong DATETIME(3) NULL,
  su_kien_gan_nhat_id BIGINT UNSIGNED NULL,
  PRIMARY KEY (nguoi_dung_id,ky_thu_thach_id),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE,
  FOREIGN KEY (ky_thu_thach_id) REFERENCES ky_thu_thach(id)
) ENGINE=InnoDB;
CREATE TABLE thong_bao (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  loai_thong_bao VARCHAR(40) NOT NULL,
  khoa_loai_trung VARCHAR(120) COLLATE utf8mb4_bin NOT NULL,
  tieu_de VARCHAR(200) NOT NULL,
  noi_dung TEXT NOT NULL,
  duong_dan_hanh_dong VARCHAR(500) NULL,
  thoi_diem_da_doc DATETIME(3) NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  UNIQUE KEY uk_thong_bao (nguoi_dung_id,khoa_loai_trung),
  KEY ix_thong_bao_hop (nguoi_dung_id,thoi_diem_da_doc,created_at),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE luot_gui_thong_bao (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  thong_bao_id BIGINT UNSIGNED NULL,
  kenh_gui VARCHAR(20) NOT NULL,
  khoa_gui_thong_bao VARCHAR(150) COLLATE utf8mb4_bin NOT NULL UNIQUE,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'QUEUED',
  thoi_diem_hen_gui DATETIME(3) NOT NULL,
  thoi_diem_gui DATETIME(3) NULL,
  so_lan_thu SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  tin_nhan_nha_cung_cap_id VARCHAR(200) NULL,
  ma_loi_xu_ly VARCHAR(100) NULL,
  KEY ix_gui_hang_doi (trang_thai,thoi_diem_hen_gui),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id),
  FOREIGN KEY (thong_bao_id) REFERENCES thong_bao(id) ON DELETE SET NULL
) ENGINE=InnoDB;
CREATE TABLE thong_bao_he_thong (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_tao_id BIGINT UNSIGNED NULL,
  tieu_de VARCHAR(200) NOT NULL,
  noi_dung TEXT NOT NULL,
  doi_tuong_nhan VARCHAR(30) NOT NULL DEFAULT 'ALL',
  thoi_diem_bat_dau DATETIME(3) NOT NULL,
  thoi_diem_ket_thuc DATETIME(3) NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
  phien_ban BIGINT NOT NULL DEFAULT 0,
  FOREIGN KEY (nguoi_tao_id) REFERENCES nguoi_dung(id) ON DELETE SET NULL
) ENGINE=InnoDB;
CREATE TABLE bao_cao_noi_dung (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_bao_cao_id BIGINT UNSIGNED NULL,
  bo_the_id BIGINT UNSIGNED NULL,
  the_id BIGINT UNSIGNED NULL,
  yeu_cau_ai_id BIGINT UNSIGNED NULL,
  ly_do VARCHAR(40) NOT NULL,
  chi_tiet TEXT NULL,
  noi_dung_dich_luc_bao_cao JSON NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'OPEN',
  quan_tri_vien_phu_trach_id BIGINT UNSIGNED NULL,
  ghi_chu_xu_ly TEXT NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_xu_ly_xong DATETIME(3) NULL,
  KEY ix_bao_cao_hang_doi (trang_thai,created_at),
  FOREIGN KEY (nguoi_bao_cao_id) REFERENCES nguoi_dung(id) ON DELETE SET NULL,
  FOREIGN KEY (bo_the_id) REFERENCES bo_the(id),
  FOREIGN KEY (the_id) REFERENCES the(id),
  FOREIGN KEY (yeu_cau_ai_id) REFERENCES yeu_cau_ai(id),
  FOREIGN KEY (quan_tri_vien_phu_trach_id) REFERENCES nguoi_dung(id) ON DELETE SET NULL,
  CHECK ((bo_the_id IS NOT NULL) + (the_id IS NOT NULL) + (yeu_cau_ai_id IS NOT NULL) = 1)
) ENGINE=InnoDB;
CREATE TABLE nhat_ky_quan_tri (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nguoi_thuc_hien_id BIGINT UNSIGNED NULL,
  hanh_dong VARCHAR(60) NOT NULL,
  loai_dich VARCHAR(40) NOT NULL,
  tham_chieu_dich VARCHAR(100) NOT NULL,
  du_lieu_truoc JSON NULL,
  du_lieu_sau JSON NULL,
  ma_truy_vet VARCHAR(64) NOT NULL,
  thoi_diem_phat_sinh DATETIME(3) NOT NULL,
  KEY ix_quan_tri_thoi_gian (thoi_diem_phat_sinh,id),
  FOREIGN KEY (nguoi_thuc_hien_id) REFERENCES nguoi_dung(id) ON DELETE SET NULL
) ENGINE=InnoDB;
CREATE TABLE cai_dat_he_thong (
  khoa_cai_dat VARCHAR(100) COLLATE utf8mb4_bin PRIMARY KEY,
  gia_tri JSON NOT NULL,
  phien_ban BIGINT NOT NULL DEFAULT 0,
  nguoi_cap_nhat_id BIGINT UNSIGNED NULL,
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  FOREIGN KEY (nguoi_cap_nhat_id) REFERENCES nguoi_dung(id) ON DELETE SET NULL
) ENGINE=InnoDB;
CREATE TABLE tac_vu_nen (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  loai_tac_vu VARCHAR(40) NOT NULL,
  chu_so_huu_id BIGINT UNSIGNED NULL,
  khoa_loai_trung VARCHAR(150) COLLATE utf8mb4_bin NOT NULL UNIQUE,
  du_lieu JSON NOT NULL,
  trang_thai VARCHAR(20) NOT NULL DEFAULT 'QUEUED',
  thoi_diem_co_the_xu_ly DATETIME(3) NOT NULL,
  tien_trinh_nhan_xu_ly VARCHAR(100) NULL,
  ma_quyen_xu_ly VARCHAR(64) NULL,
  quyen_xu_ly_den_luc DATETIME(3) NULL,
  so_lan_thu SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  so_lan_thu_toi_da SMALLINT UNSIGNED NOT NULL DEFAULT 3,
  ma_loi_gan_nhat VARCHAR(100) NULL,
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  thoi_diem_hoan_thanh DATETIME(3) NULL,
  KEY ix_tac_vu_nhan (trang_thai,thoi_diem_co_the_xu_ly,quyen_xu_ly_den_luc),
  FOREIGN KEY (chu_so_huu_id) REFERENCES nguoi_dung(id) ON DELETE SET NULL
) ENGINE=InnoDB;
CREATE TABLE su_kien_cho_gui (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  su_kien_id VARCHAR(64) COLLATE utf8mb4_bin NOT NULL UNIQUE,
  loai_nhom_du_lieu VARCHAR(40) NOT NULL,
  tham_chieu_nhom_du_lieu VARCHAR(100) NOT NULL,
  loai_su_kien VARCHAR(60) NOT NULL,
  phien_ban_cau_truc INT UNSIGNED NOT NULL,
  du_lieu JSON NOT NULL,
  thoi_diem_phat_sinh DATETIME(3) NOT NULL,
  thoi_diem_da_gui DATETIME(3) NULL,
  so_lan_thu SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  thoi_diem_co_the_xu_ly DATETIME(3) NOT NULL,
  ma_quyen_xu_ly VARCHAR(64) NULL,
  quyen_xu_ly_den_luc DATETIME(3) NULL,
  KEY ix_su_kien_gui (thoi_diem_da_gui,thoi_diem_co_the_xu_ly,quyen_xu_ly_den_luc)
) ENGINE=InnoDB;
CREATE TABLE ban_ghi_chong_trung (
  nguoi_dung_id BIGINT UNSIGNED NOT NULL,
  thao_tac VARCHAR(80) COLLATE utf8mb4_bin NOT NULL,
  khoa_chong_ghi_trung VARCHAR(80) COLLATE utf8mb4_bin NOT NULL,
  ma_bam_yeu_cau BINARY(32) NOT NULL,
  trang_thai VARCHAR(20) NOT NULL,
  ma_http_phan_hoi SMALLINT UNSIGNED NULL,
  du_lieu_phan_hoi JSON NULL,
  tham_chieu_tai_nguyen VARCHAR(100) NULL,
  created_at DATETIME(3) NOT NULL,
  thoi_diem_het_han DATETIME(3) NOT NULL,
  PRIMARY KEY (nguoi_dung_id,thao_tac,khoa_chong_ghi_trung),
  KEY ix_chong_trung_het_han (thoi_diem_het_han),
  FOREIGN KEY (nguoi_dung_id) REFERENCES nguoi_dung(id) ON DELETE CASCADE
) ENGINE=InnoDB;
```

### 8.1 Dữ liệu mẫu và lưu ý

- Vai trò `LEARNER`, `ADMIN`; chủ đề giao tiếp: chào hỏi, mua sắm, du lịch, nơi làm việc; TOEIC: văn phòng, tuyển dụng, tài chính, cuộc họp.
- Sáu bộ mẫu A1–B2, mỗi bộ 20–50 thẻ, có IPA/ví dụ/dịch/âm thanh hợp lệ về quyền sử dụng; hai bộ riêng tư và một bộ người dùng công khai đã duyệt.
- Huy hiệu `FIRST_SESSION`, `STREAK_7`, `REVIEW_100`; thử thách ôn 20 thẻ/ngày và 100 thẻ/tuần; cấu hình AI riêng từng tính năng.
- Tài khoản minh họa chỉ tạo ở môi trường minh họa, mật khẩu được băm khi tạo; không đưa mật khẩu mặc định vào môi trường thật.
- `so_the` cập nhật cùng giao dịch thêm/xóa/nhập/sao chép; đối soát định kỳ với số thẻ chưa xóa.
- `dap_an_cham` chỉ máy chủ đọc. Bản lưu lịch sử chứa đủ nội dung và phiên bản để tái hiện, không chứa thông tin đăng nhập.
- `bao_cao_noi_dung` phải có đúng một đích. Trước xóa đích, xử lý/xóa báo cáo hoặc lưu kết quả kiểm duyệt đã loại dữ liệu cá nhân vào nhật ký rồi xóa báo cáo.
- Tiền dùng DECIMAL, đơn vị USD; tách chi phí ước tính và thực trả. Sau xóa tài khoản chỉ giữ tổng chi phí không định danh khi phù hợp.

## 9. Thuật toán ôn tập và cá nhân hóa

### 9.1 Thuật toán SRS

SRS là ôn lặp lại ngắt quãng. Bộ lập lịch `SM2_V1` là **biến thể thiết kế** dựa trên hệ số dễ nhớ và khoảng ôn của SM-2, thêm bước học ngắn và bốn mức đánh giá. Không gọi đây là SM-2 nguyên bản hoặc FSRS; không dùng AI tính lịch.

| Mã | Nút | Điểm chất lượng q | Hành vi |
|---|---|---|---|
| 1 | Quên | 1 | Về bước 1 phút; đang ôn thì chuyển học lại, tăng số lần quên |
| 2 | Khó | 3 | Giữ bước ngắn hoặc tăng khoảng ôn chậm |
| 3 | Nhớ | 4 | Sang bước tiếp hoặc tăng khoảng ôn bình thường |
| 4 | Dễ | 5 | Bỏ qua bước học hoặc tăng khoảng ôn nhiều hơn |

Thông số: hệ số dễ nhớ ban đầu 2,5, tối thiểu 1,3; bước ngắn 1 phút/10 phút; tốt nghiệp học thường 1 ngày, học dễ 4 ngày; khoảng tối đa 365 ngày. Đồng hồ máy chủ được thay bằng đồng hồ giả lập trong kiểm thử.

**Thẻ mới `NEW`:** Quên → học bước 0 sau 1 phút; Khó → bước 0 sau 6 phút; Nhớ → bước 1 sau 10 phút; Dễ → ôn sau 4 ngày. Ghi `thoi_diem_hoc_dau`; không tăng số lần quên vì chưa từng học.

**Đang học `LEARNING`:** Quên về bước 0 sau 1 phút. Khó giữ bước, hẹn 6 phút ở bước 0 hoặc 15 phút ở bước 1. Nhớ đi từ bước 0 sang bước 1 sau 10 phút, từ bước 1 sang ôn sau 1 ngày. Dễ chuyển ôn sau 4 ngày. Không cập nhật hệ số dễ nhớ trong các bước ngắn.

**Đang ôn `REVIEW`:** Gọi I là khoảng ôn cũ theo ngày, EF là hệ số dễ nhớ cũ. Hàm giới hạn đưa giá trị vào khoảng 1–365.

```text
Quên:
  Chuyển RELEARNING, bước 0, đến hạn sau 1 phút.
  Số lần quên +1; số lần nhớ liên tiếp =0; số lần ôn thành công =0.
  EF mới = max(1.3, EF-0.2); giữ I để phục hồi sau học lại.
Khó:
  I mới = giới hạn(ceil(I*1.2), 1, 365).
  EF mới = max(1.3, EF-0.15); nhớ liên tiếp =0; ôn thành công +1.
Nhớ:
  I mới = giới hạn(max(I+1, round(I*EF)), 1, 365).
  EF không đổi; nhớ liên tiếp +1; ôn thành công +1.
Dễ:
  I mới = giới hạn(max(I+1, round(I*EF*1.3)), 1, 365).
  EF mới = EF+0.1; nhớ liên tiếp +1; ôn thành công +1.
Khó/Nhớ/Dễ: đến hạn = thời điểm hiện tại + I mới *24 giờ.
```

**Học lại `RELEARNING`:** Dùng hai bước ngắn như trên. Quên trong cùng đợt không tăng thêm số lần quên. Nhớ qua bước cuối hoặc chọn Dễ thì trở lại ôn với I mới = `max(1,ceil(I_cũ*0.5))`. Chỉ chuyển từ ôn sang học lại tăng một lần quên. Hoàn tất bước học không tự thêm một lần nhớ thành công trong giai đoạn ôn.

Ví dụ: I=6 ngày, EF=2,5, chọn Nhớ →15 ngày. Chọn Quên → học lại sau 1 phút, số lần quên tăng 1; hoàn tất học lại →3 ngày. Thẻ mới chọn Nhớ →10 phút, tiếp tục Nhớ →1 ngày.

Khoảng ngày trong SRS là 24 giờ; hạn mức/ngày và giờ nhắc theo ngày địa phương. Phiên bản thuật toán, trạng thái trước/sau được lưu để giải thích mọi thay đổi; không cộng khoảng thưởng vì ôn trễ ở V1.

### 9.2 Hàng đợi và giới hạn từ mới

1. Ưu tiên học/học lại đã đến hạn; sau đó ôn quá hạn theo tỷ lệ `(now-due)/max(interval,1)` giảm dần, ôn đến hạn hôm nay, cuối cùng là thẻ mới còn hạn mức.
2. Khóa người dùng/hạn mức khi giữ suất từ mới. Bản đầu chỉ có một phiên học `ACTIVE`/người; mở lại phiên cũ hoặc chủ động dừng trước khi tạo phiên khác.
3. Chỉ giữ suất cho thẻ chưa có trong `the_da_gioi_thieu`. Khi hiển thị lần đầu, chuyển suất giữ trước thành đã dùng trong cùng giao dịch và ghi thẻ đã giới thiệu.
4. Suất chưa hiển thị hết hạn sau 30 phút; tiến trình nền trả suất và đánh dấu đã trả. Muốn hiển thị sau đó phải kiểm tra và giữ lại suất; không cho bộ đếm âm.
5. Qua ngày mới: trả suất chưa dùng của ngày cũ, giữ lại theo ngày mới. Thêm kiểm tra số thẻ mới trong 24 giờ liên tục để không vượt hạn mức bằng đổi múi giờ.
6. Quỹ thời gian T: số thẻ ước tính bằng T chia trung vị thời gian trả lời 7 ngày; mặc định 20 giây/thẻ. Không ép thẻ chưa đến bước ngắn xuất hiện. Cùng tiến độ có thể lặp trong phiên bằng mục thẻ mới, mỗi mục chỉ ghi một lượt ôn.
7. Nghỉ ≥7 ngày: gợi ý tối đa 50 lượt ôn/ngày, tạm giảm từ mới về 0 nếu người dùng chọn; chia số nợ thành kế hoạch 7–14 ngày, không sửa ngày đến hạn thật.
8. Tạm ngưng loại thẻ khỏi hàng đợi. Đặt lại tăng `giai_doan_tien_do`, về `NEW` và xóa giá trị lập lịch; giữ `the_da_gioi_thieu` và lịch sử để tránh đếm lại từ mới hoặc tăng điểm bằng đặt lại.

Mục tiêu chốt đầu ngày bằng `gioi_han_chot_dau_ngay`; thay đổi áp dụng ngày sau và được thông báo. Phân biệt: đến hạn ngay là `thoi_diem_den_han≤now`; quá hạn là trước đầu ngày; đến hạn hôm nay là trước cuối ngày địa phương.

### 9.3 Tạo và chấm bài

| Mã dạng bài | Nội dung | Cách chấm |
|---|---|---|
| `MCQ_MEANING` | Chọn nghĩa từ bốn phương án | So mã phương án trên máy chủ |
| `MCQ_WORD` | Chọn từ từ nghĩa/ngữ cảnh | Một đáp án rõ ràng, tránh phương án đồng nghĩa gây mơ hồ |
| `DICTATION` | Nghe và viết lại | So cách viết được chấp nhận sau chuẩn hóa có phiên bản |
| `CLOZE` | Điền từ vào câu ví dụ | Dùng vị trí chỗ trống đã kiểm tra, không thay mọi chuỗi giống nhau |
| `MATCHING` | Ghép từ–nghĩa | Chấm từng cặp, có thể cho điểm một phần theo quy tắc cố định |
| `TYPE_FROM_MEANING` | Nhập từ từ nghĩa và từ loại | So chính xác sau chuẩn hóa; không tự chấp nhận gần đúng |
| `CONFUSABLE_PAIR` | Phân biệt hai từ dễ nhầm | Đáp án cố định; ghi cặp bị nhầm theo lựa chọn |
| `SENTENCE_WRITING` | Viết câu dùng từ mục tiêu | AI phản hồi/điểm riêng, có thể chưa chấm khi dịch vụ lỗi |
| `MIXED_TEST` | Kiểm tra tổng hợp | Tổng điểm cố định riêng với điểm AI |

`dap_an_cham` gồm `acceptedAnswers`, `correctOptionId`, `pairs`, `normalizationVersion`, `rubric` tùy dạng. Đề cố định khi `READY`; sửa thẻ không đổi đề đã tạo. Không đủ bốn phương án khác nhau thì chuyển dạng nhập từ hoặc yêu cầu bộ khác; thiếu âm thanh thì bỏ câu nghe và nêu lý do.

Không trả đáp án trước nộp. Kiểm tra tổng hợp chỉ giải thích sau nộp cả bài. Điểm AI chưa có không tính thành 0 cố định. Luyện tập ghi minh chứng nhưng không tự thay lịch SRS.

### 9.4 Điểm yếu và gợi ý

- Kỹ năng: nghĩa, chính tả, nghe, sử dụng và phát âm. Chỉ lấy dữ liệu đã chấm đáng tin cậy.
- Với bài cố định, trọng số mẫu = `exp(-age_days/30)`, mức làm chủ = `100*(1+success)/(2+success+failure)`. Dưới năm mẫu trả `NULL` và hiện “Chưa đủ dữ liệu”. Phát âm dùng trung bình điểm API có trọng số, không tự đổi thành đúng/sai.
- Phản hồi bài viết AI có nhãn riêng; chỉ tổng hợp kỹ năng sử dụng khi cùng tiêu chí/phiên bản chấm. Không trộn vào tỷ lệ đúng cố định.
- Đề xuất từ khó khi ≥3 lỗi/14 ngày hoặc ≥2 lần quên/7 ngày. Người học chấp nhận/bỏ qua; không tự xóa mục sổ tay thủ công.
- Điểm gợi ý = 0,5×tỷ lệ lỗi gần đây +0,3×độ quá hạn chuẩn hóa +0,2×điểm yếu kỹ năng, mỗi thành phần trong [0,1]. Lưu số mẫu, kỳ tính và mã lý do, ví dụ “Bạn viết sai từ này 3 lần trong 7 ngày”.
- Cặp nhầm lưu với `the_a_id<the_b_id`, tăng một lần/sự kiện. Hai nghĩa của cùng từ vẫn có ID thẻ khác nhau.

## 10. Tích hợp API AI và đánh giá phát âm

### 10.1 Dịch vụ có sẵn

| Tác vụ | Dịch vụ | Ghi chú |
|---|---|---|
| Tạo thẻ, nghĩa ngữ cảnh, mẹo nhớ, phân biệt từ, sửa câu, tình huống | API mô hình ngôn ngữ, ví dụ OpenAI | Đầu ra theo cấu trúc JSON; mô hình/chỉ dẫn lấy từ cấu hình |
| Âm thanh từ/câu | API chuyển văn bản thành giọng nói, ví dụ OpenAI/Azure Speech | Lưu lại theo văn bản/giọng/ngôn ngữ/tốc độ/phiên bản |
| Nghe chép | Âm thanh tạo sẵn hoặc biên tập | Chấm cố định, không cần AI khi làm bài |
| Phát âm | Azure Speech Pronunciation Assessment | Đọc theo văn bản mẫu; mặc định `en-US` |
| Hội thoại văn bản | API mô hình ngôn ngữ | Mở rộng; giới hạn lượt và độ dài ngữ cảnh |

Đầu ra có cấu trúc giảm lỗi định dạng nhưng không bảo đảm đúng nghĩa. Xem [đầu ra có cấu trúc của OpenAI](https://developers.openai.com/api/docs/guides/structured-outputs). Khả năng chấm/điểm theo ngôn ngữ phải bám [tài liệu đánh giá phát âm Microsoft](https://learn.microsoft.com/en-us/azure/ai-services/speech-service/how-to-pronunciation-assessment); không tự tạo điểm ngữ điệu khi dịch vụ không trả.

### 10.2 Tạo thẻ bằng AI

```text
Gửi đoạn văn + mã bộ + số thẻ tối đa
 -> Kiểm tra quyền, sự đồng ý, độ dài và tần suất.
 -> Giao dịch: giữ hạn mức + tạo yeu_cau_ai + tác vụ nền + sự kiện.
 -> Trả 202 và requestId.
 -> Tiến trình nền nhận quyền xử lý, lấy cấu hình và gọi API ngoài giao dịch.
 -> Kiểm tra cấu trúc, nghiệp vụ và nội dung an toàn.
 -> Lưu kết quả, thẻ nháp và mức sử dụng.
 -> Giao diện hỏi trạng thái sau 2 giây, rồi 5 giây; dừng khi kết thúc.
 -> Người học chọn/sửa thẻ.
 -> Khi lưu: kiểm tra lại, khóa bộ/nháp, chống trùng, ghi thẻ trong giao dịch.
```

```json
{
  "cards": [{
    "expression": "make an appointment",
    "partOfSpeech": "PHRASE",
    "meaningVi": "đặt lịch hẹn",
    "ipa": null,
    "exampleEn": "I would like to make an appointment.",
    "exampleVi": "Tôi muốn đặt lịch hẹn.",
    "memoryTipVi": "Học cả cụm make an appointment.",
    "sourceQuote": "I would like to make an appointment.",
    "difficulty": 2
  }]
}
```

Cấu trúc yêu cầu `additionalProperties=false`, đúng kiểu và trường bắt buộc, quy định NULL rõ ràng. Tối đa 30 thẻ/yêu cầu; từ ≤255 ký tự, nghĩa ≤1000, ví dụ ≤2000, độ khó 1–5. Trích dẫn nguồn phải thực sự có trong đoạn văn hoặc bị đánh dấu cần kiểm tra. IPA không chắc thì NULL. Không tự tải URL ảnh/âm thanh do mô hình trả.

Chỉ dẫn hệ thống có phiên bản; đoạn văn đặt riêng như dữ liệu, không được thay chỉ dẫn. Không cấp quyền duyệt web, tệp, công cụ thực thi hoặc khóa bí mật cho mô hình. Văn bản “ignore instructions” vẫn chỉ là dữ liệu. Đầu ra hiển thị như văn bản, không thực thi HTML.

### 10.3 Hạn mức, lỗi và chi phí

- Đề xuất ban đầu: tạo thẻ 5 tác vụ/ngày; phản hồi bài viết 10; giải thích ngữ cảnh 10; phát âm 300 giây/ngày, tối đa 30 giây/bản ghi. Giới hạn tổng chi phí bằng tính năng `TOTAL`.
- Tạo giọng đọc và phát âm cũng có `yeu_cau_ai` để dùng chung điều phối, hạn mức và nhật ký; `lan_danh_gia_phat_am.yeu_cau_ai_id` liên kết tác vụ. Trường giây âm thanh tách riêng với **token**, là đơn vị văn bản AI xử lý, không phải mã xác thực đăng nhập.
- Hạn mức API tính theo ngày UTC để không lách bằng đổi múi giờ. Khóa hạn mức tính năng và tổng theo ID tăng dần; cập nhật cả hai trong một giao dịch. Ghi thông tin tổng trong `chinh_sach_luc_tao`; khi thêm nhiều chiều hạn mức có thể bổ sung bảng liên kết phần giữ trước.
- Giữ trước số token/giây và chi phí tối đa từ đầu vào/giới hạn đầu ra/đơn giá cấu hình. Không coi các tác vụ có cùng chi phí.
- Thử lại tối đa hai lần cho lỗi mạng trước khi chắc chắn gửi, lỗi 429/5xx theo thời gian chờ nhà cung cấp, tăng dần và có độ trễ ngẫu nhiên. Lỗi khóa/hạn mức, từ chối an toàn không thử vô hạn; sửa lỗi cấu trúc tối đa một lần trong hạn mức và thời hạn.
- Đã gửi nhưng hết thời gian chờ: `UNCERTAIN`, đối soát bằng ID yêu cầu nếu dịch vụ hỗ trợ. Không tự hoàn hạn mức rồi gọi lại vô hạn. Người dùng có thể tạo yêu cầu mới có chủ ý.
- Tạm ngắt nhà cung cấp sau ngưỡng lỗi, ví dụ năm lỗi liên tiếp; thử lại kết nối sau 30 giây. Mặc định tối đa bốn lượt gọi đồng thời, hạn chế nhận tác vụ khi hàng đợi đầy.
- Hết hạn mức trả 429 và thời điểm mở lại; lỗi dịch vụ có mã riêng. Học, CSV, bài cố định và âm thanh đã lưu vẫn dùng được.
- Nội dung AI cá nhân chỉ lưu đệm theo người dùng. Chỉ chia sẻ bộ nhớ đệm với nội dung mẫu công khai hợp lệ; không trả đoạn văn người A cho B.
- Ghi từng lượt gọi, token, giây, độ trễ, lỗi và chi phí ước tính/thực trả. Đơn giá có phiên bản, không cố định bảng giá thị trường trong mã.

### 10.4 Đánh giá phát âm

1. Trình duyệt ghi tối đa 30 giây, chọn MIME thực sự hỗ trợ, tệp tối đa 10MB; xin quyền micrô và sự đồng ý gửi dịch vụ.
2. Tệp riêng tư được kiểm tra dấu nhận dạng/thời lượng; tiến trình nền chuyển sang định dạng dịch vụ yêu cầu, ví dụ PCM một kênh 16kHz, trong môi trường cách ly.
3. Tạo tác vụ và giữ trước hạn mức; cố định `van_ban_mau`, ngôn ngữ và phiên bản cấu hình.
4. Gọi API, lưu kết quả gốc/điểm/từng từ/âm vị. Không phát hiện giọng nói hoặc lỗi thì báo rõ, không gán điểm 0 giả.
5. Hiển thị độ chính xác, trôi chảy, đầy đủ và ngữ điệu chỉ khi dịch vụ hỗ trợ và trả. Phản hồi tiếng Việt ánh xạ từ lỗi; giải thích bằng LLM là tùy chọn có hạn mức riêng.
6. Xóa âm thanh thì ngăn cấp đường dẫn mới, xóa tệp và liên kết, có thể giữ điểm. Xóa cả lần đánh giá thì xóa điểm, kết quả gốc, từng từ và tệp không còn dùng.

Bản ghi người học không dùng bộ nhớ đệm chung. Sao chép tệp riêng của tác giả phải tạo tệp/thông tin độc lập hoặc dùng bản công khai đã được cấp phép. Xóa lịch sử AI liên quan phát âm phải làm sạch nội dung, không xóa ID tác vụ khi còn khóa ngoại hoặc kế toán hạn mức cần giữ.

## 11. Đặc tả API

### 11.1 Quy ước

- Tiền tố `/api/v1`; JSON dùng `camelCase`; ID là chuỗi; thời điểm UTC theo ISO-8601.
- Tạo mới 201; nhận tác vụ 202; xóa 204; dữ liệu sai 400/422; chưa đăng nhập 401; không có quyền 403; không thấy tài nguyên 404; xung đột 409; sai phiên bản 412; hết hạn mức 429; dịch vụ phụ thuộc lỗi 503.
- Phân trang `{items,page,size,total}` hoặc theo mốc `{items,nextCursor}`. Mặc định 20, tối đa 100.
- Thao tác dễ gửi lại có `Idempotency-Key`: ôn, nộp bài, sao chép, xác nhận CSV/nháp và tạo AI. Cùng khóa khác nội dung →409; cùng khóa/nội dung trả kết quả cũ. Lưu 24 giờ; ràng buộc lịch sử vẫn chống trùng lâu hơn.
- `X-CSRF-TOKEN` bảo vệ thao tác thay đổi với cookie. GET không đổi dữ liệu. `ETag`/`If-Match` kiểm tra phiên bản.
- Quyền: **G** khách; **L** người học hoạt động; **O** người sở hữu và đã đăng nhập; **A** quản trị viên; **S** tác vụ nội bộ. Kiểm tra quyền tại máy chủ.

```json
{
  "type": "urn:vocablearning:error:stale-progress",
  "title": "Tiến độ đã thay đổi",
  "status": 409,
  "code": "STALE_PROGRESS",
  "detail": "Hãy tải lại thẻ trước khi gửi kết quả.",
  "traceId": "a5f1...",
  "fieldErrors": [],
  "retryable": false
}
```

### 11.2 Tài khoản và cài đặt

| Phương thức + đường dẫn | Quyền | Nội dung |
|---|---|---|
| POST `/auth/register` | G | `email,password,displayName`; thông báo chung |
| POST `/auth/verify-email` | G | `token`; kiểm tra thời hạn và sử dụng một lần |
| POST `/auth/resend-verification` | G | `email`; giới hạn tần suất |
| GET `/auth/csrf` | G/L | Lấy mã chống giả mạo yêu cầu |
| POST `/auth/login` | G | Email/mật khẩu; thông tin người dùng và cookie phiên |
| POST `/auth/logout` | L | Hủy phiên, an toàn khi gọi lại |
| POST `/auth/forgot-password` | G | Thông báo giống nhau dù email tồn tại hay không |
| POST `/auth/reset-password` | G | `token,newPassword`; thu hồi phiên |
| POST `/auth/reauthenticate` | L | Xác thực lại để làm thao tác nhạy cảm |
| GET `/auth/sessions` | L | Danh sách phiên/thiết bị với dữ liệu tối thiểu |
| DELETE `/auth/sessions/{sessionId}` | O | Thu hồi phiên của mình |
| GET `/auth/identities` | L | Phương thức đăng nhập đã liên kết |
| POST `/auth/identities/{provider}/link` | L | Bắt đầu liên kết sau xác thực lại |
| DELETE `/auth/identities/{provider}` | L | Không cho gỡ phương thức cuối cùng |
| GET `/users/me` | L | Tài khoản và hồ sơ |
| PATCH `/users/me/profile` | L | Tên, giới thiệu, ảnh, trình độ, mục tiêu |
| PUT `/users/me/interests` | L | `topicIds` |
| GET/PUT `/users/me/preferences` | L | Kế hoạch, múi giờ, nhắc, thông báo, đồng ý AI và phiên bản |
| POST `/users/me/deletion-requests` | L | Xác thực lại và xác nhận; trả 202 |
| GET `/users/me/deletion-requests/current` | L* | Trạng thái xóa |
| POST `/users/me/deletion-requests/{id}/cancel` | O* | Chỉ trước khi bắt đầu xóa |

`L*`/`O*`: tài khoản chờ xóa chỉ được xác thực hạn chế để xem/hủy/đăng xuất. Google bắt đầu tại `/oauth2/authorization/google`, nhận kết quả tại `/login/oauth2/code/google`; Spring Security xử lý. Facebook tương tự khi bật tính năng.

### 11.3 Bộ thẻ, danh mục, tệp và CSV

| Phương thức + đường dẫn | Quyền | Nội dung |
|---|---|---|
| GET `/topics` | G | Cây chủ đề đang dùng |
| GET `/catalog/decks` | G | Tìm/lọc theo chủ đề, trình độ, loại, nguồn; sắp xếp/phân trang |
| GET `/catalog/decks/{id}` | G | Thông tin và tối đa 10 thẻ xem trước |
| GET `/library/decks` | L | Bộ sở hữu/sao chép; lọc yêu thích riêng |
| POST `/decks` | L | Tạo bộ, chủ đề, trình độ |
| GET/PATCH/DELETE `/decks/{id}` | O | Xem/sửa có phiên bản/xóa mềm |
| POST `/decks/{id}/publication` | O | Quyền công khai/riêng và trạng thái; công khai chờ duyệt |
| POST `/decks/{id}/copy` | L | Sao chép bộ công khai đã duyệt; 201 hoặc 202 khi cần sao chép tệp |
| GET/POST `/decks/{id}/cards` | O | Danh sách/tìm thẻ; tạo thẻ và ví dụ/tệp |
| GET/PATCH/DELETE `/cards/{id}` | O | Xem/sửa/xóa thẻ, kiểm tra phiên bản |
| POST `/decks/{id}/duplicate-check` | O | `expression,partOfSpeech,meaning,excludeCardId`; trả trùng hoàn toàn/cùng từ |
| GET/POST `/decks/{id}/tags` | O | Xem/tạo nhãn |
| PATCH/DELETE `/tags/{id}` | O | Sửa/xóa nhãn thuộc bộ |
| PUT `/cards/{id}/tags` | O | Gắn các nhãn cùng bộ |
| PUT/DELETE `/decks/{id}/favorite` | L | Đánh dấu/bỏ yêu thích |
| POST/GET `/decks/{id}/share-links` | O | Tạo/xem liên kết; mã gốc chỉ trả một lần |
| DELETE `/share-links/{id}` | O | Thu hồi |
| GET `/shares/{token}` | G/L | Mở theo quyền hiện tại |
| POST `/media/upload-intents` | L | Loại/kích thước/mục đích; trả mã tệp và đường dẫn tải lên có thời hạn |
| POST `/media/{id}/complete` | O | Kiểm tra tệp; chuyển xử lý/sẵn sàng |
| GET `/media/{id}/access` | O/G | Đường dẫn truy cập sau kiểm tra quyền |
| DELETE `/media/{id}` | O | Chặn nếu tệp còn dùng; phải gỡ liên kết trước |
| POST `/decks/{id}/imports` | O | Tải CSV; trả 202 và mã lần nhập |
| GET `/imports/{id}` | O | Tổng kết, số dòng hợp lệ/lỗi |
| GET `/imports/{id}/rows` | O | Dòng dữ liệu tạm có phân trang |
| POST `/imports/{id}/commit` | O | Dòng được chọn, `SKIP_STRONG`, phiên bản bộ |
| GET `/decks/{id}/export` | O | Xuất CSV từng phần, xử lý công thức bảng tính |
| POST `/reports` | L | Đúng một `deckId/cardId/aiRequestId`, lý do, mô tả |

CSV V1: `tu_cum_tu,tu_loai,nghia_tieng_viet,ipa,cau_vi_du_en,cau_vi_du_vi,chu_de_slug,nhan,do_kho,tep_anh,tep_audio_tu,tep_audio_vi_du`. UTF-8/BOM, hỗ trợ dấu phẩy/xuống dòng trong chuỗi có ngoặc kép; tối đa 5MB/5.000 dòng. Nhiều nhãn/chủ đề dùng `|` và quy tắc thoát ký tự. Tệp tham chiếu là ID tệp thuộc quyền người nhập hoặc để trống, không tự tải URL ngoài.

CSV V1 một dòng/thẻ và chỉ xuất ví dụ đầu tiên; giao diện phải thông báo không khôi phục đầy đủ nhiều ví dụ/tệp. Sao lưu đầy đủ bằng JSON là cải tiến riêng. Tên tệp/thông tin xuất ghi phiên bản định dạng.

### 11.4 Học và luyện tập

| Phương thức + đường dẫn | Quyền | Nội dung |
|---|---|---|
| GET `/study/overview` | L | Từ mới, đến hạn ngay/hôm nay, quá hạn, hạn mức |
| POST `/study/sessions` | L | Bộ, chiều, cách học, quỹ thời gian |
| GET `/study/sessions/{id}` | O | Phiên và hàng đợi |
| POST `/study/sessions/{id}/items/{itemId}/reveal` | O | Hiển thị và dùng suất từ mới trong giao dịch |
| POST `/study/sessions/{id}/reviews` | O | Mục thẻ, đánh giá, phiên bản tiến độ, mã sự kiện, thời gian trả lời |
| POST `/study/sessions/{id}/heartbeat` | O | Cập nhật khoảng hoạt động, không đếm trùng |
| POST `/study/sessions/{id}/complete` | O | Hoàn thành, trả suất chưa dùng |
| POST `/study/sessions/{id}/abandon` | O | Dừng và trả suất chưa dùng |
| GET `/study/sessions/{id}/summary` | O | Từ mới, lượt ôn, quên, thời gian |
| PUT `/progress/{id}/suspension` | O | `suspended` (true/false), lưu vào cột `tam_ngung` |
| POST `/progress/{id}/reset` | O | Phiên bản và xác nhận; tăng giai đoạn tiến độ |
| GET `/reviews` | L | Lịch sử theo bộ/khoảng ngày/mốc phân trang |
| GET `/study/recovery-plan` | L | Kế hoạch học lại sau nghỉ |
| POST `/practice/sets` | L | Bộ, dạng, số câu, nguồn `DECK/ERRORS/DUE` |
| GET `/practice/sets/{id}` | O | Đề, không có đáp án chấm |
| POST `/practice/sets/{id}/attempts` | O | Tạo lần làm |
| GET `/practice/attempts/{id}` | O | Câu hỏi và bản trả lời đang lưu |
| PUT `/practice/attempts/{id}/answers/{questionId}` | O | Lưu bản nháp, chưa lộ đáp án |
| POST `/practice/attempts/{id}/submit` | O | Khóa bài, chấm cố định, tạo tác vụ chấm AI |
| GET `/practice/attempts/{id}/results` | O | Điểm/giải thích; trạng thái AI rõ ràng |
| POST `/practice/attempts/{id}/retry-wrong` | O | Tạo đề/lần làm mới từ câu sai |
| GET `/practice/attempts` | L | Lịch sử |

```json
{
  "itemId": "91001",
  "rating": 3,
  "expectedProgressVersion": 8,
  "eventKey": "48b0fd4e-c8f9-4a4f-88c1-bb3acf21ba68",
  "responseMs": 8500
}
```

Không nhận `userId`, ngày ôn tiếp, XP hoặc đáp án đúng từ trình duyệt để quyết định kết quả. Đánh giá nhớ là tự đánh giá, không tuyên bố ngăn được người dùng cố ý chọn Dễ; giới hạn thưởng giảm động cơ lặp.

### 11.5 AI, sổ tay, phát âm và theo dõi học

| Phương thức + đường dẫn | Quyền | Nội dung |
|---|---|---|
| GET `/ai/usage` | L | Hạn mức còn lại, tổng và thời điểm mở lại |
| POST `/ai/requests` | L | Tính năng, đầu vào, bộ/thẻ mục tiêu; trả 202 |
| GET `/ai/requests` | L | Lịch sử theo tính năng/trạng thái |
| GET/DELETE `/ai/requests/{id}` | O | Xem hoặc xóa nội dung; giữ thông tin hạn mức tối thiểu khi cần |
| POST `/ai/requests/{id}/cancel` | O | Hủy trước gửi; đang chạy chỉ hủy nếu còn có thể |
| PATCH `/ai/candidates/{id}` | O | Sửa nháp và kiểm tra phiên bản |
| POST `/ai/requests/{id}/commit-cards` | O | Bộ đích, mã nháp, phiên bản bộ |
| POST `/ai/requests/{id}/feedback` | O | Sai/không hữu ích/không an toàn và mô tả |
| POST/GET `/ai/conversations` | L | Tạo/xem hội thoại mở rộng |
| POST `/ai/conversations/{id}/messages` | O | Gửi văn bản; trả mã tác vụ |
| GET/DELETE `/ai/conversations/{id}` | O | Xem/xóa |
| GET/POST `/notebook/entries` | L | Lọc theo lỗi; thêm từ khó |
| PATCH/DELETE `/notebook/entries/{id}` | O | Sửa/xóa |
| POST `/notebook/entries/{id}/accept` | O | Chấp nhận đề xuất |
| POST `/notebook/entries/{id}/dismiss` | O | Bỏ qua đề xuất |
| GET `/weaknesses` | L | Điểm yếu và số mẫu |
| GET `/cards/{id}/mastery` | O | Mức làm chủ từng kỹ năng |
| GET `/confused-pairs`, `/recommendations` | L | Cặp nhầm/gợi ý và lý do |
| POST `/recommendations/{id}/dismiss` | O | Bỏ gợi ý |
| POST `/pronunciation/attempts` | L | Thẻ/văn bản mẫu, bản ghi, ngôn ngữ; trả 202 |
| GET `/pronunciation/attempts` | L | Lịch sử; lọc điểm thấp |
| GET/DELETE `/pronunciation/attempts/{id}` | O | Xem/xóa toàn bộ kết quả |
| DELETE `/pronunciation/attempts/{id}/recording` | O | Xóa âm thanh, giữ điểm |
| GET `/statistics/summary` | L | Tổng hợp theo kỳ/bộ/chủ đề |
| GET `/statistics/activity`, `/statistics/skills`, `/statistics/forgotten-words` | L | Hoạt động, kỹ năng, từ hay quên |
| GET `/statistics/comparison`, `/statistics/weekly-reports` | L | So sánh kỳ/báo cáo tuần |
| GET `/users/me/activity`, `/users/me/streak` | L | Lịch sử/chuỗi hiện tại và dài nhất |
| GET `/achievements`, `/challenges` | L | XP, huy hiệu và thử thách |
| GET `/notifications`, `/announcements` | L | Thông báo cá nhân/hệ thống |
| PATCH `/notifications/{id}/read` | O | Đánh dấu đã đọc |
| POST `/notifications/read-all` | L | Đọc tất cả của người hiện tại |

### 11.6 Quản trị

| Phương thức + đường dẫn | Quyền | Nội dung |
|---|---|---|
| GET `/admin/users` | A | Tìm/lọc tài khoản và vai trò |
| PUT `/admin/users/{id}/lock` | A | Khóa/mở, lý do, thu hồi phiên |
| PUT `/admin/users/{id}/roles` | A | Phân quyền; xác thực lại; bảo vệ quản trị cuối |
| POST/PATCH/DELETE `/admin/topics[/{id}]` | A | Chủ đề; không tạo vòng lặp cha/con |
| POST `/admin/decks` | A | Tạo bộ nguồn ADMIN |
| PATCH/DELETE `/admin/decks/{id}` | A | Quản lý bộ mẫu |
| POST/PATCH/DELETE `/admin/decks/{id}/cards[/{cardId}]` | A | Thẻ mẫu |
| GET `/admin/reports` | A | Hàng đợi kiểm duyệt |
| POST `/admin/reports/{id}/resolve` | A | Hành động, lý do, phiên bản đích |
| PUT `/admin/decks/{id}/moderation` | A | Duyệt/từ chối/ẩn |
| GET `/admin/usage`, `/admin/api-calls` | A | Sử dụng, lỗi, token/giây/chi phí |
| GET/PUT `/admin/ai-policies[/{feature}]` | A | Hạn mức, mô hình, chỉ dẫn; không có khóa API |
| GET/PUT `/admin/settings[/{key}]` | A | Cấu hình được cho phép, có phiên bản |
| GET/POST/PATCH/DELETE `/admin/announcements[/{id}]` | A | Soạn, hẹn và công bố thông báo |
| GET `/admin/audit-logs` | A | Nhật ký bất biến, lọc và phân trang |

Ký hiệu `[/{id}]` trong bảng chỉ viết gọn, không phải đường dẫn thật. OpenAPI phải tách từng thao tác và DTO. Tài liệu tương tác Swagger chỉ mở khi phát triển hoặc trong mạng quản trị.

## 12. Luồng xử lý và giao dịch

### 12.1 Xác thực

Đăng ký: chuẩn hóa email → băm mật khẩu → tạo người/hồ sơ/tùy chọn/vai trò → tạo mã xác thực đã băm và tác vụ email → ghi giao dịch → gửi nền. Gửi email lỗi không xóa tài khoản; cho gửi lại có giới hạn.

Xác thực: băm mã nhận vào → khóa bản ghi → kiểm tra thời hạn/chưa dùng → chuyển `ACTIVE`, ghi thời điểm xác thực và dùng mã → ghi giao dịch. Mã email 24 giờ, mã đặt lại 15 phút; phát mã mới vô hiệu mã cũ cùng mục đích. Mã gốc trong tác vụ email được mã hóa và xóa sau gửi/hết hạn, không ghi nhật ký.

Google: mã cấp quyền, kiểm tra `state`, PKCE/nonce khi hỗ trợ → xác thực từ nhà cung cấp → tìm `(nha_cung_cap,dinh_danh_nha_cung_cap)` → đăng nhập danh tính cũ/tạo người mới/liên kết rõ ràng khi trùng email → đổi ID phiên → chuyển về trang được cho phép.

### 12.2 Ghi nhận lượt ôn

1. Kiểm tra tài khoản, quyền, phiên/mục đã hiển thị, thẻ/bộ chưa xóa. Thẻ đã sửa dùng nội dung lưu của lần học; đã xóa trả `CARD_UNAVAILABLE`.
2. Khóa bản chống trùng theo người/thao tác/khóa. Khác nội dung trả 409; đã hoàn tất trả phản hồi cũ trước kiểm tra phiên bản.
3. Khóa dữ liệu theo thứ tự người → hạn mức/tổng hợp ngày → phiên/mục → tiến độ → sổ thưởng/sự kiện. Đã ghi mục/sự kiện thì trả cũ; phiên bản cũ không tính lại lịch.
4. Tính SRS từ thời gian máy chủ và mức đánh giá; cập nhật tiến độ/phiên bản, ghi lịch sử trước/sau, hoàn thành mục. Quên có thể thêm mục học lại sau một phút.
5. Ghi sự kiện và thưởng trong giao dịch, khóa tổng hợp ngày và kiểm tra khóa thưởng duy nhất để không vượt 200 XP khi đồng thời.
6. Ghi sự kiện sau giao dịch và phản hồi chống trùng; hoàn tất giao dịch rồi trả kết quả.
7. Thành phần nhận sự kiện cập nhật kỹ năng/gợi ý/thử thách/báo cáo, không tăng mù bộ đếm khi nhận lại. Có thể tạo lại từ lịch sử.

Bế tắc khóa chéo thử lại tối đa ba lần với cùng khóa chống trùng. Không gọi nhà cung cấp trong giao dịch. Mọi dịch vụ chạm cùng hàng phải theo cùng thứ tự khóa.

### 12.3 CSV, sao chép và nộp bài

- Xem trước CSV chỉ ghi dữ liệu tạm. Xác nhận khóa bộ/phiên bản/lần nhập/dòng; bỏ trùng hoàn toàn theo lựa chọn. Còn lỗi dữ liệu thì 422 và không nhập một phần. Giới hạn 5.000 dòng; lần nhập chỉ xác nhận một lần. Dữ liệu tạm xóa sau 24 giờ nếu chưa nhập, tối đa bảy ngày sau nhập.
- Sao chép kiểm tra quyền công khai/duyệt và lấy dữ liệu nhất quán; sao chép nội dung/nhãn/ví dụ, không tiến độ/sổ tay/bài làm/báo cáo. Tệp riêng chuẩn bị ngoài giao dịch, gắn sau; lỗi thì dọn tệp không dùng. Có tệp trả tác vụ 202, tránh giữ giao dịch trong lúc gọi kho lưu trữ.
- Nộp bài khóa lần làm, cố định bản trả lời, chấm tại máy chủ, ghi lỗi/sự kiện và tạo chấm AI khi cần. Gửi lại trả kết quả cũ. AI lỗi giữ điểm cố định, đánh dấu chấm một phần, không tính bài viết là sai vì dịch vụ hỏng.

### 12.4 Nhắc học và báo cáo tuần

Tác vụ mỗi phút tính giờ nhắc theo múi giờ; khóa chống trùng theo người/ngày. Trước gửi kiểm tra lựa chọn và tài khoản lần nữa. Giờ địa phương không tồn tại do đổi giờ mùa dùng thời điểm hợp lệ tiếp; giờ lặp chỉ gửi một lần. Chậm quá sáu giờ thì bỏ email cũ.

Email tắt nhắc có liên kết chỉ có quyền tắt nhắc, không đăng nhập. Email xác thực/đặt lại tách khỏi lựa chọn email nhắc học. Dịch vụ email có thể nhận yêu cầu rồi hết chờ, nên không tạo gửi trùng vô hạn.

Báo cáo tạo thứ Hai theo múi giờ cho tuần vừa kết thúc; tuần bắt đầu thứ Hai, lưu nội dung và không cần AI. Mục tiêu hoàn thành khi đạt cả số mới và phút, số mới bằng 0 thì không yêu cầu; một thông báo/ngày.

### 12.5 Xóa dữ liệu

1. Xác thực lại, khóa người để chỉ có một yêu cầu hoạt động, lưu trạng thái cũ, hẹn sau bảy ngày; chuyển chờ xóa, thu hồi phiên đầy đủ, dừng nhắc/tác vụ mới.
2. Chỉ hủy khi chưa bắt đầu xóa; phục hồi trạng thái cũ, không tự mở khóa tài khoản vốn bị khóa.
3. Tiến trình nền có quyền xử lý và mốc từng nhóm, xóa an toàn khi chạy lại. Dừng/chờ tác vụ đang chạy; kết quả AI đến muộn phải bị làm sạch.
4. Xóa nội dung AI/hội thoại; phát âm/tệp; CSV; bài làm/đề; sổ tay/lỗi/kỹ năng/gợi ý; lịch sử ôn/phiên/tiến độ/hạn mức; hoạt động/điểm/thông báo; nội dung sở hữu; mã/danh tính/hồ sơ/quyền; cuối cùng là người dùng. Kiểm tra tham chiếu theo thứ tự, không chỉ một lệnh xóa dây chuyền.
5. Xử lý báo cáo trước xóa đích. Bản sao từ vựng chung hợp lệ của người khác có thể giữ, nhưng loại tên tác giả/định danh cá nhân. Nội dung có dữ liệu cá nhân phải làm sạch cả bản sao và lịch sử liên quan.
6. Nhật ký/lượt gọi/tác vụ/sự kiện phải làm sạch cả dữ liệu trong JSON và định danh, không chỉ đặt khóa ngoại NULL. Mã đối soát xóa là HMAC, vẫn có thể liên kết nên giữ tối đa 30 ngày.
7. Chỉ hoàn tất khi tệp đã xóa xác nhận; lỗi giữ mốc để thử lại. Sao lưu mã hóa hết hạn 30 ngày; sau khôi phục phải xử lý lại danh sách xóa trước mở truy cập.

Đây là chính sách sản phẩm đề xuất, chưa phải xác nhận tuân thủ quy định pháp lý. Cần rà soát quy định áp dụng trước đưa vào sử dụng thật.

## 13. Bảo mật và quyền riêng tư

### 13.1 Tài khoản và quyền

- Dùng PasswordEncoder, BCrypt/Argon2; chọn BCrypt với mức chi phí hiệu chỉnh để kiểm tra khoảng 100–300ms. Không tự băm mật khẩu bằng SHA-256; lưu tiền tố để nâng cấp bộ mã hóa.
- Cookie `Secure`, `HttpOnly`, `SameSite=Lax`, `Path=/`; đổi ID sau đăng nhập/thay đổi quyền. Hết phiên khi không hoạt động 30 phút, tối đa 24 giờ; nhớ đăng nhập dài hạn cần thiết kế riêng.
- Spring Session lập chỉ mục theo người để thu hồi. Tăng `phien_ban_xac_thuc` khi đặt lại/khóa/đổi quyền. Không trả mã truy cập Google cho giao diện hoặc lưu lâu khi chỉ cần đăng nhập.
- Chống giả mạo yêu cầu CSRF cho thay đổi bằng cookie, gồm đăng nhập/đăng xuất; đăng nhập ngoài có `state/nonce`.
- Kiểm tra vai trò và quyền sở hữu tại dịch vụ. Đoán ID thẻ/tệp/tiến độ/AI của người khác trả 403/404.
- Xác thực lại trong năm phút cho liên kết/gỡ danh tính, xóa tài khoản, đổi quyền/cấu hình nhạy cảm. Quản trị nên bật xác thực nhiều yếu tố trước vận hành thật.

### 13.2 Dữ liệu và tệp

- DTO chỉ nhận trường cho phép, kiểm tra bằng Bean Validation; truy vấn có tham số, không ghép trực tiếp điều kiện/sắp xếp từ đầu vào.
- Nội dung hiển thị được thoát ký tự; không chạy HTML AI. Trang có mã xác thực không gửi mã qua nguồn truy cập; dùng chính sách bảo mật nội dung và `Referrer-Policy` phù hợp.
- Kiểm tra kích thước, MIME thực, dấu nhận dạng, thời lượng và kích thước ảnh; loại thông tin EXIF; quét mã độc khi có công cụ. Không cho SVG/HTML hoạt động như ảnh tải lên.
- Đường dẫn tệp có chữ ký tối đa năm phút; kiểm tra quyền khi cấp. Đường dẫn đã cấp có thể còn dùng tới hết hạn, phải nêu trong hướng dẫn ẩn/xóa.
- Không tải URL tùy ý do CSV/AI/người dùng đưa để tránh truy cập mạng nội bộ. Chỉ gọi địa chỉ dịch vụ cấu hình tại máy chủ.
- Khóa API từ biến môi trường/dịch vụ quản lý bí mật phía máy chủ, không dùng `NEXT_PUBLIC` cho khóa. Cài đặt hệ thống chỉ chứa chính sách/mô hình, không khóa.
- Giới hạn ban đầu: đăng nhập 10/IP/phút và 5/tài khoản/phút; đăng ký 5/IP/giờ; gửi lại 3/tài khoản/giờ; đặt lại 5/IP/giờ. Điều chỉnh cho mạng dùng IP chung.
- Sự đồng ý AI/phát âm giải thích dữ liệu gửi ngoài; không gửi email/tên nếu không cần. Điều khoản lưu/huấn luyện của nhà cung cấp cần kiểm tra theo gói sử dụng.
- Không ghi mật khẩu, mã xác thực, đoạn văn cá nhân hoặc đường dẫn âm thanh có chữ ký vào nhật ký. Nhật ký quản trị chỉ ghi thay đổi cần thiết.
- CSV xuất xử lý ô mở đầu `= + - @` hoặc khoảng trắng điều khiển để tránh công thức bảng tính; quy tắc thêm dấu nháy có phiên bản và hướng dẫn nhập lại.

### 13.3 Thời hạn lưu đề xuất

| Dữ liệu | Thời hạn |
|---|---|
| Mã xác thực/đặt lại | Xóa sau hết hạn 24 giờ; mã đã dùng vô hiệu ngay |
| Tiến độ/lịch sử/bài làm | Đến khi người dùng xóa; có thể lưu dài hạn sau 12 tháng |
| Văn bản/kết quả/hội thoại AI | 90 ngày, cho xóa sớm; thẻ đã chọn lưu độc lập |
| Bản ghi âm người học | 30 ngày; điểm giữ tới khi xóa kết quả/tài khoản |
| Tệp chưa gắn vào nội dung | 24 giờ với khoảng chờ an toàn |
| Nhật ký gọi dịch vụ | 30 ngày, không giữ văn bản cá nhân |
| Nhật ký quản trị | 180 ngày, che dữ liệu cá nhân và hạn chế truy cập |
| Bản chống trùng yêu cầu | 24 giờ; lịch sử/sổ thưởng vẫn có ràng buộc lâu hơn |
| Sao lưu | 30 ngày, mã hóa, xử lý yêu cầu xóa khi khôi phục |

Dọn dữ liệu theo khóa ngoại; báo cáo xử lý trước xóa yêu cầu AI. Cấu hình thời hạn có giới hạn hợp lệ và nhật ký thay đổi.

## 14. Hiệu năng và vận hành

### 14.1 Định nghĩa chỉ số

| Chỉ số | Cách tính |
|---|---|
| Mới | Thẻ thư viện chưa có trong `the_da_gioi_thieu`, chưa xóa |
| Đang học | Đã giới thiệu nhưng chưa đạt tiêu chí ghi nhớ ở chiều chọn |
| Ghi nhớ | `REVIEW`, khoảng ≥21 ngày, nhớ liên tiếp ≥3, không tạm ngưng |
| Thẻ đã ôn | Số lượt lịch sử; kèm số thẻ không trùng để phân biệt lặp bước |
| Tỷ lệ đúng cố định | Câu đúng/câu đã chấm cố định, không gồm bài viết AI |
| Mục tiêu | Tỷ lệ từ mới/phút đạt, tối đa 100%; mục tiêu từ mới 0 thì không áp dụng |
| Hay quên | Số lần quên/tổng lượt theo kỳ, kèm số mẫu |
| Theo chủ đề | Một bộ có thể nhiều chủ đề; tổng các chủ đề không bằng tổng thẻ duy nhất |
| So sánh | Hai kỳ cùng độ dài; mốc bằng 0 thì hiện không áp dụng |
| XP | Tổng sổ thưởng; giới hạn trên điểm dương, sửa điểm âm có nhật ký |

Thống kê theo chiều để không đếm đôi; chọn cả hai chiều chỉ coi ghi nhớ khi cả hai đạt. Thẻ tạm ngưng có số riêng; thẻ xóa vẫn có lịch sử, không tính từ đang dùng. Tổng hợp có thể chậm ≤60 giây và trả thời điểm cập nhật; ngày đến hạn/kết quả ôn đọc dữ liệu giao dịch chính.

### 14.2 Redis

```text
spring:session:*                          # Phiên đăng nhập
rl:{scope}:{subjectHash}:{window}          # Bộ đếm tần suất có thời hạn
catalog:v1:{filterHash}                    # Danh mục, thời hạn 60 giây
ai:cache:{userId}:{inputHash}:{configHash}  # Nội dung riêng, thời hạn 24 giờ
provider:breaker:{provider}                        # Trạng thái tạm ngắt dịch vụ
```

Redis không là nguồn duy nhất của tiến độ/hạn mức/XP. Lỗi giới hạn tần suất thì chức năng xác thực/AI phải từ chối hoặc dùng phương án giới hạn tại cơ sở dữ liệu; xem công khai có thể bỏ bộ nhớ đệm. Không lưu chung dữ liệu riêng hoặc đường dẫn có chữ ký dài hạn.

### 14.3 Theo dõi và xử lý sự cố

- Actuator kiểm tra tiến trình đang chạy độc lập với API AI; kiểm tra sẵn sàng xét MySQL/Redis phiên. Thông tin sức khỏe chi tiết không công khai.
- Micrometer ghi độ trễ/lỗi HTTP, chờ kết nối DB, truy vấn đến hạn, tuổi hàng đợi, mất quyền tác vụ, số lượt/token/giây/chi phí/lỗi API, email và tệp chưa dọn.
- Mã truy vết xuyên yêu cầu → tác vụ → lượt gọi ngoài. Nhật ký quản trị bất biến với tài khoản ứng dụng thường; dọn riêng bằng quyền chuyên dụng.
- Cảnh báo: lỗi 5xx >2%/5 phút; kết nối DB >80%; tác vụ chờ >5 phút; lỗi nhà cung cấp >20%; chi phí đạt 80% ngân sách/ngày; tác vụ xóa lỗi.
- Dịch vụ AI lỗi: tắt tính năng bằng chính sách, giữ học cốt lõi. DB lỗi: không báo đã lưu, cho thử lại cùng khóa. Khôi phục: kiểm tra cấu trúc, xử lý danh sách xóa, tạo lại tổng hợp, kiểm tra nhanh rồi mở truy cập.
- Sự kiện có thể được gửi nhiều lần; thành phần nhận phải chống trùng. `su_kien_gan_nhat_id` chỉ dùng bỏ sự kiện cũ khi xử lý có thứ tự theo người; chạy song song cần dấu đã xử lý hoặc tính lại để không bỏ mất sự kiện ID nhỏ.

## 15. Triển khai và cấu hình

### 15.1 Môi trường

| Môi trường | Thành phần |
|---|---|
| Máy phát triển | Next.js/Spring Boot, MySQL 8.4, Redis, MinIO, email thử và AI giả lập |
| Kiểm thử tự động | MySQL/Redis Testcontainers, API giả lập, kiểm thử giao diện |
| Bản thử nghiệm | HTTPS, Next.js đóng gói độc lập, API/tác vụ nền, DB, Redis, kho tệp, email thử |
| Vận hành nhỏ | Mạng riêng DB/Redis, TLS, sao lưu mã hóa, email/API thật, giám sát |

Điểm bắt đầu đo tải: một máy 4 lõi xử lý/8GB RAM và kho tệp ngoài; Java khoảng 1–2GB, MySQL 2GB, Redis 256MB. Không là bảo đảm tải. Bảo đảm bộ nhớ phiên; tách Redis phiên và bộ nhớ đệm khi cần để danh mục không đẩy mất phiên.

### 15.2 Biến cấu hình

```dotenv
NEXT_PUBLIC_API_BASE=/api/v1
BACKEND_INTERNAL_URL=http://backend-api:8080
SPRING_PROFILES_ACTIVE=prod,api
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/vocab_learning?connectionTimeZone=UTC
SPRING_DATASOURCE_USERNAME=vocab_runtime
SPRING_DATASOURCE_PASSWORD=<secret>
SPRING_DATA_REDIS_HOST=redis
SPRING_DATA_REDIS_PASSWORD=<secret>
APP_PUBLIC_BASE_URL=https://vocab.example.com
GOOGLE_CLIENT_ID=<configured>
GOOGLE_CLIENT_SECRET=<secret>
STORAGE_ENDPOINT=<configured>
STORAGE_BUCKET=<configured>
STORAGE_ACCESS_KEY=<secret>
STORAGE_SECRET_KEY=<secret>
EMAIL_PROVIDER_API_KEY=<secret>
LLM_PROVIDER_API_KEY=<secret>
SPEECH_PROVIDER_KEY=<secret>
SPEECH_PROVIDER_REGION=<configured>
JOB_PAYLOAD_ENCRYPTION_KEY=<secret>
DELETION_REFERENCE_HMAC_KEY=<secret>
```

`<secret>` là khóa bí mật cấp khi triển khai; `<configured>` là giá trị cần cấu hình. Tác vụ nền dùng `prod,worker`; biến tùy chỉnh phải được ánh xạ bằng `@ConfigurationProperties`. Mô hình/chỉ dẫn có phiên bản trong cấu hình, không phải khóa bí mật.

### 15.3 Trình tự

1. Tạo khung dự án, khóa phiên bản, tự động kiểm tra/biên dịch; xác nhận tương thích theo tài liệu chính thức.
2. MySQL/Redis/kho tệp/email; tách tài khoản cập nhật cấu trúc và tài khoản ứng dụng; Flyway tạo bảng, dữ liệu mẫu và ORM kiểm tra.
3. Tài khoản/quyền/CSRF/hồ sơ/thiết lập; thử đăng ký → xác thực → đăng nhập → đăng xuất/đặt lại/Google.
4. Bộ/thẻ/danh mục/tệp/CSV/kiểm duyệt; dữ liệu mẫu có quyền sử dụng; kiểm tra cách ly bộ riêng.
5. SRS/phiên/hạn mức/giao dịch ôn; đồng hồ giả và xử lý đồng thời; tổng kết/lịch sử.
6. Bài cố định, sổ tay, thống kê, điểm và nhắc; các chức năng này chạy khi không có khóa AI/phát âm.
7. Tích hợp API giả trước, API thật với ngân sách thấp sau; kiểm tra nháp AI và ghi âm/xóa.
8. Đóng gói Docker; Nginx/HTTPS/OAuth; chỉ một tác vụ cập nhật cấu trúc trước mở phiên bản ứng dụng mới.
9. Kiểm tra nhanh, đo tải, diễn tập sao lưu/khôi phục, công bố bản minh họa và tài liệu cùng phiên bản.

Quy trình tự động: biên dịch → kiểm thử đơn vị → MySQL thật → luồng đầu-cuối → quét thư viện/khóa → đóng gói → kiểm tra cấu trúc → thử nghiệm → triển khai. Đổi cấu trúc theo cách thêm trước/bỏ sau; quay lại mã cũ chỉ khi tương thích dữ liệu. Không dùng `ddl-auto=update` hoặc xóa cơ sở dữ liệu để nâng cấp.

## 16. Kiểm thử và nghiệm thu

### 16.1 Trường hợp trọng yếu

| ID | Trường hợp | Kết quả mong đợi |
|---|---|---|
| T01 | Đăng ký cùng email đồng thời | Một tài khoản |
| T02 | Mã hết hạn/dùng lại | Từ chối, không lộ mã |
| T03 | Google trùng email chưa liên kết | Liên kết có xác nhận, không chiếm tài khoản |
| T04 | Cookie cũ sau đăng xuất/đổi mật khẩu/khóa | Không truy cập được |
| T05 | Đoán ID thẻ/tệp/tiến độ/AI của người khác | 403/404, không đổi dữ liệu |
| T06 | Bộ đổi riêng tư/bị ẩn khi chia sẻ | Không xem hoặc sao chép trái quyền |
| T07 | Trùng cả nghĩa/cùng từ khác nghĩa | Chặn trùng hoàn toàn, cảnh báo trường hợp còn lại |
| T08 | CSV dấu phẩy/xuống dòng/BOM/công thức/URL tệp | Đọc đúng, lỗi theo dòng, không truy cập mạng tùy ý |
| T09 | Thử lại nhập/sao chép, nguồn bị xóa | Không ghi đôi, nội dung nhất quán |
| T10 | Hai chiều/hai tab/qua ngày/đổi múi giờ | Không vượt hạn mức, không đếm từ mới đôi |
| T11 | Gửi cùng lượt ôn 100 lần hoặc cùng khóa khác nội dung | Một lịch sử/cập nhật, khác nội dung trả xung đột |
| T12 | Hai thay đổi cùng phiên bản tiến độ | Một thành công, một xung đột, không mất cập nhật |
| T13 | Các trạng thái SRS/tạm ngưng/đặt lại | Công thức, ngày, số quên và giai đoạn đúng |
| T14 | Thẻ sửa/xóa giữa phiên | Dùng nội dung lúc học khi sửa; báo không còn khi xóa |
| T15 | Lưu/nộp/luyện lại và xem đáp án trước nộp | Chấm đúng, không lộ đáp án sớm |
| T16 | AI sai cấu trúc/HTML/chỉ dẫn độc hại/nguồn giả | Không tự lưu, lọc hoặc báo cần kiểm tra |
| T17 | Hai tác vụ cuối hạn mức | Giữ hạn mức đồng thời đúng, không vượt tổng |
| T18 | API 429/5xx/hết chờ/khóa sai | Thử hữu hạn, trạng thái chưa xác định đúng, học vẫn chạy |
| T19 | Lưu lại cùng nháp AI | Một thẻ đã nhận, kiểm tra phiên bản |
| T20 | Âm thanh sai/quá dài/không giọng/thiếu điểm hỗ trợ | Từ chối hoặc báo rõ, không giả điểm |
| T21 | Xóa tệp/tài khoản khi tác vụ đang chạy | Không lưu đầu ra mới, tệp được xóa, tiếp tục đúng mốc |
| T22 | Lặp bài dễ, thưởng đồng thời | Không thưởng đôi, tối đa 200 XP/ngày |
| T23 | Sự kiện trùng/sai thứ tự/mất quyền tác vụ | Không tăng đôi hoặc bỏ sự kiện |
| T24 | Múi giờ/đổi giờ mùa/tắt email sau khi xếp lịch | Chuỗi ngày đúng, không gửi trùng/trái lựa chọn |
| T25 | Chỉ số nhiều chủ đề/mốc 0/ít mẫu | Định nghĩa đúng, không kết luận quá mức |
| T26 | Xóa có khóa ngoại/báo cáo/khôi phục sao lưu | Đúng thứ tự, không phục hồi dữ liệu đã yêu cầu xóa |
| T27 | Màn hình 360px/bàn phím/mạng chậm/rỗng/lỗi | Dễ hiểu, thử lại không gửi đôi |
| T28 | Dữ liệu và tải lớn | Đạt mục tiêu hoặc công bố giới hạn và điểm nghẽn |

Kiểm thử đơn vị cho SRS/chấm/hạn mức/múi giờ; MySQL thật cho khóa/ràng buộc; Redis cho phiên; dịch vụ giả cho hợp đồng API; Playwright cho luồng đầu-cuối. Không dùng H2 thay hoàn toàn MySQL vì khác cú pháp và cách khóa.

### 16.2 Điều kiện hoàn thành

- [ ] Chức năng chính chạy từ đầu đến cuối; mở rộng có cờ bật/tắt và trạng thái rõ.
- [ ] Mỗi yêu cầu có API/màn hình/kiểm thử; MySQL tạo mới và giao dịch đồng thời được kiểm tra.
- [ ] AI/phát âm tắt hoặc lỗi không chặn học cốt lõi; âm thanh đã tạo phát lại được.
- [ ] Thẻ AI chỉ lưu sau chọn/xác nhận; đáp án/khóa không lộ giao diện.
- [ ] Quyền, thu hồi phiên, CSRF và truy cập trái sở hữu được kiểm tra.
- [ ] Có dữ liệu/tài khoản minh họa, URL HTTPS chạy thực tế, hướng dẫn cài đặt, OpenAPI và vận hành.
- [ ] Xóa dữ liệu được kiểm chứng cả cơ sở dữ liệu/tệp/tác vụ đến muộn/sao lưu.
- [ ] Kiểm thử tự động đạt; có báo cáo tải, diễn tập khôi phục và giám sát.

### 16.3 Kiểm chứng tài liệu

Ngày 26/09/2026, SQL tạo thành công **67 bảng trên MySQL 8.4.11**. Đã kiểm tra khóa ngoại từ chối tham chiếu không tồn tại; chống thẻ trùng đang dùng và cho tạo lại sau xóa mềm; giới hạn từ mới; báo cáo đúng một đích; tiến độ duy nhất theo người/thẻ/chiều.

Bản 1.2 đổi tên bảng/cột, giữ nguyên kiểu dữ liệu, quan hệ và quy tắc. Khối SQL đổi tên được kiểm tra lại trên MySQL 8.4.11. Kiểm tra cấu trúc tài liệu và đường dẫn mục lục được thực hiện riêng. Chưa có ứng dụng để kiểm chứng hiệu năng, bảo mật, giao dịch nghiệp vụ hoặc website; các ô nghiệm thu bên trên là yêu cầu khi xây dựng, không phải kết quả đã đạt.

## 17. Lộ trình và rủi ro

### 17.1 Giai đoạn

| Giai đoạn | Phạm vi | Điều kiện kết thúc |
|---|---|---|
| GĐ0 Nền tảng | Mã nguồn, môi trường, dữ liệu, tài khoản, tự động kiểm thử, tệp, quyền | Xác thực/quyền/CSRF và cập nhật cấu trúc chạy |
| GĐ1 Cốt lõi | Bộ thẻ, danh mục, CSV, SRS, bài cố định, lịch sử | Học không cần AI; bản minh họa cốt lõi |
| GĐ2 Cá nhân hóa | Sổ tay, kỹ năng, thống kê, chuỗi ngày, thưởng, nhắc, quản trị | Gợi ý có lý do, chống tăng điểm và thời gian đúng |
| GĐ3 API AI | Thẻ, ngữ cảnh, bài viết/bài luyện, lịch sử, hạn mức/chi phí, phát âm | Kiểm tra nháp, lỗi dịch vụ và quyền riêng tư |
| GĐ4 Hoàn thiện | Tải, khôi phục, điện thoại, tài liệu và HTTPS | Nghiệm thu chức năng chính |
| GĐ5 Mở rộng | Facebook, IELTS, trẻ em, hội thoại văn bản | Kiểm thử và rà soát riêng |

Bản GĐ1 chỉ là minh họa cốt lõi; nghiệm thu toàn bộ phải gồm phát âm ở GĐ3. GĐ0–GĐ5 là nhóm kỹ thuật, không phải mức ưu tiên P0/P1/P2 của danh sách sản phẩm. Lịch thực hiện theo ba đợt trong SCI_SE_2: đợt 1 (12–25/10) gồm GĐ0 và phần tài khoản/bộ thẻ/danh mục/CSV của GĐ1; đợt 2 (26/10–08/11) gồm SRS, bài cố định, cá nhân hóa, thống kê và phân quyền QT-02; đợt 3 (09–29/11) gồm GĐ3 cùng thưởng, nhắc và quản trị còn lại; 30/11–14/12 là GĐ4. GĐ5 chỉ làm khi phần chính đã nghiệm thu.

### 17.2 Rủi ro

| Rủi ro | Xử lý |
|---|---|
| Phạm vi lớn | Chia mốc, đối chiếu yêu cầu, bật/tắt mở rộng |
| AI sai nghĩa/đáp án | Xem/sửa trước lưu, kiểm tra, báo cáo; chấm cố định khi có đáp án |
| Đầu vào cố thay chỉ dẫn AI | Tách dữ liệu, không cấp công cụ, kiểm tra cấu trúc và nội dung |
| Vượt chi phí | Giữ hạn mức tổng/tính năng, giới hạn đồng thời, đối soát lượt chưa rõ |
| Tiến độ/XP bị ghi đôi | Khóa, phiên bản, khóa chống trùng, sổ thưởng duy nhất |
| Lách hạn mức/chuỗi ngày | Ngày sự kiện cố định, kiểm tra 24 giờ, chống gửi/thưởng trùng |
| Âm thanh khác định dạng | Kiểm tra khả năng trình duyệt, chuyển định dạng có kiểm soát |
| Sao chép tệp/xóa dữ liệu | Tệp độc lập hoặc công khai có phép; mốc xóa và kiểm tra tham chiếu |
| Redis/tác vụ nền gián đoạn | Giám sát, khởi động lại, sao lưu; nâng dự phòng khi cần |
| Tìm kiếm từ ngắn kém | Tìm tiền tố có giới hạn; cân nhắc công cụ tìm kiếm riêng sau đo |
| Nội dung trẻ em | Rà soát riêng; không mặc định thu dữ liệu trẻ em |

### 17.3 Nguyên tắc giữ khi lập trình

Spring Boot sở hữu nghiệp vụ; nội dung tách khỏi tiến độ; bản sao không mang tiến độ; điểm AI tách điểm cố định; chia sẻ không bỏ qua quyền/kiểm duyệt; UTC cho thời điểm, ngày địa phương cho học, ngày UTC cho hạn mức API; gọi dịch vụ ngoài giao dịch; sự kiện xử lý lại không ghi đôi; lịch sử vẫn có quy trình loại dữ liệu cá nhân.

## 18. Thuật ngữ và tài liệu tham khảo

### 18.1 Thuật ngữ cần giữ

| Thuật ngữ | Ý nghĩa |
|---|---|
| ID / USER | Mã định danh / người dùng; giữ trong tên trường và mã hệ thống |
| API | Giao diện giao tiếp giữa các phần mềm |
| SQL / DDL | Ngôn ngữ truy vấn / phần định nghĩa cấu trúc dữ liệu |
| PK / FK | Khóa chính / khóa ngoại |
| AI / LLM | Trí tuệ nhân tạo / mô hình ngôn ngữ lớn |
| Token AI | Đơn vị văn bản mô hình xử lý, khác mã xác thực đăng nhập |
| SRS / SM-2 | Ôn ngắt quãng / thuật toán tham khảo để tính lịch |
| TTS | Chuyển văn bản thành giọng nói |
| IPA / CEFR | Phiên âm quốc tế / khung trình độ ngôn ngữ |
| OAuth / OIDC | Chuẩn cấp quyền và xác thực khi đăng nhập qua dịch vụ khác |
| CSRF | Giả mạo yêu cầu dựa trên phiên đăng nhập |
| SSR | Dựng nội dung trang tại máy chủ |
| DTO / JSON | Đối tượng truyền dữ liệu / định dạng trao đổi dữ liệu |
| UTC / IANA | Chuẩn thời gian / hệ tên múi giờ |
| XP | Điểm kinh nghiệm |
| Outbox | Bảng lưu sự kiện cùng giao dịch nghiệp vụ để gửi sau |
| Idempotency | Gửi lại/xử lý lại không gây thêm kết quả trùng |

### 18.2 Nguồn

Nguồn được đối chiếu khi thiết kế. Kiến trúc, hạn mức và SRS biến thể là đề xuất của tài liệu, không phải yêu cầu bắt buộc từ nhà cung cấp.

- [Tự triển khai Next.js](https://nextjs.org/docs/app/guides/self-hosting).
- [Cài đặt Next.js và yêu cầu môi trường](https://nextjs.org/docs/app/getting-started/installation).
- [Yêu cầu hệ thống Spring Boot](https://docs.spring.io/spring-boot/system-requirements.html).
- [Phiên bản Spring Boot được hỗ trợ](https://github.com/spring-projects/spring-boot/wiki/Supported-Versions).
- [Lưu mật khẩu với Spring Security](https://docs.spring.io/spring-security/reference/7.0/features/authentication/password-storage.html).
- [Chống giả mạo yêu cầu với Spring Security](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html).
- [Đăng nhập OAuth với Spring Security](https://docs.spring.io/spring-security/reference/servlet/oauth2/login/core.html).
- [Khóa ngoại MySQL 8.4](https://dev.mysql.com/doc/refman/8.4/en/create-table-foreign-keys.html).
- [Thông tin ràng buộc MySQL](https://dev.mysql.com/doc/refman/8.4/en/information-schema-table-constraints-table.html).
- [Đầu ra có cấu trúc OpenAI](https://developers.openai.com/api/docs/guides/structured-outputs).
- [API âm thanh OpenAI](https://platform.openai.com/docs/api-reference/audio).
- [Đánh giá phát âm Microsoft](https://learn.microsoft.com/en-us/azure/ai-services/speech-service/how-to-pronunciation-assessment).
- [Mã tham khảo SM-2](https://github.com/open-spaced-repetition/sm-2).
