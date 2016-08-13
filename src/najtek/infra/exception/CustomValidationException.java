package najtek.infra.exception;

import java.util.Map;

public class CustomValidationException extends RuntimeException {

	private static final long serialVersionUID = 8347119250363399412L;

	private Map<String, String> errors;

	public CustomValidationException(Map<String, String> errors) {
		this.errors = errors;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
}