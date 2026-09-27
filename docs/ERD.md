# ERD — VocabLearning (67 bảng, theo `V1__schema.sql`)

Chia 7 nhóm để render được. Bảng thuộc nhóm khác chỉ hiện tên (không cột).
Ký hiệu: `||--o{` 1–n bắt buộc · `|o--o{` 1–n, FK cho phép NULL · `||--o|` 1–1 · `..` liên kết logic (không có FK).

## 1. Tài khoản & tệp

```mermaid
erDiagram
    nguoi_dung {
        bigint id PK
        varchar email
        varchar email_chuan_hoa UK
        varchar password_hash
        varchar trang_thai "PENDING_VERIFICATION|ACTIVE|LOCKED|PENDING_DELETION"
        datetime thoi_diem_xac_thuc_email
        datetime thoi_diem_dang_nhap_gan_nhat
        bigint phien_ban_xac_thuc
        varchar ly_do_khoa
        datetime created_at
        datetime updated_at
    }
    vai_tro {
        bigint id PK
        varchar ma UK
        varchar mo_ta
    }
    nguoi_dung_vai_tro {
        bigint nguoi_dung_id PK, FK
        bigint vai_tro_id PK, FK
    }
    danh_tinh_dang_nhap {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,nha_cung_cap)"
        varchar nha_cung_cap "GOOGLE|FACEBOOK"
        varchar dinh_danh_nha_cung_cap "UK(nha_cung_cap,dinh_danh)"
        varchar email_nha_cung_cap
        datetime thoi_diem_lien_ket
    }
    ma_xac_thuc {
        bigint id PK
        bigint nguoi_dung_id FK
        varchar muc_dich "VERIFY_EMAIL|RESET_PASSWORD|LINK_IDENTITY|DELETE_CONFIRM"
        binary ma_xac_thuc_bam UK
        datetime thoi_diem_het_han
        datetime thoi_diem_da_su_dung
        datetime created_at
    }
    tep_da_phuong_tien {
        bigint id PK
        bigint chu_so_huu_id FK
        varchar khoa_doi_tuong_luu_tru UK
        varchar mime_type
        varchar loai_tep "IMAGE|AUDIO"
        bigint kich_thuoc_byte
        int thoi_luong_ms
        binary ma_bam_noi_dung
        varchar pham_vi_truy_cap "PRIVATE|PUBLIC"
        varchar trang_thai "UPLOADING|PROCESSING|READY|REJECTED|DELETED"
        varchar nguon_goc_tep
        varchar ghi_chu_quyen_su_dung
        datetime created_at
        datetime thoi_diem_xoa
    }
    ho_so_nguoi_dung {
        bigint nguoi_dung_id PK, FK
        varchar ten_hien_thi
        varchar gioi_thieu
        bigint tep_anh_dai_dien_id FK
        varchar trinh_do_tu_danh_gia "A1..C2"
        varchar muc_tieu_hoc
        datetime thoi_diem_xong_thiet_lap
        datetime updated_at
    }
    tuy_chon_hoc {
        bigint nguoi_dung_id PK, FK
        smallint so_the_moi_moi_ngay "<= 100"
        smallint muc_tieu_phut_moi_ngay "1..240"
        varchar mui_gio
        datetime thoi_diem_doi_mui_gio
        time gio_nhac_dia_phuong
        boolean bat_nhac_hoc
        boolean dong_y_email_nhac_hoc
        boolean thong_bao_trong_ung_dung
        boolean thong_bao_muc_tieu
        datetime thoi_diem_dong_y_ai
        varchar chieu_hoc_mac_dinh "EN_VI|VI_EN"
        bigint phien_ban
        datetime updated_at
    }
    yeu_cau_xoa_tai_khoan {
        bigint id PK
        bigint nguoi_dung_id FK
        binary tham_chieu_doi_tuong
        varchar trang_thai_tai_khoan_truoc
        varchar trang_thai "REQUESTED|PROCESSING|COMPLETED|FAILED|CANCELLED"
        datetime thoi_diem_yeu_cau
        datetime thoi_diem_duoc_phep_xoa
        datetime thoi_diem_hoan_thanh
        varchar ma_loi_xu_ly
        json moc_xu_ly
    }

    nguoi_dung ||--o{ nguoi_dung_vai_tro : "có"
    vai_tro ||--o{ nguoi_dung_vai_tro : "gán cho"
    nguoi_dung ||--o{ danh_tinh_dang_nhap : "liên kết"
    nguoi_dung ||--o{ ma_xac_thuc : "nhận mã"
    nguoi_dung |o--o{ tep_da_phuong_tien : "sở hữu"
    nguoi_dung ||--o| ho_so_nguoi_dung : "hồ sơ"
    tep_da_phuong_tien |o--o{ ho_so_nguoi_dung : "ảnh đại diện"
    nguoi_dung ||--o| tuy_chon_hoc : "tùy chọn"
    nguoi_dung |o--o{ yeu_cau_xoa_tai_khoan : "yêu cầu xoá"
```

