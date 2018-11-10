package br.com.caelum.fj27.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.caelum.fj27.model.Course;
import br.com.caelum.fj27.model.User;
import br.com.caelum.fj27.model.topic_domain.Topic;
import br.com.caelum.fj27.repositories.CourseRepository;

public class NewTopicInputDTO {
	@NotBlank
	@Size(min = 10)
	private String shortDescription;
	
	@NotBlank
	@Size(min = 10)
	private String content;
	
	@NotBlank
	@Size(min = 4)
	private String courseName;

	public Topic build(User owner, CourseRepository repository) {
		Course course = repository.findByName(this.courseName);
		return new Topic(this.shortDescription, this.content, owner, course);
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
