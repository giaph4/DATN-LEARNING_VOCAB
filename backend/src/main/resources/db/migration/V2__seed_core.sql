-- Core seed data (roles, default settings, AI policies, root topics)

INSERT INTO vai_tro (ma, mo_ta) VALUES
  ('LEARNER', 'Người học'),
  ('ADMIN', 'Quản trị viên');

INSERT INTO cai_dat_he_thong (khoa_cai_dat, gia_tri) VALUES
  ('learning.defaults', JSON_OBJECT('newCardsPerDay', 10, 'dailyMinutes', 15, 'timezone', 'Asia/Ho_Chi_Minh', 'secondsPerCard', 20)),
  ('registration.enabled', JSON_OBJECT('value', TRUE)),
  ('ai.enabled', JSON_OBJECT('value', TRUE)),
  ('speech.enabled', JSON_OBJECT('value', TRUE));

INSERT INTO chinh_sach_ai (tinh_nang, nha_cung_cap, ten_mo_hinh,
  gioi_han_luot_moi_ngay, gioi_han_token_moi_ngay, gioi_han_giay_am_thanh_moi_ngay, gioi_han_chi_phi_usd_moi_ngay,
  so_ky_tu_dau_vao_toi_da, so_token_dau_ra_toi_da, so_giay_am_thanh_toi_da, thoi_gian_cho_giay,
  phien_ban_chi_dan_ai, phien_ban_cau_truc) VALUES
  ('CARD_GENERATION',  'OPENAI', 'gpt-4.1-mini', 5,   40000, NULL, 0.050000, 2000, 4000, NULL, 30, 'card-gen-v1', 'card-gen-schema-v1'),
  ('WRITING_FEEDBACK', 'OPENAI', 'gpt-4.1-mini', 10,  30000, NULL, 0.030000, 1500, 1500, NULL, 30, 'writing-v1',  'writing-schema-v1'),
  ('EXPLANATION',      'OPENAI', 'gpt-4.1-mini', 10,  20000, NULL, 0.020000, 1000, 1000, NULL, 20, 'explain-v1',  'explain-schema-v1'),
  ('PRONUNCIATION',    'AZURE_SPEECH', 'pronunciation-assessment', 300, 0, 300, 0.100000, 200, 0, 15, 20, 'pa-v1', 'pa-schema-v1'),
  ('TOTAL',            'ALL', 'all', 325, 90000, 300, 0.200000, 2000, 4000, 15, 30, 'total-v1', 'total-schema-v1');

INSERT INTO chu_de (slug, ten, mo_ta) VALUES
  ('giao-tiep',  'Giao tiếp hằng ngày', 'Từ vựng dùng trong đời sống hằng ngày'),
  ('du-lich',    'Du lịch',             'Sân bay, khách sạn, hỏi đường'),
  ('cong-viec',  'Công việc',           'Văn phòng, họp, email'),
  ('cong-nghe',  'Công nghệ',           'Máy tính, phần mềm, Internet'),
  ('hoc-thuat',  'Học thuật',           'Từ vựng học thuật chung'),
  ('suc-khoe',   'Sức khỏe',            'Cơ thể, bệnh, bệnh viện'),
  ('am-thuc',    'Ẩm thực',             'Món ăn, nấu ăn, nhà hàng');

INSERT INTO chu_de (cha_id, slug, ten, mo_ta)
SELECT id, 'san-bay', 'Sân bay', 'Làm thủ tục, hành lý' FROM chu_de WHERE slug = 'du-lich';
INSERT INTO chu_de (cha_id, slug, ten, mo_ta)
SELECT id, 'lap-trinh', 'Lập trình', 'Thuật ngữ lập trình cơ bản' FROM chu_de WHERE slug = 'cong-nghe';
