package com.team.vocab_learn.catalog.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.vocab_learn.catalog.api.TopicMapper;
import com.team.vocab_learn.catalog.api.TopicResponse;
import com.team.vocab_learn.catalog.domain.Topic;
import com.team.vocab_learn.catalog.infrastructure.TopicRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopicService {

	private final TopicRepository topics;
	private final TopicMapper mapper;

	/** Active topics as a tree; a child of an inactive parent is hidden. */
	public List<TopicResponse> tree() {
		List<Topic> all = topics.findByActiveTrueOrderByNameAsc();
		Map<Long, TopicResponse> byId = new LinkedHashMap<>();
		all.forEach(t -> byId.put(t.getId(), mapper.toResponse(t)));

		List<TopicResponse> roots = new ArrayList<>();
		for (Topic t : all) {
			if (t.getParentId() == null) {
				roots.add(byId.get(t.getId()));
			} else if (byId.containsKey(t.getParentId())) {
				byId.get(t.getParentId()).children().add(byId.get(t.getId()));
			}
		}
		return roots;
	}

	public record TopicRef(Long id, String slug, String name) {
	}

	/** TK-07: chủ đề dùng được khi đang hoạt động và mọi chủ đề cha cũng đang hoạt động (giống cây công khai). */
	public List<TopicRef> findUsable(Collection<Long> ids) {
		Map<Long, Topic> active = new LinkedHashMap<>();
		topics.findByActiveTrueOrderByNameAsc().forEach(t -> active.put(t.getId(), t));
		return active.values().stream()
				.filter(t -> ids.contains(t.getId()) && usable(t, active))
				.map(t -> new TopicRef(t.getId(), t.getSlug(), t.getName()))
				.toList();
	}

	private static boolean usable(Topic t, Map<Long, Topic> active) {
		for (Topic cur = t; cur.getParentId() != null; cur = active.get(cur.getParentId())) {
			if (!active.containsKey(cur.getParentId())) {
				return false;
			}
		}
		return true;
	}
}
