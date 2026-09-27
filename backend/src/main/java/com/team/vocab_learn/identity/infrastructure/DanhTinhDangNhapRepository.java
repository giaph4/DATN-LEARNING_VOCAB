package com.team.vocab_learn.identity.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.vocab_learn.identity.domain.DanhTinhDangNhap;
import com.team.vocab_learn.identity.domain.NhaCungCapDangNhap;

public interface DanhTinhDangNhapRepository extends JpaRepository<DanhTinhDangNhap, Long> {

	Optional<DanhTinhDangNhap> findByNhaCungCapAndDinhDanhNhaCungCap(NhaCungCapDangNhap nhaCungCap, String dinhDanh);

	List<DanhTinhDangNhap> findByNguoiDungIdOrderByThoiDiemLienKet(Long nguoiDungId);

	Optional<DanhTinhDangNhap> findByNguoiDungIdAndNhaCungCap(Long nguoiDungId, NhaCungCapDangNhap nhaCungCap);
}
