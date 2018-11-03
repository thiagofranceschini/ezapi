package br.com.caelum.fj27.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import br.com.caelum.fj27.model.topic_domain.Topic;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String content;

	private Instant creationTime = Instant.now();

	private boolean solution = false;

	@ManyToOne
	private Topic topic;

	@ManyToOne
	private User owner;


	/**
	 * @deprecated
	 */
	public Answer() {
	}

	public Answer(String content, Topic topic, User owner) {
		this.content = content;
		this.topic = topic;
		this.owner = owner;

		topic.registerNewReply(this);
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Instant getCreationTime() {
		return creationTime;
	}

	public boolean isSolution() {
		return solution;
	}
	
	public Topic getTopic() {
		return topic;
	}


	public String getOwnerName() {
		return this.owner.getName();
	}

	public User getOwner() {
		return owner;
	}
}
