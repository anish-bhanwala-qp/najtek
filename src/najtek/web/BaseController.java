package najtek.web;

import najtek.infra.user.User;
import org.springframework.security.core.Authentication;

import java.security.Principal;

public class BaseController {
    protected User getUser(Principal principal) {
        if (principal == null) {
            return null;
        }
        return (User) ((Authentication) principal).getPrincipal();
    }
}
