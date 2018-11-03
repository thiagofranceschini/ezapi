package br.com.caelum.fj27.DTO;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.data.domain.Page;

import br.com.caelum.fj27.model.topic_domain.Topic;
import br.com.caelum.fj27.model.topic_domain.TopicStatus;

public class TopicBriefOutputDTO {

	private Long id;
	private String shortDescription;
	private long secondsSinceLastUpdate;
	private String ownerName;
	private String courseName;
	private String subcategoryName;
	private String categoryName;
	private int numberOfResponses;
	private boolean solved;

	public TopicBriefOutputDTO(Topic topic) {
		super();
		this.id = topic.getId();
		this.shortDescription = topic.getShortDescription();
		this.secondsSinceLastUpdate = getSecondSinceLastUpdate(topic.getLastUpdate());
		this.ownerName = topic.getOwnerName();
		this.courseName = topic.getCourse().getName();
		this.subcategoryName = topic.getCourse().getSubcategoryName();
		this.categoryName = topic.getCourse().getCategoryName();
		this.numberOfResponses = topic.getNumberOfAnswers();
		this.solved = TopicStatus.SOLVED.equals(topic.getStatus());
	}

	public static Page<TopicBriefOutputDTO> listFromTopics(Page<Topic> topicPage) {
		return topicPage.map(TopicBriefOutputDTO::new);
	}

	private long getSecondSinceLastUpdate(Instant lastUpdate) {

		return Duration.between(lastUpdate, Instant.now()).get(ChronoUnit.SECONDS);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortdescription) {
		this.shortDescription = shortdescription;
	}

	public long getSecondSinceLastUpdate() {
		return secondsSinceLastUpdate;
	}

	public void setSecondSinceLastUpdate(long secondSinceLastUpdate) {
		this.secondsSinceLastUpdate = secondSinceLastUpdate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownername) {
		this.ownerName = ownername;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getNumberOfResponses() {
		return numberOfResponses;
	}

	public void setNumberOfResponses(int numberOfResponse) {
		this.numberOfResponses = numberOfResponse;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

}
