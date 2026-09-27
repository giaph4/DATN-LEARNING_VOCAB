package com.team.vocab_learn.identity.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team.vocab_learn.identity.domain.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {

	boolean existsByEmailChuanHoa(String emailChuanHoa);

	Optional<NguoiDung> findByEmailChuanHoa(String emailChuanHoa);

	@Query(value = """
			SELECT v.ma FROM nguoi_dung_vai_tro nv JOIN vai_tro v ON v.id = nv.vai_tro_id
			WHERE nv.nguoi_dung_id = :id""", nativeQuery = true)
	List<String> findRoleCodes(Long id);
}