## 2. Nội dung: chủ đề, bộ thẻ, thẻ, nhập CSV

```mermaid
erDiagram
    chu_de {
        bigint id PK
        bigint cha_id FK
        varchar slug UK
        varchar ten
        text mo_ta
        boolean dang_su_dung
    }
    chu_de_quan_tam {
        bigint nguoi_dung_id PK, FK
        bigint chu_de_id PK, FK
    }
    bo_the {
        bigint id PK
        bigint chu_so_huu_id FK
        bigint bo_the_nguon_id FK
        varchar tieu_de
        text mo_ta "FULLTEXT(tieu_de,mo_ta)"
        varchar loai_nguon "USER|ADMIN"
        varchar phan_loai "GENERAL|COMMUNICATION|TOEIC|IELTS|CHILDREN"
        varchar trinh_do "A1..C2"
        varchar che_do_hien_thi "PRIVATE|PUBLIC"
        varchar vong_doi "DRAFT|PUBLISHED|ARCHIVED"
        varchar trang_thai_kiem_duyet "NOT_REQUIRED|PENDING|APPROVED|REJECTED|HIDDEN"
        bigint tep_anh_bia_id FK
        int so_the
        bigint phien_ban
        datetime created_at
        datetime updated_at
        datetime thoi_diem_xoa
    }
    bo_the_chu_de {
        bigint bo_the_id PK, FK
        bigint chu_de_id PK, FK
    }
    nhan {
        bigint id PK
        bigint bo_the_id FK
        varchar ten
        varchar ten_chuan_hoa "UK(bo_the_id,ten_chuan_hoa)"
    }
    the {
        bigint id PK
        bigint bo_the_id FK
        bigint the_nguon_id FK
        varchar tu_cum_tu
        varchar tu_cum_tu_chuan_hoa
        varchar tu_loai
        varchar nghia_tieng_viet
        varchar ipa
        text meo_ghi_nho
        tinyint do_kho "1..5"
        int thu_tu
        varchar nguon_tao "MANUAL|CSV|AI|COPY|ADMIN"
        binary khoa_kiem_tra_trung
        datetime thoi_diem_xoa
        binary khoa_trung_dang_su_dung "generated; UK(bo_the_id, cot nay)"
        bigint phien_ban
        datetime created_at
        datetime updated_at
    }
    the_nhan {
        bigint the_id PK, FK
        bigint nhan_id PK, FK
    }
    vi_du_cua_the {
        bigint id PK
        bigint the_id FK
        text cau_tieng_anh
        text ban_dich_tieng_viet
        int vi_tri_bat_dau_cho_trong
        int vi_tri_ket_thuc_cho_trong
        int thu_tu
    }
    tep_cua_the {
        bigint the_id PK, FK
        bigint tep_id PK, FK
        varchar muc_dich_su_dung PK "IMAGE|WORD_AUDIO"
        varchar giong_doc
    }
    tep_cua_vi_du {
        bigint vi_du_id PK, FK
        bigint tep_id PK, FK
    }
    bo_the_yeu_thich {
        bigint nguoi_dung_id PK, FK
        bigint bo_the_id PK, FK
        datetime created_at
    }
    lien_ket_chia_se_bo_the {
        bigint id PK
        bigint bo_the_id FK
        binary ma_xac_thuc_bam UK
        datetime thoi_diem_het_han
        datetime thoi_diem_thu_hoi
        datetime created_at
    }
    lan_nhap_du_lieu {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint bo_the_id FK
        varchar trang_thai
        varchar khoa_tep_luu_tru
        binary ma_bam_tep
        bigint phien_ban_bo_the_mong_doi
        int tong_so_dong
        int so_dong_hop_le
        int so_dong_loi
        int so_dong_da_nhap
        datetime created_at
        datetime thoi_diem_het_han
    }
    dong_nhap_du_lieu {
        bigint lan_nhap_id PK, FK
        int so_dong PK
        json du_lieu
        binary khoa_kiem_tra_trung
        varchar trang_thai
        json danh_sach_loi
    }

    chu_de |o--o{ chu_de : "chủ đề cha"
    nguoi_dung ||--o{ chu_de_quan_tam : "quan tâm"
    chu_de ||--o{ chu_de_quan_tam : ""
    nguoi_dung ||--o{ bo_the : "sở hữu"
    bo_the |o--o{ bo_the : "sao chép từ"
    tep_da_phuong_tien |o--o{ bo_the : "ảnh bìa"
    bo_the ||--o{ bo_the_chu_de : ""
    chu_de ||--o{ bo_the_chu_de : ""
    bo_the ||--o{ nhan : "có nhãn"
    bo_the ||--o{ the : "chứa"
    the |o--o{ the : "sao chép từ"
    the ||--o{ the_nhan : ""
    nhan ||--o{ the_nhan : ""
    the ||--o{ vi_du_cua_the : "ví dụ"
    the ||--o{ tep_cua_the : ""
    tep_da_phuong_tien ||--o{ tep_cua_the : ""
    vi_du_cua_the ||--o{ tep_cua_vi_du : ""
    tep_da_phuong_tien ||--o{ tep_cua_vi_du : ""
    nguoi_dung ||--o{ bo_the_yeu_thich : "yêu thích"
    bo_the ||--o{ bo_the_yeu_thich : ""
    bo_the ||--o{ lien_ket_chia_se_bo_the : "chia sẻ"
    nguoi_dung ||--o{ lan_nhap_du_lieu : "nhập"
    bo_the ||--o{ lan_nhap_du_lieu : "vào"
    lan_nhap_du_lieu ||--o{ dong_nhap_du_lieu : "dòng"
```

