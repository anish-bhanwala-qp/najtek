package najtek.infra.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * Created by anish on 15/8/16.
 */
@Service
public class AlertMessageService {
    public static final String SUCCESS_HEADER = "Success-Message";
    public static final String ERROR_HEADER = "Error-Message";

    @Autowired
    private MessageSourceUtil messageSourceUtil;

    public HttpHeaders getSuccessHeader(String messageKey, String[]... replaceValues) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SUCCESS_HEADER, messageSourceUtil.getMessageByKey(messageKey, replaceValues));
        return headers;
    }

    public HttpHeaders getErrorHeader(String messageKey, String[]... replaceValues) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(ERROR_HEADER, messageSourceUtil.getMessageByKey(messageKey, replaceValues));
        return headers;
    }
}
