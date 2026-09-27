package com.team.vocab_learn.identity.application;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.vocab_learn.catalog.application.TopicService;
import com.team.vocab_learn.catalog.application.TopicService.TopicRef;
import com.team.vocab_learn.identity.domain.HoSoNguoiDung;
import com.team.vocab_learn.identity.domain.NguoiDung;
import com.team.vocab_learn.identity.infrastructure.ChuDeQuanTamStore;
import com.team.vocab_learn.identity.infrastructure.HoSoNguoiDungRepository;
import com.team.vocab_learn.identity.infrastructure.NguoiDungRepository;
import com.team.vocab_learn.media.application.MediaService;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.errors.FieldErrorItem;

import lombok.RequiredArgsConstructor;

/** TK-06/07: chỉ đọc/sửa hồ sơ của người đang đăng nhập (id lấy từ phiên). */
@Service
@RequiredArgsConstructor
public class ProfileService {

	/** Trường null = không đổi; bio/learningGoal rỗng = xóa. */
	public record ProfileChanges(String displayName, String bio, String avatarMediaId, boolean removeAvatar,
			String level, String learningGoal, boolean completeOnboarding) {
	}

	public record Me(Long id, String email, String status, Set<String> roles, boolean emailVerified, String displayName,
			String bio, String level, String learningGoal, List<TopicRef> interests, Long avatarMediaId,
			MediaService.Access avatar, Instant onboardingCompletedAt) {
	}

	/** TK-07: trình độ tự đánh giá theo khung CEFR (CHECK trong SQL). */
	public static final List<String> LEVELS = List.of("A1", "A2", "B1", "B2", "C1", "C2");
	public static final int MAX_INTERESTS = 20;

	private final NguoiDungRepository nguoiDungRepo;
	private final HoSoNguoiDungRepository hoSoRepo;
	private final ChuDeQuanTamStore interestStore;
	private final TopicService topics;
	private final MediaService media;
	private final Clock clock;

	@Transactional(readOnly = true)
	public Me me(long userId) {
		NguoiDung nguoiDung = nguoiDungRepo.findById(userId).orElseThrow(() -> ApiException.notFound("tài khoản"));
		HoSoNguoiDung hoSo = hoSoRepo.findById(userId).orElseThrow(() -> ApiException.notFound("hồ sơ"));
		return new Me(nguoiDung.getId(), nguoiDung.getEmail(), nguoiDung.getTrangThai().name(),
				new TreeSet<>(nguoiDungRepo.findRoleCodes(userId)), nguoiDung.getThoiDiemXacThucEmail() != null,
				hoSo.getTenHienThi(), hoSo.getGioiThieu(), hoSo.getTrinhDoTuDanhGia(), hoSo.getMucTieuHoc(),
				interests(userId), hoSo.getTepAnhDaiDienId(), media.accessOrNull(hoSo.getTepAnhDaiDienId()),
				hoSo.getThoiDiemXongThietLap());
	}

	@Transactional
	public Me update(long userId, ProfileChanges changes) {
		HoSoNguoiDung hoSo = hoSoRepo.findForUpdate(userId).orElseThrow(() -> ApiException.notFound("hồ sơ"));
		if (changes.displayName() != null) {
			String name = changes.displayName().strip();
			if (name.length() < 2 || name.length() > 100) {
				throw invalid("displayName", "Tên hiển thị cần từ 2 đến 100 ký tự.");
			}
			hoSo.rename(name);
		}
		if (changes.bio() != null) {
			hoSo.describe(optionalText("bio", changes.bio(), "Giới thiệu tối đa 500 ký tự."));
		}
		if (changes.level() != null) {
			if (!LEVELS.contains(changes.level())) {
				throw invalid("level", "Trình độ phải là một trong A1, A2, B1, B2, C1, C2.");
			}
			hoSo.assessLevel(changes.level());
		}
		if (changes.learningGoal() != null) {
			hoSo.setGoal(optionalText("learningGoal", changes.learningGoal(), "Mục tiêu học tối đa 500 ký tự."));
		}
		if (changes.completeOnboarding()) {
			hoSo.completeOnboarding(clock.instant().truncatedTo(ChronoUnit.MILLIS)); // khớp DATETIME(3)
		}
		Long oldAvatar = hoSo.getTepAnhDaiDienId();
		if (changes.removeAvatar()) {
			hoSo.changeAvatar(null);
		} else if (changes.avatarMediaId() != null) {
			long mediaId = parseId(changes.avatarMediaId(), "tệp");
			media.requireUsableImage(userId, mediaId);
			hoSo.changeAvatar(mediaId);
		}
		hoSoRepo.saveAndFlush(hoSo);
		if (oldAvatar != null && !Objects.equals(oldAvatar, hoSo.getTepAnhDaiDienId())) {
			media.releaseIfUnused(oldAvatar);
		}
		return me(userId);
	}

	/** TK-07: thay toàn bộ chủ đề quan tâm (gửi lại cùng danh sách cho cùng kết quả). Rỗng = bỏ hết. */
	@Transactional
	public List<TopicRef> replaceInterests(long userId, List<String> topicIds) {
		hoSoRepo.findForUpdate(userId).orElseThrow(() -> ApiException.notFound("hồ sơ"));
		Set<Long> ids = new LinkedHashSet<>();
		for (String raw : topicIds) {
			if (raw == null || !raw.matches("\\d{1,19}")) {
				throw invalid("topicIds", "Mã chủ đề không hợp lệ.");
			}
			ids.add(Long.valueOf(raw));
		}
		if (ids.size() > MAX_INTERESTS) {
			throw invalid("topicIds", "Chọn tối đa %d chủ đề.".formatted(MAX_INTERESTS));
		}
		List<TopicRef> usable = topics.findUsable(ids);
		if (usable.size() != ids.size()) {
			String message = "Có chủ đề không còn hoạt động. Hãy tải lại danh sách chủ đề và chọn lại.";
			throw new ApiException(ErrorCode.BUSINESS_RULE_VIOLATED, message, List.of(new FieldErrorItem("topicIds", message)));
		}
		interestStore.replace(userId, ids);
		return usable;
	}

	/** Chủ đề đã chọn nhưng sau đó bị ẩn thì không trả về (vẫn giữ trong DB). */
	@Transactional(readOnly = true)
	public List<TopicRef> interests(long userId) {
		List<Long> ids = interestStore.findTopicIds(userId);
		return ids.isEmpty() ? List.of() : topics.findUsable(ids);
	}

	private static String optionalText(String field, String value, String tooLong) {
		String text = value.strip();
		if (text.length() > 500) {
			throw invalid(field, tooLong);
		}
		return text.isEmpty() ? null : text;
	}

	private static long parseId(String id, String what) {
		try {
			return Long.parseLong(id);
		} catch (NumberFormatException e) {
			throw ApiException.notFound(what);
		}
	}

	private static ApiException invalid(String field, String message) {
		return new ApiException(ErrorCode.VALIDATION_FAILED, message, List.of(new FieldErrorItem(field, message)));
	}
}