## 3. Học & ôn tập (SRS)

```mermaid
erDiagram
    han_muc_hoc_hang_ngay {
        bigint nguoi_dung_id PK, FK
        date ngay_dia_phuong PK
        varchar mui_gio
        smallint gioi_han_chot_dau_ngay
        smallint so_the_da_gioi_thieu
        smallint so_suat_giu_truoc
        bigint phien_ban
    }
    the_da_gioi_thieu {
        bigint nguoi_dung_id PK, FK
        bigint the_id PK, FK
        datetime thoi_diem_gioi_thieu_dau
        date ngay_dia_phuong
    }
    phien_hoc {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint bo_the_id FK
        varchar chieu_hoc "EN_VI|VI_EN"
        varchar che_do
        int quy_thoi_gian_giay
        varchar trang_thai "ACTIVE|COMPLETED|ABANDONED"
        varchar mui_gio
        date ngay_dia_phuong
        smallint so_suat_the_moi_giu_truoc
        datetime thoi_diem_het_giu_suat
        datetime thoi_diem_bat_dau
        datetime thoi_diem_hoan_thanh
        int so_giay_hoat_dong
    }
    tien_do_hoc_the {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,the_id,chieu_hoc)"
        bigint the_id FK
        varchar chieu_hoc "EN_VI|VI_EN"
        varchar trang_thai_hoc "NEW|LEARNING|REVIEW|RELEARNING"
        datetime thoi_diem_den_han
        int khoang_on_ngay
        decimal he_so_de_nho ">= 1.3"
        int so_lan_on_thanh_cong
        int so_lan_nho_lien_tiep
        int so_lan_quen
        smallint buoc_hoc
        boolean tam_ngung
        varchar phien_ban_lap_lich
        int giai_doan_tien_do
        datetime thoi_diem_hoc_dau
        datetime thoi_diem_on_gan_nhat
        bigint phien_ban
    }
    muc_the_trong_phien {
        bigint id PK
        bigint phien_hoc_id FK "UK(phien_hoc_id,thu_tu_hang_doi)"
        bigint tien_do_id FK
        int thu_tu_hang_doi
        bigint phien_ban_the
        json noi_dung_luc_hoc
        varchar trang_thai
        boolean giu_suat_the_moi
        datetime thoi_diem_tra_suat
    }
    lich_su_on_tap {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,khoa_su_kien)"
        bigint tien_do_id FK
        bigint muc_the_trong_phien_id FK, UK
        varchar khoa_su_kien
        tinyint muc_danh_gia "1..4"
        json trang_thai_truoc
        json trang_thai_sau
        varchar phien_ban_lap_lich
        int giai_doan_tien_do
        datetime thoi_diem_on
        date ngay_dia_phuong
        varchar mui_gio
        int thoi_gian_tra_loi_ms
    }

    nguoi_dung ||--o{ han_muc_hoc_hang_ngay : "hạn mức/ngày"
    nguoi_dung ||--o{ the_da_gioi_thieu : ""
    the ||--o{ the_da_gioi_thieu : ""
    nguoi_dung ||--o{ phien_hoc : "học"
    bo_the |o--o{ phien_hoc : "phạm vi"
    nguoi_dung ||--o{ tien_do_hoc_the : "tiến độ"
    the ||--o{ tien_do_hoc_the : ""
    phien_hoc ||--o{ muc_the_trong_phien : "hàng đợi"
    tien_do_hoc_the ||--o{ muc_the_trong_phien : ""
    nguoi_dung ||--o{ lich_su_on_tap : ""
    tien_do_hoc_the ||--o{ lich_su_on_tap : "lịch sử"
    muc_the_trong_phien ||--o| lich_su_on_tap : "chấm 1 lần"
```

