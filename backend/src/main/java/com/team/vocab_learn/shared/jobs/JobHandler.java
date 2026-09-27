package com.team.vocab_learn.shared.jobs;

/** Implement as a Spring bean to process one job type. Must be safe to run twice (at-least-once). */
public interface JobHandler {

	String type();

	void handle(Job job) throws Exception;
}
