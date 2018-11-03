package br.com.caelum.fj27.model.topic_domain;

import br.com.caelum.fj27.model.Answer;

public interface TopicState {

    void registerNewReply(Topic topic, Answer newReply);
	void markAsSolved(Topic topic, Answer solution);
	void close(Topic topic);
}
