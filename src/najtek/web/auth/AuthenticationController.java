package najtek.web.auth;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	@RequestMapping("/api/user")
	public Principal user(Principal principal) {
		logger.info("********************************* AuthenticationController");
		return principal;
	}
}
