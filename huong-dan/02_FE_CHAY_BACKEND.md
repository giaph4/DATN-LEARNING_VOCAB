# 02 — FE: chạy backend bằng Docker và nối Next.js

> FE **không cần** cài Java/Maven/MySQL. Chỉ cần **Docker Desktop** + Node.js.

## 1. Chạy backend (1 lệnh)

```bash
cd backend
docker compose --profile app up -d --build
```

- Lần đầu mất vài phút (tải image + build). Các lần sau chỉ vài giây.
- Chờ đến khi `api` báo **healthy**:
  ```bash
  docker compose --profile app ps
  ```
- Kiểm tra:
  - http://localhost:8080/actuator/health → `{"status":"UP"}`
  - http://localhost:8080/api/v1/topics → cây chủ đề (JSON)
  - http://localhost:8080/swagger-ui.html → danh sách API, gọi thử ngay trên trình duyệt

| Việc | Lệnh (trong `backend/`) |
|---|---|
| Lấy code BE mới → chạy bản mới | `git pull` rồi `docker compose --profile app up -d --build` |
| Xem log khi API lỗi | `docker compose logs -f api` |
| Tắt (giữ dữ liệu) | `docker compose --profile app down` |
| Xóa sạch dữ liệu, làm lại từ đầu | `docker compose --profile app down -v` |

Email xác thực, đặt lại mật khẩu… **không gửi thật** — mở http://localhost:8025 (Mailpit) để xem và bấm link.

## 2. Nối Next.js vào API (cùng origin → không lỗi CORS/cookie)

Trình duyệt chỉ gọi `http://localhost:3000/api/v1/...`; Next.js chuyển tiếp sang `http://localhost:8080`. Cookie phiên `VOCAB_SESSION` và `XSRF-TOKEN` vì thế thuộc `localhost:3000`.

**`frontend/.env.local`**
```dotenv
BACKEND_INTERNAL_URL=http://localhost:8080
NEXT_PUBLIC_API_BASE=/api/v1
```

**`frontend/next.config.mjs`**
```js
/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return [
      { source: '/api/:path*', destination: `${process.env.BACKEND_INTERNAL_URL}/api/:path*` },
    ];
  },
};

export default nextConfig;
```

## 3. Gọi API: CSRF, Idempotency-Key, lỗi

Quy tắc của BE (THIET_KE §11.1):

| Quy tắc | FE làm gì |
|---|---|
| Mọi POST/PUT/PATCH/DELETE cần header `X-CSRF-TOKEN` | Lấy giá trị cookie `XSRF-TOKEN` (hoặc gọi `GET /api/v1/auth/csrf`) rồi gửi kèm |
| Gọi fetch phải gửi cookie | `credentials: 'include'` |
| Ôn thẻ, nộp bài, sao chép, xác nhận CSV/nháp AI, tạo AI | Gửi `Idempotency-Key: <uuid>`; **giữ nguyên key khi bấm thử lại** |
| Sửa dữ liệu có phiên bản | Gửi `If-Match` = `ETag` nhận được lúc GET; 412 → tải lại |
| ID luôn là **chuỗi** | Không `Number(id)` |
| Lỗi luôn là `application/problem+json` | Đọc `code` (máy đọc), `detail` (hiển thị), `fieldErrors` (gắn vào ô nhập) |

**`frontend/src/lib/csrf.js`**
```js
const BASE = process.env.NEXT_PUBLIC_API_BASE ?? '/api/v1';

export function readCsrfCookie() {
  return document.cookie.split('; ').find((c) => c.startsWith('XSRF-TOKEN='))?.split('=')[1];
}

export async function fetchCsrfToken() {
  const res = await fetch(`${BASE}/auth/csrf`, { credentials: 'include' });
  const { token } = await res.json();
  return token;
}
```

