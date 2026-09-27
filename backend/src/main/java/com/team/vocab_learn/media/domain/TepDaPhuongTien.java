package com.team.vocab_learn.media.domain;

import java.time.Instant;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TepDaPhuongTien {

	/** Chưa có nội dung thật khi mới tạo ý định tải lên; cột NOT NULL nên ghi 32 byte 0. */
	private static final byte[] NO_HASH = new byte[32];

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long chuSoHuuId;

	private String khoaDoiTuongLuuTru;

	private String mimeType;

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private LoaiTep loaiTep;

	private long kichThuocByte;

	@JdbcTypeCode(SqlTypes.BINARY)
	private byte[] maBamNoiDung;

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private PhamViTruyCap phamViTruyCap;

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private TrangThaiTep trangThai;

	private String nguonGocTep;

	private Instant createdAt;

	private Instant thoiDiemXoa;

	public static TepDaPhuongTien startUpload(Long chuSoHuuId, String key, String mimeType, long size, Instant now) {
		TepDaPhuongTien tep = new TepDaPhuongTien();
		tep.chuSoHuuId = chuSoHuuId;
		tep.khoaDoiTuongLuuTru = key;
		tep.mimeType = mimeType;
		tep.loaiTep = LoaiTep.IMAGE;
		tep.kichThuocByte = size;
		tep.maBamNoiDung = NO_HASH;
		tep.phamViTruyCap = PhamViTruyCap.PRIVATE;
		tep.trangThai = TrangThaiTep.UPLOADING;
		tep.nguonGocTep = "USER_UPLOAD";
		tep.createdAt = now;
		return tep;
	}

	public boolean ownedBy(long nguoiDungId) {
		return chuSoHuuId != null && chuSoHuuId == nguoiDungId;
	}

	public void markReady(String realMimeType, long size, byte[] hash) {
		this.mimeType = realMimeType;
		this.kichThuocByte = size;
		this.maBamNoiDung = hash;
		this.trangThai = TrangThaiTep.READY;
	}

	public void reject() {
		this.trangThai = TrangThaiTep.REJECTED;
	}

	public void markDeleted(Instant now) {
		this.trangThai = TrangThaiTep.DELETED;
		this.thoiDiemXoa = now;
	}
}
