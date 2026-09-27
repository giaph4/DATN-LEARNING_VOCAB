package com.team.vocab_learn.catalog.api;

import java.util.List;

import lombok.Builder;

/** Topic node; {@code children} is filled by the service when building the tree. */
@Builder
public record TopicResponse(Long id, String slug, String name, String description, List<TopicResponse> children) {
}