**`frontend/src/lib/api-client.js`**
```js
import { fetchCsrfToken, readCsrfCookie } from './csrf';

const BASE = process.env.NEXT_PUBLIC_API_BASE ?? '/api/v1';
const WRITE = new Set(['POST', 'PUT', 'PATCH', 'DELETE']);

export class ApiError extends Error {
  constructor(problem) {
    super(problem.detail ?? problem.title);
    Object.assign(this, problem); // status, code, fieldErrors, retryable, traceId
  }
}

export async function api(path, { method = 'GET', body, idempotencyKey, ifMatch, retried } = {}) {
  const headers = { Accept: 'application/json' };
  if (body !== undefined) headers['Content-Type'] = 'application/json';
  if (WRITE.has(method)) headers['X-CSRF-TOKEN'] = readCsrfCookie() ?? (await fetchCsrfToken());
  if (idempotencyKey) headers['Idempotency-Key'] = idempotencyKey;
  if (ifMatch) headers['If-Match'] = ifMatch;

  const res = await fetch(`${BASE}${path}`, {
    method,
    headers,
    credentials: 'include',
    body: body === undefined ? undefined : JSON.stringify(body),
  });

  // Token CSRF đổi sau đăng nhập/đăng xuất → lấy lại và thử 1 lần.
  if (res.status === 403 && !retried) {
    const problem = await res.clone().json().catch(() => ({}));
    if (problem.code === 'CSRF_INVALID') {
      await fetchCsrfToken();
      return api(path, { method, body, idempotencyKey, ifMatch, retried: true });
    }
  }
  if (res.status === 204) return null;
  const data = await res.json().catch(() => null);
  if (!res.ok) throw new ApiError(data ?? { status: res.status, title: res.statusText });
  return data;
}
```

Ví dụ:
```js
const topics = await api('/topics');                                   // GET công khai
const key = crypto.randomUUID();                                       // tạo 1 lần cho 1 thao tác
await api('/study/sessions/123/reviews', { method: 'POST', body: { grade: 3 }, idempotencyKey: key });
```

## 4. Bảng mã lỗi FE cần xử lý

| HTTP | `code` | FE hiển thị / hành động |
|---|---|---|
| 400 | `VALIDATION_FAILED` | Gắn `fieldErrors[].message` vào từng ô |
| 401 | `UNAUTHENTICATED` | Chuyển `/dang-nhap?quay-lai=<trang hiện tại>` |
| 403 | `FORBIDDEN` | Trang "Không có quyền" |
| 403 | `CSRF_INVALID` | `api-client` tự lấy token và thử lại |
| 404 | `NOT_FOUND` | Trang/khối "Không tìm thấy" |
| 409 | `CONFLICT`, `IDEMPOTENCY_KEY_REUSED`, `REQUEST_IN_PROGRESS` | Thông báo `detail`; `REQUEST_IN_PROGRESS` thử lại sau |
| 412 | `STALE_VERSION` | "Dữ liệu đã thay đổi" + nút tải lại |
| 422 | `BUSINESS_RULE_VIOLATED` | Thông báo `detail` |
| 429 | `RATE_LIMITED`, `QUOTA_EXCEEDED` | Thông báo `detail` (có số giây/thời điểm mở lại) |
| 5xx | `INTERNAL_ERROR`, `DEPENDENCY_UNAVAILABLE` | "Có lỗi, thử lại" + hiện `traceId` để báo BE |

Khi báo lỗi cho BE: gửi **`traceId`** (trong JSON lỗi hoặc header `X-Trace-Id`) → BE tìm đúng dòng log bằng `docker compose logs api | grep <traceId>`.

## 5. Tải tệp (ảnh/âm thanh) — khi BE làm xong Đợt 1

1. `POST /api/v1/media/upload-intents` → nhận `url` (MinIO, `http://localhost:9000/...`) + `method: PUT`.
2. Trình duyệt `PUT` thẳng file lên `url` đó, header `Content-Type` đúng như đã khai báo (không kèm cookie).
3. `POST /api/v1/media/{id}/complete` → BE kiểm tra kích thước/MIME.
4. Hiển thị bằng URL ký sẵn BE trả về (hết hạn sau 5 phút → gọi lại để lấy URL mới).

## 6. Lỗi thường gặp

| Hiện tượng | Cách xử lý |
|---|---|
| `fetch failed` / `ECONNREFUSED` ở Next.js | API chưa chạy hoặc chưa healthy → `docker compose --profile app ps`, xem `logs -f api` |
| Luôn 401 dù đã đăng nhập | Thiếu `credentials: 'include'` hoặc gọi thẳng `http://localhost:8080` thay vì `/api/v1` qua rewrite |
| POST trả 403 `CSRF_INVALID` liên tục | Không gửi `X-CSRF-TOKEN` hoặc gửi giá trị khác cookie `XSRF-TOKEN` |
| Lỗi CORS | Đang gọi khác origin → dùng rewrite (mục 2). Nếu buộc gọi thẳng, thêm origin vào `APP_CORS_ALLOWED_ORIGINS` trong `backend/.env` |
| Cổng 8080 bị chiếm | `backend/.env`: `API_PORT=8081` và đổi `BACKEND_INTERNAL_URL=http://localhost:8081` |
| Dữ liệu lạ sau khi BE đổi migration | `docker compose --profile app down -v` rồi bật lại |
| API chưa có (BE chưa làm) | Bật MSW: `NEXT_PUBLIC_API_MOCK=1` (ROADMAP_FE F0.5) |
