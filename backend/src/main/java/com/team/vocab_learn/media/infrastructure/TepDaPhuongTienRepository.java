package com.team.vocab_learn.media.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.team.vocab_learn.media.domain.TepDaPhuongTien;

import jakarta.persistence.LockModeType;

public interface TepDaPhuongTienRepository extends JpaRepository<TepDaPhuongTien, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT t FROM TepDaPhuongTien t WHERE t.id = :id")
	Optional<TepDaPhuongTien> findForUpdate(Long id);

	/** Mọi nơi trong lược đồ có thể trỏ tới một tệp; thêm bảng mới tham chiếu tệp thì thêm vào đây. */
	@Query(nativeQuery = true, value = """
			SELECT (SELECT COUNT(*) FROM ho_so_nguoi_dung WHERE tep_anh_dai_dien_id = :id)
			     + (SELECT COUNT(*) FROM bo_the WHERE tep_anh_bia_id = :id)
			     + (SELECT COUNT(*) FROM tep_cua_the WHERE tep_id = :id)
			     + (SELECT COUNT(*) FROM tep_cua_vi_du WHERE tep_id = :id)
			     + (SELECT COUNT(*) FROM lan_danh_gia_phat_am WHERE tep_ghi_am_id = :id)""")
	long countReferences(Long id);
}
