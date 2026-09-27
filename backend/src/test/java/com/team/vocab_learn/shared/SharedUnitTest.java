package com.team.vocab_learn.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

import com.team.vocab_learn.shared.errors.ErrorCode;
import com.team.vocab_learn.shared.time.LocalDayService;
import com.team.vocab_learn.shared.web.ETags;
import com.team.vocab_learn.shared.web.PageQuery;

/** Pure logic, no Spring/Docker: local day boundaries, paging limits, ETag check. */
class SharedUnitTest {

	private static final ZoneId HCM = ZoneId.of("Asia/Ho_Chi_Minh");

	private static LocalDayService at(String utc) {
		return new LocalDayService(Clock.fixed(Instant.parse(utc), ZoneOffset.UTC));
	}

	@Test
	void localDay_switchesAtLocalMidnight() {
		assertThat(at("2026-10-12T16:59:59Z").today(HCM)).isEqualTo(LocalDate.of(2026, 10, 12)); // 23:59:59 +07
		assertThat(at("2026-10-12T17:00:00Z").today(HCM)).isEqualTo(LocalDate.of(2026, 10, 13)); // 00:00 +07
	}

	@Test
	void localDay_boundsInUtc() {
		LocalDayService days = at("2026-10-12T00:00:00Z");
		assertThat(days.startOfDay(LocalDate.of(2026, 10, 13), HCM)).isEqualTo(Instant.parse("2026-10-12T17:00:00Z"));
		assertThat(days.endOfDay(LocalDate.of(2026, 10, 13), HCM)).isEqualTo(Instant.parse("2026-10-13T17:00:00Z"));
		assertThatThrownBy(() -> LocalDayService.parseZone("Mars/Base")).extracting("code").isEqualTo(ErrorCode.VALIDATION_FAILED);
	}

	@Test
	void pageQuery_defaultsAndLimits() {
		assertThat(PageQuery.of(null, null)).isEqualTo(new PageQuery(0, 20));
		assertThatThrownBy(() -> PageQuery.of(0, 101)).extracting("code").isEqualTo(ErrorCode.VALIDATION_FAILED);
		assertThatThrownBy(() -> PageQuery.of(-1, 10)).extracting("code").isEqualTo(ErrorCode.VALIDATION_FAILED);
	}

	@Test
	void etag_mismatchIs412() {
		ETags.requireMatch("\"5\"", 5);
		assertThat(ETags.of(5)).isEqualTo("\"5\"");
		assertThatThrownBy(() -> ETags.requireMatch("\"4\"", 5)).extracting("code").isEqualTo(ErrorCode.STALE_VERSION);
		assertThatThrownBy(() -> ETags.requireMatch(null, 5)).extracting("code").isEqualTo(ErrorCode.VALIDATION_FAILED);
	}
}
