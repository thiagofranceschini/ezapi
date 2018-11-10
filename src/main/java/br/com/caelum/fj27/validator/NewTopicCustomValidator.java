package br.com.caelum.fj27.validator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.caelum.fj27.dto.input.NewTopicInputDTO;
import br.com.caelum.fj27.model.PossibleSpam;
import br.com.caelum.fj27.model.User;
import br.com.caelum.fj27.model.topic_domain.Topic;
import br.com.caelum.fj27.repositories.TopicRepository;

public class NewTopicCustomValidator implements Validator {

	private final TopicRepository topicRepository;
	private User loggedUser;

	@Override
	public boolean supports(Class<?> clazz) {
		return NewTopicInputDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Instant oneHourAgo = Instant.now().minus(1, ChronoUnit.HOURS);
		List<Topic> topics = topicRepository.findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(loggedUser,
				oneHourAgo);
		PossibleSpam possibleSpam = new PossibleSpam(topics);
		if (possibleSpam.hasTopicLimitExceeded()) {
			long minutesToNextTopic = possibleSpam.minutesToNextTopic(oneHourAgo);
			errors.reject("NewTopicInputDTO.limitExceeded", new Object[] { minutesToNextTopic },
					"O limite individual de novos tópicos por hora foi excedido");
		}
	}

	public NewTopicCustomValidator(TopicRepository topicRepository, User loggeduser) {
		this.topicRepository = topicRepository;
		this.loggedUser = loggeduser;
	}

}
