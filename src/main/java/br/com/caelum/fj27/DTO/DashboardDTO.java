package br.com.caelum.fj27.DTO;

import java.util.List;

import br.com.caelum.fj27.model.topic_domain.Topic;

public class DashboardDTO {

	private String categoryName;
	private List<String> subcategories;
	private Integer allTopics;
	private Integer lastWeekTopics;
	private Integer unansweredTopics;

	public DashboardDTO(List<Topic> topics) {
		
		
		
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<String> subcategories) {
		this.subcategories = subcategories;
	}

	public Integer getAllTopics() {
		return allTopics;
	}

	public void setAllTopics(Integer allTopics) {
		this.allTopics = allTopics;
	}

	public Integer getLastWeekTopics() {
		return lastWeekTopics;
	}

	public void setLastWeekTopics(Integer lastWeekTopics) {
		this.lastWeekTopics = lastWeekTopics;
	}

	public Integer getUnansweredTopics() {
		return unansweredTopics;
	}

	public void setUnansweredTopics(Integer unansweredTopics) {
		this.unansweredTopics = unansweredTopics;
	}

}
