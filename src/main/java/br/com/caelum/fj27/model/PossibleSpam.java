package br.com.caelum.fj27.model;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import br.com.caelum.fj27.model.topic_domain.Topic;

public class PossibleSpam {

	private List<Topic> topics;
	private static final int TOPICS_LIMIT = 4;

	public PossibleSpam(List<Topic> topics) {
		this.topics = topics;
	}

	public boolean hasTopicLimitExceeded() {
		return this.topics.size() >= TOPICS_LIMIT;
	}

	public long minutesToNextTopic(Instant from) {
		Instant instantOfTheOldestTopic = topics.get(0).getCreationInstant();
		return Duration.between(from, instantOfTheOldestTopic).getSeconds() / 60;
	}
}
