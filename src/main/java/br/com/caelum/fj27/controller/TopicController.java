package br.com.caelum.fj27.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.fj27.DTO.TopicBriefOutputDTO;
import br.com.caelum.fj27.DTO.TopicOutputDTO;
import br.com.caelum.fj27.dto.input.NewTopicInputDTO;
import br.com.caelum.fj27.dto.input.TopicSearchInputDto;
import br.com.caelum.fj27.model.User;
import br.com.caelum.fj27.model.topic_domain.Topic;
import br.com.caelum.fj27.repositories.CourseRepository;
import br.com.caelum.fj27.repositories.TopicRepository;
import br.com.caelum.fj27.validator.NewTopicCustomValidator;

@RestController
public class TopicController {

	@Autowired
	private TopicRepository repository;
	@Autowired
	private CourseRepository courseRepository;

	@GetMapping("/api/topics")
	public Page<TopicBriefOutputDTO> listTopics(TopicSearchInputDto topicSearch,
			@PageableDefault(sort = "creationInstant", direction = Sort.Direction.DESC) Pageable pageable) {

		Specification<Topic> topicSearchspecification = topicSearch.build();

		Page<Topic> topics = repository.findAll(topicSearchspecification, pageable);

		return TopicBriefOutputDTO.listFromTopics(topics);
	}

	@PostMapping("/api/topics")
	public ResponseEntity<?> createTopic(@RequestBody @Valid NewTopicInputDTO newTopicDto,
			@AuthenticationPrincipal User loggedUser, UriComponentsBuilder uriBuilder) {

		Topic topic = newTopicDto.build(loggedUser, this.courseRepository);
		this.repository.save(topic);
		URI path = uriBuilder.path("api/topics/{id}").buildAndExpand(topic.getId()).toUri();

		return ResponseEntity.created(path).body(new TopicOutputDTO(topic));
	}

	@InitBinder("newTopicInputDTO")
	public void initBinder(WebDataBinder binder, @AuthenticationPrincipal User loggedUser) {
		binder.addValidators(new NewTopicCustomValidator(this.repository, loggedUser));
	}

}
