package com.team.vocab_learn.catalog.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.vocab_learn.catalog.application.TopicService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class TopicController {

	private final TopicService topicService;

	/** GET /api/v1/topics — public topic tree (guest allowed). */
	@GetMapping
	public List<TopicResponse> list() {
		return topicService.tree();
	}
}
