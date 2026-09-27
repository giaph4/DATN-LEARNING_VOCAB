package com.team.vocab_learn.identity.infrastructure;

import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

/** Bảng nối chu_de_quan_tam (chỉ 2 khóa) — JdbcTemplate gọn hơn một entity khóa ghép. */
@Repository
@RequiredArgsConstructor
public class ChuDeQuanTamStore {

	private final JdbcTemplate jdbc;

	public List<Long> findTopicIds(long nguoiDungId) {
		return jdbc.queryForList("SELECT chu_de_id FROM chu_de_quan_tam WHERE nguoi_dung_id = ?", Long.class, nguoiDungId);
	}

	/** Thay toàn bộ danh sách; gọi trong giao dịch đã khóa hồ sơ của người dùng. */
	public void replace(long nguoiDungId, Collection<Long> chuDeIds) {
		jdbc.update("DELETE FROM chu_de_quan_tam WHERE nguoi_dung_id = ?", nguoiDungId);
		jdbc.batchUpdate("INSERT INTO chu_de_quan_tam (nguoi_dung_id, chu_de_id) VALUES (?, ?)",
				chuDeIds.stream().map(id -> new Object[] { nguoiDungId, id }).toList());
	}
}
