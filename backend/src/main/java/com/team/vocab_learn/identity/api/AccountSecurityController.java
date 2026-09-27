package com.team.vocab_learn.identity.api;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.team.vocab_learn.identity.application.AccountSecurityService;
import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.security.CurrentUser;
import com.team.vocab_learn.shared.security.Reauthentication;
import com.team.vocab_learn.shared.security.SessionAuthenticator;
import com.team.vocab_learn.shared.security.SessionAuthenticator.SessionInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

/** B1.5: phiên đăng nhập, xác thực lại, phương thức đăng nhập đã liên kết. Chỉ tác động lên chính người gọi. */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AccountSecurityController {

	public record ReauthenticateRequest(
			@NotBlank(message = "Vui lòng nhập mật khẩu.") @Size(max = 128, message = "Mật khẩu tối đa 128 ký tự.") String password) {

		@Override
		public String toString() {
			return "ReauthenticateRequest[password=***]";
		}
	}

	public record ReauthenticateResponse(Instant reauthenticatedUntil) {
	}

	public record IdentityItem(String provider, String email, Instant linkedAt) {
	}

	public record LoginMethodsResponse(boolean hasPassword, List<IdentityItem> identities) {
	}

	public record LinkResponse(String redirectUrl) {
	}

	private final CurrentUser currentUser;
	private final SessionAuthenticator sessions;
	private final Reauthentication reauth;
	private final AccountSecurityService accountSecurity;
	private final ObjectProvider<ClientRegistrationRepository> oauthClients;
	private final Clock clock;

	@GetMapping("/sessions")
	public List<SessionInfo> sessions(HttpServletRequest http) {
		return sessions.list(currentUser.id(), http);
	}

	// Thu hồi phiên hiện tại cũng được: tương đương đăng xuất
	@DeleteMapping("/sessions/{sessionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void revokeSession(@PathVariable String sessionId) {
		sessions.revoke(currentUser.id(), sessionId);
	}

	@PostMapping("/reauthenticate")
	public ReauthenticateResponse reauthenticate(@Valid @RequestBody ReauthenticateRequest req, HttpServletRequest http) {
		accountSecurity.verifyPassword(currentUser.id(), req.password());
		return new ReauthenticateResponse(reauth.mark(http));
	}

	@GetMapping("/identities")
	public LoginMethodsResponse identities() {
		var methods = accountSecurity.loginMethods(currentUser.id());
		return new LoginMethodsResponse(methods.hasPassword(), methods.identities().stream()
				.map(d -> new IdentityItem(d.getNhaCungCap().name().toLowerCase(), d.getEmailNhaCungCap(), d.getThoiDiemLienKet()))
				.toList());
	}

	/** FE điều hướng cả trang tới redirectUrl; Google xong quay về /cai-dat?lien-ket=google hoặc /cai-dat?loi=CODE. */
	@PostMapping("/identities/{provider}/link")
	public LinkResponse link(@PathVariable String provider, HttpServletRequest http) {
		AccountSecurityService.provider(provider);
		reauth.require(http);
		if (oauthClients.getIfAvailable() == null) {
			throw new ApiException(ErrorCode.DEPENDENCY_UNAVAILABLE, "Đăng nhập Google chưa được bật trên máy chủ.");
		}
		GoogleLoginHandler.rememberLinkIntent(http.getSession(), currentUser.id(), clock.instant());
		return new LinkResponse("/oauth2/authorization/google");
	}

	@DeleteMapping("/identities/{provider}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void unlink(@PathVariable String provider, HttpServletRequest http) {
		var nhaCungCap = AccountSecurityService.provider(provider);
		reauth.require(http);
		accountSecurity.unlink(currentUser.id(), nhaCungCap);
	}
}
