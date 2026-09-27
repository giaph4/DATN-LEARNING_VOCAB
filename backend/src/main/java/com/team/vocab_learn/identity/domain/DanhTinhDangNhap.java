package com.team.vocab_learn.identity.domain;

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
public class DanhTinhDangNhap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long nguoiDungId;

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private NhaCungCapDangNhap nhaCungCap;

	/** "sub" của Google: định danh bất biến, không phải email */
	private String dinhDanhNhaCungCap;

	private String emailNhaCungCap;

	private Instant thoiDiemLienKet;

	public static DanhTinhDangNhap link(Long nguoiDungId, NhaCungCapDangNhap nhaCungCap, String dinhDanh, String email,
			Instant now) {
		DanhTinhDangNhap d = new DanhTinhDangNhap();
		d.nguoiDungId = nguoiDungId;
		d.nhaCungCap = nhaCungCap;
		d.dinhDanhNhaCungCap = dinhDanh;
		d.emailNhaCungCap = email;
		d.thoiDiemLienKet = now;
		return d;
	}
}
