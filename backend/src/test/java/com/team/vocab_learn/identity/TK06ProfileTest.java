package com.team.vocab_learn.identity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.JsonPath;
import com.team.vocab_learn.AbstractIntegrationTest;
import com.team.vocab_learn.media.ImageSanitizerTest;
import com.team.vocab_learn.shared.storage.StorageService;

import jakarta.servlet.http.Cookie;

/** B1.6 (TK-06): hồ sơ, ảnh đại diện qua upload-intent → PUT → complete, thay ảnh có kiểm tham chiếu. */
class TK06ProfileTest extends AbstractIntegrationTest {

	private static final String CSRF = "b16-csrf-token";
	private static final String PASSWORD = "DemoHoc!2026";

	@Autowired
	StorageService storage;

	@Test
	void tc01_updateProfileAndReplaceAvatar() throws Exception {
		Cookie a = login(activeAccount());
		call(HttpMethod.GET, "/api/v1/users/me", null, a)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.displayName").value("Học Hồ Sơ"))
				.andExpect(jsonPath("$.roles[0]").value("LEARNER"))
				.andExpect(jsonPath("$.avatar").doesNotExist());

		call(HttpMethod.PATCH, "/api/v1/users/me/profile", """
				{"displayName":"  Nguyễn Thị Ánh Dương  ","bio":"Mỗi ngày 15 phút."}""", a)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.displayName").value("Nguyễn Thị Ánh Dương"))
				.andExpect(jsonPath("$.bio").value("Mỗi ngày 15 phút."));

		String first = uploadImage(a, "image/jpeg", ImageSanitizerTest.jpegWithExif(64, 64));
		String firstKey = objectKey(first);
		assertThat(new String(storage.read(firstKey), StandardCharsets.ISO_8859_1)).doesNotContain("GPS-");

		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"avatarMediaId\":\"%s\"}".formatted(first), a)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.avatar.mediaId").value(first))
				.andExpect(jsonPath("$.avatar.url").isString())
				.andExpect(jsonPath("$.displayName").value("Nguyễn Thị Ánh Dương"));

