package najtek.web;

import najtek.infra.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    protected User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            logger.info("Principal is null");
            return null;
        }
        logger.info("Principal is NOT null");
        return (User) principal;
    }
}
