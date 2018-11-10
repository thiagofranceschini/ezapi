package br.com.caelum.fj27.validator.dto;

public class FieldErrorOutputDTO {

	private String field;
	private String message;

	public FieldErrorOutputDTO() {
		super();
	}

	public FieldErrorOutputDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