## 4. Luyện tập & phân tích học tập

```mermaid
erDiagram
    de_luyen_tap {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint bo_the_id FK
        varchar tieu_de
        varchar loai_nguon
        varchar trang_thai
        varchar phien_ban_tao_de
        datetime created_at
        datetime thoi_diem_xoa
    }
    cau_hoi_luyen_tap {
        bigint id PK
        bigint de_luyen_tap_id FK "UK(de_luyen_tap_id,thu_tu)"
        bigint the_id FK
        varchar loai_cau_hoi
        varchar ky_nang
        json noi_dung_cau_hoi
        json dap_an_cham
        text giai_thich_tieng_viet
        varchar cach_cham "FIXED|AI"
        decimal diem_toi_da
        int thu_tu
    }
    lan_lam_bai {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint de_luyen_tap_id FK
        bigint lan_lam_bai_goc_id FK
        varchar trang_thai
        decimal diem_co_dinh
        decimal diem_co_dinh_toi_da
        decimal diem_ai
        datetime thoi_diem_bat_dau
        datetime thoi_diem_nop
        int so_giay_hoat_dong
        bigint phien_ban
    }
    cau_tra_loi {
        bigint id PK
        bigint lan_thuc_hien_id FK "UK(lan_thuc_hien_id,cau_hoi_id)"
        bigint cau_hoi_id FK
        json noi_dung_tra_loi
        boolean tra_loi_dung
        decimal diem
        text phan_hoi_tieng_viet
        varchar trang_thai_cham
        varchar phien_ban_cham
        bigint yeu_cau_ai_id FK
        datetime thoi_diem_tra_loi
        int thoi_gian_tra_loi_ms
    }
    muc_so_tay {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,the_id)"
        bigint the_id FK
        varchar loai_nguon
        varchar trang_thai
        text ghi_chu
        json nhom_loi
        datetime created_at
        datetime updated_at
    }
    loi_hoc_tap {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,khoa_loai_trung)"
        bigint the_id FK
        varchar phan_loai "MEANING|SPELLING|LISTENING|USAGE"
        bigint cau_tra_loi_id FK
        bigint lich_su_on_id FK
        varchar khoa_loai_trung
        json minh_chung
        datetime thoi_diem_phat_sinh
    }
    muc_do_lam_chu_tu {
        bigint nguoi_dung_id PK, FK
        bigint the_id PK, FK
        varchar ky_nang PK
        int so_mau_minh_chung
        decimal trong_so_thanh_cong
        decimal trong_so_that_bai
        decimal diem_lam_chu "0..100"
        datetime thoi_diem_tinh_toan
        varchar phien_ban_thuat_toan
    }
    cap_tu_de_nham {
        bigint nguoi_dung_id PK, FK
        bigint the_a_id PK, FK "the_a_id < the_b_id"
        bigint the_b_id PK, FK
        int so_lan_nham
        datetime thoi_diem_nham_gan_nhat
        text giai_thich_tieng_viet
    }
    goi_y_hoc_tap {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint the_id FK
        bigint bo_the_id FK
        varchar loai_goi_y
        decimal do_uu_tien
        varchar ma_ly_do
        json du_lieu_ly_do
        varchar phien_ban_thuat_toan
        varchar trang_thai
        datetime created_at
        datetime thoi_diem_het_han
    }

    nguoi_dung ||--o{ de_luyen_tap : "tạo"
    bo_the |o--o{ de_luyen_tap : "từ"
    de_luyen_tap ||--o{ cau_hoi_luyen_tap : "câu hỏi"
    the |o--o{ cau_hoi_luyen_tap : ""
    nguoi_dung ||--o{ lan_lam_bai : "làm"
    de_luyen_tap ||--o{ lan_lam_bai : ""
    lan_lam_bai |o--o{ lan_lam_bai : "làm lại từ"
    lan_lam_bai ||--o{ cau_tra_loi : "trả lời"
    cau_hoi_luyen_tap ||--o{ cau_tra_loi : ""
    yeu_cau_ai |o--o{ cau_tra_loi : "chấm AI"
    nguoi_dung ||--o{ muc_so_tay : "sổ tay"
    the ||--o{ muc_so_tay : ""
    nguoi_dung ||--o{ loi_hoc_tap : "lỗi"
    the ||--o{ loi_hoc_tap : ""
    cau_tra_loi |o--o{ loi_hoc_tap : "nguồn"
    lich_su_on_tap |o--o{ loi_hoc_tap : "nguồn"
    nguoi_dung ||--o{ muc_do_lam_chu_tu : ""
    the ||--o{ muc_do_lam_chu_tu : ""
    nguoi_dung ||--o{ cap_tu_de_nham : ""
    the ||--o{ cap_tu_de_nham : "the_a"
    the ||--o{ cap_tu_de_nham : "the_b"
    nguoi_dung ||--o{ goi_y_hoc_tap : "gợi ý"
    the |o--o{ goi_y_hoc_tap : ""
    bo_the |o--o{ goi_y_hoc_tap : ""
```

