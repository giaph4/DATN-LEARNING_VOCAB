# YÊU CẦU NGƯỜI DÙNG

**Tên đề tài:** Xây dựng website học từ vựng tiếng Anh ứng dụng ôn tập ngắt quãng và hỗ trợ trí tuệ nhân tạo.

## THÔNG TIN DỰ ÁN

| Mục | Thông tin |
| --- | --- |
| Dự án viết tắt | VocabLearning — tên quy ước |
| Tên dự án | Xây dựng website học từ vựng tiếng Anh ứng dụng ôn tập ngắt quãng và hỗ trợ trí tuệ nhân tạo |
| Ngày bắt đầu | 28/09/2026 |
| Ngày kết thúc | 14/12/2026 |
| Thời gian thực hiện | 2,5 tháng; 78 ngày lịch tính cả hai đầu mốc |
| Nơi thực hiện | Khoa Công nghệ thông tin |
| Giảng viên hướng dẫn | Lương Thị Thu Phương |
| Email/điện thoại giảng viên | Chưa cung cấp; không dùng thông tin của giảng viên trong mẫu |
| Đại diện nhóm | Huỳnh Gia Pho |
| Chủ sở hữu sản phẩm | Đề xuất nhóm trưởng điều phối yêu cầu; cả nhóm thống nhất với giảng viên |
| Quản lý dự án | Huỳnh Gia Pho — nhóm trưởng; vai trò tổ chức phát triển được mô tả trong kế hoạch |
| Liên hệ nhóm | Email/điện thoại chưa cung cấp |

### Thành viên trong đội

| TT | Họ và tên | Mã số sinh viên | Lớp | Vai trò |
| --- | --- | --- | --- | --- |
| 1 | Huỳnh Gia Pho | 28211152416 | K28TPM5 | Nhóm trưởng |
| 2 | Phạm Ngọc Thái | 28211106637 | K28TPM10 | Thành viên |
| 3 | Nguyễn Đình Nhật Quân | 28211100097 | K28TPM10 | Thành viên |
| 4 | Lê Hữu Thảo | 28212241493 | K28TPM9 | Thành viên |
| 5 | Trần Văn Màu | 28212306184 | K28TPM24 | Thành viên |

## THÔNG TIN TÀI LIỆU

| Mục | Nội dung |
| --- | --- |
| Tên dự án | Xây dựng website học từ vựng tiếng Anh ứng dụng ôn tập ngắt quãng và hỗ trợ trí tuệ nhân tạo |
| Tiêu đề tài liệu | Yêu cầu người dùng |
| Mã tài liệu | SCI_SE_3 |
| Người thực hiện | Nhóm Huỳnh Gia Pho |
| Phiên bản | 1.1 |
| Ngày lập | 26/09/2026 |
| Trạng thái | Bản thiết kế và kế hoạch; chưa ghi nhận phê duyệt hay kết quả kiểm thử thực tế |
| Ngôn ngữ | Tiếng Việt; giữ tên công nghệ, ID, API, SQL và các tên kỹ thuật cần thiết |
| Quy ước dữ liệu | Tên bảng/cột tiếng Việt không dấu dạng nguoi_dung, bo_the, trang_thai; giữ id, email, password_hash, created_at, updated_at, ipa, slug và kiểu SQL |

## LỊCH SỬ CHỈNH SỬA TÀI LIỆU

| Phiên bản | Người chỉnh sửa | Ngày | Ghi chú |
| --- | --- | --- | --- |
| 1.0 | Nhóm thực hiện | 26/09/2026 | Lập đầy đủ nội dung theo mẫu; đối chiếu 111 mục chức năng và 12 yêu cầu phi chức năng |
| 1.1 | Nhóm thực hiện | 26/09/2026 | Đồng bộ giữa các tài liệu: giao diện JavaScript; đường dẫn trang tiếng Việt; tên VocabLearning và CSDL `vocab_learning`; QT-02 sang đợt 2; tiêu chí ghi nhớ và ngưỡng sổ tay thống nhất; sửa tên kỹ thuật bị đổi nhầm; phân bổ giờ theo người |

## PHÊ DUYỆT TÀI LIỆU

| Vai trò | Họ tên | Chữ ký | Ngày |
| --- | --- | --- | --- |
| Giảng viên hướng dẫn | Lương Thị Thu Phương | Chưa ký | Chưa xác nhận |
| Nhóm trưởng | Huỳnh Gia Pho | Chưa ký | Chưa xác nhận |
| Thành viên | Phạm Ngọc Thái | Chưa ký | Chưa xác nhận |
| Thành viên | Nguyễn Đình Nhật Quân | Chưa ký | Chưa xác nhận |
| Thành viên | Lê Hữu Thảo | Chưa ký | Chưa xác nhận |
| Thành viên | Trần Văn Màu | Chưa ký | Chưa xác nhận |

## MỤC LỤC

  - Thành viên trong đội
- 1. MỤC ĐÍCH TÀI LIỆU
- 2. MỤC TIÊU HỆ THỐNG
- 3. RÀNG BUỘC
- 4. CÔNG CỤ VÀ PHƯƠNG PHÁP QUẢN LÝ HỆ THỐNG
- 5. USER STORY TRONG HỆ THỐNG
  - 5.0. Danh mục yêu cầu
  - 5.1. User Story 1 — TK-01 — Đăng ký bằng email
  - 5.2. User Story 2 — TK-02 — Xác thực email
  - 5.3. User Story 3 — TK-03 — Đăng nhập bằng email và Google
  - 5.4. User Story 4 — TK-04 — Đăng nhập Facebook — mở rộng
  - 5.5. User Story 5 — TK-05 — Đăng xuất, quên mật khẩu và đặt lại mật khẩu
  - 5.6. User Story 6 — TK-06 — Cập nhật hồ sơ và ảnh đại diện
  - 5.7. User Story 7 — TK-07 — Chọn trình độ, mục tiêu và chủ đề quan tâm
  - 5.8. User Story 8 — TK-08 — Đặt số từ mới và thời gian học mỗi ngày
  - 5.9. User Story 9 — TK-09 — Cài đặt giờ nhắc học, múi giờ và thông báo
  - 5.10. User Story 10 — TK-10 — Xem streak và lịch sử hoạt động
  - 5.11. User Story 11 — TK-11 — Yêu cầu xóa tài khoản và dữ liệu cá nhân
  - 5.12. User Story 12 — BT-01 — Tạo, xem, sửa và xóa bộ thẻ
  - 5.13. User Story 13 — BT-02 — Tạo, sửa và xóa từng thẻ
  - 5.14. User Story 14 — BT-03 — Lưu nội dung từ/cụm từ và đa phương tiện
  - 5.15. User Story 15 — BT-04 — Gắn chủ đề, nhãn và mức độ khó
  - 5.16. User Story 16 — BT-05 — Đặt bộ thẻ công khai hoặc riêng tư
  - 5.17. User Story 17 — BT-06 — Tìm kiếm, lọc và sắp xếp bộ thẻ
  - 5.18. User Story 18 — BT-07 — Sao chép bộ thẻ công khai
  - 5.19. User Story 19 — BT-08 — Nhập và xuất CSV
  - 5.20. User Story 20 — BT-09 — Cảnh báo từ trùng trong cùng bộ
  - 5.21. User Story 21 — BT-10 — Đánh dấu bộ thẻ yêu thích
  - 5.22. User Story 22 — BT-11 — Chia sẻ bộ thẻ bằng liên kết
  - 5.23. User Story 23 — BT-12 — Báo cáo nội dung sai hoặc không phù hợp
  - 5.24. User Story 24 — ND-01 — Bộ từ vựng giao tiếp theo tình huống
  - 5.25. User Story 25 — ND-02 — Bộ từ vựng TOEIC theo chủ đề
  - 5.26. User Story 26 — ND-03 — Phân loại nội dung theo trình độ
  - 5.27. User Story 27 — ND-04 — Tìm kiếm và xem trước bộ thẻ
  - 5.28. User Story 28 — ND-05 — Hiển thị số thẻ và mô tả nội dung
  - 5.29. User Story 29 — ND-06 — Phân biệt bộ quản trị và bộ người dùng
  - 5.30. User Story 30 — ND-07 — Bộ IELTS và tiếng Anh trẻ em — mở rộng
  - 5.31. User Story 31 — ON-01 — Lật thẻ tương tác
  - 5.32. User Story 32 — ON-02 — Hỗ trợ phím tắt trên máy tính
  - 5.33. User Story 33 — ON-03 — Học Anh–Việt hoặc Việt–Anh
  - 5.34. User Story 34 — ON-04 — Phát audio từ và câu ví dụ
  - 5.35. User Story 35 — ON-05 — Chọn mức độ Quên, Khó, Nhớ, Dễ
  - 5.36. User Story 36 — ON-06 — Tính ngày ôn tiếp theo bằng SRS
  - 5.37. User Story 37 — ON-07 — Lưu trạng thái riêng từng người với từng thẻ
  - 5.38. User Story 38 — ON-08 — Hiển thị thẻ đến hạn, quá hạn và từ mới
  - 5.39. User Story 39 — ON-09 — Lưu lịch sử ôn và số lần quên
  - 5.40. User Story 40 — ON-10 — Giới hạn số từ mới mỗi ngày
  - 5.41. User Story 41 — ON-11 — Tổng kết phiên học
  - 5.42. User Story 42 — ON-12 — Tạm ngưng thẻ hoặc đặt lại tiến độ
  - 5.43. User Story 43 — ON-13 — Tạo phiên theo quỹ thời gian
  - 5.44. User Story 44 — ON-14 — Chia lịch khi quay lại sau nghỉ
  - 5.45. User Story 45 — LT-01 — Trắc nghiệm chọn nghĩa
  - 5.46. User Story 46 — LT-02 — Trắc nghiệm chọn từ phù hợp
  - 5.47. User Story 47 — LT-03 — Nghe và viết lại từ
  - 5.48. User Story 48 — LT-04 — Điền từ vào chỗ trống
  - 5.49. User Story 49 — LT-05 — Ghép từ với nghĩa
  - 5.50. User Story 50 — LT-06 — Tự nhập từ từ gợi ý nghĩa
  - 5.51. User Story 51 — LT-07 — Phân biệt cặp từ dễ nhầm
  - 5.52. User Story 52 — LT-08 — Viết câu dùng từ mục tiêu
  - 5.53. User Story 53 — LT-09 — Bài kiểm tra tổng hợp theo bộ
  - 5.54. User Story 54 — LT-10 — Chấm bài có đáp án cố định
  - 5.55. User Story 55 — LT-11 — Giải thích đáp án
  - 5.56. User Story 56 — LT-12 — Lịch sử bài làm và luyện lại câu sai
  - 5.57. User Story 57 — AI-01 — Tạo Flashcard từ đoạn văn tiếng Anh
  - 5.58. User Story 58 — AI-02 — Gợi ý nghĩa ngữ cảnh, ví dụ và mẹo nhớ
  - 5.59. User Story 59 — AI-03 — Xem, sửa và chọn thẻ AI trước khi lưu
  - 5.60. User Story 60 — AI-04 — Tạo bài luyện từ từ sai hoặc đến hạn
  - 5.61. User Story 61 — AI-05 — Giải thích khác nhau giữa từ dễ nhầm
  - 5.62. User Story 62 — AI-06 — Sửa câu và giải thích tiếng Việt
  - 5.63. User Story 63 — AI-07 — Tạo đoạn văn hoặc tình huống chứa từ mục tiêu
  - 5.64. User Story 64 — AI-08 — Phản hồi cách dùng từ trong nhiệm vụ thực tế
  - 5.65. User Story 65 — AI-09 — Hội thoại AI bằng văn bản — mở rộng
  - 5.66. User Story 66 — AI-10 — Báo cáo AI sai hoặc không hữu ích
  - 5.67. User Story 67 — AI-11 — Lưu lịch sử nội dung AI
  - 5.68. User Story 68 — AI-12 — Giới hạn lượt và xử lý API không khả dụng
  - 5.69. User Story 69 — ST-01 — Lưu thủ công từ khó
  - 5.70. User Story 70 — ST-02 — Đề xuất từ thường sai vào sổ tay
  - 5.71. User Story 71 — ST-03 — Phân nhóm lỗi
  - 5.72. User Story 72 — ST-04 — Xem hồ sơ điểm yếu
  - 5.73. User Story 73 — ST-05 — Tạo bộ luyện từ lỗi phổ biến
  - 5.74. User Story 74 — ST-06 — Mức độ làm chủ từng từ qua nhiều kỹ năng
  - 5.75. User Story 75 — ST-07 — Theo dõi cặp từ thường nhầm
  - 5.76. User Story 76 — ST-08 — Gợi ý bài luyện phù hợp
  - 5.77. User Story 77 — ST-09 — Xem lý do đề xuất bài hoặc thẻ
  - 5.78. User Story 78 — TKH-01 — Số từ mới, đang học và đạt tiêu chí ghi nhớ
  - 5.79. User Story 79 — TKH-02 — Số thẻ ôn theo ngày, tuần, tháng
  - 5.80. User Story 80 — TKH-03 — Tỷ lệ đúng theo dạng bài
  - 5.81. User Story 81 — TKH-04 — Thời gian học và hoàn thành mục tiêu
  - 5.82. User Story 82 — TKH-05 — Biểu đồ hoạt động và streak
  - 5.83. User Story 83 — TKH-06 — Thống kê theo chủ đề hoặc bộ thẻ
  - 5.84. User Story 84 — TKH-07 — Danh sách từ thường quên
  - 5.85. User Story 85 — TKH-08 — So sánh tiến độ giữa các giai đoạn
  - 5.86. User Story 86 — TKH-09 — Báo cáo tổng kết tuần
  - 5.87. User Story 87 — TKH-10 — Tiến độ theo kỹ năng sử dụng từ
  - 5.88. User Story 88 — DL-01 — Điểm kinh nghiệm khi hoàn thành nhiệm vụ
  - 5.89. User Story 89 — DL-02 — Huy hiệu theo thành tích
  - 5.90. User Story 90 — DL-03 — Thử thách hằng ngày và hằng tuần
  - 5.91. User Story 91 — DL-04 — Theo dõi chuỗi ngày học
  - 5.92. User Story 92 — DL-05 — Thông báo thẻ đến hạn trong ứng dụng
  - 5.93. User Story 93 — DL-06 — Email nhắc học theo lựa chọn
  - 5.94. User Story 94 — DL-07 — Thông báo hoàn thành mục tiêu
  - 5.95. User Story 95 — PA-01 — Nghe phát âm mẫu
  - 5.96. User Story 96 — PA-02 — Ghi âm đọc từ hoặc câu
  - 5.97. User Story 97 — PA-03 — Phát lại bản ghi
  - 5.98. User Story 98 — PA-04 — Gửi bản ghi đến API phát âm
  - 5.99. User Story 99 — PA-05 — Hiển thị điểm và phản hồi API hỗ trợ
  - 5.100. User Story 100 — PA-06 — Lưu kết quả theo dõi cải thiện
  - 5.101. User Story 101 — PA-07 — Luyện lại từ kết quả thấp
  - 5.102. User Story 102 — PA-08 — Xóa bản ghi âm
  - 5.103. User Story 103 — QT-01 — Quản lý, khóa và mở khóa tài khoản
  - 5.104. User Story 104 — QT-02 — Phân quyền người học và quản trị viên
  - 5.105. User Story 105 — QT-03 — Quản lý chủ đề, bộ thẻ và thẻ mẫu
  - 5.106. User Story 106 — QT-04 — Kiểm duyệt nội dung báo cáo
  - 5.107. User Story 107 — QT-05 — Xem thống kê sử dụng
  - 5.108. User Story 108 — QT-06 — Cấu hình giới hạn AI
  - 5.109. User Story 109 — QT-07 — Theo dõi lượt gọi, lỗi và chi phí API
  - 5.110. User Story 110 — QT-08 — Quản lý thông báo hệ thống
  - 5.111. User Story 111 — QT-09 — Nhật ký thao tác quản trị quan trọng
- PHỤ LỤC A — YÊU CẦU PHI CHỨC NĂNG
- PHỤ LỤC B — QUY TẮC CHUNG
  - Quy tắc nghiệp vụ
- PHỤ LỤC C — ĐẶC TẢ API
  - Đặc tả API

## 1. MỤC ĐÍCH TÀI LIỆU
Mô tả từng nhu cầu theo tác nhân, lợi ích, điều kiện, luồng, tiêu chí chấp nhận và ràng buộc. Mỗi ID tương ứng đúng một mục trong danh sách người dùng ban đầu; mục có nhiều thao tác như đăng xuất/quên/đặt lại mật khẩu được kiểm tra từng nhánh, không chỉ một nhánh đại diện.
## 2. MỤC TIÊU HỆ THỐNG
Giúp người học xây dựng thư viện từ, học mới có hạn mức, ôn theo lịch, luyện nhiều kỹ năng, nhận gợi ý có căn cứ và theo dõi tiến độ. AI/phát âm cung cấp hỗ trợ qua API có sẵn. Quản trị quản lý nội dung, tài khoản và vận hành có minh chứng.
## 3. RÀNG BUỘC
Năm sinh viên, 2,5 tháng, Next.js/Spring Boot/MySQL; nội dung tiếng Việt, tên dữ liệu không dấu. Quyền kiểm tra ở máy chủ. Các mức/giới hạn số lượng là đề xuất thiết kế có phiên bản; thay đổi phải cập nhật tiêu chí kiểm thử. Mở rộng giữ đầy đủ đặc tả, chỉ triển khai sau phần chính. Không tuyên bố AI đúng tuyệt đối hoặc chỉ báo ôn tập đồng nghĩa mọi kỹ năng đã làm chủ.
## 4. CÔNG CỤ VÀ PHƯƠNG PHÁP QUẢN LÝ HỆ THỐNG
Quản lý theo đợt, Git và bảng công việc theo ID. Dùng Markdown cho tài liệu; API được định nghĩa và kiểm chứng bằng OpenAPI khi lập trình. SQL cập nhật bằng Flyway. Mỗi yêu cầu liên kết danh sách sản phẩm, UI, nhóm bảng và tình huống kiểm thử. Tình huống kiểm thử và trạng thái triển khai không tự chuyển thành đã đạt.
## 5. USER STORY TRONG HỆ THỐNG
### 5.0. Danh mục yêu cầu

