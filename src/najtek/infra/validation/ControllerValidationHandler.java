package najtek.infra.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by anish on 15/8/16.
 */
@Service
public class ControllerValidationHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ValidationError> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return processValidationError(result);
    }

    public List<ValidationError> processValidationError(BindingResult result) {
        List<ValidationError> errors = new ArrayList<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            errors.add(processFieldError(fieldError));
        }

        return errors;
    }

    private ValidationError processFieldError(FieldError error) {
        if (error != null) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String message = messageSource.getMessage(error.getDefaultMessage(), null, currentLocale);
            return  new ValidationError(error.getField(), message);
        }
        return null;
    }

    public ResponseEntity getValidationErrorResponse(BindingResult bindingResult) {
        List<ValidationError> errors = processValidationError(bindingResult);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
