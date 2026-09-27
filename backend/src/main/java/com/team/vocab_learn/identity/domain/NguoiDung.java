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
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String emailChuanHoa;

    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private TrangThaiNguoiDung trangThai;

    private Instant thoiDiemXacThucEmail;

    private Instant thoiDiemDangNhapGanNhat;

    // TK-05: tăng khi đổi/đặt lại mật khẩu để vô hiệu mọi phiên cũ
    private long phienBanXacThuc;

    public static NguoiDung registerWithEmail(String email, String emailChuanHoa, String passwordHash) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.email = email;
        nguoiDung.emailChuanHoa = emailChuanHoa;
        nguoiDung.passwordHash = passwordHash;
        nguoiDung.trangThai = TrangThaiNguoiDung.PENDING_VERIFICATION;
        return nguoiDung;
    }

    // TK-03: tài khoản Google đã được Google xác thực email, không có mật khẩu
    public static NguoiDung registerWithGoogle(String email, String emailChuanHoa, Instant now) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.email = email;
        nguoiDung.emailChuanHoa = emailChuanHoa;
        nguoiDung.trangThai = TrangThaiNguoiDung.ACTIVE;
        nguoiDung.thoiDiemXacThucEmail = now;
        return nguoiDung;
    }

    // TK-05: tăng phiên bản xác thực để phiên cũ không còn hợp lệ
    public void changePassword(String newPasswordHash) {
        passwordHash = newPasswordHash;
        phienBanXacThuc++;
    }

    public void recordLogin(Instant now) {
        thoiDiemDangNhapGanNhat = now;
    }

    // TK-02: chỉ tài khoản đang chờ mới lên ACTIVE; tài khoản bị khóa/chờ xóa giữ nguyên trạng thái
    public void verifyEmail(Instant now) {
        if (thoiDiemXacThucEmail == null) {
            thoiDiemXacThucEmail = now;
        }
        if (trangThai == TrangThaiNguoiDung.PENDING_VERIFICATION) {
            trangThai = TrangThaiNguoiDung.ACTIVE;
        }
    }
}