| ID | Nhóm | Chức năng | Tác nhân | Phạm vi | Đợt | Ước lượng giờ |
| --- | --- | --- | --- | --- | --- | --- |
| TK-01 | Tài khoản và hồ sơ | Đăng ký bằng email | Khách/người học | Chính | 1 | 8 |
| TK-02 | Tài khoản và hồ sơ | Xác thực email | Khách/người học | Chính | 1 | 8 |
| TK-03 | Tài khoản và hồ sơ | Đăng nhập bằng email và Google | Khách/người học | Chính | 1 | 8 |
| TK-04 | Tài khoản và hồ sơ | Đăng nhập Facebook — mở rộng | Khách/người học | Mở rộng | Sau phần chính | Chưa cam kết |
| TK-05 | Tài khoản và hồ sơ | Đăng xuất, quên mật khẩu và đặt lại mật khẩu | Khách/người học | Chính | 1 | 8 |
| TK-06 | Tài khoản và hồ sơ | Cập nhật hồ sơ và ảnh đại diện | Người học | Chính | 1 | 8 |
| TK-07 | Tài khoản và hồ sơ | Chọn trình độ, mục tiêu và chủ đề quan tâm | Người học | Chính | 1 | 8 |
| TK-08 | Tài khoản và hồ sơ | Đặt số từ mới và thời gian học mỗi ngày | Người học | Chính | 1 | 8 |
| TK-09 | Tài khoản và hồ sơ | Cài đặt giờ nhắc học, múi giờ và thông báo | Người học | Chính | 1 | 8 |
| TK-10 | Tài khoản và hồ sơ | Xem streak và lịch sử hoạt động | Người học | Chính | 1 | 8 |
| TK-11 | Tài khoản và hồ sơ | Yêu cầu xóa tài khoản và dữ liệu cá nhân | Người học | Chính | 1 | 8 |
| BT-01 | Bộ thẻ và thẻ | Tạo, xem, sửa và xóa bộ thẻ | Người học | Chính | 1 | 8 |
| BT-02 | Bộ thẻ và thẻ | Tạo, sửa và xóa từng thẻ | Người học | Chính | 1 | 8 |
| BT-03 | Bộ thẻ và thẻ | Lưu nội dung từ/cụm từ và đa phương tiện | Người học | Chính | 1 | 8 |
| BT-04 | Bộ thẻ và thẻ | Gắn chủ đề, nhãn và mức độ khó | Người học | Chính | 1 | 8 |
| BT-05 | Bộ thẻ và thẻ | Đặt bộ thẻ công khai hoặc riêng tư | Người học | Chính | 1 | 8 |
| BT-06 | Bộ thẻ và thẻ | Tìm kiếm, lọc và sắp xếp bộ thẻ | Người học | Chính | 1 | 8 |
| BT-07 | Bộ thẻ và thẻ | Sao chép bộ thẻ công khai | Người học | Chính | 1 | 8 |
| BT-08 | Bộ thẻ và thẻ | Nhập và xuất CSV | Người học | Chính | 1 | 8 |
| BT-09 | Bộ thẻ và thẻ | Cảnh báo từ trùng trong cùng bộ | Người học | Chính | 1 | 8 |
| BT-10 | Bộ thẻ và thẻ | Đánh dấu bộ thẻ yêu thích | Người học | Chính | 1 | 8 |
| BT-11 | Bộ thẻ và thẻ | Chia sẻ bộ thẻ bằng liên kết | Người học | Chính | 1 | 8 |
| BT-12 | Bộ thẻ và thẻ | Báo cáo nội dung sai hoặc không phù hợp | Người học | Chính | 1 | 8 |
| ND-01 | Nội dung có sẵn | Bộ từ vựng giao tiếp theo tình huống | Khách/người học | Chính | 1 | 8 |
| ND-02 | Nội dung có sẵn | Bộ từ vựng TOEIC theo chủ đề | Khách/người học | Chính | 1 | 8 |
| ND-03 | Nội dung có sẵn | Phân loại nội dung theo trình độ | Khách/người học | Chính | 1 | 8 |
| ND-04 | Nội dung có sẵn | Tìm kiếm và xem trước bộ thẻ | Khách/người học | Chính | 1 | 8 |
| ND-05 | Nội dung có sẵn | Hiển thị số thẻ và mô tả nội dung | Khách/người học | Chính | 1 | 8 |
| ND-06 | Nội dung có sẵn | Phân biệt bộ quản trị và bộ người dùng | Khách/người học | Chính | 1 | 8 |
| ND-07 | Nội dung có sẵn | Bộ IELTS và tiếng Anh trẻ em — mở rộng | Khách/người học | Mở rộng | Sau phần chính | Chưa cam kết |
| ON-01 | Học và ôn tập | Lật thẻ tương tác | Người học | Chính | 2 | 8 |
| ON-02 | Học và ôn tập | Hỗ trợ phím tắt trên máy tính | Người học | Chính | 2 | 8 |
| ON-03 | Học và ôn tập | Học Anh–Việt hoặc Việt–Anh | Người học | Chính | 2 | 8 |
| ON-04 | Học và ôn tập | Phát audio từ và câu ví dụ | Người học | Chính | 2 | 8 |
| ON-05 | Học và ôn tập | Chọn mức độ Quên, Khó, Nhớ, Dễ | Người học | Chính | 2 | 8 |
| ON-06 | Học và ôn tập | Tính ngày ôn tiếp theo bằng SRS | Người học | Chính | 2 | 8 |
| ON-07 | Học và ôn tập | Lưu trạng thái riêng từng người với từng thẻ | Người học | Chính | 2 | 8 |
| ON-08 | Học và ôn tập | Hiển thị thẻ đến hạn, quá hạn và từ mới | Người học | Chính | 2 | 8 |
| ON-09 | Học và ôn tập | Lưu lịch sử ôn và số lần quên | Người học | Chính | 2 | 8 |
| ON-10 | Học và ôn tập | Giới hạn số từ mới mỗi ngày | Người học | Chính | 2 | 8 |
| ON-11 | Học và ôn tập | Tổng kết phiên học | Người học | Chính | 2 | 8 |
| ON-12 | Học và ôn tập | Tạm ngưng thẻ hoặc đặt lại tiến độ | Người học | Chính | 2 | 8 |
| ON-13 | Học và ôn tập | Tạo phiên theo quỹ thời gian | Người học | Chính | 2 | 8 |
| ON-14 | Học và ôn tập | Chia lịch khi quay lại sau nghỉ | Người học | Chính | 2 | 8 |
| LT-01 | Luyện tập và kiểm tra | Trắc nghiệm chọn nghĩa | Người học | Chính | 2 | 8 |
| LT-02 | Luyện tập và kiểm tra | Trắc nghiệm chọn từ phù hợp | Người học | Chính | 2 | 8 |
| LT-03 | Luyện tập và kiểm tra | Nghe và viết lại từ | Người học | Chính | 2 | 8 |
| LT-04 | Luyện tập và kiểm tra | Điền từ vào chỗ trống | Người học | Chính | 2 | 8 |
| LT-05 | Luyện tập và kiểm tra | Ghép từ với nghĩa | Người học | Chính | 2 | 8 |
| LT-06 | Luyện tập và kiểm tra | Tự nhập từ từ gợi ý nghĩa | Người học | Chính | 2 | 8 |
| LT-07 | Luyện tập và kiểm tra | Phân biệt cặp từ dễ nhầm | Người học | Chính | 2 | 8 |
| LT-08 | Luyện tập và kiểm tra | Viết câu dùng từ mục tiêu | Người học | Chính | 2 | 8 |
| LT-09 | Luyện tập và kiểm tra | Bài kiểm tra tổng hợp theo bộ | Người học | Chính | 2 | 8 |
| LT-10 | Luyện tập và kiểm tra | Chấm bài có đáp án cố định | Người học | Chính | 2 | 8 |
| LT-11 | Luyện tập và kiểm tra | Giải thích đáp án | Người học | Chính | 2 | 8 |
| LT-12 | Luyện tập và kiểm tra | Lịch sử bài làm và luyện lại câu sai | Người học | Chính | 2 | 8 |
| AI-01 | Trí tuệ nhân tạo | Tạo Flashcard từ đoạn văn tiếng Anh | Người học | Chính | 3 | 10 |
| AI-02 | Trí tuệ nhân tạo | Gợi ý nghĩa ngữ cảnh, ví dụ và mẹo nhớ | Người học | Chính | 3 | 10 |
| AI-03 | Trí tuệ nhân tạo | Xem, sửa và chọn thẻ AI trước khi lưu | Người học | Chính | 3 | 10 |
| AI-04 | Trí tuệ nhân tạo | Tạo bài luyện từ từ sai hoặc đến hạn | Người học | Chính | 3 | 10 |
| AI-05 | Trí tuệ nhân tạo | Giải thích khác nhau giữa từ dễ nhầm | Người học | Chính | 3 | 10 |
| AI-06 | Trí tuệ nhân tạo | Sửa câu và giải thích tiếng Việt | Người học | Chính | 3 | 10 |
| AI-07 | Trí tuệ nhân tạo | Tạo đoạn văn hoặc tình huống chứa từ mục tiêu | Người học | Chính | 3 | 10 |
| AI-08 | Trí tuệ nhân tạo | Phản hồi cách dùng từ trong nhiệm vụ thực tế | Người học | Chính | 3 | 10 |
| AI-09 | Trí tuệ nhân tạo | Hội thoại AI bằng văn bản — mở rộng | Người học | Mở rộng | Sau phần chính | Chưa cam kết |
| AI-10 | Trí tuệ nhân tạo | Báo cáo AI sai hoặc không hữu ích | Người học | Chính | 3 | 10 |
| AI-11 | Trí tuệ nhân tạo | Lưu lịch sử nội dung AI | Người học | Chính | 3 | 10 |
| AI-12 | Trí tuệ nhân tạo | Giới hạn lượt và xử lý API không khả dụng | Người học | Chính | 3 | 10 |
| ST-01 | Sổ tay và điểm yếu | Lưu thủ công từ khó | Người học | Chính | 2 | 8 |
| ST-02 | Sổ tay và điểm yếu | Đề xuất từ thường sai vào sổ tay | Người học | Chính | 2 | 8 |
| ST-03 | Sổ tay và điểm yếu | Phân nhóm lỗi | Người học | Chính | 2 | 8 |
| ST-04 | Sổ tay và điểm yếu | Xem hồ sơ điểm yếu | Người học | Chính | 2 | 8 |
| ST-05 | Sổ tay và điểm yếu | Tạo bộ luyện từ lỗi phổ biến | Người học | Chính | 2 | 8 |
| ST-06 | Sổ tay và điểm yếu | Mức độ làm chủ từng từ qua nhiều kỹ năng | Người học | Chính | 2 | 8 |
| ST-07 | Sổ tay và điểm yếu | Theo dõi cặp từ thường nhầm | Người học | Chính | 2 | 8 |
| ST-08 | Sổ tay và điểm yếu | Gợi ý bài luyện phù hợp | Người học | Chính | 2 | 8 |
| ST-09 | Sổ tay và điểm yếu | Xem lý do đề xuất bài hoặc thẻ | Người học | Chính | 2 | 8 |
| TKH-01 | Thống kê học tập | Số từ mới, đang học và đạt tiêu chí ghi nhớ | Người học | Chính | 2 | 8 |
| TKH-02 | Thống kê học tập | Số thẻ ôn theo ngày, tuần, tháng | Người học | Chính | 2 | 8 |
| TKH-03 | Thống kê học tập | Tỷ lệ đúng theo dạng bài | Người học | Chính | 2 | 8 |
| TKH-04 | Thống kê học tập | Thời gian học và hoàn thành mục tiêu | Người học | Chính | 2 | 8 |
| TKH-05 | Thống kê học tập | Biểu đồ hoạt động và streak | Người học | Chính | 2 | 8 |
| TKH-06 | Thống kê học tập | Thống kê theo chủ đề hoặc bộ thẻ | Người học | Chính | 2 | 8 |
| TKH-07 | Thống kê học tập | Danh sách từ thường quên | Người học | Chính | 2 | 8 |
| TKH-08 | Thống kê học tập | So sánh tiến độ giữa các giai đoạn | Người học | Chính | 2 | 8 |
| TKH-09 | Thống kê học tập | Báo cáo tổng kết tuần | Người học | Chính | 2 | 8 |
| TKH-10 | Thống kê học tập | Tiến độ theo kỹ năng sử dụng từ | Người học | Chính | 2 | 8 |
| DL-01 | Động lực và thông báo | Điểm kinh nghiệm khi hoàn thành nhiệm vụ | Người học | Chính | 3 | 10 |
| DL-02 | Động lực và thông báo | Huy hiệu theo thành tích | Người học | Chính | 3 | 10 |
| DL-03 | Động lực và thông báo | Thử thách hằng ngày và hằng tuần | Người học | Chính | 3 | 10 |
| DL-04 | Động lực và thông báo | Theo dõi chuỗi ngày học | Người học | Chính | 3 | 10 |
| DL-05 | Động lực và thông báo | Thông báo thẻ đến hạn trong ứng dụng | Người học | Chính | 3 | 10 |
| DL-06 | Động lực và thông báo | Email nhắc học theo lựa chọn | Người học | Chính | 3 | 10 |
| DL-07 | Động lực và thông báo | Thông báo hoàn thành mục tiêu | Người học | Chính | 3 | 10 |
| PA-01 | Luyện phát âm | Nghe phát âm mẫu | Người học | Chính | 3 | 10 |
| PA-02 | Luyện phát âm | Ghi âm đọc từ hoặc câu | Người học | Chính | 3 | 10 |
| PA-03 | Luyện phát âm | Phát lại bản ghi | Người học | Chính | 3 | 10 |
| PA-04 | Luyện phát âm | Gửi bản ghi đến API phát âm | Người học | Chính | 3 | 10 |
| PA-05 | Luyện phát âm | Hiển thị điểm và phản hồi API hỗ trợ | Người học | Chính | 3 | 10 |
| PA-06 | Luyện phát âm | Lưu kết quả theo dõi cải thiện | Người học | Chính | 3 | 10 |
| PA-07 | Luyện phát âm | Luyện lại từ kết quả thấp | Người học | Chính | 3 | 10 |
| PA-08 | Luyện phát âm | Xóa bản ghi âm | Người học | Chính | 3 | 10 |
| QT-01 | Quản trị | Quản lý, khóa và mở khóa tài khoản | Quản trị viên | Chính | 3 | 10 |
| QT-02 | Quản trị | Phân quyền người học và quản trị viên | Quản trị viên | Chính | 2 | 10 |
| QT-03 | Quản trị | Quản lý chủ đề, bộ thẻ và thẻ mẫu | Quản trị viên | Chính | 3 | 10 |
| QT-04 | Quản trị | Kiểm duyệt nội dung báo cáo | Quản trị viên | Chính | 3 | 10 |
| QT-05 | Quản trị | Xem thống kê sử dụng | Quản trị viên | Chính | 3 | 10 |
| QT-06 | Quản trị | Cấu hình giới hạn AI | Quản trị viên | Chính | 3 | 10 |
| QT-07 | Quản trị | Theo dõi lượt gọi, lỗi và chi phí API | Quản trị viên | Chính | 3 | 10 |
| QT-08 | Quản trị | Quản lý thông báo hệ thống | Quản trị viên | Chính | 3 | 10 |
| QT-09 | Quản trị | Nhật ký thao tác quản trị quan trọng | Quản trị viên | Chính | 3 | 10 |


### 5.1. User Story 1 — TK-01 — Đăng ký bằng email

Là **khách/người học**, tôi muốn **đăng ký bằng email** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-01 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Khách truy cập được trang xác thực; dịch vụ email/nhà cung cấp đăng nhập được cấu hình cho luồng tương ứng. Thao tác đăng xuất/đổi mật khẩu áp dụng phiên của chính người dùng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Email chuẩn hóa là duy nhất; tạo tài khoản chờ xác thực, password_hash và email xác thực, không trả mật khẩu trong phản hồi |
| Nhánh không hợp lệ | Email sai định dạng hoặc đã tồn tại; mật khẩu dưới 10 ký tự; gửi lại cùng yêu cầu không tạo thêm tài khoản |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Email chuẩn hóa là duy nhất; tạo tài khoản chờ xác thực, password_hash và email xác thực, không trả mật khẩu trong phản hồi.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Email sai định dạng hoặc đã tồn tại; mật khẩu dưới 10 ký tự; gửi lại cùng yêu cầu không tạo thêm tài khoản. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.2. User Story 2 — TK-02 — Xác thực email

Là **khách/người học**, tôi muốn **xác thực email** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-02 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Khách truy cập được trang xác thực; dịch vụ email/nhà cung cấp đăng nhập được cấu hình cho luồng tương ứng. Thao tác đăng xuất/đổi mật khẩu áp dụng phiên của chính người dùng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Mã ngẫu nhiên chỉ lưu bản băm, dùng một lần trong 24 giờ; xác thực thành công chuyển trạng thái sang ACTIVE; gửi lại có giới hạn |
| Nhánh không hợp lệ | Mã hết hạn, đã dùng hoặc của tài khoản khác không kích hoạt tài khoản |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Mã ngẫu nhiên chỉ lưu bản băm, dùng một lần trong 24 giờ; xác thực thành công chuyển trạng thái sang ACTIVE; gửi lại có giới hạn.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Mã hết hạn, đã dùng hoặc của tài khoản khác không kích hoạt tài khoản. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.3. User Story 3 — TK-03 — Đăng nhập bằng email và Google

Là **khách/người học**, tôi muốn **đăng nhập bằng email và google** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-03 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Khách truy cập được trang xác thực; dịch vụ email/nhà cung cấp đăng nhập được cấu hình cho luồng tương ứng. Thao tác đăng xuất/đổi mật khẩu áp dụng phiên của chính người dùng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Email/mật khẩu đúng tạo phiên mới; Google kiểm tra nhà cung cấp, state, nonce và định danh; không tự ghép tài khoản trùng email |
| Nhánh không hợp lệ | Mật khẩu sai, tài khoản khóa, email chưa xác thực, Google hủy cấp quyền; trả thông báo phù hợp và không tạo phiên |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Email/mật khẩu đúng tạo phiên mới; Google kiểm tra nhà cung cấp, state, nonce và định danh; không tự ghép tài khoản trùng email.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Mật khẩu sai, tài khoản khóa, email chưa xác thực, Google hủy cấp quyền; trả thông báo phù hợp và không tạo phiên. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.4. User Story 4 — TK-04 — Đăng nhập Facebook — mở rộng

Là **khách/người học**, tôi muốn **đăng nhập facebook** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-04 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Khách truy cập được trang xác thực; dịch vụ email/nhà cung cấp đăng nhập được cấu hình cho luồng tương ứng. Thao tác đăng xuất/đổi mật khẩu áp dụng phiên của chính người dùng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Đăng nhập qua định danh Facebook hợp lệ; cùng quy tắc liên kết và khóa tài khoản như Google; có thể tắt bằng cấu hình |
| Nhánh không hợp lệ | Nhà cung cấp không trả email hoặc người dùng từ chối; không tạo email giả hay tự ghép danh tính |
| Điều kiện ràng buộc | Mở rộng, mặc định tắt trước khi có bản triển khai và kiểm thử; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Đăng nhập qua định danh Facebook hợp lệ; cùng quy tắc liên kết và khóa tài khoản như Google; có thể tắt bằng cấu hình.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Nhà cung cấp không trả email hoặc người dùng từ chối; không tạo email giả hay tự ghép danh tính. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.5. User Story 5 — TK-05 — Đăng xuất, quên mật khẩu và đặt lại mật khẩu

Là **khách/người học**, tôi muốn **đăng xuất, quên mật khẩu và đặt lại mật khẩu** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-05 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Khách truy cập được trang xác thực; dịch vụ email/nhà cung cấp đăng nhập được cấu hình cho luồng tương ứng. Thao tác đăng xuất/đổi mật khẩu áp dụng phiên của chính người dùng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Đăng xuất thu hồi phiên; quên mật khẩu trả thông báo chung; mã đặt lại dùng một lần trong 15 phút; đổi mật khẩu thu hồi các phiên cũ |
| Nhánh không hợp lệ | Mã đặt lại sai/hết hạn/đã dùng; mật khẩu xác nhận khác nhau; tài khoản chỉ Google không được tự đặt mật khẩu khi chưa xác minh |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Đăng xuất thu hồi phiên; quên mật khẩu trả thông báo chung; mã đặt lại dùng một lần trong 15 phút; đổi mật khẩu thu hồi các phiên cũ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Mã đặt lại sai/hết hạn/đã dùng; mật khẩu xác nhận khác nhau; tài khoản chỉ Google không được tự đặt mật khẩu khi chưa xác minh. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.6. User Story 6 — TK-06 — Cập nhật hồ sơ và ảnh đại diện

Là **người học**, tôi muốn **cập nhật hồ sơ và ảnh đại diện** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-06 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu tên hiển thị 2–100 ký tự; nhận JPEG/PNG/WebP tối đa 5 MB; xử lý ảnh và thay tệp cũ có kiểm tra tham chiếu |
| Nhánh không hợp lệ | Tệp giả ảnh, ảnh quá lớn, tên rỗng; người khác không được sửa hồ sơ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Lưu tên hiển thị 2–100 ký tự; nhận JPEG/PNG/WebP tối đa 5 MB; xử lý ảnh và thay tệp cũ có kiểm tra tham chiếu.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Tệp giả ảnh, ảnh quá lớn, tên rỗng; người khác không được sửa hồ sơ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.7. User Story 7 — TK-07 — Chọn trình độ, mục tiêu và chủ đề quan tâm

Là **người học**, tôi muốn **chọn trình độ, mục tiêu và chủ đề quan tâm** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-07 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Chọn A1–C2 khi hoàn tất thiết lập; lưu mục tiêu và nhiều chủ đề đang hoạt động; nêu rõ đây là trình độ tự đánh giá |
| Nhánh không hợp lệ | Trình độ ngoài danh sách; chủ đề đã bị ẩn; mục tiêu vượt 500 ký tự |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Chọn A1–C2 khi hoàn tất thiết lập; lưu mục tiêu và nhiều chủ đề đang hoạt động; nêu rõ đây là trình độ tự đánh giá.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Trình độ ngoài danh sách; chủ đề đã bị ẩn; mục tiêu vượt 500 ký tự. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.8. User Story 8 — TK-08 — Đặt số từ mới và thời gian học mỗi ngày

