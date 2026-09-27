# Hướng dẫn chạy dự án VocabLearning

| Tài liệu | Dành cho | Nội dung |
|---|---|---|
| [01_DOCKER_BACKEND.md](01_DOCKER_BACKEND.md) | BE | Cách backend được đóng gói Docker, các file, lệnh, profile, biến môi trường, xử lý sự cố |
| [02_FE_CHAY_BACKEND.md](02_FE_CHAY_BACKEND.md) | FE | Chạy toàn bộ backend bằng **1 lệnh**, nối Next.js vào API, CSRF/cookie, lỗi thường gặp |
| [03_DOI_MAT_KHAU_DB.md](03_DOI_MAT_KHAU_DB.md) | BE | `backend/.env`, 1 user DB `vocab`, đổi mật khẩu, kết nối MySQL Workbench |

## Tóm tắt 10 giây

```bash
cd backend
docker compose --profile app up -d --build
```

| Dịch vụ | Địa chỉ trên máy bạn |
|---|---|
| API | http://localhost:8080/api/v1 |
| Swagger UI (danh sách API) | http://localhost:8080/swagger-ui.html |
| Tình trạng API | http://localhost:8080/actuator/health |
| Hộp thư test (Mailpit) | http://localhost:8025 |
| Kho tệp MinIO (console) | http://localhost:9001 — `minioadmin` / `minioadmin` |
| MySQL | `localhost:3307`, DB `vocab_learning`, user `vocab` / mật khẩu `DB_PASSWORD` trong `backend/.env` (mặc định `vocab_dev_pw`) |
| Redis | `localhost:6379` |
