package br.com.caelum.fj27.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.fj27.DTO.DashboardDTO;
import br.com.caelum.fj27.DTO.TopicBriefOutputDTO;
import br.com.caelum.fj27.dto.input.TopicSearchInputDto;
import br.com.caelum.fj27.model.topic_domain.Topic;
import br.com.caelum.fj27.repositories.TopicRepository;

@RestController
public class TopicController {

	@Autowired
	private TopicRepository repository;

	@GetMapping("/api/topics")
	public Page<TopicBriefOutputDTO> listTopics(TopicSearchInputDto topicSearch,
			@PageableDefault(sort = "creationInstant", direction = Sort.Direction.DESC) Pageable pageable) {
		// findAll specification -> especie de filtro que criamos para passar
		// pra nossa interface

		Specification<Topic> topicSearchspecification = topicSearch.build();

		Page<Topic> topics = repository.findAll(topicSearchspecification, pageable);

		return TopicBriefOutputDTO.listFromTopics(topics);
	}
	
	@GetMapping("/api/topics/dashboard")
	public DashboardDTO findDashBoard(){
		//List<Topic> list = repository.findAll();
		return null;
	}

}
