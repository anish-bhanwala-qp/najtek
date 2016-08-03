package najtek.infra.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = new User("anishuser", "b");
		/*if (user == null) {
			throw new UsernameNotFoundException("username " + username
					+ " not found");
		}*/

		return user;
	}
}
