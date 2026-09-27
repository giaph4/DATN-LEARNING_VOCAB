package com.team.vocab_learn.shared.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;

/** Validated paging params: page ≥ 0, size 1..100 (default 20). */
public record PageQuery(int page, int size) {

	public static final int DEFAULT_SIZE = 20;
	public static final int MAX_SIZE = 100;

	public static PageQuery of(Integer page, Integer size) {
		int p = page == null ? 0 : page;
		int s = size == null ? DEFAULT_SIZE : size;
		if (p < 0 || s < 1 || s > MAX_SIZE) {
			throw new ApiException(ErrorCode.VALIDATION_FAILED, "page phải ≥ 0 và size trong khoảng 1–" + MAX_SIZE + ".");
		}
		return new PageQuery(p, s);
	}

	public Pageable toPageable(Sort sort) {
		return PageRequest.of(page, size, sort);
	}
}
