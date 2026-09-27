package com.team.vocab_learn.shared.web;

import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;

/** ETag = quoted {@code phien_ban}. Updates send it back in If-Match; mismatch → 412. */
public final class ETags {

	private ETags() {
	}

	public static String of(long version) {
		return "\"" + version + "\"";
	}

	/** Parses If-Match and checks it against the current version. */
	public static void requireMatch(String ifMatch, long currentVersion) {
		if (ifMatch == null || ifMatch.isBlank()) {
			throw new ApiException(ErrorCode.VALIDATION_FAILED, "Thiếu header If-Match.");
		}
		long expected;
		try {
			expected = Long.parseLong(ifMatch.replace("W/", "").replace("\"", "").trim());
		} catch (NumberFormatException e) {
			throw new ApiException(ErrorCode.VALIDATION_FAILED, "If-Match không hợp lệ.");
		}
		if (expected != currentVersion) {
			throw new ApiException(ErrorCode.STALE_VERSION, "Dữ liệu vừa được thay đổi ở nơi khác, hãy tải lại.");
		}
	}
}
