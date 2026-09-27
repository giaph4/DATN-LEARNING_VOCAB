# VocabLearning

Website học từ vựng: backend Spring Boot 4 / Java 21, MySQL 8.4, Redis và MinIO. Frontend dự kiến dùng Next.js; hiện có yêu cầu thiết kế và mockup HTML.

## Chạy bằng Docker

Yêu cầu Docker Desktop đang chạy.

```powershell
Copy-Item backend/.env.example backend/.env
cd backend
docker compose --profile app up -d --build
```

- API: http://localhost:8080
- Kiểm tra sức khỏe: http://localhost:8080/actuator/health/readiness
- Swagger: http://localhost:8080/swagger-ui/index.html
- Mailpit: http://localhost:8025
- MinIO console: http://localhost:9001

Các giá trị mặc định trong `.env.example` chỉ dùng cho môi trường phát triển. Không commit file `.env` chứa cấu hình riêng.

## Tài liệu

- [Hướng dẫn chạy](huong-dan/)
- [Đặc tả và thiết kế](docs/)
- [Roadmap backend](roadmap/ROADMAP_BE.md)
- [Roadmap frontend](roadmap/ROADMAP_FE.md)
- [Báo cáo bàn giao](report/)
- [Mockup giao diện](mockups/)
- [Quy tắc làm việc](AGENTS.md)
