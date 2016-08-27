package najtek.web.auth;

import java.security.Principal;

import najtek.domain.user.UserService;
import najtek.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;

	@RequestMapping("/api/user")
	public Principal user(Principal principal) {
		logger.info("********************************* AuthenticationController");
		if (getCurrentUser() != null) {
		    //return userService.;
        }
		return principal;
	}
}
