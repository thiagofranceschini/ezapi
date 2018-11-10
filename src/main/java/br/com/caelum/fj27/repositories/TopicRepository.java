package br.com.caelum.fj27.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import br.com.caelum.fj27.model.User;
import br.com.caelum.fj27.model.topic_domain.Topic;

public interface TopicRepository extends Repository<Topic, Long>, JpaSpecificationExecutor<Topic>{

	@Query("select t from Topic t")
	List<Topic>list();
	
	List<Topic> findAll();

	Page<Topic> findAll(Specification<Topic> topicSearchspecification, Pageable pageable);

	void save(Topic topic);

	List<Topic> findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(User loggedUser, Instant oneHourAgo);
}
