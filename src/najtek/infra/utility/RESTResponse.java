package najtek.infra.utility;

import najtek.infra.validation.ControllerValidationHandler;
import najtek.infra.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * Created by anish on 15/8/16.
 */
@Service
public class RESTResponse {

    @Autowired
    private AlertMessageService alertMessageService;

    @Autowired
    private ControllerValidationHandler controllerValidationHandler;

    public ResponseEntity getSuccessResponse(Object responseBody) {
        return getSuccessResponse(responseBody, null);
    }

    public ResponseEntity getSuccessResponse(Object responseBody,
                                             String alertMessageKey,
                                             String[]... replaceValues) {
        if (StringUtils.isEmpty(alertMessageKey)) {
            return ResponseEntity.ok(responseBody);
        }

        return ResponseEntity.ok()
                .headers(alertMessageService.getSuccessHeader(alertMessageKey, replaceValues))
                .body(responseBody);
    }

    public ResponseEntity getErrorResponse(Object responseBody) {
        return getErrorResponse(responseBody, null);
    }

    public ResponseEntity getErrorResponse(Object responseBody,
                                           String alertMessageKey,
                                           String[]... replaceValues) {
        if (StringUtils.isEmpty(alertMessageKey)) {
            return ResponseEntity.ok(responseBody);
        }

        return ResponseEntity.ok()
                .headers(alertMessageService.getErrorHeader(alertMessageKey, replaceValues))
                .body(responseBody);
    }

    public ResponseEntity getValidationErrorResponse(BindingResult bindingResult) {
        return getValidationErrorResponse(bindingResult, null);
    }

    public ResponseEntity getValidationErrorResponse(BindingResult bindingResult,
                                                     String alertMessageKey,
                                                     String[]... replaceValues) {
        List<ValidationError> errors = controllerValidationHandler
                .processValidationError(bindingResult);
        if (StringUtils.isEmpty(alertMessageKey)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .headers(alertMessageService.getErrorHeader(alertMessageKey, replaceValues))
                .body(errors);
    }
}
