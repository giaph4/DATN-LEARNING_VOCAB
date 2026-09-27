package com.team.vocab_learn.shared.errors;

import java.util.List;

import lombok.Getter;

/** Throw from services; {@link GlobalExceptionHandler} turns it into ProblemDetail. Detail is user-facing Vietnamese. */
@Getter
public class ApiException extends RuntimeException {

	private final ErrorCode code;
	private final List<FieldErrorItem> fieldErrors;

	public ApiException(ErrorCode code, String detail) {
		this(code, detail, List.of());
	}

	public ApiException(ErrorCode code, String detail, List<FieldErrorItem> fieldErrors) {
		super(detail);
		this.code = code;
		this.fieldErrors = fieldErrors;
	}

	public static ApiException notFound(String what) {
		return new ApiException(ErrorCode.NOT_FOUND, "Không tìm thấy " + what + ".");
	}
}
