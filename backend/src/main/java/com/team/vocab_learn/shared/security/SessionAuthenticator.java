package com.team.vocab_learn.shared.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Comparator;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.team.vocab_learn.shared.errors.ApiException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/** Tạo, liệt kê, thu hồi phiên đăng nhập (lưu trong Redis qua Spring Session). Dùng chung cho email và Google. */
@Component
@RequiredArgsConstructor
public class SessionAuthenticator {

	static final String USER_AGENT = "vl.userAgent";

	/** Một phiên cho FE: id là mã băm, không lộ session id thật (session id = giá trị cookie). */
	public record SessionInfo(String id, String userAgent, Instant createdAt, Instant lastActiveAt, boolean current) {
	}

	private final SecurityContextRepository contexts = new HttpSessionSecurityContextRepository();
	private final FindByIndexNameSessionRepository<?> sessionRepo;

	public void signIn(AuthUser user, HttpServletRequest request, HttpServletResponse response) {
		// Đổi session id sau đăng nhập để chống session fixation
		HttpSession session = request.getSession(false);
		if (session != null) {
			request.changeSessionId();
		} else {
			session = request.getSession(true);
		}
		// Chỉ mục theo userId để liệt kê/thu hồi mọi phiên của một người
		session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, String.valueOf(user.id()));
		String agent = request.getHeader("User-Agent");
		session.setAttribute(USER_AGENT, agent == null ? "" : agent.substring(0, Math.min(agent.length(), 255)));

		var authorities = user.roles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).toList();
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(UsernamePasswordAuthenticationToken.authenticated(user, null, authorities));
		SecurityContextHolder.setContext(context);
		contexts.saveContext(context, request, response);
	}

	public List<SessionInfo> list(long userId, HttpServletRequest request) {
		HttpSession current = request.getSession(false);
		String currentId = current == null ? null : current.getId();
		return sessionsOf(userId).values().stream()
				.map(s -> new SessionInfo(handle(s.getId()), (String) s.getAttribute(USER_AGENT), s.getCreationTime(),
						s.getLastAccessedTime(), s.getId().equals(currentId)))
				.sorted(Comparator.comparing(SessionInfo::lastActiveAt).reversed())
				.toList();
	}

	/** TK-03: chỉ thu hồi được phiên của chính mình; mã lạ hoặc của người khác → 404. */
	public void revoke(long userId, String handle) {
		String sessionId = sessionsOf(userId).keySet().stream().filter(id -> handle(id).equals(handle)).findFirst()
				.orElseThrow(() -> ApiException.notFound("phiên đăng nhập"));
		sessionRepo.deleteById(sessionId);
	}

	/** TK-05: xóa mọi phiên của người dùng; trong transaction thì chờ commit để không thu hồi khi rollback. */
	public void revokeAll(long userId) {
		Runnable revoke = () -> sessionsOf(userId).keySet().forEach(sessionRepo::deleteById);
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
				@Override
				public void afterCommit() {
					revoke.run();
				}
			});
		} else {
			revoke.run();
		}
	}

	public void signOut(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		SecurityContextHolder.clearContext();
	}

	private Map<String, ? extends Session> sessionsOf(long userId) {
		return sessionRepo.findByPrincipalName(String.valueOf(userId));
	}

	private static String handle(String sessionId) {
		try {
			byte[] hash = MessageDigest.getInstance("SHA-256").digest(sessionId.getBytes(StandardCharsets.UTF_8));
			return HexFormat.of().formatHex(hash, 0, 8);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}
}