## 5. AI & phát âm

```mermaid
erDiagram
    chinh_sach_ai {
        bigint id PK
        varchar tinh_nang UK
        boolean duoc_bat
        varchar nha_cung_cap
        varchar ten_mo_hinh
        int gioi_han_luot_moi_ngay
        int gioi_han_token_moi_ngay
        int gioi_han_giay_am_thanh_moi_ngay
        decimal gioi_han_chi_phi_usd_moi_ngay
        int so_ky_tu_dau_vao_toi_da
        int so_token_dau_ra_toi_da
        smallint so_giay_am_thanh_toi_da
        smallint thoi_gian_cho_giay
        varchar phien_ban_chi_dan_ai
        varchar phien_ban_cau_truc
        bigint phien_ban
        datetime updated_at
    }
    han_muc_su_dung_ai {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,tinh_nang,ngay_tinh_han_muc)"
        varchar tinh_nang
        date ngay_tinh_han_muc
        int so_luot_da_dung
        int so_luot_giu_truoc
        bigint so_token_da_dung
        bigint so_token_giu_truoc
        decimal so_giay_am_thanh_da_dung
        decimal so_giay_am_thanh_giu_truoc
        decimal chi_phi_usd_da_dung
        decimal chi_phi_usd_giu_truoc
        bigint phien_ban
    }
    yeu_cau_ai {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint bo_the_id FK
        varchar tinh_nang
        varchar trang_thai "QUEUED|RUNNING|SUCCEEDED|FAILED|CANCELLED"
        json du_lieu_dau_vao
        binary ma_bam_dau_vao
        json du_lieu_dau_ra
        json chinh_sach_luc_tao
        varchar nha_cung_cap
        varchar ten_mo_hinh
        varchar phien_ban_chi_dan_ai
        varchar phien_ban_cau_truc
        varchar ma_loi_xu_ly
        datetime created_at
        datetime thoi_diem_hoan_thanh
        datetime thoi_diem_het_han
    }
    han_muc_ai_giu_truoc {
        bigint id PK
        bigint yeu_cau_ai_id FK, UK
        bigint han_muc_id FK
        int so_token_giu_truoc
        decimal so_giay_am_thanh_giu_truoc
        decimal chi_phi_usd_giu_truoc
        varchar trang_thai "RESERVED|SETTLED|RELEASED|UNCERTAIN"
        datetime created_at
        datetime thoi_diem_doi_soat
    }
    the_nhap_ai {
        bigint id PK
        bigint yeu_cau_ai_id FK "UK(yeu_cau_ai_id,thu_tu)"
        int thu_tu
        json noi_dung_the_nhap
        json noi_dung_da_sua
        json loi_kiem_tra
        varchar trang_thai "PENDING|ACCEPTED|REJECTED"
        bigint the_da_luu_id FK
        bigint phien_ban
    }
    phan_hoi_ai {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,yeu_cau_ai_id,loai_phan_hoi)"
        bigint yeu_cau_ai_id FK
        varchar loai_phan_hoi
        text chi_tiet
        varchar trang_thai_kiem_duyet
        datetime created_at
    }
    cuoc_hoi_thoai_ai {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint chu_de_id FK
        varchar tieu_de
        datetime created_at
        datetime thoi_diem_xoa
    }
    tin_nhan_ai {
        bigint id PK
        bigint cuoc_hoi_thoai_id FK
        bigint yeu_cau_ai_id FK
        varchar vai_tro "USER|ASSISTANT"
        text noi_dung
        datetime created_at
    }
    luot_goi_api {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint yeu_cau_ai_id FK
        varchar loai_dich_vu
        varchar tham_chieu_thao_tac
        varchar nha_cung_cap
        varchar ten_mo_hinh
        varchar yeu_cau_nha_cung_cap_id
        smallint thu_tu_lan_goi
        varchar trang_thai
        int so_token_dau_vao
        int so_token_dau_ra
        decimal so_giay_am_thanh
        decimal chi_phi_uoc_tinh_usd
        decimal chi_phi_thuc_tra_usd
        json don_gia_luc_goi
        varchar ma_loi
        int do_tre_ms
        datetime thoi_diem_bat_dau
        datetime thoi_diem_hoan_thanh
    }
    lan_danh_gia_phat_am {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint yeu_cau_ai_id FK, UK
        bigint the_id FK
        bigint tep_ghi_am_id FK
        text van_ban_mau
        varchar ma_ngon_ngu_vung
        varchar nha_cung_cap
        varchar phien_ban_cau_hinh_dich_vu
        varchar trang_thai
        decimal diem_phat_am "0..100"
        decimal diem_chinh_xac
        decimal diem_troi_chay
        decimal diem_day_du
        decimal diem_ngu_dieu
        text van_ban_nhan_dang
        text phan_hoi_tieng_viet
        json ket_qua_goc
        varchar ma_loi_xu_ly
        datetime thoi_diem_xoa_ghi_am
        datetime created_at
        datetime thoi_diem_hoan_thanh
    }
    ket_qua_phat_am_tung_tu {
        bigint id PK
        bigint lan_thuc_hien_id FK "UK(lan_thuc_hien_id,thu_tu)"
        int thu_tu
        varchar tu
        decimal diem_chinh_xac
        varchar loai_loi
        int vi_tri_bat_dau_ms
        int thoi_luong_ms
        json ket_qua_am_vi
    }

    chinh_sach_ai ||..o{ yeu_cau_ai : "tinh_nang (logic)"
    nguoi_dung ||--o{ han_muc_su_dung_ai : "hạn mức AI"
    nguoi_dung ||--o{ yeu_cau_ai : "gửi"
    bo_the |o--o{ yeu_cau_ai : "ngữ cảnh"
    yeu_cau_ai ||--o| han_muc_ai_giu_truoc : "giữ trước"
    han_muc_su_dung_ai ||--o{ han_muc_ai_giu_truoc : ""
    yeu_cau_ai ||--o{ the_nhap_ai : "thẻ nháp"
    the |o--o{ the_nhap_ai : "đã lưu thành"
    nguoi_dung ||--o{ phan_hoi_ai : ""
    yeu_cau_ai ||--o{ phan_hoi_ai : "phản hồi"
    nguoi_dung ||--o{ cuoc_hoi_thoai_ai : "trò chuyện"
    chu_de |o--o{ cuoc_hoi_thoai_ai : ""
    cuoc_hoi_thoai_ai ||--o{ tin_nhan_ai : "tin nhắn"
    yeu_cau_ai |o--o{ tin_nhan_ai : ""
    nguoi_dung |o--o{ luot_goi_api : ""
    yeu_cau_ai |o--o{ luot_goi_api : "lượt gọi"
    nguoi_dung ||--o{ lan_danh_gia_phat_am : "luyện phát âm"
    yeu_cau_ai ||--o| lan_danh_gia_phat_am : ""
    the |o--o{ lan_danh_gia_phat_am : ""
    tep_da_phuong_tien |o--o{ lan_danh_gia_phat_am : "ghi âm"
    lan_danh_gia_phat_am ||--o{ ket_qua_phat_am_tung_tu : "từng từ"
```

