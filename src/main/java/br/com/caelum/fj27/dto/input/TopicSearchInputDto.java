package br.com.caelum.fj27.dto.input;

import java.util.ArrayList;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.caelum.fj27.model.topic_domain.Topic;
import br.com.caelum.fj27.model.topic_domain.TopicStatus;

public class TopicSearchInputDto {

	private TopicStatus status;
	private String categoryName;

	public Specification<Topic> build() {
		return (root, criteriaQuery, criteriaBuilder) -> {
			ArrayList<Predicate> predicates = new ArrayList<>();
			if (status != null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), status));
			}

			if (categoryName != null) {
				Path<String> categoryNamePath = root.get("course").get("subcategory").get("category").get("name");
				predicates.add(criteriaBuilder.equal(categoryNamePath, categoryName));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	public TopicStatus status() {
		return status;
	}

	public void setTopicStatus(TopicStatus status) {
		this.status = status;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
