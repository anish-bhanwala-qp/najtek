package najtek.infra.user;

import najtek.database.dao.user.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.selectByUsername(username);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("*****************************Passsword\n"
				+ passwordEncoder.encode("a"));

		if (user == null) {
			throw new UsernameNotFoundException("username " + username
					+ " not found");
		}

		return user;
	}
}