## 6. Thống kê, thành tích, thông báo

```mermaid
erDiagram
    su_kien_hoat_dong {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,khoa_loai_trung)"
        varchar loai_su_kien
        varchar khoa_loai_trung
        bigint bo_the_id FK
        bigint the_id FK
        datetime thoi_diem_phat_sinh
        date ngay_dia_phuong
        varchar mui_gio
        json du_lieu
    }
    thong_ke_hang_ngay {
        bigint nguoi_dung_id PK, FK
        date ngay_dia_phuong PK
        int so_the_moi
        int so_luot_on
        int so_cau_dung_co_dinh
        int so_cau_cham_co_dinh
        int so_giay_hoat_dong
        int diem_kinh_nghiem_da_nhan
        smallint muc_tieu_the_moi_luc_chot
        smallint muc_tieu_phut_luc_chot
        boolean du_dieu_kien_chuoi_ngay
        boolean da_hoan_thanh_muc_tieu
        bigint su_kien_gan_nhat_id
        varchar phien_ban_tong_hop
        datetime updated_at
    }
    bao_cao_hang_tuan {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,ngay_dau_tuan)"
        date ngay_dau_tuan
        varchar mui_gio
        json noi_dung_bao_cao
        datetime thoi_diem_tao_bao_cao
    }
    so_diem_kinh_nghiem {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,khoa_thuong)"
        varchar khoa_thuong
        varchar loai_nguon
        varchar tham_chieu_nguon
        smallint diem
        date ngay_dia_phuong
        varchar phien_ban_chinh_sach
        datetime thoi_diem_nhan_thuong
    }
    huy_hieu {
        bigint id PK
        varchar ma UK
        varchar tieu_de
        varchar mo_ta
        json quy_tac
        boolean dang_su_dung
    }
    huy_hieu_nguoi_dung {
        bigint nguoi_dung_id PK, FK
        bigint huy_hieu_id PK, FK
        datetime thoi_diem_nhan_thuong
        json minh_chung
    }
    mau_thu_thach {
        bigint id PK
        varchar ma UK
        varchar tieu_de
        varchar chu_ky "DAILY|WEEKLY"
        varchar chi_so
        int gia_tri_muc_tieu
        smallint diem_kinh_nghiem_thuong
        boolean dang_su_dung
    }
    ky_thu_thach {
        bigint id PK
        bigint mau_thu_thach_id FK "UK(mau_thu_thach_id,ngay_bat_dau_ky)"
        date ngay_bat_dau_ky
        date ngay_ket_thuc_ky
        json quy_tac_luc_tao
    }
    tien_do_thu_thach {
        bigint nguoi_dung_id PK, FK
        bigint ky_thu_thach_id PK, FK
        int gia_tri_hien_tai
        datetime thoi_diem_hoan_thanh
        datetime thoi_diem_cap_thuong
        bigint su_kien_gan_nhat_id
    }
    thong_bao {
        bigint id PK
        bigint nguoi_dung_id FK "UK(nguoi_dung_id,khoa_loai_trung)"
        varchar loai_thong_bao
        varchar khoa_loai_trung
        varchar tieu_de
        text noi_dung
        varchar duong_dan_hanh_dong
        datetime thoi_diem_da_doc
        datetime created_at
    }
    luot_gui_thong_bao {
        bigint id PK
        bigint nguoi_dung_id FK
        bigint thong_bao_id FK
        varchar kenh_gui
        varchar khoa_gui_thong_bao UK
        varchar trang_thai
        datetime thoi_diem_hen_gui
        datetime thoi_diem_gui
        smallint so_lan_thu
        varchar tin_nhan_nha_cung_cap_id
        varchar ma_loi_xu_ly
    }

    nguoi_dung ||--o{ su_kien_hoat_dong : "hoạt động"
    bo_the |o--o{ su_kien_hoat_dong : ""
    the |o--o{ su_kien_hoat_dong : ""
    nguoi_dung ||--o{ thong_ke_hang_ngay : "thống kê ngày"
    su_kien_hoat_dong ||..o{ thong_ke_hang_ngay : "su_kien_gan_nhat_id (logic)"
    nguoi_dung ||--o{ bao_cao_hang_tuan : "báo cáo tuần"
    nguoi_dung ||--o{ so_diem_kinh_nghiem : "XP"
    nguoi_dung ||--o{ huy_hieu_nguoi_dung : "đạt"
    huy_hieu ||--o{ huy_hieu_nguoi_dung : ""
    mau_thu_thach ||--o{ ky_thu_thach : "kỳ"
    nguoi_dung ||--o{ tien_do_thu_thach : ""
    ky_thu_thach ||--o{ tien_do_thu_thach : "tiến độ"
    nguoi_dung ||--o{ thong_bao : "nhận"
    nguoi_dung ||--o{ luot_gui_thong_bao : ""
    thong_bao |o--o{ luot_gui_thong_bao : "gửi qua kênh"
```

