package com.team.vocab_learn.shared.errors;

import java.net.URI;
import java.util.List;

import org.slf4j.MDC;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.team.vocab_learn.shared.web.TraceIdFilter;

import lombok.extern.slf4j.Slf4j;

/** Every error leaves the API as application/problem+json with code, traceId, fieldErrors, retryable. */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	ResponseEntity<ProblemDetail> api(ApiException e) {
		return problem(e.getCode(), e.getMessage(), e.getFieldErrors());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ProblemDetail> invalidBody(MethodArgumentNotValidException e) {
		List<FieldErrorItem> fields = e.getBindingResult().getFieldErrors().stream()
				.map(f -> new FieldErrorItem(f.getField(), f.getDefaultMessage()))
				.toList();
		return problem(ErrorCode.VALIDATION_FAILED, "Vui lòng kiểm tra lại các trường được đánh dấu.", fields);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	ResponseEntity<ProblemDetail> invalidParams(HandlerMethodValidationException e) {
		List<FieldErrorItem> fields = e.getParameterValidationResults().stream()
				.flatMap(r -> r.getResolvableErrors().stream()
						.map(err -> new FieldErrorItem(r.getMethodParameter().getParameterName(), err.getDefaultMessage())))
				.toList();
		return problem(ErrorCode.VALIDATION_FAILED, "Tham số không hợp lệ.", fields);
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, MissingServletRequestParameterException.class,
			MissingRequestHeaderException.class, MethodArgumentTypeMismatchException.class })
	ResponseEntity<ProblemDetail> badRequest(Exception e) {
		return problem(ErrorCode.VALIDATION_FAILED, "Yêu cầu không đúng định dạng.", List.of());
	}

	@ExceptionHandler(NoResourceFoundException.class)
	ResponseEntity<ProblemDetail> noRoute(NoResourceFoundException e) {
		return problem(ErrorCode.NOT_FOUND, "Không tìm thấy đường dẫn.", List.of());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	ResponseEntity<ProblemDetail> method(HttpRequestMethodNotSupportedException e) {
		return problem(ErrorCode.METHOD_NOT_ALLOWED, null, List.of());
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	ResponseEntity<ProblemDetail> mediaType(HttpMediaTypeNotSupportedException e) {
		return problem(ErrorCode.UNSUPPORTED_MEDIA_TYPE, null, List.of());
	}

	@ExceptionHandler(OptimisticLockingFailureException.class)
	ResponseEntity<ProblemDetail> stale(OptimisticLockingFailureException e) {
		return problem(ErrorCode.STALE_VERSION, "Dữ liệu vừa được thay đổi ở nơi khác, hãy tải lại.", List.of());
	}

	@ExceptionHandler(AuthenticationException.class)
	ResponseEntity<ProblemDetail> unauthenticated(AuthenticationException e) {
		return problem(ErrorCode.UNAUTHENTICATED, "Bạn cần đăng nhập để tiếp tục.", List.of());
	}

	@ExceptionHandler(CsrfException.class)
	ResponseEntity<ProblemDetail> csrf(CsrfException e) {
		return problem(ErrorCode.CSRF_INVALID, "Hãy tải lại trang rồi thử lại.", List.of());
	}

	@ExceptionHandler(AccessDeniedException.class)
	ResponseEntity<ProblemDetail> denied(AccessDeniedException e) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || auth instanceof AnonymousAuthenticationToken) {
			return unauthenticated(null);
		}
		return problem(ErrorCode.FORBIDDEN, "Bạn không có quyền thực hiện thao tác này.", List.of());
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<ProblemDetail> unexpected(Exception e) {
		log.error("Unhandled error", e);
		return problem(ErrorCode.INTERNAL_ERROR, "Đã có lỗi xảy ra, vui lòng thử lại sau.", List.of());
	}

	private static ResponseEntity<ProblemDetail> problem(ErrorCode code, String detail, List<FieldErrorItem> fields) {
		ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(code.getStatus()),
				detail != null ? detail : code.getTitle());
		pd.setType(URI.create(code.type()));
		pd.setTitle(code.getTitle());
		pd.setProperty("code", code.name());
		pd.setProperty("traceId", MDC.get(TraceIdFilter.MDC_KEY));
		pd.setProperty("fieldErrors", fields);
		pd.setProperty("retryable", code.isRetryable());
		return ResponseEntity.status(code.getStatus()).body(pd);
	}
}
