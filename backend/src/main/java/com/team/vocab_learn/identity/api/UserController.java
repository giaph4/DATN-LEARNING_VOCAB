package com.team.vocab_learn.identity.api;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.vocab_learn.catalog.application.TopicService.TopicRef;
import com.team.vocab_learn.identity.application.ProfileService;
import com.team.vocab_learn.identity.application.ProfileService.Me;
import com.team.vocab_learn.identity.application.ProfileService.ProfileChanges;
import com.team.vocab_learn.shared.security.CurrentUser;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

/** TK-06/07: tài khoản và hồ sơ của chính người gọi; không có tham số userId. */
@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class UserController {

	public record UpdateProfileRequest(
			@Size(max = 100, message = "Tên hiển thị tối đa 100 ký tự.") String displayName,
			@Size(max = 500, message = "Giới thiệu tối đa 500 ký tự.") String bio,
			@Size(max = 20, message = "Mã ảnh không hợp lệ.") String avatarMediaId,
			Boolean removeAvatar,
			@Size(max = 2, message = "Trình độ phải là một trong A1, A2, B1, B2, C1, C2.") String level,
			@Size(max = 500, message = "Mục tiêu học tối đa 500 ký tự.") String learningGoal,
			Boolean completeOnboarding) {
	}

	public record InterestsRequest(
			@NotNull(message = "Thiếu danh sách chủ đề.") @Size(max = 100, message = "Danh sách chủ đề quá dài.") List<String> topicIds) {
	}

	public record TopicItem(String id, String slug, String name) {
		static List<TopicItem> of(List<TopicRef> refs) {
			return refs.stream().map(t -> new TopicItem(String.valueOf(t.id()), t.slug(), t.name())).toList();
		}
	}

	public record InterestsResponse(List<TopicItem> topics) {
	}

	public record AvatarResponse(String mediaId, URI url, Instant expiresAt) {
	}

	public record MeResponse(String id, String email, String status, Set<String> roles, boolean emailVerified,
			String displayName, String bio, String level, String learningGoal, List<TopicItem> interests, AvatarResponse avatar,
			Instant onboardingCompletedAt) {

		static MeResponse of(Me me) {
			AvatarResponse avatar = me.avatar() == null ? null
					: new AvatarResponse(String.valueOf(me.avatarMediaId()), me.avatar().url(), me.avatar().expiresAt());
			return new MeResponse(String.valueOf(me.id()), me.email(), me.status(), me.roles(), me.emailVerified(),
					me.displayName(), me.bio(), me.level(), me.learningGoal(), TopicItem.of(me.interests()), avatar, me.onboardingCompletedAt());
		}
	}

	private final ProfileService profiles;
	private final CurrentUser currentUser;

	@GetMapping
	public MeResponse me() {
		return MeResponse.of(profiles.me(currentUser.id()));
	}

	@PatchMapping("/profile")
	public MeResponse updateProfile(@Valid @RequestBody UpdateProfileRequest req) {
		var changes = new ProfileChanges(req.displayName(), req.bio(), req.avatarMediaId(),
				Boolean.TRUE.equals(req.removeAvatar()), req.level(), req.learningGoal(),
				Boolean.TRUE.equals(req.completeOnboarding()));
		return MeResponse.of(profiles.update(currentUser.id(), changes));
	}

	@PutMapping("/interests")
	public InterestsResponse replaceInterests(@Valid @RequestBody InterestsRequest req) {
		return new InterestsResponse(TopicItem.of(profiles.replaceInterests(currentUser.id(), req.topicIds())));
	}
}
