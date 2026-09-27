package com.team.vocab_learn.catalog.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.vocab_learn.catalog.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	List<Topic> findByActiveTrueOrderByNameAsc();
}
