package com.team.vocab_learn.shared.errors;

/** One invalid field in {@code fieldErrors}. */
public record FieldErrorItem(String field, String message) {
}