Là **người học**, tôi muốn **đặt số từ mới và thời gian học mỗi ngày** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-08 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Số từ mới 0–100, thời gian 1–240 phút; thay đổi không xóa tiến độ; hạn mức từ mới được kiểm tra tại máy chủ |
| Nhánh không hợp lệ | Số âm, số thập phân hoặc vượt ngưỡng; hai phiên đồng thời không vượt số từ mới |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-08-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Số từ mới 0–100, thời gian 1–240 phút; thay đổi không xóa tiến độ; hạn mức từ mới được kiểm tra tại máy chủ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Số âm, số thập phân hoặc vượt ngưỡng; hai phiên đồng thời không vượt số từ mới. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.9. User Story 9 — TK-09 — Cài đặt giờ nhắc học, múi giờ và thông báo

Là **người học**, tôi muốn **cài đặt giờ nhắc học, múi giờ và thông báo** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-09 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu giờ HH:mm và tên múi giờ IANA; bật/tắt riêng email và thông báo; tính lại lịch nhắc tương lai |
| Nhánh không hợp lệ | Múi giờ không tồn tại; giờ sai định dạng; đổi múi giờ không tạo thêm chuỗi ngày hoặc thêm lượt nhắc |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-09-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Lưu giờ HH:mm và tên múi giờ IANA; bật/tắt riêng email và thông báo; tính lại lịch nhắc tương lai.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Múi giờ không tồn tại; giờ sai định dạng; đổi múi giờ không tạo thêm chuỗi ngày hoặc thêm lượt nhắc. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.10. User Story 10 — TK-10 — Xem streak và lịch sử hoạt động

Là **người học**, tôi muốn **xem streak và lịch sử hoạt động** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-10 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Hiển thị chuỗi ngày học và sự kiện theo ngày địa phương cố định tại lúc ghi; ngày có hoạt động học hợp lệ mới được tính |
| Nhánh không hợp lệ | Chưa có dữ liệu; đổi múi giờ hoặc mở trang nhiều lần không tự tăng chuỗi ngày |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-10-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Hiển thị chuỗi ngày học và sự kiện theo ngày địa phương cố định tại lúc ghi; ngày có hoạt động học hợp lệ mới được tính.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Chưa có dữ liệu; đổi múi giờ hoặc mở trang nhiều lần không tự tăng chuỗi ngày. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.11. User Story 11 — TK-11 — Yêu cầu xóa tài khoản và dữ liệu cá nhân

Là **người học**, tôi muốn **yêu cầu xóa tài khoản và dữ liệu cá nhân** để quản lý tài khoản và thiết lập quá trình học an toàn.

| Mục | Đặc tả |
| --- | --- |
| ID | TK-11 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI01, UI02, UI03, UI04, UI05, UI06, UI07; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Xác thực lại, khóa sử dụng ngay, cho hủy trong 7 ngày trước khi bắt đầu xóa; xóa dữ liệu cá nhân và tệp theo tham chiếu, theo dõi trạng thái |
| Nhánh không hợp lệ | Người khác gửi yêu cầu; hủy khi đã xóa; tác vụ lỗi phải tiếp tục từ bước đã hoàn thành thay vì báo xóa thành công giả |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung, danh_tinh_dang_nhap, ma_xac_thuc, ho_so_nguoi_dung, tuy_chon_hoc, yeu_cau_xoa_tai_khoan |
| Giao diện | UI01, UI02, UI03, UI04, UI05, UI06, UI07 |
| Kiểm thử | TC-TK-11-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Chuẩn hóa và kiểm tra dữ liệu; xác minh danh tính/phiên hoặc mã một lần theo luồng; ghi thay đổi, thu hồi phiên khi cần và tạo email/tác vụ có kiểm soát.
3. Áp dụng tiêu chí riêng: Xác thực lại, khóa sử dụng ngay, cho hủy trong 7 ngày trước khi bắt đầu xóa; xóa dữ liệu cá nhân và tệp theo tham chiếu, theo dõi trạng thái.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Người khác gửi yêu cầu; hủy khi đã xóa; tác vụ lỗi phải tiếp tục từ bước đã hoàn thành thay vì báo xóa thành công giả. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.12. User Story 12 — BT-01 — Tạo, xem, sửa và xóa bộ thẻ

Là **người học**, tôi muốn **tạo, xem, sửa và xóa bộ thẻ** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-01 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Tên bộ 1–150 ký tự; chủ sở hữu được sửa/xóa; xóa ngừng đưa thẻ vào học; kiểm tra phiên bản khi sửa |
| Nhánh không hợp lệ | Người khác thao tác; tên rỗng; bản sửa đã cũ; không xóa bản sao độc lập của người khác |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Tên bộ 1–150 ký tự; chủ sở hữu được sửa/xóa; xóa ngừng đưa thẻ vào học; kiểm tra phiên bản khi sửa.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Người khác thao tác; tên rỗng; bản sửa đã cũ; không xóa bản sao độc lập của người khác. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.13. User Story 13 — BT-02 — Tạo, sửa và xóa từng thẻ

Là **người học**, tôi muốn **tạo, sửa và xóa từng thẻ** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-02 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Thẻ thuộc đúng bộ; từ/cụm từ 1–255 ký tự và nghĩa mục tiêu bắt buộc; sửa nội dung không ghi đè lịch sử đã học |
| Nhánh không hợp lệ | Thẻ sai bộ; thiếu nghĩa; phiên bản cũ; không tiếp tục học thẻ đã xóa |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Thẻ thuộc đúng bộ; từ/cụm từ 1–255 ký tự và nghĩa mục tiêu bắt buộc; sửa nội dung không ghi đè lịch sử đã học.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Thẻ sai bộ; thiếu nghĩa; phiên bản cũ; không tiếp tục học thẻ đã xóa. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.14. User Story 14 — BT-03 — Lưu nội dung từ/cụm từ và đa phương tiện

Là **người học**, tôi muốn **lưu nội dung từ/cụm từ và đa phương tiện** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-03 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu từ loại, nghĩa, IPA, nhiều ví dụ và bản dịch, ảnh, audio từ và audio ví dụ; một thẻ ứng với một nghĩa mục tiêu |
| Nhánh không hợp lệ | Tệp hỏng, loại tệp không cho phép hoặc tệp thuộc người khác; trường bắt buộc thiếu |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Lưu từ loại, nghĩa, IPA, nhiều ví dụ và bản dịch, ảnh, audio từ và audio ví dụ; một thẻ ứng với một nghĩa mục tiêu.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Tệp hỏng, loại tệp không cho phép hoặc tệp thuộc người khác; trường bắt buộc thiếu. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.15. User Story 15 — BT-04 — Gắn chủ đề, nhãn và mức độ khó

Là **người học**, tôi muốn **gắn chủ đề, nhãn và mức độ khó** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-04 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Bộ có nhiều chủ đề; thẻ có nhiều nhãn thuộc chính bộ; độ khó thuộc 1–5 |
| Nhánh không hợp lệ | Nhãn thuộc bộ khác; chủ đề không hoạt động; độ khó không hợp lệ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Bộ có nhiều chủ đề; thẻ có nhiều nhãn thuộc chính bộ; độ khó thuộc 1–5.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Nhãn thuộc bộ khác; chủ đề không hoạt động; độ khó không hợp lệ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.16. User Story 16 — BT-05 — Đặt bộ thẻ công khai hoặc riêng tư

Là **người học**, tôi muốn **đặt bộ thẻ công khai hoặc riêng tư** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-05 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Riêng tư chỉ chủ sở hữu truy cập; công khai chờ kiểm duyệt và chỉ hiển thị khi được duyệt; đổi về riêng tư có hiệu lực ngay |
| Nhánh không hợp lệ | Chia sẻ liên kết không vượt quyền; bản bị từ chối không xuất hiện ở danh mục |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Riêng tư chỉ chủ sở hữu truy cập; công khai chờ kiểm duyệt và chỉ hiển thị khi được duyệt; đổi về riêng tư có hiệu lực ngay.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Chia sẻ liên kết không vượt quyền; bản bị từ chối không xuất hiện ở danh mục. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.17. User Story 17 — BT-06 — Tìm kiếm, lọc và sắp xếp bộ thẻ

Là **người học**, tôi muốn **tìm kiếm, lọc và sắp xếp bộ thẻ** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-06 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Tìm theo tên; lọc chủ đề, trình độ, nguồn và quyền; sắp xếp mới nhất/tên/số thẻ; phân trang ổn định |
| Nhánh không hợp lệ | Tham số sắp xếp lạ; trang quá lớn; kết quả không chứa bộ riêng tư của người khác |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Tìm theo tên; lọc chủ đề, trình độ, nguồn và quyền; sắp xếp mới nhất/tên/số thẻ; phân trang ổn định.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Tham số sắp xếp lạ; trang quá lớn; kết quả không chứa bộ riêng tư của người khác. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.18. User Story 18 — BT-07 — Sao chép bộ thẻ công khai

Là **người học**, tôi muốn **sao chép bộ thẻ công khai** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-07 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Chỉ sao chép bộ công khai đã duyệt; tạo bộ riêng tư độc lập, giữ nguồn và nội dung, không mang tiến độ của chủ bộ |
| Nhánh không hợp lệ | Bộ nguồn bị ẩn khi sao chép; lỗi tệp hoặc gửi lại không tạo bản sao trùng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Chỉ sao chép bộ công khai đã duyệt; tạo bộ riêng tư độc lập, giữ nguồn và nội dung, không mang tiến độ của chủ bộ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Bộ nguồn bị ẩn khi sao chép; lỗi tệp hoặc gửi lại không tạo bản sao trùng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.19. User Story 19 — BT-08 — Nhập và xuất CSV

Là **người học**, tôi muốn **nhập và xuất csv** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-08 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | CSV UTF-8 có tiêu đề cột; giới hạn 5 MB/5000 dòng; xem trước lỗi theo dòng trước xác nhận; xuất thoát dữ liệu công thức nguy hiểm |
| Nhánh không hợp lệ | Dòng lỗi, mã hóa sai, thiếu cột, tệp quá lớn; người dùng chọn dòng hợp lệ trước xác nhận; tập được xác nhận còn lỗi thì trả 422 và không ghi một phần |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-08-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: CSV UTF-8 có tiêu đề cột; giới hạn 5 MB/5000 dòng; xem trước lỗi theo dòng trước xác nhận; xuất thoát dữ liệu công thức nguy hiểm.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Dòng lỗi, mã hóa sai, thiếu cột, tệp quá lớn; người dùng chọn dòng hợp lệ trước xác nhận; tập được xác nhận còn lỗi thì trả 422 và không ghi một phần. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.20. User Story 20 — BT-09 — Cảnh báo từ trùng trong cùng bộ

Là **người học**, tôi muốn **cảnh báo từ trùng trong cùng bộ** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-09 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | So sánh từ chuẩn hóa + từ loại + nghĩa chuẩn hóa; cảnh báo liên kết thẻ hiện có; cùng từ khác nghĩa được phép lưu |
| Nhánh không hợp lệ | Khác chữ hoa/khoảng trắng vẫn nhận diện; hai yêu cầu đồng thời không lách quy tắc trùng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-09-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: So sánh từ chuẩn hóa + từ loại + nghĩa chuẩn hóa; cảnh báo liên kết thẻ hiện có; cùng từ khác nghĩa được phép lưu.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Khác chữ hoa/khoảng trắng vẫn nhận diện; hai yêu cầu đồng thời không lách quy tắc trùng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.21. User Story 21 — BT-10 — Đánh dấu bộ thẻ yêu thích

Là **người học**, tôi muốn **đánh dấu bộ thẻ yêu thích** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-10 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Một người chỉ có một bản đánh dấu mỗi bộ; bật/tắt không xóa bộ; danh sách yêu thích vẫn kiểm tra quyền |
| Nhánh không hợp lệ | Bộ đã ẩn/xóa phải hiện không khả dụng; gửi bật yêu thích lặp không tạo hàng trùng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-10-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Một người chỉ có một bản đánh dấu mỗi bộ; bật/tắt không xóa bộ; danh sách yêu thích vẫn kiểm tra quyền.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Bộ đã ẩn/xóa phải hiện không khả dụng; gửi bật yêu thích lặp không tạo hàng trùng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.22. User Story 22 — BT-11 — Chia sẻ bộ thẻ bằng liên kết

Là **người học**, tôi muốn **chia sẻ bộ thẻ bằng liên kết** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-11 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Liên kết có mã ngẫu nhiên, có thể thu hồi; chỉ cấp xem theo quyền của bộ; bộ riêng tư không tự công khai |
| Nhánh không hợp lệ | Liên kết bị thu hồi, sai hoặc đến bộ bị ẩn; không lộ dữ liệu riêng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-11-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Liên kết có mã ngẫu nhiên, có thể thu hồi; chỉ cấp xem theo quyền của bộ; bộ riêng tư không tự công khai.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Liên kết bị thu hồi, sai hoặc đến bộ bị ẩn; không lộ dữ liệu riêng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.23. User Story 23 — BT-12 — Báo cáo nội dung sai hoặc không phù hợp

Là **người học**, tôi muốn **báo cáo nội dung sai hoặc không phù hợp** để tổ chức và sử dụng thư viện từ vựng theo nhu cầu.

| Mục | Đặc tả |
| --- | --- |
| ID | BT-12 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI10, UI11, UI12, UI13; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Chọn lý do, mô tả và đối tượng; lưu phiếu kiểm duyệt; một báo cáo không tự khóa nội dung |
| Nhánh không hợp lệ | Mô tả rỗng, đối tượng không tồn tại, gửi liên tục; hạn chế lạm dụng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | bo_the, the, nhan, the_nhan, vi_du_cua_the, tep_cua_the, lan_nhap_du_lieu, bao_cao_noi_dung |
| Giao diện | UI08, UI09, UI10, UI11, UI12, UI13 |
| Kiểm thử | TC-BT-12-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra chủ sở hữu, quyền tệp và phiên bản bộ/thẻ; chuẩn hóa dữ liệu, kiểm tra trùng; ghi thay đổi/ảnh chụp lịch sử và sự kiện liên quan; không sửa tiến độ của người khác.
3. Áp dụng tiêu chí riêng: Chọn lý do, mô tả và đối tượng; lưu phiếu kiểm duyệt; một báo cáo không tự khóa nội dung.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Mô tả rỗng, đối tượng không tồn tại, gửi liên tục; hạn chế lạm dụng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.24. User Story 24 — ND-01 — Bộ từ vựng giao tiếp theo tình huống

Là **khách/người học**, tôi muốn **bộ từ vựng giao tiếp theo tình huống** để chọn nội dung phù hợp trình độ và mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | ND-01 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Bộ thẻ công khai đã qua kiểm duyệt; khách được xem danh mục/xem trước, sao chép cần tài khoản hoạt động. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI14; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Cung cấp bộ chào hỏi, mua sắm, du lịch và công việc; ví dụ phù hợp tình huống, có nguồn quản trị |
| Nhánh không hợp lệ | Bộ chưa duyệt hoặc thiếu thẻ không được giới thiệu là bộ hoàn chỉnh |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | chu_de, bo_the, bo_the_chu_de, the |
| Giao diện | UI08, UI09, UI14 |
| Kiểm thử | TC-ND-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Truy vấn bộ công khai đã duyệt với bộ lọc/phân trang; trả nguồn, trình độ, mô tả, số thẻ và phần xem trước; kiểm tra lại quyền trước sao chép.
3. Áp dụng tiêu chí riêng: Cung cấp bộ chào hỏi, mua sắm, du lịch và công việc; ví dụ phù hợp tình huống, có nguồn quản trị.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Bộ chưa duyệt hoặc thiếu thẻ không được giới thiệu là bộ hoàn chỉnh. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.25. User Story 25 — ND-02 — Bộ từ vựng TOEIC theo chủ đề

Là **khách/người học**, tôi muốn **bộ từ vựng toeic theo chủ đề** để chọn nội dung phù hợp trình độ và mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | ND-02 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Bộ thẻ công khai đã qua kiểm duyệt; khách được xem danh mục/xem trước, sao chép cần tài khoản hoạt động. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI14; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Cung cấp bộ công sở, tuyển dụng, thương mại và du lịch; nhãn TOEIC, mô tả và trình độ phù hợp |
| Nhánh không hợp lệ | Nội dung sai hoặc quyền sử dụng chưa rõ phải được rà soát trước công khai |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | chu_de, bo_the, bo_the_chu_de, the |
| Giao diện | UI08, UI09, UI14 |
| Kiểm thử | TC-ND-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Truy vấn bộ công khai đã duyệt với bộ lọc/phân trang; trả nguồn, trình độ, mô tả, số thẻ và phần xem trước; kiểm tra lại quyền trước sao chép.
3. Áp dụng tiêu chí riêng: Cung cấp bộ công sở, tuyển dụng, thương mại và du lịch; nhãn TOEIC, mô tả và trình độ phù hợp.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Nội dung sai hoặc quyền sử dụng chưa rõ phải được rà soát trước công khai. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.26. User Story 26 — ND-03 — Phân loại nội dung theo trình độ

Là **khách/người học**, tôi muốn **phân loại nội dung theo trình độ** để chọn nội dung phù hợp trình độ và mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | ND-03 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Bộ thẻ công khai đã qua kiểm duyệt; khách được xem danh mục/xem trước, sao chép cần tài khoản hoạt động. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI14; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Bộ có trình độ CEFR và chủ đề; người dùng lọc theo trình độ; không dùng tự đánh giá để khóa nội dung |
| Nhánh không hợp lệ | Trình độ ngoài danh sách hoặc bộ không hoạt động không xuất hiện ở bộ lọc |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | chu_de, bo_the, bo_the_chu_de, the |
| Giao diện | UI08, UI09, UI14 |
| Kiểm thử | TC-ND-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Truy vấn bộ công khai đã duyệt với bộ lọc/phân trang; trả nguồn, trình độ, mô tả, số thẻ và phần xem trước; kiểm tra lại quyền trước sao chép.
3. Áp dụng tiêu chí riêng: Bộ có trình độ CEFR và chủ đề; người dùng lọc theo trình độ; không dùng tự đánh giá để khóa nội dung.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Trình độ ngoài danh sách hoặc bộ không hoạt động không xuất hiện ở bộ lọc. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.27. User Story 27 — ND-04 — Tìm kiếm và xem trước bộ thẻ

Là **khách/người học**, tôi muốn **tìm kiếm và xem trước bộ thẻ** để chọn nội dung phù hợp trình độ và mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | ND-04 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Bộ thẻ công khai đã qua kiểm duyệt; khách được xem danh mục/xem trước, sao chép cần tài khoản hoạt động. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI14; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Khách xem tối đa 10 thẻ mẫu của bộ đã duyệt; người học xem chi tiết theo quyền và có nút sao chép |
| Nhánh không hợp lệ | Không trả toàn bộ dữ liệu bộ riêng tư; bộ không tồn tại hoặc bị ẩn trả trạng thái rõ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | chu_de, bo_the, bo_the_chu_de, the |
| Giao diện | UI08, UI09, UI14 |
| Kiểm thử | TC-ND-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Truy vấn bộ công khai đã duyệt với bộ lọc/phân trang; trả nguồn, trình độ, mô tả, số thẻ và phần xem trước; kiểm tra lại quyền trước sao chép.
3. Áp dụng tiêu chí riêng: Khách xem tối đa 10 thẻ mẫu của bộ đã duyệt; người học xem chi tiết theo quyền và có nút sao chép.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không trả toàn bộ dữ liệu bộ riêng tư; bộ không tồn tại hoặc bị ẩn trả trạng thái rõ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.28. User Story 28 — ND-05 — Hiển thị số thẻ và mô tả nội dung

