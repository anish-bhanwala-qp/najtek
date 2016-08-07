package najtek.web.home;

import java.security.Principal;

import najtek.infra.user.User;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	//@RequestMapping("/home")
	public User user(Principal principal) {
		return (User) ((Authentication) principal).getPrincipal();
	}
}
