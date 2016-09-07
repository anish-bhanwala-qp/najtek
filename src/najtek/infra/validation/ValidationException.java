package najtek.infra.validation;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by anish on 7/9/16.
 */
public class ValidationException extends Exception {
    private FieldError fieldErrors[];

    public ValidationException(FieldError... fieldError) {
        this.fieldErrors = fieldErrors;
    }

    public FieldError[] getFieldErrors() {
        return fieldErrors;
    }
}