Là **khách/người học**, tôi muốn **hiển thị số thẻ và mô tả nội dung** để chọn nội dung phù hợp trình độ và mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | ND-05 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Bộ thẻ công khai đã qua kiểm duyệt; khách được xem danh mục/xem trước, sao chép cần tài khoản hoạt động. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI14; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Số thẻ phản ánh thẻ đang hoạt động; mô tả tối đa 2000 ký tự; phân biệt số thẻ với số từ gốc |
| Nhánh không hợp lệ | Xóa/thêm thẻ phải cập nhật số đếm; chưa có thẻ hiện 0 và hướng dẫn |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | chu_de, bo_the, bo_the_chu_de, the |
| Giao diện | UI08, UI09, UI14 |
| Kiểm thử | TC-ND-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Truy vấn bộ công khai đã duyệt với bộ lọc/phân trang; trả nguồn, trình độ, mô tả, số thẻ và phần xem trước; kiểm tra lại quyền trước sao chép.
3. Áp dụng tiêu chí riêng: Số thẻ phản ánh thẻ đang hoạt động; mô tả tối đa 2000 ký tự; phân biệt số thẻ với số từ gốc.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Xóa/thêm thẻ phải cập nhật số đếm; chưa có thẻ hiện 0 và hướng dẫn. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.29. User Story 29 — ND-06 — Phân biệt bộ quản trị và bộ người dùng

Là **khách/người học**, tôi muốn **phân biệt bộ quản trị và bộ người dùng** để chọn nội dung phù hợp trình độ và mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | ND-06 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Bộ thẻ công khai đã qua kiểm duyệt; khách được xem danh mục/xem trước, sao chép cần tài khoản hoạt động. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI14; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Nhãn nguồn rõ ràng, hiển thị tác giả công khai phù hợp quyền; bộ quản trị có quy trình duyệt và nguồn nội dung |
| Nhánh không hợp lệ | Người học không thể tự đặt nhãn bộ chính thức bằng sửa yêu cầu |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | chu_de, bo_the, bo_the_chu_de, the |
| Giao diện | UI08, UI09, UI14 |
| Kiểm thử | TC-ND-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Truy vấn bộ công khai đã duyệt với bộ lọc/phân trang; trả nguồn, trình độ, mô tả, số thẻ và phần xem trước; kiểm tra lại quyền trước sao chép.
3. Áp dụng tiêu chí riêng: Nhãn nguồn rõ ràng, hiển thị tác giả công khai phù hợp quyền; bộ quản trị có quy trình duyệt và nguồn nội dung.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Người học không thể tự đặt nhãn bộ chính thức bằng sửa yêu cầu. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.30. User Story 30 — ND-07 — Bộ IELTS và tiếng Anh trẻ em — mở rộng

Là **khách/người học**, tôi muốn **bộ ielts và tiếng anh trẻ em** để chọn nội dung phù hợp trình độ và mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | ND-07 |
| Tác nhân | Khách/người học |
| Điều kiện tiên quyết | Bộ thẻ công khai đã qua kiểm duyệt; khách được xem danh mục/xem trước, sao chép cần tài khoản hoạt động. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI08, UI09, UI14; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Có phân loại IELTS/CHILDREN, trình độ và rà soát nội dung riêng; chỉ bật sau các chức năng chính |
| Nhánh không hợp lệ | Không mặc nhiên thu dữ liệu trẻ em; nội dung chưa rà soát không được công khai |
| Điều kiện ràng buộc | Mở rộng, mặc định tắt trước khi có bản triển khai và kiểm thử; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | chu_de, bo_the, bo_the_chu_de, the |
| Giao diện | UI08, UI09, UI14 |
| Kiểm thử | TC-ND-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Truy vấn bộ công khai đã duyệt với bộ lọc/phân trang; trả nguồn, trình độ, mô tả, số thẻ và phần xem trước; kiểm tra lại quyền trước sao chép.
3. Áp dụng tiêu chí riêng: Có phân loại IELTS/CHILDREN, trình độ và rà soát nội dung riêng; chỉ bật sau các chức năng chính.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không mặc nhiên thu dữ liệu trẻ em; nội dung chưa rà soát không được công khai. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.31. User Story 31 — ON-01 — Lật thẻ tương tác

Là **người học**, tôi muốn **lật thẻ tương tác** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-01 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Mặt trước và sau hiển thị đúng chiều học; chỉ đánh giá sau khi đã lật; chuyển thẻ không mất lượt ôn đã xác nhận |
| Nhánh không hợp lệ | Nhấn nhiều lần khi đang gửi không ghi nhiều lượt; thẻ không còn khả dụng bị bỏ qua có thông báo |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Mặt trước và sau hiển thị đúng chiều học; chỉ đánh giá sau khi đã lật; chuyển thẻ không mất lượt ôn đã xác nhận.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Nhấn nhiều lần khi đang gửi không ghi nhiều lượt; thẻ không còn khả dụng bị bỏ qua có thông báo. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.32. User Story 32 — ON-02 — Hỗ trợ phím tắt trên máy tính

Là **người học**, tôi muốn **hỗ trợ phím tắt trên máy tính** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-02 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Space lật; 1/2/3/4 chọn Quên/Khó/Nhớ/Dễ; Enter tiếp tục; có hướng dẫn và không bắt phím trong ô nhập |
| Nhánh không hợp lệ | Đang nhập câu hoặc hộp thoại thì phím tắt không gửi đánh giá |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Space lật; 1/2/3/4 chọn Quên/Khó/Nhớ/Dễ; Enter tiếp tục; có hướng dẫn và không bắt phím trong ô nhập.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Đang nhập câu hoặc hộp thoại thì phím tắt không gửi đánh giá. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.33. User Story 33 — ON-03 — Học Anh–Việt hoặc Việt–Anh

Là **người học**, tôi muốn **học anh–việt hoặc việt–anh** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-03 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Người học chọn chiều trước phiên; đáp án và tiến độ tách theo người + thẻ + chiều học |
| Nhánh không hợp lệ | Đổi chiều không sử dụng nhầm lịch hay tự coi chiều còn lại đã nhớ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Người học chọn chiều trước phiên; đáp án và tiến độ tách theo người + thẻ + chiều học.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Đổi chiều không sử dụng nhầm lịch hay tự coi chiều còn lại đã nhớ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.34. User Story 34 — ON-04 — Phát audio từ và câu ví dụ

Là **người học**, tôi muốn **phát audio từ và câu ví dụ** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-04 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Phát tệp đã lưu đúng từ/câu, có điều khiển dừng và tốc độ; không tự phát gây chồng âm |
| Nhánh không hợp lệ | Tệp thiếu/lỗi hiện nút thử lại và vẫn cho phép học bằng chữ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Phát tệp đã lưu đúng từ/câu, có điều khiển dừng và tốc độ; không tự phát gây chồng âm.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Tệp thiếu/lỗi hiện nút thử lại và vẫn cho phép học bằng chữ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.35. User Story 35 — ON-05 — Chọn mức độ Quên, Khó, Nhớ, Dễ

Là **người học**, tôi muốn **chọn mức độ quên, khó, nhớ, dễ** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-05 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Gửi mã mức nhớ hợp lệ, mã lượt và phiên bản tiến độ; kết quả được máy chủ xác nhận trước cập nhật giao diện |
| Nhánh không hợp lệ | Mức ngoài danh sách, lượt không thuộc phiên, gửi trùng hoặc đánh giá trước lật bị từ chối |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Gửi mã mức nhớ hợp lệ, mã lượt và phiên bản tiến độ; kết quả được máy chủ xác nhận trước cập nhật giao diện.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Mức ngoài danh sách, lượt không thuộc phiên, gửi trùng hoặc đánh giá trước lật bị từ chối. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.36. User Story 36 — ON-06 — Tính ngày ôn tiếp theo bằng SRS

Là **người học**, tôi muốn **tính ngày ôn tiếp theo bằng srs** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-06 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Biến thể SM-2 dùng hệ số tối thiểu 1.3, bước học mới/học lại và khoảng ôn; lưu phiên bản thuật toán |
| Nhánh không hợp lệ | Kiểm thử bốn mức nhớ và dữ liệu biên; ngày đến hạn không lấy từ máy người dùng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Biến thể SM-2 dùng hệ số tối thiểu 1.3, bước học mới/học lại và khoảng ôn; lưu phiên bản thuật toán.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Kiểm thử bốn mức nhớ và dữ liệu biên; ngày đến hạn không lấy từ máy người dùng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.37. User Story 37 — ON-07 — Lưu trạng thái riêng từng người với từng thẻ

Là **người học**, tôi muốn **lưu trạng thái riêng từng người với từng thẻ** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-07 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Khóa duy nhất người + thẻ + chiều; có NEW/LEARNING/REVIEW/RELEARNING và cờ tam_ngung, khoảng ôn và phiên bản |
| Nhánh không hợp lệ | Hai người không dùng chung tiến độ; cập nhật xung đột phải đọc lại trạng thái mới |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Khóa duy nhất người + thẻ + chiều; có NEW/LEARNING/REVIEW/RELEARNING và cờ tam_ngung, khoảng ôn và phiên bản.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Hai người không dùng chung tiến độ; cập nhật xung đột phải đọc lại trạng thái mới. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.38. User Story 38 — ON-08 — Hiển thị thẻ đến hạn, quá hạn và từ mới

Là **người học**, tôi muốn **hiển thị thẻ đến hạn, quá hạn và từ mới** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-08 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Đến hạn khi due_at nhỏ hơn hoặc bằng hiện tại; quá hạn là vượt mốc đến hạn; thẻ mới chưa giới thiệu; đếm không trùng |
| Nhánh không hợp lệ | Không đưa thẻ tạm ngưng/đã xóa vào hàng đợi; múi giờ không thay đổi thời điểm UTC |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-08-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Đến hạn khi due_at nhỏ hơn hoặc bằng hiện tại; quá hạn là vượt mốc đến hạn; thẻ mới chưa giới thiệu; đếm không trùng.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không đưa thẻ tạm ngưng/đã xóa vào hàng đợi; múi giờ không thay đổi thời điểm UTC. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.39. User Story 39 — ON-09 — Lưu lịch sử ôn và số lần quên

Là **người học**, tôi muốn **lưu lịch sử ôn và số lần quên** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-09 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu mức nhớ, thời gian, lịch trước/sau và ảnh chụp nội dung; Chuyển REVIEW sang RELEARNING tăng số lần quên một lần; Quên khi NEW hoặc trong cùng đợt học lại không tăng bộ đếm |
| Nhánh không hợp lệ | Gửi lại cùng mã lượt không tăng lần quên hoặc sinh thêm lịch sử |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-09-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Lưu mức nhớ, thời gian, lịch trước/sau và ảnh chụp nội dung; Chuyển REVIEW sang RELEARNING tăng số lần quên một lần; Quên khi NEW hoặc trong cùng đợt học lại không tăng bộ đếm.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Gửi lại cùng mã lượt không tăng lần quên hoặc sinh thêm lịch sử. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.40. User Story 40 — ON-10 — Giới hạn số từ mới mỗi ngày

Là **người học**, tôi muốn **giới hạn số từ mới mỗi ngày** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-10 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Tính thẻ mới duy nhất theo ngày học; cùng thẻ học hai chiều không tăng hai từ mới; kiểm tra đồng thời và cửa sổ chống đổi múi giờ |
| Nhánh không hợp lệ | Hết hạn mức chỉ còn ôn thẻ cũ; không vượt hạn mức bằng mở nhiều phiên |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-10-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Tính thẻ mới duy nhất theo ngày học; cùng thẻ học hai chiều không tăng hai từ mới; kiểm tra đồng thời và cửa sổ chống đổi múi giờ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Hết hạn mức chỉ còn ôn thẻ cũ; không vượt hạn mức bằng mở nhiều phiên. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.41. User Story 41 — ON-11 — Tổng kết phiên học

Là **người học**, tôi muốn **tổng kết phiên học** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-11 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Hiển thị số thẻ đã ôn, từ mới, mức nhớ và thời gian hoạt động; tách phiên hoàn thành/dở dang |
| Nhánh không hợp lệ | Đóng trang rồi mở lại giữ lượt đã xác nhận; thời gian bỏ tab không tính toàn bộ là học |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-11-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Hiển thị số thẻ đã ôn, từ mới, mức nhớ và thời gian hoạt động; tách phiên hoàn thành/dở dang.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Đóng trang rồi mở lại giữ lượt đã xác nhận; thời gian bỏ tab không tính toàn bộ là học. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.42. User Story 42 — ON-12 — Tạm ngưng thẻ hoặc đặt lại tiến độ

Là **người học**, tôi muốn **tạm ngưng thẻ hoặc đặt lại tiến độ** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-12 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Tạm ngưng không xóa lịch sử; khôi phục theo chính sách; đặt lại tạo giai đoạn mới có xác nhận |
| Nhánh không hợp lệ | Không được đặt lại tiến độ của người khác; đặt lại không xóa lịch sử cũ hoặc tạo XP giả |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-12-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Tạm ngưng không xóa lịch sử; khôi phục theo chính sách; đặt lại tạo giai đoạn mới có xác nhận.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không được đặt lại tiến độ của người khác; đặt lại không xóa lịch sử cũ hoặc tạo XP giả. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.43. User Story 43 — ON-13 — Tạo phiên theo quỹ thời gian

Là **người học**, tôi muốn **tạo phiên theo quỹ thời gian** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-13 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Người học chọn 5/10/15/30 phút hoặc 1–240 phút; ưu tiên thẻ đến hạn, ước lượng theo tốc độ gần đây |
| Nhánh không hợp lệ | Không có dữ liệu dùng ước lượng mặc định; đây là ước lượng, không cam kết số phút tuyệt đối |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-13-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Người học chọn 5/10/15/30 phút hoặc 1–240 phút; ưu tiên thẻ đến hạn, ước lượng theo tốc độ gần đây.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không có dữ liệu dùng ước lượng mặc định; đây là ước lượng, không cam kết số phút tuyệt đối. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.44. User Story 44 — ON-14 — Chia lịch khi quay lại sau nghỉ

Là **người học**, tôi muốn **chia lịch khi quay lại sau nghỉ** để học và ôn có lịch, bảo toàn tiến độ.

| Mục | Đặc tả |
| --- | --- |
| ID | ON-14 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI15, UI16, UI17, UI18; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Đề xuất ngân sách ôn nợ theo ngày và ưu tiên thẻ hay quên; không sửa giả ngày đến hạn để che nợ |
| Nhánh không hợp lệ | Nợ lớn, không đủ thời gian hoặc đổi mục tiêu phải tính lại đề xuất và giải thích |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | phien_hoc, tien_do_hoc_the, muc_the_trong_phien, lich_su_on_tap, han_muc_hoc_hang_ngay, the_da_gioi_thieu |
| Giao diện | UI15, UI16, UI17, UI18 |
| Kiểm thử | TC-ON-14-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc phiên và tiến độ hiện tại; kiểm tra chiều học, suất từ mới và phiên bản; tính kết quả tại máy chủ; ghi tiến độ, lịch sử và sự kiện trong giao dịch ngắn.
3. Áp dụng tiêu chí riêng: Đề xuất ngân sách ôn nợ theo ngày và ưu tiên thẻ hay quên; không sửa giả ngày đến hạn để che nợ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Nợ lớn, không đủ thời gian hoặc đổi mục tiêu phải tính lại đề xuất và giải thích. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.45. User Story 45 — LT-01 — Trắc nghiệm chọn nghĩa

Là **người học**, tôi muốn **trắc nghiệm chọn nghĩa** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-01 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Một đáp án đúng và phương án nhiễu khác biệt; chấm ở máy chủ theo đáp án của câu tại thời điểm tạo |
| Nhánh không hợp lệ | Không đủ nghĩa khác biệt thì thông báo và chọn dạng khác, không tạo câu mơ hồ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Một đáp án đúng và phương án nhiễu khác biệt; chấm ở máy chủ theo đáp án của câu tại thời điểm tạo.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không đủ nghĩa khác biệt thì thông báo và chọn dạng khác, không tạo câu mơ hồ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.46. User Story 46 — LT-02 — Trắc nghiệm chọn từ phù hợp

Là **người học**, tôi muốn **trắc nghiệm chọn từ phù hợp** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-02 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Gợi ý nghĩa/ngữ cảnh có một từ mục tiêu; phương án nhiễu cùng vai trò hợp lý nhưng không cùng đáp án |
| Nhánh không hợp lệ | Không trả cờ đúng về giao diện trước nộp; đáp án không thuộc lựa chọn bị từ chối |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Gợi ý nghĩa/ngữ cảnh có một từ mục tiêu; phương án nhiễu cùng vai trò hợp lý nhưng không cùng đáp án.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không trả cờ đúng về giao diện trước nộp; đáp án không thuộc lựa chọn bị từ chối. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.47. User Story 47 — LT-03 — Nghe và viết lại từ

Là **người học**, tôi muốn **nghe và viết lại từ** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-03 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Phát audio hợp lệ, nhận văn bản, chuẩn hóa chữ hoa/khoảng trắng và so sánh danh sách đáp án được duyệt |
| Nhánh không hợp lệ | Thiếu audio không tạo câu nghe; ký tự rỗng hoặc chỉ khoảng trắng không được chấm đúng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Phát audio hợp lệ, nhận văn bản, chuẩn hóa chữ hoa/khoảng trắng và so sánh danh sách đáp án được duyệt.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Thiếu audio không tạo câu nghe; ký tự rỗng hoặc chỉ khoảng trắng không được chấm đúng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.48. User Story 48 — LT-04 — Điền từ vào chỗ trống

Là **người học**, tôi muốn **điền từ vào chỗ trống** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-04 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Câu có chỗ trống xác định, lưu vị trí và đáp án cho phép; chấm theo dạng câu và giải thích |
| Nhánh không hợp lệ | Không dùng thay chuỗi toàn cục gây mất nhiều từ; nhiều đáp án hợp lệ phải được khai báo |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Câu có chỗ trống xác định, lưu vị trí và đáp án cho phép; chấm theo dạng câu và giải thích.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không dùng thay chuỗi toàn cục gây mất nhiều từ; nhiều đáp án hợp lệ phải được khai báo. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.49. User Story 49 — LT-05 — Ghép từ với nghĩa

Là **người học**, tôi muốn **ghép từ với nghĩa** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-05 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Bộ cặp không trùng đáp án mơ hồ; chấm từng cặp, không phụ thuộc thứ tự hiển thị |
| Nhánh không hợp lệ | Gửi thiếu cặp, lặp đối tượng hoặc ghép sai; điểm phản ánh từng cặp đúng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Bộ cặp không trùng đáp án mơ hồ; chấm từng cặp, không phụ thuộc thứ tự hiển thị.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Gửi thiếu cặp, lặp đối tượng hoặc ghép sai; điểm phản ánh từng cặp đúng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.50. User Story 50 — LT-06 — Tự nhập từ từ gợi ý nghĩa

Là **người học**, tôi muốn **tự nhập từ từ gợi ý nghĩa** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-06 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu đáp án được phép, chuẩn hóa theo chính sách; không tự bỏ dấu hoặc sửa chính tả để coi đúng |
| Nhánh không hợp lệ | Cùng nghĩa có nhiều từ hợp lệ phải khai báo; không chấp nhận gần giống nếu không có quy tắc |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Lưu đáp án được phép, chuẩn hóa theo chính sách; không tự bỏ dấu hoặc sửa chính tả để coi đúng.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Cùng nghĩa có nhiều từ hợp lệ phải khai báo; không chấp nhận gần giống nếu không có quy tắc. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.51. User Story 51 — LT-07 — Phân biệt cặp từ dễ nhầm

Là **người học**, tôi muốn **phân biệt cặp từ dễ nhầm** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-07 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Câu hỏi cho từng cặp được duyệt, có ngữ cảnh và giải thích phân biệt sau trả lời |
| Nhánh không hợp lệ | Cặp thiếu ngữ cảnh rõ phải sửa trước dùng; ghi lỗi vào nhóm cặp từ cụ thể |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Câu hỏi cho từng cặp được duyệt, có ngữ cảnh và giải thích phân biệt sau trả lời.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Cặp thiếu ngữ cảnh rõ phải sửa trước dùng; ghi lỗi vào nhóm cặp từ cụ thể. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.52. User Story 52 — LT-08 — Viết câu dùng từ mục tiêu

Là **người học**, tôi muốn **viết câu dùng từ mục tiêu** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-08 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Kiểm tra có từ mục tiêu rồi gửi AI khi đủ hạn mức; phản hồi tiếng Việt; điểm AI tách khỏi điểm cố định |
| Nhánh không hợp lệ | AI lỗi vẫn lưu câu viết và trạng thái chưa đánh giá; không tự chấm 0 khi dịch vụ lỗi |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-08-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Kiểm tra có từ mục tiêu rồi gửi AI khi đủ hạn mức; phản hồi tiếng Việt; điểm AI tách khỏi điểm cố định.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** AI lỗi vẫn lưu câu viết và trạng thái chưa đánh giá; không tự chấm 0 khi dịch vụ lỗi. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.53. User Story 53 — LT-09 — Bài kiểm tra tổng hợp theo bộ

