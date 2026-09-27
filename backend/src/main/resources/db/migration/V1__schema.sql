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
