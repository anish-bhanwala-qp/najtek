package najtek.web.auth;

import java.security.Principal;

import najtek.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	@RequestMapping("/api/user")
	public Principal user(Principal principal) {
		logger.info("********************************* AuthenticationController");
		if (getCurrentUser() != null) {
		    getCurrentUser().setupNavigationLinks();
        }
		return principal;
	}
}
