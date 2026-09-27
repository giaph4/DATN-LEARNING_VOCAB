package com.team.vocab_learn.shared.time;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;

import lombok.RequiredArgsConstructor;

/** Converts between UTC instants and the learner's local day (IANA zone from tuy_chon_hoc). */
@Service
@RequiredArgsConstructor
public class LocalDayService {

	private final Clock clock;

	public LocalDate today(ZoneId zone) {
		return LocalDate.now(clock.withZone(zone));
	}

	public Instant startOfDay(LocalDate day, ZoneId zone) {
		return day.atStartOfDay(zone).toInstant();
	}

	/** Exclusive end: start of the next local day. */
	public Instant endOfDay(LocalDate day, ZoneId zone) {
		return day.plusDays(1).atStartOfDay(zone).toInstant();
	}

	public static ZoneId parseZone(String tz) {
		try {
			return ZoneId.of(tz);
		} catch (DateTimeException | NullPointerException e) {
			throw new ApiException(ErrorCode.VALIDATION_FAILED, "Múi giờ không hợp lệ: " + tz);
		}
	}
}