Là **người học**, tôi muốn **bài kiểm tra tổng hợp theo bộ** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-09 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Chọn số câu, dạng và bộ có quyền; lưu bộ đề cố định cho lần làm, chấm tổng kết |
| Nhánh không hợp lệ | Không đủ nội dung thì thông báo số câu khả dụng; không sinh câu dùng thẻ người khác riêng tư |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-09-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Chọn số câu, dạng và bộ có quyền; lưu bộ đề cố định cho lần làm, chấm tổng kết.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không đủ nội dung thì thông báo số câu khả dụng; không sinh câu dùng thẻ người khác riêng tư. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.54. User Story 54 — LT-10 — Chấm bài có đáp án cố định

Là **người học**, tôi muốn **chấm bài có đáp án cố định** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-10 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Máy chủ đối chiếu đáp án lưu riêng, chấm từng câu và tổng điểm trong cùng giao dịch |
| Nhánh không hợp lệ | Gửi lại cùng lần nộp không chấm/cộng thưởng lại; không tin điểm do trình duyệt gửi |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-10-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Máy chủ đối chiếu đáp án lưu riêng, chấm từng câu và tổng điểm trong cùng giao dịch.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Gửi lại cùng lần nộp không chấm/cộng thưởng lại; không tin điểm do trình duyệt gửi. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.55. User Story 55 — LT-11 — Giải thích đáp án

Là **người học**, tôi muốn **giải thích đáp án** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-11 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Sau nộp hiển thị đáp án đúng, nghĩa/ngữ cảnh và lý do; giải thích cố định không phụ thuộc AI |
| Nhánh không hợp lệ | Chưa nộp không lộ đáp án; giải thích AI được gắn nhãn và báo cáo được |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-11-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Sau nộp hiển thị đáp án đúng, nghĩa/ngữ cảnh và lý do; giải thích cố định không phụ thuộc AI.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Chưa nộp không lộ đáp án; giải thích AI được gắn nhãn và báo cáo được. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.56. User Story 56 — LT-12 — Lịch sử bài làm và luyện lại câu sai

Là **người học**, tôi muốn **lịch sử bài làm và luyện lại câu sai** để kiểm tra và rèn khả năng sử dụng từ.

| Mục | Đặc tả |
| --- | --- |
| ID | LT-12 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI19, UI20, UI21, UI22; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Giữ lịch sử điểm, thời gian, câu trả lời; tạo lần luyện mới từ câu sai và truy vết lần gốc |
| Nhánh không hợp lệ | Không ghi đè điểm lần trước; câu nguồn đã xóa dùng ảnh chụp hợp lệ hoặc thông báo |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | de_luyen_tap, cau_hoi_luyen_tap, lan_lam_bai, cau_tra_loi, loi_hoc_tap |
| Giao diện | UI19, UI20, UI21, UI22 |
| Kiểm thử | TC-LT-12-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy đề/câu đã cố định; nhận câu trả lời hoặc cấu hình tạo bài; kiểm tra câu thuộc đề và lần làm; chấm ở máy chủ hoặc tạo tác vụ AI riêng; ghi lịch sử và lỗi có minh chứng.
3. Áp dụng tiêu chí riêng: Giữ lịch sử điểm, thời gian, câu trả lời; tạo lần luyện mới từ câu sai và truy vết lần gốc.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không ghi đè điểm lần trước; câu nguồn đã xóa dùng ảnh chụp hợp lệ hoặc thông báo. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.57. User Story 57 — AI-01 — Tạo Flashcard từ đoạn văn tiếng Anh

Là **người học**, tôi muốn **tạo flashcard từ đoạn văn tiếng anh** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-01 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Nhận đoạn 50–10000 ký tự, mục tiêu tối đa 30 thẻ; gọi API, kiểm tra cấu trúc và lưu nháp; không lưu thẳng vào bộ |
| Nhánh không hợp lệ | Đoạn rỗng/quá dài, API trả JSON sai hoặc có chỉ dẫn độc hại; không thực thi nội dung người dùng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Nhận đoạn 50–10000 ký tự, mục tiêu tối đa 30 thẻ; gọi API, kiểm tra cấu trúc và lưu nháp; không lưu thẳng vào bộ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Đoạn rỗng/quá dài, API trả JSON sai hoặc có chỉ dẫn độc hại; không thực thi nội dung người dùng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.58. User Story 58 — AI-02 — Gợi ý nghĩa ngữ cảnh, ví dụ và mẹo nhớ

Là **người học**, tôi muốn **gợi ý nghĩa ngữ cảnh, ví dụ và mẹo nhớ** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-02 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Mỗi gợi ý có từ mục tiêu, nghĩa tiếng Việt, ví dụ phù hợp và mẹo tùy chọn; gắn nhãn AI |
| Nhánh không hợp lệ | Nghĩa không phù hợp, ví dụ thiếu từ, phản hồi không hữu ích được phép sửa/báo cáo |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Mỗi gợi ý có từ mục tiêu, nghĩa tiếng Việt, ví dụ phù hợp và mẹo tùy chọn; gắn nhãn AI.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Nghĩa không phù hợp, ví dụ thiếu từ, phản hồi không hữu ích được phép sửa/báo cáo. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.59. User Story 59 — AI-03 — Xem, sửa và chọn thẻ AI trước khi lưu

Là **người học**, tôi muốn **xem, sửa và chọn thẻ ai trước khi lưu** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-03 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Hiển thị từng nháp, cho chỉnh sửa/chọn; xác nhận mới ghi thẻ đã chọn vào bộ có quyền, kiểm tra trùng |
| Nhánh không hợp lệ | Nháp của người khác, thẻ không chọn, bộ bị xóa hoặc gửi xác nhận lặp không được ghi sai/trùng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Hiển thị từng nháp, cho chỉnh sửa/chọn; xác nhận mới ghi thẻ đã chọn vào bộ có quyền, kiểm tra trùng.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Nháp của người khác, thẻ không chọn, bộ bị xóa hoặc gửi xác nhận lặp không được ghi sai/trùng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.60. User Story 60 — AI-04 — Tạo bài luyện từ từ sai hoặc đến hạn

Là **người học**, tôi muốn **tạo bài luyện từ từ sai hoặc đến hạn** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-04 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lấy danh sách từ của người học, chọn số câu và dạng; kiểm tra đáp án/nguồn trước phát hành bài |
| Nhánh không hợp lệ | AI không tạo được đáp án chắc chắn thì đánh dấu cần duyệt; không phát hành câu mơ hồ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Lấy danh sách từ của người học, chọn số câu và dạng; kiểm tra đáp án/nguồn trước phát hành bài.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** AI không tạo được đáp án chắc chắn thì đánh dấu cần duyệt; không phát hành câu mơ hồ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.61. User Story 61 — AI-05 — Giải thích khác nhau giữa từ dễ nhầm

Là **người học**, tôi muốn **giải thích khác nhau giữa từ dễ nhầm** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-05 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Nhận 2–5 từ, trả nghĩa, cách dùng, ví dụ đối chiếu và giải thích tiếng Việt |
| Nhánh không hợp lệ | Từ trùng hoặc không rõ nghĩa yêu cầu bổ sung ngữ cảnh; phản hồi lỗi không được lưu như thành công |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Nhận 2–5 từ, trả nghĩa, cách dùng, ví dụ đối chiếu và giải thích tiếng Việt.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Từ trùng hoặc không rõ nghĩa yêu cầu bổ sung ngữ cảnh; phản hồi lỗi không được lưu như thành công. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.62. User Story 62 — AI-06 — Sửa câu và giải thích tiếng Việt

Là **người học**, tôi muốn **sửa câu và giải thích tiếng việt** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-06 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu câu gốc, câu gợi ý và lý do từng sửa; người học có thể chấp nhận hoặc giữ câu cũ |
| Nhánh không hợp lệ | Câu quá dài hoặc AI đổi nghĩa phải cho báo cáo; không sửa đè câu gốc |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Lưu câu gốc, câu gợi ý và lý do từng sửa; người học có thể chấp nhận hoặc giữ câu cũ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Câu quá dài hoặc AI đổi nghĩa phải cho báo cáo; không sửa đè câu gốc. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.63. User Story 63 — AI-07 — Tạo đoạn văn hoặc tình huống chứa từ mục tiêu

Là **người học**, tôi muốn **tạo đoạn văn hoặc tình huống chứa từ mục tiêu** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-07 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Nhận 1–20 từ có quyền, trình độ và chủ đề; kiểm tra từ mục tiêu xuất hiện, nêu từ nào chưa dùng |
| Nhánh không hợp lệ | Đầu ra bỏ từ hoặc nội dung không phù hợp bị đánh dấu và không coi đủ yêu cầu |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Nhận 1–20 từ có quyền, trình độ và chủ đề; kiểm tra từ mục tiêu xuất hiện, nêu từ nào chưa dùng.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Đầu ra bỏ từ hoặc nội dung không phù hợp bị đánh dấu và không coi đủ yêu cầu. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.64. User Story 64 — AI-08 — Phản hồi cách dùng từ trong nhiệm vụ thực tế

Là **người học**, tôi muốn **phản hồi cách dùng từ trong nhiệm vụ thực tế** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-08 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Cho nhiệm vụ viết email/đoạn hội thoại, phản hồi về từ mục tiêu, ngữ cảnh và gợi ý cải thiện bằng tiếng Việt |
| Nhánh không hợp lệ | AI lỗi giữ bài gốc; phản hồi mang tính hỗ trợ, không coi là kết quả kiểm tra chuẩn hóa |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-08-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Cho nhiệm vụ viết email/đoạn hội thoại, phản hồi về từ mục tiêu, ngữ cảnh và gợi ý cải thiện bằng tiếng Việt.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** AI lỗi giữ bài gốc; phản hồi mang tính hỗ trợ, không coi là kết quả kiểm tra chuẩn hóa. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.65. User Story 65 — AI-09 — Hội thoại AI bằng văn bản — mở rộng

Là **người học**, tôi muốn **hội thoại ai bằng văn bản** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-09 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu cuộc hội thoại theo chủ đề và người sở hữu, giới hạn lượt/ngữ cảnh; cho kết thúc và xóa |
| Nhánh không hợp lệ | Không truy cập hội thoại người khác; không để lịch sử vô hạn làm vượt ngân sách |
| Điều kiện ràng buộc | Mở rộng, mặc định tắt trước khi có bản triển khai và kiểm thử; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-09-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Lưu cuộc hội thoại theo chủ đề và người sở hữu, giới hạn lượt/ngữ cảnh; cho kết thúc và xóa.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không truy cập hội thoại người khác; không để lịch sử vô hạn làm vượt ngân sách. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.66. User Story 66 — AI-10 — Báo cáo AI sai hoặc không hữu ích

Là **người học**, tôi muốn **báo cáo ai sai hoặc không hữu ích** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-10 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Gắn báo cáo với phản hồi cụ thể, lý do và ghi chú; quản trị xem theo quyền kiểm duyệt |
| Nhánh không hợp lệ | Phản hồi không tồn tại/không thuộc người học hoặc lạm dụng báo cáo bị từ chối |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-10-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Gắn báo cáo với phản hồi cụ thể, lý do và ghi chú; quản trị xem theo quyền kiểm duyệt.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Phản hồi không tồn tại/không thuộc người học hoặc lạm dụng báo cáo bị từ chối. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.67. User Story 67 — AI-11 — Lưu lịch sử nội dung AI

Là **người học**, tôi muốn **lưu lịch sử nội dung ai** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-11 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Hiển thị yêu cầu, trạng thái và phản hồi đã lưu; tìm theo loại/ngày; tuân thủ thời hạn lưu |
| Nhánh không hợp lệ | Hết hạn lưu hoặc yêu cầu đã xóa hiện rõ; không trả toàn bộ chỉ dẫn hệ thống hay khóa API |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-11-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Hiển thị yêu cầu, trạng thái và phản hồi đã lưu; tìm theo loại/ngày; tuân thủ thời hạn lưu.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Hết hạn lưu hoặc yêu cầu đã xóa hiện rõ; không trả toàn bộ chỉ dẫn hệ thống hay khóa API. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.68. User Story 68 — AI-12 — Giới hạn lượt và xử lý API không khả dụng

Là **người học**, tôi muốn **giới hạn lượt và xử lý api không khả dụng** để nhận hỗ trợ ngôn ngữ có kiểm soát.

| Mục | Đặc tả |
| --- | --- |
| ID | AI-12 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI23, UI24, UI25, UI26; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Giữ trước hạn mức theo ngày UTC, hạn mức toàn hệ thống và tính năng; có thời gian chờ, thử lại giới hạn và đối soát chi phí |
| Nhánh không hợp lệ | 429, timeout, 5xx hoặc kết quả chưa rõ không trừ/cộng lượt tùy tiện; ôn tập và bài cố định vẫn dùng được |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | yeu_cau_ai, the_nhap_ai, phan_hoi_ai, luot_goi_api, han_muc_su_dung_ai, cuoc_hoi_thoai_ai, tin_nhan_ai |
| Giao diện | UI23, UI24, UI25, UI26 |
| Kiểm thử | TC-AI-12-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra quyền, sự đồng ý và hạn mức; giữ trước mức sử dụng, ghi tác vụ; tiến trình nền gọi API ngoài giao dịch; kiểm tra đầu ra; ghi kết quả/nháp và đối soát.
3. Áp dụng tiêu chí riêng: Giữ trước hạn mức theo ngày UTC, hạn mức toàn hệ thống và tính năng; có thời gian chờ, thử lại giới hạn và đối soát chi phí.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** 429, timeout, 5xx hoặc kết quả chưa rõ không trừ/cộng lượt tùy tiện; ôn tập và bài cố định vẫn dùng được. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.69. User Story 69 — ST-01 — Lưu thủ công từ khó

Là **người học**, tôi muốn **lưu thủ công từ khó** để nhận biết và khắc phục điểm yếu có minh chứng.

| Mục | Đặc tả |
| --- | --- |
| ID | ST-01 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI27, UI28, UI29; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu thẻ có quyền, ghi chú và lý do; một thẻ có một mục sổ tay/người; bỏ lưu không xóa thẻ |
| Nhánh không hợp lệ | Gửi lặp không tạo mục trùng; thẻ bị ẩn phải hiện tình trạng khả dụng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | muc_so_tay, loi_hoc_tap, muc_do_lam_chu_tu, cap_tu_de_nham, goi_y_hoc_tap |
| Giao diện | UI27, UI28, UI29 |
| Kiểm thử | TC-ST-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc lịch sử sai/ôn thuộc người dùng; áp dụng quy tắc có phiên bản và đủ mẫu; lưu gợi ý/ghi chú hoặc tạo bài; kèm nguồn và lý do giải thích.
3. Áp dụng tiêu chí riêng: Lưu thẻ có quyền, ghi chú và lý do; một thẻ có một mục sổ tay/người; bỏ lưu không xóa thẻ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Gửi lặp không tạo mục trùng; thẻ bị ẩn phải hiện tình trạng khả dụng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.70. User Story 70 — ST-02 — Đề xuất từ thường sai vào sổ tay

Là **người học**, tôi muốn **đề xuất từ thường sai vào sổ tay** để nhận biết và khắc phục điểm yếu có minh chứng.

| Mục | Đặc tả |
| --- | --- |
| ID | ST-02 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI27, UI28, UI29; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Khi có ít nhất 3 lỗi trong 14 ngày hoặc ít nhất 2 lần Quên trong 7 ngày, đề xuất có minh chứng; người dùng chấp nhận mới lưu |
| Nhánh không hợp lệ | Không tự kết luận điểm yếu từ một câu sai; từ đã lưu không đề xuất lặp |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | muc_so_tay, loi_hoc_tap, muc_do_lam_chu_tu, cap_tu_de_nham, goi_y_hoc_tap |
| Giao diện | UI27, UI28, UI29 |
| Kiểm thử | TC-ST-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc lịch sử sai/ôn thuộc người dùng; áp dụng quy tắc có phiên bản và đủ mẫu; lưu gợi ý/ghi chú hoặc tạo bài; kèm nguồn và lý do giải thích.
3. Áp dụng tiêu chí riêng: Khi có ít nhất 3 lỗi trong 14 ngày hoặc ít nhất 2 lần Quên trong 7 ngày, đề xuất có minh chứng; người dùng chấp nhận mới lưu.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không tự kết luận điểm yếu từ một câu sai; từ đã lưu không đề xuất lặp. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.71. User Story 71 — ST-03 — Phân nhóm lỗi

Là **người học**, tôi muốn **phân nhóm lỗi** để nhận biết và khắc phục điểm yếu có minh chứng.

| Mục | Đặc tả |
| --- | --- |
| ID | ST-03 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI27, UI28, UI29; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Nhóm MEANING/SPELLING/LISTENING/USAGE; một lỗi có nguồn lần làm/lượt ôn và từ liên quan |
| Nhánh không hợp lệ | Không gán lỗi AI chưa đánh giá thành lỗi cố định; người học thấy nguồn minh chứng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | muc_so_tay, loi_hoc_tap, muc_do_lam_chu_tu, cap_tu_de_nham, goi_y_hoc_tap |
| Giao diện | UI27, UI28, UI29 |
| Kiểm thử | TC-ST-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc lịch sử sai/ôn thuộc người dùng; áp dụng quy tắc có phiên bản và đủ mẫu; lưu gợi ý/ghi chú hoặc tạo bài; kèm nguồn và lý do giải thích.
3. Áp dụng tiêu chí riêng: Nhóm MEANING/SPELLING/LISTENING/USAGE; một lỗi có nguồn lần làm/lượt ôn và từ liên quan.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không gán lỗi AI chưa đánh giá thành lỗi cố định; người học thấy nguồn minh chứng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.72. User Story 72 — ST-04 — Xem hồ sơ điểm yếu

Là **người học**, tôi muốn **xem hồ sơ điểm yếu** để nhận biết và khắc phục điểm yếu có minh chứng.

| Mục | Đặc tả |
| --- | --- |
| ID | ST-04 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI27, UI28, UI29; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Tổng hợp theo kỹ năng, chủ đề, từ và khoảng thời gian; nêu số mẫu và mức độ tin cậy |
| Nhánh không hợp lệ | Chưa đủ mẫu hiện chưa đủ dữ liệu; không coi chưa làm là kỹ năng yếu |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | muc_so_tay, loi_hoc_tap, muc_do_lam_chu_tu, cap_tu_de_nham, goi_y_hoc_tap |
| Giao diện | UI27, UI28, UI29 |
| Kiểm thử | TC-ST-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc lịch sử sai/ôn thuộc người dùng; áp dụng quy tắc có phiên bản và đủ mẫu; lưu gợi ý/ghi chú hoặc tạo bài; kèm nguồn và lý do giải thích.
3. Áp dụng tiêu chí riêng: Tổng hợp theo kỹ năng, chủ đề, từ và khoảng thời gian; nêu số mẫu và mức độ tin cậy.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Chưa đủ mẫu hiện chưa đủ dữ liệu; không coi chưa làm là kỹ năng yếu. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.73. User Story 73 — ST-05 — Tạo bộ luyện từ lỗi phổ biến

Là **người học**, tôi muốn **tạo bộ luyện từ lỗi phổ biến** để nhận biết và khắc phục điểm yếu có minh chứng.

| Mục | Đặc tả |
| --- | --- |
| ID | ST-05 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI27, UI28, UI29; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Chọn lỗi/từ, tạo đề với dạng phù hợp, giữ liên kết các lỗi đầu vào |
| Nhánh không hợp lệ | Lỗi đã xóa hoặc thẻ không dùng được thì loại khỏi đề và báo số câu còn lại |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | muc_so_tay, loi_hoc_tap, muc_do_lam_chu_tu, cap_tu_de_nham, goi_y_hoc_tap |
| Giao diện | UI27, UI28, UI29 |
| Kiểm thử | TC-ST-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc lịch sử sai/ôn thuộc người dùng; áp dụng quy tắc có phiên bản và đủ mẫu; lưu gợi ý/ghi chú hoặc tạo bài; kèm nguồn và lý do giải thích.
3. Áp dụng tiêu chí riêng: Chọn lỗi/từ, tạo đề với dạng phù hợp, giữ liên kết các lỗi đầu vào.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Lỗi đã xóa hoặc thẻ không dùng được thì loại khỏi đề và báo số câu còn lại. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.74. User Story 74 — ST-06 — Mức độ làm chủ từng từ qua nhiều kỹ năng

