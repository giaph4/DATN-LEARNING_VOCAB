package com.team.vocab_learn.shared.web;

import java.util.List;

/** Cursor page: {@code {items,nextCursor}}; nextCursor is null on the last page. */
public record CursorResponse<T>(List<T> items, String nextCursor) {
}
