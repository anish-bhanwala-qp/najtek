package najtek.infra.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AppCustomExceptionHandler {

	/*@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler({ MethodArgumentNotValidException.class,
			CustomValidationException.class })*/
	public Map<String, String> processValidationExceptions(
			HttpServletRequest request, Exception ex) {
		Map<String, String> validationErrors = new HashMap<>();
		if (ex instanceof MethodArgumentNotValidException) {
			for (FieldError fieldError : ((MethodArgumentNotValidException) ex)
					.getBindingResult().getFieldErrors()) {
				validationErrors.put(fieldError.getField(),
						fieldError.getDefaultMessage());
			}
		}

		if (ex instanceof ConstraintViolationException) {
			for (ConstraintViolation<?> violation : ((ConstraintViolationException) ex)
					.getConstraintViolations()) {
				validationErrors.put(violation.getPropertyPath().toString(),
						violation.getMessage());
			}
		}

		if (ex instanceof CustomValidationException) {
			validationErrors.putAll(((CustomValidationException) ex)
					.getErrors());
		}
		return validationErrors;
	}
}
