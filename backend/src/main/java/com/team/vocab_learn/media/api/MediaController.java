package com.team.vocab_learn.media.api;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.team.vocab_learn.media.application.MediaService;
import com.team.vocab_learn.media.application.MediaService.Access;
import com.team.vocab_learn.media.application.MediaService.MediaView;
import com.team.vocab_learn.media.application.MediaService.Purpose;
import com.team.vocab_learn.shared.security.CurrentUser;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

/** THIET_KE §11: upload-intents → trình duyệt PUT lên kho → complete → access. */
@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
public class MediaController {

	public record UploadIntentRequest(
			@NotNull(message = "Chọn mục đích tải lên.") Purpose purpose,
			@NotBlank(message = "Thiếu định dạng tệp.") String contentType,
			@NotNull(message = "Thiếu kích thước tệp.") @Positive(message = "Kích thước tệp không hợp lệ.") Long sizeBytes) {
	}

	public record UploadIntentResponse(String mediaId, URI uploadUrl, String method, Map<String, String> headers,
			Instant expiresAt) {
	}

	public record MediaResponse(String id, String mimeType, long sizeBytes, String status) {
		static MediaResponse of(MediaView v) {
			return new MediaResponse(String.valueOf(v.id()), v.mimeType(), v.sizeBytes(), v.status().name());
		}
	}

	private final MediaService media;
	private final CurrentUser currentUser;

	@PostMapping("/upload-intents")
	@ResponseStatus(HttpStatus.CREATED)
	public UploadIntentResponse createUploadIntent(@Valid @RequestBody UploadIntentRequest req) {
		var intent = media.createUploadIntent(currentUser.id(), req.purpose(), req.contentType(), req.sizeBytes());
		return new UploadIntentResponse(String.valueOf(intent.mediaId()), intent.uploadUrl(), intent.method(),
				intent.headers(), intent.expiresAt());
	}

	@PostMapping("/{id}/complete")
	public MediaResponse complete(@PathVariable long id) {
		return MediaResponse.of(media.complete(currentUser.id(), id));
	}

	@GetMapping("/{id}/access")
	public Access access(@PathVariable long id) {
		return media.access(currentUser.id(), id);
	}
}
