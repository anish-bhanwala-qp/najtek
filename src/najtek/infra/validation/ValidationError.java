package najtek.infra.validation;

/**
 * Created by anish on 15/8/16.
 */
public class ValidationError {
    private String fieldName;
    private String errorMessage;

    public ValidationError(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