Là **người học**, tôi muốn **mức độ làm chủ từng từ qua nhiều kỹ năng** để nhận biết và khắc phục điểm yếu có minh chứng.

| Mục | Đặc tả |
| --- | --- |
| ID | ST-06 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI27, UI28, UI29; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Tách nhận biết nghĩa, chính tả, nghe và sử dụng; tính trên các sự kiện hợp lệ có đủ mẫu |
| Nhánh không hợp lệ | Không gộp điểm AI với điểm cố định hoặc coi một chiều học đại diện mọi kỹ năng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | muc_so_tay, loi_hoc_tap, muc_do_lam_chu_tu, cap_tu_de_nham, goi_y_hoc_tap |
| Giao diện | UI27, UI28, UI29 |
| Kiểm thử | TC-ST-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc lịch sử sai/ôn thuộc người dùng; áp dụng quy tắc có phiên bản và đủ mẫu; lưu gợi ý/ghi chú hoặc tạo bài; kèm nguồn và lý do giải thích.
3. Áp dụng tiêu chí riêng: Tách nhận biết nghĩa, chính tả, nghe và sử dụng; tính trên các sự kiện hợp lệ có đủ mẫu.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không gộp điểm AI với điểm cố định hoặc coi một chiều học đại diện mọi kỹ năng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.75. User Story 75 — ST-07 — Theo dõi cặp từ thường nhầm

Là **người học**, tôi muốn **theo dõi cặp từ thường nhầm** để nhận biết và khắc phục điểm yếu có minh chứng.

| Mục | Đặc tả |
| --- | --- |
| ID | ST-07 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI27, UI28, UI29; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Thống kê lỗi đúng cặp và ngữ cảnh, số lần, lần gần nhất; liên kết bài phân biệt |
| Nhánh không hợp lệ | Không tạo cặp từ chỉ từ trùng chuỗi; cặp thuộc người khác không được xem |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | muc_so_tay, loi_hoc_tap, muc_do_lam_chu_tu, cap_tu_de_nham, goi_y_hoc_tap |
| Giao diện | UI27, UI28, UI29 |
| Kiểm thử | TC-ST-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc lịch sử sai/ôn thuộc người dùng; áp dụng quy tắc có phiên bản và đủ mẫu; lưu gợi ý/ghi chú hoặc tạo bài; kèm nguồn và lý do giải thích.
3. Áp dụng tiêu chí riêng: Thống kê lỗi đúng cặp và ngữ cảnh, số lần, lần gần nhất; liên kết bài phân biệt.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không tạo cặp từ chỉ từ trùng chuỗi; cặp thuộc người khác không được xem. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.76. User Story 76 — ST-08 — Gợi ý bài luyện phù hợp

Là **người học**, tôi muốn **gợi ý bài luyện phù hợp** để nhận biết và khắc phục điểm yếu có minh chứng.

| Mục | Đặc tả |
| --- | --- |
| ID | ST-08 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI27, UI28, UI29; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Xếp ưu tiên từ đến hạn, lỗi lặp và kỹ năng yếu; giới hạn số gợi ý; ghi lý do và phiên bản quy tắc |
| Nhánh không hợp lệ | Không đủ dữ liệu dùng đề xuất khởi đầu; từ tạm ngưng không được đề xuất ôn |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | muc_so_tay, loi_hoc_tap, muc_do_lam_chu_tu, cap_tu_de_nham, goi_y_hoc_tap |
| Giao diện | UI27, UI28, UI29 |
| Kiểm thử | TC-ST-08-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc lịch sử sai/ôn thuộc người dùng; áp dụng quy tắc có phiên bản và đủ mẫu; lưu gợi ý/ghi chú hoặc tạo bài; kèm nguồn và lý do giải thích.
3. Áp dụng tiêu chí riêng: Xếp ưu tiên từ đến hạn, lỗi lặp và kỹ năng yếu; giới hạn số gợi ý; ghi lý do và phiên bản quy tắc.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không đủ dữ liệu dùng đề xuất khởi đầu; từ tạm ngưng không được đề xuất ôn. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.77. User Story 77 — ST-09 — Xem lý do đề xuất bài hoặc thẻ

Là **người học**, tôi muốn **xem lý do đề xuất bài hoặc thẻ** để nhận biết và khắc phục điểm yếu có minh chứng.

| Mục | Đặc tả |
| --- | --- |
| ID | ST-09 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI27, UI28, UI29; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Hiển thị như 3 lần sai chính tả trong 7 ngày hoặc đến hạn hôm nay, kèm nguồn có quyền |
| Nhánh không hợp lệ | Lý do thiếu dữ liệu hoặc đã cũ phải tính lại; không lộ thông tin người khác |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | muc_so_tay, loi_hoc_tap, muc_do_lam_chu_tu, cap_tu_de_nham, goi_y_hoc_tap |
| Giao diện | UI27, UI28, UI29 |
| Kiểm thử | TC-ST-09-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc lịch sử sai/ôn thuộc người dùng; áp dụng quy tắc có phiên bản và đủ mẫu; lưu gợi ý/ghi chú hoặc tạo bài; kèm nguồn và lý do giải thích.
3. Áp dụng tiêu chí riêng: Hiển thị như 3 lần sai chính tả trong 7 ngày hoặc đến hạn hôm nay, kèm nguồn có quyền.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Lý do thiếu dữ liệu hoặc đã cũ phải tính lại; không lộ thông tin người khác. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.78. User Story 78 — TKH-01 — Số từ mới, đang học và đạt tiêu chí ghi nhớ

Là **người học**, tôi muốn **số từ mới, đang học và đạt tiêu chí ghi nhớ** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-01 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Đếm theo thẻ/chiều hoặc thẻ duy nhất được ghi rõ; đạt ghi nhớ khi REVIEW, khoảng ôn ít nhất 21 ngày và nhớ liên tiếp ít nhất 3 lần (chọn Khó hoặc Quên đặt lại bộ đếm), không tạm ngưng |
| Nhánh không hợp lệ | Không gọi số thẻ là số từ gốc; thẻ tạm ngưng/xóa được tách riêng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: Đếm theo thẻ/chiều hoặc thẻ duy nhất được ghi rõ; đạt ghi nhớ khi REVIEW, khoảng ôn ít nhất 21 ngày và nhớ liên tiếp ít nhất 3 lần (chọn Khó hoặc Quên đặt lại bộ đếm), không tạm ngưng.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không gọi số thẻ là số từ gốc; thẻ tạm ngưng/xóa được tách riêng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.79. User Story 79 — TKH-02 — Số thẻ ôn theo ngày, tuần, tháng

Là **người học**, tôi muốn **số thẻ ôn theo ngày, tuần, tháng** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-02 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Tổng hợp lượt ôn duy nhất theo ngày địa phương, có tùy chọn số thẻ duy nhất và số lượt |
| Nhánh không hợp lệ | Không đếm kết quả gửi trùng; khoảng ngày không hợp lệ trả lỗi |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: Tổng hợp lượt ôn duy nhất theo ngày địa phương, có tùy chọn số thẻ duy nhất và số lượt.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không đếm kết quả gửi trùng; khoảng ngày không hợp lệ trả lỗi. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.80. User Story 80 — TKH-03 — Tỷ lệ đúng theo dạng bài

Là **người học**, tôi muốn **tỷ lệ đúng theo dạng bài** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-03 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Đúng/chấm được cho từng dạng; ghi số câu và tỷ lệ; bài viết AI báo riêng |
| Nhánh không hợp lệ | Mẫu bằng 0 hiện chưa có dữ liệu, không chia cho 0 hoặc coi là 0% yếu |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: Đúng/chấm được cho từng dạng; ghi số câu và tỷ lệ; bài viết AI báo riêng.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Mẫu bằng 0 hiện chưa có dữ liệu, không chia cho 0 hoặc coi là 0% yếu. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.81. User Story 81 — TKH-04 — Thời gian học và hoàn thành mục tiêu

Là **người học**, tôi muốn **thời gian học và hoàn thành mục tiêu** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-04 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Tổng thời gian hoạt động có giới hạn, so mục tiêu ngày; lưu mục tiêu áp dụng trong ngày |
| Nhánh không hợp lệ | Tab nền/không hoạt động không tính vô hạn; sửa mục tiêu không sửa lịch sử ngày cũ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: Tổng thời gian hoạt động có giới hạn, so mục tiêu ngày; lưu mục tiêu áp dụng trong ngày.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Tab nền/không hoạt động không tính vô hạn; sửa mục tiêu không sửa lịch sử ngày cũ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.82. User Story 82 — TKH-05 — Biểu đồ hoạt động và streak

Là **người học**, tôi muốn **biểu đồ hoạt động và streak** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-05 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lịch hoạt động có ngày, cường độ, chú giải; chuỗi ngày dựa sự kiện học đã xác nhận |
| Nhánh không hợp lệ | Không chỉ dùng màu; đổi múi giờ không thay đổi ngày lịch sử đã đóng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: Lịch hoạt động có ngày, cường độ, chú giải; chuỗi ngày dựa sự kiện học đã xác nhận.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không chỉ dùng màu; đổi múi giờ không thay đổi ngày lịch sử đã đóng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.83. User Story 83 — TKH-06 — Thống kê theo chủ đề hoặc bộ thẻ

Là **người học**, tôi muốn **thống kê theo chủ đề hoặc bộ thẻ** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-06 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lọc bộ/chủ đề có quyền, khoảng thời gian; tổng lượt và đúng được tính từ cùng tập sự kiện |
| Nhánh không hợp lệ | Bộ nhiều chủ đề cần nêu không cộng chéo để tránh tổng vượt toàn bộ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: Lọc bộ/chủ đề có quyền, khoảng thời gian; tổng lượt và đúng được tính từ cùng tập sự kiện.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Bộ nhiều chủ đề cần nêu không cộng chéo để tránh tổng vượt toàn bộ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.84. User Story 84 — TKH-07 — Danh sách từ thường quên

Là **người học**, tôi muốn **danh sách từ thường quên** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-07 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Xếp theo số Quên/tỷ lệ đủ mẫu/gần đây, hiển thị lịch sử và nút luyện |
| Nhánh không hợp lệ | Từ không đủ mẫu không khẳng định hay quên; thẻ đã xóa được ghi rõ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: Xếp theo số Quên/tỷ lệ đủ mẫu/gần đây, hiển thị lịch sử và nút luyện.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Từ không đủ mẫu không khẳng định hay quên; thẻ đã xóa được ghi rõ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.85. User Story 85 — TKH-08 — So sánh tiến độ giữa các giai đoạn

Là **người học**, tôi muốn **so sánh tiến độ giữa các giai đoạn** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-08 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | So hai khoảng có độ dài và bộ lọc rõ; hiển thị số mẫu, chênh lệch và đơn vị |
| Nhánh không hợp lệ | Hai khoảng khác độ dài cần chuẩn hóa theo ngày hoặc cảnh báo; không suy ra cải thiện nếu thiếu dữ liệu |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-08-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: So hai khoảng có độ dài và bộ lọc rõ; hiển thị số mẫu, chênh lệch và đơn vị.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Hai khoảng khác độ dài cần chuẩn hóa theo ngày hoặc cảnh báo; không suy ra cải thiện nếu thiếu dữ liệu. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.86. User Story 86 — TKH-09 — Báo cáo tổng kết tuần

Là **người học**, tôi muốn **báo cáo tổng kết tuần** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-09 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Chốt tuần theo múi giờ, lưu số mới/ôn/đúng/thời gian/mục tiêu và gợi ý; xem lại bản báo cáo |
| Nhánh không hợp lệ | Tác vụ chạy lại không tạo báo cáo tuần trùng; email chỉ gửi khi đã chọn |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-09-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: Chốt tuần theo múi giờ, lưu số mới/ôn/đúng/thời gian/mục tiêu và gợi ý; xem lại bản báo cáo.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Tác vụ chạy lại không tạo báo cáo tuần trùng; email chỉ gửi khi đã chọn. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.87. User Story 87 — TKH-10 — Tiến độ theo kỹ năng sử dụng từ

Là **người học**, tôi muốn **tiến độ theo kỹ năng sử dụng từ** để hiểu quá trình học và điều chỉnh mục tiêu.

| Mục | Đặc tả |
| --- | --- |
| ID | TKH-10 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI30, UI31; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Hiển thị nhận biết, chính tả, nghe, sử dụng riêng; điểm AI gắn nhãn và đủ mẫu |
| Nhánh không hợp lệ | Không suy từ streak ra khả năng phát âm; API không hỗ trợ chỉ số nào thì không tự tạo |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | su_kien_hoat_dong, thong_ke_hang_ngay, bao_cao_hang_tuan, muc_do_lam_chu_tu |
| Giao diện | UI30, UI31 |
| Kiểm thử | TC-TKH-10-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Lấy các sự kiện đã xác nhận theo ngày địa phương và quyền; áp dụng cùng bộ lọc; tính mẫu số, số mẫu và chỉ số; trả dữ liệu và thời điểm cập nhật.
3. Áp dụng tiêu chí riêng: Hiển thị nhận biết, chính tả, nghe, sử dụng riêng; điểm AI gắn nhãn và đủ mẫu.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không suy từ streak ra khả năng phát âm; API không hỗ trợ chỉ số nào thì không tự tạo. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.88. User Story 88 — DL-01 — Điểm kinh nghiệm khi hoàn thành nhiệm vụ

Là **người học**, tôi muốn **điểm kinh nghiệm khi hoàn thành nhiệm vụ** để duy trì thói quen và nhận nhắc phù hợp.

| Mục | Đặc tả |
| --- | --- |
| ID | DL-01 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI32, UI33, UI34; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Ghi sổ thưởng theo sự kiện duy nhất, giới hạn 200 XP/ngày, cùng nhiệm vụ quá dễ chỉ thưởng lần đầu theo chính sách |
| Nhánh không hợp lệ | Lặp nộp bài, đổi tiến độ hoặc phát lại sự kiện không tăng XP; máy chủ tự tính điểm |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | so_diem_kinh_nghiem, huy_hieu, huy_hieu_nguoi_dung, mau_thu_thach, ky_thu_thach, tien_do_thu_thach, thong_bao, luot_gui_thong_bao |
| Giao diện | UI32, UI33, UI34 |
| Kiểm thử | TC-DL-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc sự kiện học hợp lệ; kiểm tra kỳ thưởng/nhắc, múi giờ và tùy chọn; dùng khóa sự kiện chống trùng; ghi tiến độ/thưởng/thông báo, gửi email qua tác vụ nền.
3. Áp dụng tiêu chí riêng: Ghi sổ thưởng theo sự kiện duy nhất, giới hạn 200 XP/ngày, cùng nhiệm vụ quá dễ chỉ thưởng lần đầu theo chính sách.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Lặp nộp bài, đổi tiến độ hoặc phát lại sự kiện không tăng XP; máy chủ tự tính điểm. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.89. User Story 89 — DL-02 — Huy hiệu theo thành tích

Là **người học**, tôi muốn **huy hiệu theo thành tích** để duy trì thói quen và nhận nhắc phù hợp.

| Mục | Đặc tả |
| --- | --- |
| ID | DL-02 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI32, UI33, UI34; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Điều kiện huy hiệu phiên bản hóa; cấp một lần/người/huy hiệu, có ngày đạt và minh chứng |
| Nhánh không hợp lệ | Không cấp trước khi đủ điều kiện; chạy tác vụ lặp không cấp thêm |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | so_diem_kinh_nghiem, huy_hieu, huy_hieu_nguoi_dung, mau_thu_thach, ky_thu_thach, tien_do_thu_thach, thong_bao, luot_gui_thong_bao |
| Giao diện | UI32, UI33, UI34 |
| Kiểm thử | TC-DL-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc sự kiện học hợp lệ; kiểm tra kỳ thưởng/nhắc, múi giờ và tùy chọn; dùng khóa sự kiện chống trùng; ghi tiến độ/thưởng/thông báo, gửi email qua tác vụ nền.
3. Áp dụng tiêu chí riêng: Điều kiện huy hiệu phiên bản hóa; cấp một lần/người/huy hiệu, có ngày đạt và minh chứng.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không cấp trước khi đủ điều kiện; chạy tác vụ lặp không cấp thêm. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.90. User Story 90 — DL-03 — Thử thách hằng ngày và hằng tuần

Là **người học**, tôi muốn **thử thách hằng ngày và hằng tuần** để duy trì thói quen và nhận nhắc phù hợp.

| Mục | Đặc tả |
| --- | --- |
| ID | DL-03 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI32, UI33, UI34; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Có kỳ bắt đầu/kết thúc, mục tiêu đo được, tiến độ và thưởng; ngày/tuần theo chính sách thời gian |
| Nhánh không hợp lệ | Sự kiện ngoài kỳ hoặc đã dùng không cộng tiến độ/thưởng lần nữa |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | so_diem_kinh_nghiem, huy_hieu, huy_hieu_nguoi_dung, mau_thu_thach, ky_thu_thach, tien_do_thu_thach, thong_bao, luot_gui_thong_bao |
| Giao diện | UI32, UI33, UI34 |
| Kiểm thử | TC-DL-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc sự kiện học hợp lệ; kiểm tra kỳ thưởng/nhắc, múi giờ và tùy chọn; dùng khóa sự kiện chống trùng; ghi tiến độ/thưởng/thông báo, gửi email qua tác vụ nền.
3. Áp dụng tiêu chí riêng: Có kỳ bắt đầu/kết thúc, mục tiêu đo được, tiến độ và thưởng; ngày/tuần theo chính sách thời gian.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Sự kiện ngoài kỳ hoặc đã dùng không cộng tiến độ/thưởng lần nữa. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.91. User Story 91 — DL-04 — Theo dõi chuỗi ngày học

Là **người học**, tôi muốn **theo dõi chuỗi ngày học** để duy trì thói quen và nhận nhắc phù hợp.

| Mục | Đặc tả |
| --- | --- |
| ID | DL-04 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI32, UI33, UI34; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Hiển thị chuỗi hiện tại/dài nhất, ngày gần nhất; sử dụng cùng nguồn với thống kê |
| Nhánh không hợp lệ | Mở ứng dụng không tính ngày học; gián đoạn ngày hợp lệ kết thúc chuỗi theo quy tắc |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | so_diem_kinh_nghiem, huy_hieu, huy_hieu_nguoi_dung, mau_thu_thach, ky_thu_thach, tien_do_thu_thach, thong_bao, luot_gui_thong_bao |
| Giao diện | UI32, UI33, UI34 |
| Kiểm thử | TC-DL-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc sự kiện học hợp lệ; kiểm tra kỳ thưởng/nhắc, múi giờ và tùy chọn; dùng khóa sự kiện chống trùng; ghi tiến độ/thưởng/thông báo, gửi email qua tác vụ nền.
3. Áp dụng tiêu chí riêng: Hiển thị chuỗi hiện tại/dài nhất, ngày gần nhất; sử dụng cùng nguồn với thống kê.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Mở ứng dụng không tính ngày học; gián đoạn ngày hợp lệ kết thúc chuỗi theo quy tắc. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.92. User Story 92 — DL-05 — Thông báo thẻ đến hạn trong ứng dụng

Là **người học**, tôi muốn **thông báo thẻ đến hạn trong ứng dụng** để duy trì thói quen và nhận nhắc phù hợp.

