package com.team.vocab_learn.identity.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.team.vocab_learn.identity.domain.HoSoNguoiDung;

import jakarta.persistence.LockModeType;

public interface HoSoNguoiDungRepository extends JpaRepository<HoSoNguoiDung, Long> {

	/** Tuần tự hóa các lần ghi hồ sơ/chủ đề của cùng một người (hai tab gửi cùng lúc). */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT h FROM HoSoNguoiDung h WHERE h.nguoiDungId = :id")
	Optional<HoSoNguoiDung> findForUpdate(Long id);
}
