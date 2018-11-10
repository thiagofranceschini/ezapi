package br.com.caelum.fj27.validator.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDTO {

	private List<String> globalErrorMessages = new ArrayList<>();
	private List<FieldErrorOutputDTO> fieldErrors = new ArrayList<>();

	public void addError(String message) {
		globalErrorMessages.add(message);
	}

	public void addFieldError(String field, String message) {
		FieldErrorOutputDTO fieldError = new FieldErrorOutputDTO(field, message);
		fieldErrors.add(fieldError);
	}

	public List<String> getGlobalErrorMessages() {
		return globalErrorMessages;
	}

	public List<FieldErrorOutputDTO> getFieldErrorOutputDTO() {
		return fieldErrors;
	}

	public int getNumberOfErrors() {
		return this.globalErrorMessages.size() + this.fieldErrors.size();
	}
}