		// Thay ảnh: tệp cũ không còn ai dùng → DELETED và bị xóa khỏi kho
		String second = uploadImage(a, "image/png", ImageSanitizerTest.pngWithText(32, 32));
		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"avatarMediaId\":\"%s\"}".formatted(second), a)
				.andExpect(jsonPath("$.avatar.mediaId").value(second));
		assertThat(fileStatus(first)).isEqualTo("DELETED");
		assertThat(storage.head(firstKey)).isEmpty();

		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"removeAvatar\":true,\"bio\":\"\"}", a)
				.andExpect(jsonPath("$.avatar").doesNotExist()).andExpect(jsonPath("$.bio").doesNotExist());
		assertThat(fileStatus(second)).isEqualTo("DELETED");
	}

	@Test
	void tc02_invalidNameBioAndFakeOrOversizedImages() throws Exception {
		Cookie a = login(activeAccount());
		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"displayName\":\"   a  \"}", a)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
				.andExpect(jsonPath("$.fieldErrors[0].field").value("displayName"));
		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"bio\":\"%s\"}".formatted("x".repeat(501)), a)
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.fieldErrors[0].field").value("bio"));
		call(HttpMethod.GET, "/api/v1/users/me", null, a).andExpect(jsonPath("$.displayName").value("Học Hồ Sơ"));

		call(HttpMethod.POST, "/api/v1/media/upload-intents", intent("image/gif", 1000), a)
				.andExpect(status().isUnsupportedMediaType()).andExpect(jsonPath("$.code").value("UNSUPPORTED_MEDIA_TYPE"));
		call(HttpMethod.POST, "/api/v1/media/upload-intents", intent("image/png", 5L * 1024 * 1024 + 1), a)
				.andExpect(status().isUnprocessableEntity());

		// Chưa tải tệp lên kho mà đã xác nhận
		String empty = createIntent(a, "image/png", 100).mediaId();
		call(HttpMethod.POST, "/api/v1/media/" + empty + "/complete", null, a).andExpect(status().isUnprocessableEntity());

		// Tệp giả ảnh: khai báo PNG nhưng nội dung là HTML
		byte[] fake = "<html><script>alert(1)</script></html>".getBytes(StandardCharsets.UTF_8);
		var fakeIntent = createIntent(a, "image/png", fake.length);
		assertThat(put(fakeIntent, "image/png", fake)).isEqualTo(200);
		call(HttpMethod.POST, "/api/v1/media/" + fakeIntent.mediaId() + "/complete", null, a)
				.andExpect(status().isUnsupportedMediaType());
		assertThat(fileStatus(fakeIntent.mediaId())).isEqualTo("REJECTED");
		assertThat(storage.head(objectKey(fakeIntent.mediaId()))).isEmpty();
		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"avatarMediaId\":\"%s\"}".formatted(fakeIntent.mediaId()), a)
				.andExpect(status().isUnprocessableEntity());
	}

	@Test
	void tc03_otherUsersCannotUseMyFile_completeIsIdempotent_anonymous401() throws Exception {
		Cookie a = login(activeAccount());
		Cookie b = login(activeAccount());
		String mine = uploadImage(a, "image/jpeg", ImageSanitizerTest.jpegWithExif(16, 16));
		call(HttpMethod.POST, "/api/v1/media/" + mine + "/complete", null, a)
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("READY"));

		call(HttpMethod.POST, "/api/v1/media/" + mine + "/complete", null, b).andExpect(status().isNotFound());
		call(HttpMethod.GET, "/api/v1/media/" + mine + "/access", null, b).andExpect(status().isNotFound());
		call(HttpMethod.PATCH, "/api/v1/users/me/profile", "{\"avatarMediaId\":\"%s\"}".formatted(mine), b)
				.andExpect(status().isNotFound());
		call(HttpMethod.GET, "/api/v1/media/" + mine + "/access", null, a)
				.andExpect(status().isOk()).andExpect(jsonPath("$.url").isString());

		call(HttpMethod.GET, "/api/v1/users/me", null, null).andExpect(status().isUnauthorized());
		call(HttpMethod.POST, "/api/v1/media/upload-intents", intent("image/png", 10), null)
				.andExpect(status().isUnauthorized());
	}

	private record Intent(String mediaId, String uploadUrl) {
	}

	private String uploadImage(Cookie session, String contentType, byte[] bytes) throws Exception {
		Intent intent = createIntent(session, contentType, bytes.length);
		assertThat(put(intent, contentType, bytes)).isEqualTo(200);
		call(HttpMethod.POST, "/api/v1/media/" + intent.mediaId() + "/complete", null, session)
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("READY"));
		return intent.mediaId();
	}

	private Intent createIntent(Cookie session, String contentType, long size) throws Exception {
		String body = call(HttpMethod.POST, "/api/v1/media/upload-intents", intent(contentType, size), session)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.method").value("PUT"))
				.andReturn().getResponse().getContentAsString();
		return new Intent(JsonPath.read(body, "$.mediaId"), JsonPath.read(body, "$.uploadUrl"));
	}

	/** Giống trình duyệt: PUT thẳng lên MinIO bằng URL ký. */
	private static int put(Intent intent, String contentType, byte[] bytes) throws Exception {
		try (HttpClient http = HttpClient.newHttpClient()) {
			return http.send(HttpRequest.newBuilder(URI.create(intent.uploadUrl()))
					.header("Content-Type", contentType)
					.PUT(HttpRequest.BodyPublishers.ofByteArray(bytes)).build(),
					HttpResponse.BodyHandlers.discarding()).statusCode();
		}
	}

	private static String intent(String contentType, long size) {
		return "{\"purpose\":\"AVATAR\",\"contentType\":\"%s\",\"sizeBytes\":%d}".formatted(contentType, size);
	}

	private String fileStatus(String id) {
		return jdbc.queryForObject("SELECT trang_thai FROM tep_da_phuong_tien WHERE id = ?", String.class, Long.valueOf(id));
	}

	private String objectKey(String id) {
		return jdbc.queryForObject("SELECT khoa_doi_tuong_luu_tru FROM tep_da_phuong_tien WHERE id = ?", String.class,
				Long.valueOf(id));
	}

	private String activeAccount() throws Exception {
		String email = "b16-" + UUID.randomUUID() + "@test.local";
		call(HttpMethod.POST, "/api/v1/auth/register", """
				{"email":"%s","password":"%s","displayName":"Học Hồ Sơ"}""".formatted(email, PASSWORD), null)
				.andExpect(status().isAccepted());
		jdbc.update("UPDATE nguoi_dung SET trang_thai = 'ACTIVE', thoi_diem_xac_thuc_email = NOW(3) WHERE email_chuan_hoa = ?",
				email);
		return email;
	}

	private Cookie login(String email) throws Exception {
		return call(HttpMethod.POST, "/api/v1/auth/login", "{\"email\":\"%s\",\"password\":\"%s\"}".formatted(email, PASSWORD),
				null).andExpect(status().isOk()).andReturn().getResponse().getCookie("VOCAB_SESSION");
	}

	private ResultActions call(HttpMethod method, String path, String body, Cookie session) throws Exception {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		String ip = "10." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(1, 255);
		var req = MockMvcRequestBuilders.request(method, path)
				.header("X-CSRF-TOKEN", CSRF)
				.with(x -> { x.setRemoteAddr(ip); return x; });
		if (session != null) {
			req.cookie(new Cookie("XSRF-TOKEN", CSRF), session);
		} else {
			req.cookie(new Cookie("XSRF-TOKEN", CSRF));
		}
		if (body != null) {
			req.contentType(MediaType.APPLICATION_JSON).content(body);
		}
		return mvc.perform(req);
	}
}
