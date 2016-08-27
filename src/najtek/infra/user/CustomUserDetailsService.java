package najtek.infra.user;

import najtek.database.dao.user.OrganizationDao;
import najtek.database.dao.user.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.selectByUsername(username);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		logger.info("*****************************Passsword\n"
				+ passwordEncoder.encode("a"));

		if (user == null) {
			throw new UsernameNotFoundException("username " + username
					+ " not found");
		}

		return user;
	}
}
