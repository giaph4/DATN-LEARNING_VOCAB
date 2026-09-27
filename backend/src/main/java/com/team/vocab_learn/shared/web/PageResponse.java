package com.team.vocab_learn.shared.web;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

/** Offset page: {@code {items,page,size,total}}; page is 0-based. */
public record PageResponse<T>(List<T> items, int page, int size, long total) {

	public static <E, T> PageResponse<T> of(Page<E> page, Function<E, T> mapper) {
		return new PageResponse<>(page.map(mapper).getContent(), page.getNumber(), page.getSize(), page.getTotalElements());
	}
}