| Mục | Đặc tả |
| --- | --- |
| ID | DL-05 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI32, UI33, UI34; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu thông báo theo đợt nhắc và người; hiển thị số đến hạn, nút mở hàng đợi, đọc/đánh dấu tất cả |
| Nhánh không hợp lệ | Không có thẻ đến hạn không gửi nhắc giả; sự kiện lặp không tạo thông báo trùng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | so_diem_kinh_nghiem, huy_hieu, huy_hieu_nguoi_dung, mau_thu_thach, ky_thu_thach, tien_do_thu_thach, thong_bao, luot_gui_thong_bao |
| Giao diện | UI32, UI33, UI34 |
| Kiểm thử | TC-DL-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc sự kiện học hợp lệ; kiểm tra kỳ thưởng/nhắc, múi giờ và tùy chọn; dùng khóa sự kiện chống trùng; ghi tiến độ/thưởng/thông báo, gửi email qua tác vụ nền.
3. Áp dụng tiêu chí riêng: Lưu thông báo theo đợt nhắc và người; hiển thị số đến hạn, nút mở hàng đợi, đọc/đánh dấu tất cả.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không có thẻ đến hạn không gửi nhắc giả; sự kiện lặp không tạo thông báo trùng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.93. User Story 93 — DL-06 — Email nhắc học theo lựa chọn

Là **người học**, tôi muốn **email nhắc học theo lựa chọn** để duy trì thói quen và nhận nhắc phù hợp.

| Mục | Đặc tả |
| --- | --- |
| ID | DL-06 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI32, UI33, UI34; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Chỉ gửi khi bật email, email xác thực và đến giờ/múi giờ; liên kết quản lý tùy chọn, theo dõi gửi |
| Nhánh không hợp lệ | Tắt thông báo trước gửi thì bỏ lượt; nhà cung cấp lỗi thử lại có giới hạn không gửi nhiều bản |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | so_diem_kinh_nghiem, huy_hieu, huy_hieu_nguoi_dung, mau_thu_thach, ky_thu_thach, tien_do_thu_thach, thong_bao, luot_gui_thong_bao |
| Giao diện | UI32, UI33, UI34 |
| Kiểm thử | TC-DL-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc sự kiện học hợp lệ; kiểm tra kỳ thưởng/nhắc, múi giờ và tùy chọn; dùng khóa sự kiện chống trùng; ghi tiến độ/thưởng/thông báo, gửi email qua tác vụ nền.
3. Áp dụng tiêu chí riêng: Chỉ gửi khi bật email, email xác thực và đến giờ/múi giờ; liên kết quản lý tùy chọn, theo dõi gửi.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Tắt thông báo trước gửi thì bỏ lượt; nhà cung cấp lỗi thử lại có giới hạn không gửi nhiều bản. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.94. User Story 94 — DL-07 — Thông báo hoàn thành mục tiêu

Là **người học**, tôi muốn **thông báo hoàn thành mục tiêu** để duy trì thói quen và nhận nhắc phù hợp.

| Mục | Đặc tả |
| --- | --- |
| ID | DL-07 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI32, UI33, UI34; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Phát khi lần đầu đạt mục tiêu trong ngày; nội dung nêu chỉ tiêu đã đạt và cập nhật tiến độ |
| Nhánh không hợp lệ | Gửi lại kết quả không thông báo lại; đổi mục tiêu không tạo chuỗi thưởng vô hạn |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | so_diem_kinh_nghiem, huy_hieu, huy_hieu_nguoi_dung, mau_thu_thach, ky_thu_thach, tien_do_thu_thach, thong_bao, luot_gui_thong_bao |
| Giao diện | UI32, UI33, UI34 |
| Kiểm thử | TC-DL-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Đọc sự kiện học hợp lệ; kiểm tra kỳ thưởng/nhắc, múi giờ và tùy chọn; dùng khóa sự kiện chống trùng; ghi tiến độ/thưởng/thông báo, gửi email qua tác vụ nền.
3. Áp dụng tiêu chí riêng: Phát khi lần đầu đạt mục tiêu trong ngày; nội dung nêu chỉ tiêu đã đạt và cập nhật tiến độ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Gửi lại kết quả không thông báo lại; đổi mục tiêu không tạo chuỗi thưởng vô hạn. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.95. User Story 95 — PA-01 — Nghe phát âm mẫu

Là **người học**, tôi muốn **nghe phát âm mẫu** để luyện và theo dõi phát âm trong phạm vi API hỗ trợ.

| Mục | Đặc tả |
| --- | --- |
| ID | PA-01 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI35, UI36; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Phát audio từ/câu đã lưu hoặc tạo bằng dịch vụ TTS và lưu lại theo quyền; chọn giọng khi được hỗ trợ |
| Nhánh không hợp lệ | Mất tệp/TTS lỗi có thông báo; nghe mẫu không được coi là đã đánh giá phát âm |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | lan_danh_gia_phat_am, ket_qua_phat_am_tung_tu, tep_da_phuong_tien, luot_goi_api |
| Giao diện | UI35, UI36 |
| Kiểm thử | TC-PA-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra văn bản tham chiếu, quyền tệp và định dạng; bản ghi chỉ gửi khi người dùng xác nhận; gọi API ngoài giao dịch; lưu chỉ số thực nhận cùng phiên bản nhà cung cấp.
3. Áp dụng tiêu chí riêng: Phát audio từ/câu đã lưu hoặc tạo bằng dịch vụ TTS và lưu lại theo quyền; chọn giọng khi được hỗ trợ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Mất tệp/TTS lỗi có thông báo; nghe mẫu không được coi là đã đánh giá phát âm. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.96. User Story 96 — PA-02 — Ghi âm đọc từ hoặc câu

Là **người học**, tôi muốn **ghi âm đọc từ hoặc câu** để luyện và theo dõi phát âm trong phạm vi API hỗ trợ.

| Mục | Đặc tả |
| --- | --- |
| ID | PA-02 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI35, UI36; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Xin quyền micro rõ ràng, hiển thị bắt đầu/dừng và thời lượng tối đa 30 giây; kiểm tra định dạng hỗ trợ |
| Nhánh không hợp lệ | Từ chối quyền, không có micro, bản ghi rỗng hoặc quá dài phải có hướng xử lý |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | lan_danh_gia_phat_am, ket_qua_phat_am_tung_tu, tep_da_phuong_tien, luot_goi_api |
| Giao diện | UI35, UI36 |
| Kiểm thử | TC-PA-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra văn bản tham chiếu, quyền tệp và định dạng; bản ghi chỉ gửi khi người dùng xác nhận; gọi API ngoài giao dịch; lưu chỉ số thực nhận cùng phiên bản nhà cung cấp.
3. Áp dụng tiêu chí riêng: Xin quyền micro rõ ràng, hiển thị bắt đầu/dừng và thời lượng tối đa 30 giây; kiểm tra định dạng hỗ trợ.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Từ chối quyền, không có micro, bản ghi rỗng hoặc quá dài phải có hướng xử lý. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.97. User Story 97 — PA-03 — Phát lại bản ghi

Là **người học**, tôi muốn **phát lại bản ghi** để luyện và theo dõi phát âm trong phạm vi API hỗ trợ.

| Mục | Đặc tả |
| --- | --- |
| ID | PA-03 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI35, UI36; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Người học nghe bản ghi trước gửi, ghi lại hoặc xóa; không tự gửi khi chưa xác nhận |
| Nhánh không hợp lệ | Bản ghi hỏng không gửi API; không phát bản ghi của người khác |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | lan_danh_gia_phat_am, ket_qua_phat_am_tung_tu, tep_da_phuong_tien, luot_goi_api |
| Giao diện | UI35, UI36 |
| Kiểm thử | TC-PA-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra văn bản tham chiếu, quyền tệp và định dạng; bản ghi chỉ gửi khi người dùng xác nhận; gọi API ngoài giao dịch; lưu chỉ số thực nhận cùng phiên bản nhà cung cấp.
3. Áp dụng tiêu chí riêng: Người học nghe bản ghi trước gửi, ghi lại hoặc xóa; không tự gửi khi chưa xác nhận.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Bản ghi hỏng không gửi API; không phát bản ghi của người khác. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.98. User Story 98 — PA-04 — Gửi bản ghi đến API phát âm

Là **người học**, tôi muốn **gửi bản ghi đến api phát âm** để luyện và theo dõi phát âm trong phạm vi API hỗ trợ.

| Mục | Đặc tả |
| --- | --- |
| ID | PA-04 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI35, UI36; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Máy chủ kiểm tra quyền, hạn mức, định dạng và văn bản tham chiếu; tạo tác vụ đánh giá, lưu nhà cung cấp |
| Nhánh không hợp lệ | API lỗi/timeout/thiếu chức năng hiển thị thất bại hoặc chưa rõ; không tự bịa điểm |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | lan_danh_gia_phat_am, ket_qua_phat_am_tung_tu, tep_da_phuong_tien, luot_goi_api |
| Giao diện | UI35, UI36 |
| Kiểm thử | TC-PA-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra văn bản tham chiếu, quyền tệp và định dạng; bản ghi chỉ gửi khi người dùng xác nhận; gọi API ngoài giao dịch; lưu chỉ số thực nhận cùng phiên bản nhà cung cấp.
3. Áp dụng tiêu chí riêng: Máy chủ kiểm tra quyền, hạn mức, định dạng và văn bản tham chiếu; tạo tác vụ đánh giá, lưu nhà cung cấp.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** API lỗi/timeout/thiếu chức năng hiển thị thất bại hoặc chưa rõ; không tự bịa điểm. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.99. User Story 99 — PA-05 — Hiển thị điểm và phản hồi API hỗ trợ

Là **người học**, tôi muốn **hiển thị điểm và phản hồi api hỗ trợ** để luyện và theo dõi phát âm trong phạm vi API hỗ trợ.

| Mục | Đặc tả |
| --- | --- |
| ID | PA-05 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI35, UI36; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Hiển thị độ chính xác/lưu loát/đầy đủ khi nhà cung cấp trả; chỉ số không hỗ trợ hiển thị không có |
| Nhánh không hợp lệ | Không ánh xạ nhận dạng lời nói thành điểm phát âm; phản hồi lỗi cấu trúc không coi thành công |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | lan_danh_gia_phat_am, ket_qua_phat_am_tung_tu, tep_da_phuong_tien, luot_goi_api |
| Giao diện | UI35, UI36 |
| Kiểm thử | TC-PA-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra văn bản tham chiếu, quyền tệp và định dạng; bản ghi chỉ gửi khi người dùng xác nhận; gọi API ngoài giao dịch; lưu chỉ số thực nhận cùng phiên bản nhà cung cấp.
3. Áp dụng tiêu chí riêng: Hiển thị độ chính xác/lưu loát/đầy đủ khi nhà cung cấp trả; chỉ số không hỗ trợ hiển thị không có.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không ánh xạ nhận dạng lời nói thành điểm phát âm; phản hồi lỗi cấu trúc không coi thành công. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.100. User Story 100 — PA-06 — Lưu kết quả theo dõi cải thiện

Là **người học**, tôi muốn **lưu kết quả theo dõi cải thiện** để luyện và theo dõi phát âm trong phạm vi API hỗ trợ.

| Mục | Đặc tả |
| --- | --- |
| ID | PA-06 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI35, UI36; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Giữ thời gian, từ/câu, mô hình, điểm và bản ghi theo tùy chọn; so sánh cùng cấu hình tương thích |
| Nhánh không hợp lệ | Khác thang điểm/nhà cung cấp phải nêu rõ; không so trực tiếp như cùng thước đo |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | lan_danh_gia_phat_am, ket_qua_phat_am_tung_tu, tep_da_phuong_tien, luot_goi_api |
| Giao diện | UI35, UI36 |
| Kiểm thử | TC-PA-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra văn bản tham chiếu, quyền tệp và định dạng; bản ghi chỉ gửi khi người dùng xác nhận; gọi API ngoài giao dịch; lưu chỉ số thực nhận cùng phiên bản nhà cung cấp.
3. Áp dụng tiêu chí riêng: Giữ thời gian, từ/câu, mô hình, điểm và bản ghi theo tùy chọn; so sánh cùng cấu hình tương thích.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Khác thang điểm/nhà cung cấp phải nêu rõ; không so trực tiếp như cùng thước đo. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.101. User Story 101 — PA-07 — Luyện lại từ kết quả thấp

Là **người học**, tôi muốn **luyện lại từ kết quả thấp** để luyện và theo dõi phát âm trong phạm vi API hỗ trợ.

| Mục | Đặc tả |
| --- | --- |
| ID | PA-07 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI35, UI36; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Chọn từ dưới ngưỡng cấu hình và đủ dữ liệu, tạo danh sách luyện, nghe mẫu và ghi lại |
| Nhánh không hợp lệ | Từ thiếu điểm không coi điểm 0; kết quả mới không ghi đè kết quả cũ |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | lan_danh_gia_phat_am, ket_qua_phat_am_tung_tu, tep_da_phuong_tien, luot_goi_api |
| Giao diện | UI35, UI36 |
| Kiểm thử | TC-PA-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra văn bản tham chiếu, quyền tệp và định dạng; bản ghi chỉ gửi khi người dùng xác nhận; gọi API ngoài giao dịch; lưu chỉ số thực nhận cùng phiên bản nhà cung cấp.
3. Áp dụng tiêu chí riêng: Chọn từ dưới ngưỡng cấu hình và đủ dữ liệu, tạo danh sách luyện, nghe mẫu và ghi lại.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Từ thiếu điểm không coi điểm 0; kết quả mới không ghi đè kết quả cũ. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.102. User Story 102 — PA-08 — Xóa bản ghi âm

Là **người học**, tôi muốn **xóa bản ghi âm** để luyện và theo dõi phát âm trong phạm vi API hỗ trợ.

| Mục | Đặc tả |
| --- | --- |
| ID | PA-08 |
| Tác nhân | Người học |
| Điều kiện tiên quyết | Tài khoản ACTIVE đã xác thực email; phiên còn hiệu lực; có quyền với bộ/thẻ/bài/tệp được chọn; tài nguyên chưa bị xóa hoặc ẩn khỏi quyền sử dụng. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI35, UI36; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Xóa tệp của mình, cập nhật trạng thái; có thể giữ điểm khi người dùng chọn; xóa tài khoản vẫn xóa toàn bộ cá nhân |
| Nhánh không hợp lệ | Bản ghi của người khác bị từ chối; xóa lặp an toàn; tác vụ còn chạy phải kiểm tra trạng thái trước lưu tệp |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | lan_danh_gia_phat_am, ket_qua_phat_am_tung_tu, tep_da_phuong_tien, luot_goi_api |
| Giao diện | UI35, UI36 |
| Kiểm thử | TC-PA-08-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra văn bản tham chiếu, quyền tệp và định dạng; bản ghi chỉ gửi khi người dùng xác nhận; gọi API ngoài giao dịch; lưu chỉ số thực nhận cùng phiên bản nhà cung cấp.
3. Áp dụng tiêu chí riêng: Xóa tệp của mình, cập nhật trạng thái; có thể giữ điểm khi người dùng chọn; xóa tài khoản vẫn xóa toàn bộ cá nhân.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Bản ghi của người khác bị từ chối; xóa lặp an toàn; tác vụ còn chạy phải kiểm tra trạng thái trước lưu tệp. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.103. User Story 103 — QT-01 — Quản lý, khóa và mở khóa tài khoản

Là **quản trị viên**, tôi muốn **quản lý, khóa và mở khóa tài khoản** để quản lý và vận hành hệ thống có trách nhiệm.

| Mục | Đặc tả |
| --- | --- |
| ID | QT-01 |
| Tác nhân | Quản trị viên |
| Điều kiện tiên quyết | Tài khoản ACTIVE có vai trò quản trị viên; thao tác nhạy cảm cần xác thực lại; có lý do và ghi nhật ký. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI37, UI38, UI39, UI40, UI41, UI42; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Quản trị tìm/lọc tài khoản, khóa có lý do, thu hồi phiên và mở theo chính sách; ghi nhật ký |
| Nhánh không hợp lệ | Người học gọi API quản trị bị từ chối; tránh khóa quản trị cuối cùng gây mất quyền vận hành |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung_vai_tro, bao_cao_noi_dung, chinh_sach_ai, luot_goi_api, thong_bao_he_thong, nhat_ky_quan_tri, cai_dat_he_thong |
| Giao diện | UI37, UI38, UI39, UI40, UI41, UI42 |
| Kiểm thử | TC-QT-01-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra vai trò hiện tại, đối tượng và phiên bản; xác thực lại khi cần; ghi thay đổi có lý do và nhật ký đã che dữ liệu nhạy cảm; phát sự kiện thu hồi phiên/cập nhật nội dung nếu cần.
3. Áp dụng tiêu chí riêng: Quản trị tìm/lọc tài khoản, khóa có lý do, thu hồi phiên và mở theo chính sách; ghi nhật ký.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Người học gọi API quản trị bị từ chối; tránh khóa quản trị cuối cùng gây mất quyền vận hành. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.104. User Story 104 — QT-02 — Phân quyền người học và quản trị viên

Là **quản trị viên**, tôi muốn **phân quyền người học và quản trị viên** để quản lý và vận hành hệ thống có trách nhiệm.

| Mục | Đặc tả |
| --- | --- |
| ID | QT-02 |
| Tác nhân | Quản trị viên |
| Điều kiện tiên quyết | Tài khoản ACTIVE có vai trò quản trị viên; thao tác nhạy cảm cần xác thực lại; có lý do và ghi nhật ký. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI37, UI38, UI39, UI40, UI41, UI42; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Cấp/thu hồi vai trò tại máy chủ, cập nhật phiên/quyền; thao tác nhạy cảm cần xác thực lại và nhật ký |
| Nhánh không hợp lệ | Không tự nâng quyền từ trình duyệt; không thu hồi quản trị cuối cùng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung_vai_tro, bao_cao_noi_dung, chinh_sach_ai, luot_goi_api, thong_bao_he_thong, nhat_ky_quan_tri, cai_dat_he_thong |
| Giao diện | UI37, UI38, UI39, UI40, UI41, UI42 |
| Kiểm thử | TC-QT-02-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra vai trò hiện tại, đối tượng và phiên bản; xác thực lại khi cần; ghi thay đổi có lý do và nhật ký đã che dữ liệu nhạy cảm; phát sự kiện thu hồi phiên/cập nhật nội dung nếu cần.
3. Áp dụng tiêu chí riêng: Cấp/thu hồi vai trò tại máy chủ, cập nhật phiên/quyền; thao tác nhạy cảm cần xác thực lại và nhật ký.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không tự nâng quyền từ trình duyệt; không thu hồi quản trị cuối cùng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.105. User Story 105 — QT-03 — Quản lý chủ đề, bộ thẻ và thẻ mẫu

Là **quản trị viên**, tôi muốn **quản lý chủ đề, bộ thẻ và thẻ mẫu** để quản lý và vận hành hệ thống có trách nhiệm.

| Mục | Đặc tả |
| --- | --- |
| ID | QT-03 |
| Tác nhân | Quản trị viên |
| Điều kiện tiên quyết | Tài khoản ACTIVE có vai trò quản trị viên; thao tác nhạy cảm cần xác thực lại; có lý do và ghi nhật ký. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI37, UI38, UI39, UI40, UI41, UI42; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Quản trị tạo/sửa/ẩn nội dung chính thức, duyệt nghĩa/audio và nguồn; số thẻ/mô tả cập nhật |
| Nhánh không hợp lệ | Không xóa chủ đề đang dùng gây khóa ngoại lỗi; ẩn không phá bản sao độc lập |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung_vai_tro, bao_cao_noi_dung, chinh_sach_ai, luot_goi_api, thong_bao_he_thong, nhat_ky_quan_tri, cai_dat_he_thong |
| Giao diện | UI37, UI38, UI39, UI40, UI41, UI42 |
| Kiểm thử | TC-QT-03-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra vai trò hiện tại, đối tượng và phiên bản; xác thực lại khi cần; ghi thay đổi có lý do và nhật ký đã che dữ liệu nhạy cảm; phát sự kiện thu hồi phiên/cập nhật nội dung nếu cần.
3. Áp dụng tiêu chí riêng: Quản trị tạo/sửa/ẩn nội dung chính thức, duyệt nghĩa/audio và nguồn; số thẻ/mô tả cập nhật.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không xóa chủ đề đang dùng gây khóa ngoại lỗi; ẩn không phá bản sao độc lập. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.106. User Story 106 — QT-04 — Kiểm duyệt nội dung báo cáo

Là **quản trị viên**, tôi muốn **kiểm duyệt nội dung báo cáo** để quản lý và vận hành hệ thống có trách nhiệm.