## 7. Quản trị & hạ tầng

```mermaid
erDiagram
    thong_bao_he_thong {
        bigint id PK
        bigint nguoi_tao_id FK
        varchar tieu_de
        text noi_dung
        varchar doi_tuong_nhan
        datetime thoi_diem_bat_dau
        datetime thoi_diem_ket_thuc
        varchar trang_thai
        bigint phien_ban
    }
    bao_cao_noi_dung {
        bigint id PK
        bigint nguoi_bao_cao_id FK
        bigint bo_the_id FK "đúng 1 trong 3 đích"
        bigint the_id FK
        bigint yeu_cau_ai_id FK
        varchar ly_do
        text chi_tiet
        json noi_dung_dich_luc_bao_cao
        varchar trang_thai
        bigint quan_tri_vien_phu_trach_id FK
        text ghi_chu_xu_ly
        datetime created_at
        datetime thoi_diem_xu_ly_xong
    }
    nhat_ky_quan_tri {
        bigint id PK
        bigint nguoi_thuc_hien_id FK
        varchar hanh_dong
        varchar loai_dich
        varchar tham_chieu_dich
        json du_lieu_truoc
        json du_lieu_sau
        varchar ma_truy_vet
        datetime thoi_diem_phat_sinh
    }
    cai_dat_he_thong {
        varchar khoa_cai_dat PK
        json gia_tri
        bigint phien_ban
        bigint nguoi_cap_nhat_id FK
        datetime updated_at
    }
    tac_vu_nen {
        bigint id PK
        varchar loai_tac_vu
        bigint chu_so_huu_id FK
        varchar khoa_loai_trung UK
        json du_lieu
        varchar trang_thai
        datetime thoi_diem_co_the_xu_ly
        varchar tien_trinh_nhan_xu_ly
        varchar ma_quyen_xu_ly
        datetime quyen_xu_ly_den_luc
        smallint so_lan_thu
        smallint so_lan_thu_toi_da
        varchar ma_loi_gan_nhat
        datetime created_at
        datetime thoi_diem_hoan_thanh
    }
    su_kien_cho_gui {
        bigint id PK
        varchar su_kien_id UK
        varchar loai_nhom_du_lieu
        varchar tham_chieu_nhom_du_lieu
        varchar loai_su_kien
        int phien_ban_cau_truc
        json du_lieu
        datetime thoi_diem_phat_sinh
        datetime thoi_diem_da_gui
        smallint so_lan_thu
        datetime thoi_diem_co_the_xu_ly
        varchar ma_quyen_xu_ly
        datetime quyen_xu_ly_den_luc
    }
    ban_ghi_chong_trung {
        bigint nguoi_dung_id PK, FK
        varchar thao_tac PK
        varchar khoa_chong_ghi_trung PK
        binary ma_bam_yeu_cau
        varchar trang_thai
        smallint ma_http_phan_hoi
        json du_lieu_phan_hoi
        varchar tham_chieu_tai_nguyen
        datetime created_at
        datetime thoi_diem_het_han
    }

    nguoi_dung |o--o{ thong_bao_he_thong : "tạo"
    nguoi_dung |o--o{ bao_cao_noi_dung : "báo cáo"
    nguoi_dung |o--o{ bao_cao_noi_dung : "phụ trách"
    bo_the |o--o{ bao_cao_noi_dung : ""
    the |o--o{ bao_cao_noi_dung : ""
    yeu_cau_ai |o--o{ bao_cao_noi_dung : ""
    nguoi_dung |o--o{ nhat_ky_quan_tri : "thực hiện"
    nguoi_dung |o--o{ cai_dat_he_thong : "cập nhật"
    nguoi_dung |o--o{ tac_vu_nen : "chủ tác vụ"
    nguoi_dung ||--o{ ban_ghi_chong_trung : "chống trùng"
```

`su_kien_cho_gui` (outbox) không có FK — liên kết qua `tham_chieu_nhom_du_lieu`.
