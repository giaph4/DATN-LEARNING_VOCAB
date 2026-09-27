package com.team.vocab_learn.catalog.api;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.team.vocab_learn.catalog.domain.Topic;

/** MapStruct generates TopicMapperImpl (a Spring bean) at compile time; it uses the Lombok builder. */
@Mapper
public interface TopicMapper {

	@Mapping(target = "children", expression = "java(new java.util.ArrayList<>())")
	TopicResponse toResponse(Topic topic);
}