| Mục | Đặc tả |
| --- | --- |
| ID | QT-04 |
| Tác nhân | Quản trị viên |
| Điều kiện tiên quyết | Tài khoản ACTIVE có vai trò quản trị viên; thao tác nhạy cảm cần xác thực lại; có lý do và ghi nhật ký. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI37, UI38, UI39, UI40, UI41, UI42; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Xem phiếu, minh chứng, quyết định duyệt/từ chối/ẩn, lý do và người xử lý; kiểm tra xung đột phiên bản |
| Nhánh không hợp lệ | Hai quản trị xử lý đồng thời không ghi đè quyết định; báo cáo không tự cấp quyền sửa nội dung riêng |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung_vai_tro, bao_cao_noi_dung, chinh_sach_ai, luot_goi_api, thong_bao_he_thong, nhat_ky_quan_tri, cai_dat_he_thong |
| Giao diện | UI37, UI38, UI39, UI40, UI41, UI42 |
| Kiểm thử | TC-QT-04-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra vai trò hiện tại, đối tượng và phiên bản; xác thực lại khi cần; ghi thay đổi có lý do và nhật ký đã che dữ liệu nhạy cảm; phát sự kiện thu hồi phiên/cập nhật nội dung nếu cần.
3. Áp dụng tiêu chí riêng: Xem phiếu, minh chứng, quyết định duyệt/từ chối/ẩn, lý do và người xử lý; kiểm tra xung đột phiên bản.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Hai quản trị xử lý đồng thời không ghi đè quyết định; báo cáo không tự cấp quyền sửa nội dung riêng. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.107. User Story 107 — QT-05 — Xem thống kê sử dụng

Là **quản trị viên**, tôi muốn **xem thống kê sử dụng** để quản lý và vận hành hệ thống có trách nhiệm.

| Mục | Đặc tả |
| --- | --- |
| ID | QT-05 |
| Tác nhân | Quản trị viên |
| Điều kiện tiên quyết | Tài khoản ACTIVE có vai trò quản trị viên; thao tác nhạy cảm cần xác thực lại; có lý do và ghi nhật ký. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI37, UI38, UI39, UI40, UI41, UI42; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Số người hoạt động, phiên/lượt học, nội dung, sự cố và chỉ số theo thời gian; ẩn thông tin cá nhân không cần thiết |
| Nhánh không hợp lệ | Bộ lọc ngày sai, mẫu bằng 0 hoặc truy cập trái quyền; không lấy số tài khoản làm số người hoạt động |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung_vai_tro, bao_cao_noi_dung, chinh_sach_ai, luot_goi_api, thong_bao_he_thong, nhat_ky_quan_tri, cai_dat_he_thong |
| Giao diện | UI37, UI38, UI39, UI40, UI41, UI42 |
| Kiểm thử | TC-QT-05-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra vai trò hiện tại, đối tượng và phiên bản; xác thực lại khi cần; ghi thay đổi có lý do và nhật ký đã che dữ liệu nhạy cảm; phát sự kiện thu hồi phiên/cập nhật nội dung nếu cần.
3. Áp dụng tiêu chí riêng: Số người hoạt động, phiên/lượt học, nội dung, sự cố và chỉ số theo thời gian; ẩn thông tin cá nhân không cần thiết.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Bộ lọc ngày sai, mẫu bằng 0 hoặc truy cập trái quyền; không lấy số tài khoản làm số người hoạt động. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.108. User Story 108 — QT-06 — Cấu hình giới hạn AI

Là **quản trị viên**, tôi muốn **cấu hình giới hạn ai** để quản lý và vận hành hệ thống có trách nhiệm.

| Mục | Đặc tả |
| --- | --- |
| ID | QT-06 |
| Tác nhân | Quản trị viên |
| Điều kiện tiên quyết | Tài khoản ACTIVE có vai trò quản trị viên; thao tác nhạy cảm cần xác thực lại; có lý do và ghi nhật ký. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI37, UI38, UI39, UI40, UI41, UI42; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Hạn mức người/ngày/tính năng, đồng thời và ngân sách toàn hệ thống; lưu phiên bản/ngày hiệu lực |
| Nhánh không hợp lệ | Số âm hoặc hạn mức mới thấp hơn giữ trước cần xử lý rõ; cập nhật không làm mất đối soát |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung_vai_tro, bao_cao_noi_dung, chinh_sach_ai, luot_goi_api, thong_bao_he_thong, nhat_ky_quan_tri, cai_dat_he_thong |
| Giao diện | UI37, UI38, UI39, UI40, UI41, UI42 |
| Kiểm thử | TC-QT-06-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra vai trò hiện tại, đối tượng và phiên bản; xác thực lại khi cần; ghi thay đổi có lý do và nhật ký đã che dữ liệu nhạy cảm; phát sự kiện thu hồi phiên/cập nhật nội dung nếu cần.
3. Áp dụng tiêu chí riêng: Hạn mức người/ngày/tính năng, đồng thời và ngân sách toàn hệ thống; lưu phiên bản/ngày hiệu lực.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Số âm hoặc hạn mức mới thấp hơn giữ trước cần xử lý rõ; cập nhật không làm mất đối soát. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.109. User Story 109 — QT-07 — Theo dõi lượt gọi, lỗi và chi phí API

Là **quản trị viên**, tôi muốn **theo dõi lượt gọi, lỗi và chi phí api** để quản lý và vận hành hệ thống có trách nhiệm.

| Mục | Đặc tả |
| --- | --- |
| ID | QT-07 |
| Tác nhân | Quản trị viên |
| Điều kiện tiên quyết | Tài khoản ACTIVE có vai trò quản trị viên; thao tác nhạy cảm cần xác thực lại; có lý do và ghi nhật ký. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI37, UI38, UI39, UI40, UI41, UI42; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Theo nhà cung cấp/mô hình/tính năng; tách chi phí ước tính và đối soát; hiển thị timeout/429/5xx |
| Nhánh không hợp lệ | Không lưu khóa API trong nhật ký; lượt chưa rõ phải có trạng thái đối soát |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung_vai_tro, bao_cao_noi_dung, chinh_sach_ai, luot_goi_api, thong_bao_he_thong, nhat_ky_quan_tri, cai_dat_he_thong |
| Giao diện | UI37, UI38, UI39, UI40, UI41, UI42 |
| Kiểm thử | TC-QT-07-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra vai trò hiện tại, đối tượng và phiên bản; xác thực lại khi cần; ghi thay đổi có lý do và nhật ký đã che dữ liệu nhạy cảm; phát sự kiện thu hồi phiên/cập nhật nội dung nếu cần.
3. Áp dụng tiêu chí riêng: Theo nhà cung cấp/mô hình/tính năng; tách chi phí ước tính và đối soát; hiển thị timeout/429/5xx.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Không lưu khóa API trong nhật ký; lượt chưa rõ phải có trạng thái đối soát. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.110. User Story 110 — QT-08 — Quản lý thông báo hệ thống

Là **quản trị viên**, tôi muốn **quản lý thông báo hệ thống** để quản lý và vận hành hệ thống có trách nhiệm.

| Mục | Đặc tả |
| --- | --- |
| ID | QT-08 |
| Tác nhân | Quản trị viên |
| Điều kiện tiên quyết | Tài khoản ACTIVE có vai trò quản trị viên; thao tác nhạy cảm cần xác thực lại; có lý do và ghi nhật ký. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI37, UI38, UI39, UI40, UI41, UI42; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Soạn, xem trước, chọn đối tượng và thời gian, phát hành/thu hồi; không gửi email ngoài tùy chọn người học |
| Nhánh không hợp lệ | Nội dung script bị loại; lịch kết thúc trước bắt đầu hoặc phát hành lặp bị từ chối |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung_vai_tro, bao_cao_noi_dung, chinh_sach_ai, luot_goi_api, thong_bao_he_thong, nhat_ky_quan_tri, cai_dat_he_thong |
| Giao diện | UI37, UI38, UI39, UI40, UI41, UI42 |
| Kiểm thử | TC-QT-08-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra vai trò hiện tại, đối tượng và phiên bản; xác thực lại khi cần; ghi thay đổi có lý do và nhật ký đã che dữ liệu nhạy cảm; phát sự kiện thu hồi phiên/cập nhật nội dung nếu cần.
3. Áp dụng tiêu chí riêng: Soạn, xem trước, chọn đối tượng và thời gian, phát hành/thu hồi; không gửi email ngoài tùy chọn người học.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Nội dung script bị loại; lịch kết thúc trước bắt đầu hoặc phát hành lặp bị từ chối. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

### 5.111. User Story 111 — QT-09 — Nhật ký thao tác quản trị quan trọng

Là **quản trị viên**, tôi muốn **nhật ký thao tác quản trị quan trọng** để quản lý và vận hành hệ thống có trách nhiệm.

| Mục | Đặc tả |
| --- | --- |
| ID | QT-09 |
| Tác nhân | Quản trị viên |
| Điều kiện tiên quyết | Tài khoản ACTIVE có vai trò quản trị viên; thao tác nhạy cảm cần xác thực lại; có lý do và ghi nhật ký. |
| Đầu vào | Lựa chọn/biểu mẫu tại UI37, UI38, UI39, UI40, UI41, UI42; ID tài nguyên truyền dạng chuỗi; người dùng hiện tại lấy từ phiên, không lấy userId trình duyệt để quyết định quyền |
| Tiêu chí chấp nhận | Lưu người thao tác, đối tượng, hành động, trước/sau đã che dữ liệu nhạy cảm, thời gian và mã truy vết |
| Nhánh không hợp lệ | Người học không xem nhật ký; quản trị không sửa/xóa nhật ký qua API thông thường |
| Điều kiện ràng buộc | Trong phạm vi chính; quyền, phiên bản và chống ghi trùng theo quy tắc chung |
| Bảng liên quan | nguoi_dung_vai_tro, bao_cao_noi_dung, chinh_sach_ai, luot_goi_api, thong_bao_he_thong, nhat_ky_quan_tri, cai_dat_he_thong |
| Giao diện | UI37, UI38, UI39, UI40, UI41, UI42 |
| Kiểm thử | TC-QT-09-01/02/03 và các tình huống trọng yếu trong kế hoạch kiểm thử |


**Luồng chính:**

1. Người dùng mở màn hình tương ứng, chọn tài nguyên hoặc nhập dữ liệu của yêu cầu.
2. Kiểm tra vai trò hiện tại, đối tượng và phiên bản; xác thực lại khi cần; ghi thay đổi có lý do và nhật ký đã che dữ liệu nhạy cảm; phát sự kiện thu hồi phiên/cập nhật nội dung nếu cần.
3. Áp dụng tiêu chí riêng: Lưu người thao tác, đối tượng, hành động, trước/sau đã che dữ liệu nhạy cảm, thời gian và mã truy vết.
4. Trả kết quả cùng trạng thái rõ; tác vụ chậm trả mã theo dõi, chưa coi là hoàn tất.

**Luồng thay thế/lỗi:** Người học không xem nhật ký; quản trị không sửa/xóa nhật ký qua API thông thường. Thông báo bằng tiếng Việt; lỗi không tạo dữ liệu/điểm/thông báo ngoài quy tắc. Tác vụ đã ghi phải có trạng thái và khả năng xem lại.

**Điều kiện sau:** Kết quả đã được máy chủ xác nhận; chỉ dữ liệu thuộc phạm vi được phép thay đổi. Thao tác xem không tạo sự kiện học/thưởng. Thao tác ghi lưu được mã truy vết; lỗi không để dữ liệu dở dang hoặc báo thành công trước khi hoàn tất.

## PHỤ LỤC A — YÊU CẦU PHI CHỨC NĂNG

| ID | Yêu cầu | Tiêu chí |
| --- | --- | --- |
| PC01 | Giao diện tiếng Việt trên máy tính và điện thoại | Kiểm tra 360, 390, 768, 1280 và 1440 px; không tràn ngang, nút thao tác đủ lớn, có điều khiển bàn phím. |
| PC02 | Trạng thái tải, rỗng, lỗi và thành công | Mỗi màn hình và tác vụ có phản hồi; lỗi chỉ rõ cách thử lại, dữ liệu biểu mẫu được giữ khi có thể. |
| PC03 | Mật khẩu, phân quyền và quyền sở hữu | Băm mật khẩu tại máy chủ; Spring Security kiểm tra mỗi yêu cầu; người A không đọc/sửa/xóa tài nguyên riêng của B. |
| PC04 | Khóa API ở máy chủ | Biến bí mật chỉ nằm ở máy chủ/kho bí mật; không có trong mã Next.js trả trình duyệt, lịch sử Git hoặc nhật ký. |
| PC05 | Kiểm tra đầu ra AI | Kiểm tra cấu trúc, giới hạn, nghĩa mục tiêu, đáp án và quyền trước ghi; nháp cần người dùng xác nhận. |
| PC06 | Đầu vào AI là dữ liệu | Tách chỉ dẫn hệ thống và văn bản người học; không cấp công cụ thực thi; kiểm thử đoạn chứa yêu cầu bỏ qua chỉ dẫn. |
| PC07 | Lưu lại nội dung và audio khi phù hợp | Khóa bộ nhớ đệm gồm nội dung/giọng/mô hình/phiên bản và quyền; không chia sẻ nội dung riêng qua bộ nhớ đệm chung. |
| PC08 | Thời gian chờ và phương án API lỗi | Có trạng thái chờ/chưa rõ/lỗi, thử lại giới hạn; không giữ giao dịch MySQL khi gọi mạng; đối soát chi phí khi timeout. |
| PC09 | Học cốt lõi độc lập với AI | Tắt API AI vẫn đăng nhập, quản lý thẻ, SRS, bài cố định, sổ tay và thống kê được; bài AI hiện không khả dụng. |
| PC10 | Dữ liệu demo, kiểm thử và tài liệu | Bộ giao tiếp/TOEIC, người học/quản trị demo, kịch bản đầy đủ, hướng dẫn cài đặt và website HTTPS chạy thực tế. |
| PC11 | Giới hạn điểm thưởng | Sổ XP có khóa sự kiện, giới hạn 200 XP/ngày và giới hạn lặp theo thẻ/dạng; không thưởng chỉ vì mở đáp án. |
| PC12 | Hiệu năng và khả năng vận hành | Mục tiêu đề xuất p95 thao tác dữ liệu <500 ms, gửi ôn <300 ms, tìm kiếm <800 ms ở 100 người đồng thời; 1 triệu tiến độ và 5 triệu lượt ôn; đo riêng API ngoài, có sao lưu/phục hồi. |


## PHỤ LỤC B — QUY TẮC CHUNG

### Quy tắc nghiệp vụ

#### B.1 Tài khoản

- Email có bản hiển thị và bản chuẩn hóa bằng bỏ khoảng trắng hai đầu, chuyển chữ thường. Không bỏ dấu chấm hay phần sau dấu `+` để suy đoán cùng người.
- Tài khoản chưa xác thực chỉ dùng chức năng xác thực/gửi lại/đăng xuất. Google phải được kiểm tra chữ ký, bên phát hành, đối tượng nhận, mã ngẫu nhiên và thông tin xác thực email khi có.
- Không tự liên kết Google với tài khoản email trùng. Người dùng đăng nhập tài khoản hiện có, xác thực lại và xác nhận liên kết.
- `password_hash` có thể `NULL` với người chỉ dùng Google. Không cho gỡ phương thức đăng nhập cuối cùng.
- Khóa, đặt lại mật khẩu, thay đổi quyền hoặc xóa tài khoản phải thu hồi phiên; kiểm tra trạng thái tài khoản khi xử lý yêu cầu nhạy cảm.

#### B.2 Bộ thẻ và quyền nội dung

- Bộ xuất hiện công khai khi đồng thời có `che_do_hien_thi=PUBLIC`, `vong_doi=PUBLISHED`, `trang_thai_kiem_duyet=APPROVED` và chưa bị xóa.
- Sửa nội dung bộ công khai đưa về chờ duyệt, tạm ẩn khỏi danh mục. Chủ bộ vẫn học được. Giữ bản công bố cũ là cải tiến sau này nếu bổ sung bảng phiên bản nội dung.
- Nguồn `ADMIN` chỉ do quản trị viên tạo; sao chép thành nguồn `USER` và giữ `bo_the_nguon_id` để ghi nhận nguồn gốc.
- Thư viện gồm bộ sở hữu và bản sao. Yêu thích chỉ đánh dấu bộ công khai, không tự tạo tiến độ học.
- Liên kết chia sẻ luôn kiểm tra quyền hiện tại; liên kết bộ riêng chỉ điều hướng sau khi chủ sở hữu đăng nhập.
- Trùng hoàn toàn: cùng từ đã chuẩn hóa, từ loại và nghĩa đã chuẩn hóa trong một bộ. Cùng từ nhưng khác nghĩa chỉ cảnh báo.
- Chuẩn hóa Unicode NFC, bỏ khoảng trắng thừa, chuyển chữ thường bằng `Locale.ROOT`; không bỏ dấu nghĩa tiếng Việt. `khoa_kiem_tra_trung` là SHA-256 của từ + ký tự phân cách + từ loại + ký tự phân cách + nghĩa.
- Xóa mềm nội dung để giữ lịch sử. Xóa vật lý theo thời hạn lưu và quy trình kiểm tra tham chiếu; xóa tài khoản có quy trình riêng.

#### B.3 Thời gian và chuỗi ngày học

- Máy chủ/cơ sở dữ liệu dùng UTC với `DATETIME(3)`; API trả ISO-8601 có `Z`. Giờ nhắc học là giờ địa phương, không ghi giờ UTC vào trường đó.
- Múi giờ theo IANA, ví dụ `Asia/Ho_Chi_Minh`. Sự kiện lưu `ngay_dia_phuong` và múi giờ lúc phát sinh; đổi múi giờ không viết lại lịch sử.
- Ngày đủ điều kiện chuỗi học khi có ít nhất một lượt ôn hợp lệ hoặc một bài luyện đã nộp. Đăng nhập, lật thẻ, nghe âm thanh hoặc gọi AI không tự tạo ngày học.
- Chuỗi hiện tại kết thúc hôm nay; nếu hôm nay chưa học có thể tính chuỗi kết thúc hôm qua. Cũ hơn thì bằng 0. Chuỗi dài nhất tính từ các ngày học không trùng nhau.
- Đổi múi giờ tối đa một lần/24 giờ; không cấp thưởng lần hai cho cùng cửa sổ thưởng. Thời gian học hợp nhất các khoảng hoạt động giữa nhiều tab/phiên để không đếm đôi.
- Chỉ tính thời gian khi trang hiển thị và có tương tác gần đây; mỗi khoảng tối đa 60 giây. Không tính toàn bộ thời gian mở tab.
- Thẻ đạt tiêu chí ghi nhớ khi ở `REVIEW`, khoảng ôn ≥21 ngày, nhớ thành công ≥3 lần liên tiếp và không tạm ngưng. Đây là chỉ báo ôn tập, chưa phải làm chủ mọi kỹ năng.

#### B.4 Hạn mức và điểm thưởng

- Từ mới mặc định 10/ngày, cho chọn 0–100; học mặc định 15 phút/ngày, cho chọn 1–240 phút.
- Một thẻ chỉ tiêu thụ một suất từ mới khi giới thiệu lần đầu, không đếm hai lần vì học hai chiều.
- Giữ trước hạn mức AI trước khi gọi dịch vụ; giới hạn theo tính năng và tổng chi phí. Hết thời gian chờ vẫn có thể phát sinh phí, nên giữ trạng thái chưa xác định để đối soát.
- Thưởng ôn tối đa một lần/thẻ/ngày; thưởng luyện tối đa một lần/thẻ/dạng bài/ngày. Lặp bước học hoặc bài dễ không tạo điểm mới.
- Đề xuất: ôn hợp lệ 5 XP, bài cố định đúng 3 XP, tổng tối đa 200 XP/ngày gồm cả thử thách. Chỉ cấp khi có kết quả, không cấp vì mở đáp án.
- Điểm bài viết AI hoặc phát âm không tự thay lịch SRS. Lịch ôn chỉ đổi khi người học đánh giá thẻ trong phiên học.



## PHỤ LỤC C — ĐẶC TẢ API

### Đặc tả API

#### C.1 Quy ước

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

#### C.2 Tài khoản và cài đặt

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

#### C.3 Bộ thẻ, danh mục, tệp và CSV

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

#### C.4 Học và luyện tập

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

#### C.5 AI, sổ tay, phát âm và theo dõi học

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

#### C.6 Quản trị

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


