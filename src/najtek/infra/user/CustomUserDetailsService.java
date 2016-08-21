package najtek.infra.user;

import najtek.database.dao.user.OrganizationDao;
import najtek.database.dao.user.UserDao;

import najtek.database.dao.user.UserRoleDao;
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

	@Autowired
	private UserDao userDao;

	@Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private UserRoleDao userRoleDao;

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

		user.initUserCache(userRoleDao, organizationDao);

		return user;
	}
}
