package com.team.vocab_learn.identity.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Các cột còn lại lấy DEFAULT của DB (10 thẻ mới/ngày, 15 phút, Asia/Ho_Chi_Minh...). */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TuyChonHoc {

	@Id
	private Long nguoiDungId;

	// Long (không phải long): null = bản ghi mới → persist thẳng, không SELECT trước
	@Version
	private Long phienBan;

	public static TuyChonHoc defaultsFor(Long nguoiDungId) {
		TuyChonHoc tuyChon = new TuyChonHoc();
		tuyChon.nguoiDungId = nguoiDungId;
		return tuyChon;
	}
}
