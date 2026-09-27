package com.team.vocab_learn.identity.domain;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HoSoNguoiDung {

	@Id
	private Long nguoiDungId;

	private String tenHienThi;

	private String gioiThieu;

	private Long tepAnhDaiDienId;

	private String trinhDoTuDanhGia;

	private String mucTieuHoc;

	private Instant thoiDiemXongThietLap;

	public static HoSoNguoiDung create(Long nguoiDungId, String tenHienThi) {
		HoSoNguoiDung hoSo = new HoSoNguoiDung();
		hoSo.nguoiDungId = nguoiDungId;
		hoSo.tenHienThi = tenHienThi;
		hoSo.trinhDoTuDanhGia = "A1";
		return hoSo;
	}

	public void rename(String tenHienThi) {
		this.tenHienThi = tenHienThi;
	}

	public void describe(String gioiThieu) {
		this.gioiThieu = gioiThieu;
	}

	public void assessLevel(String trinhDo) {
		this.trinhDoTuDanhGia = trinhDo;
	}

	public void setGoal(String mucTieu) {
		this.mucTieuHoc = mucTieu;
	}

	/** TK-07: chỉ ghi lần đầu, gửi lại không đổi mốc. */
	public void completeOnboarding(Instant now) {
		if (thoiDiemXongThietLap == null) {
			thoiDiemXongThietLap = now;
		}
	}

	/** Trả về ảnh cũ để kiểm tra tham chiếu và dọn tệp. */
	public Long changeAvatar(Long tepId) {
		Long old = this.tepAnhDaiDienId;
		this.tepAnhDaiDienId = tepId;
		return old;
	}
}
