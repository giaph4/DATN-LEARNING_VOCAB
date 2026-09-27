package com.team.vocab_learn.shared.jobs;

import lombok.Builder;

/** A claimed row of tac_vu_nen; {@code claimToken} proves this worker still owns the lease. */
@Builder
public record Job(long id, String type, Long ownerId, String payload, int attempts, int maxAttempts, String claimToken) {
}
