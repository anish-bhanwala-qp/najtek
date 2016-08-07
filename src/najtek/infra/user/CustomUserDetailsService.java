package najtek.infra.user;

import najtek.database.common.SelectFromDatabase;
import najtek.database.mapper.user.UserMapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private SelectFromDatabase selectFromDatabase;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = new User("anishuser", "b");
		printAllUsers();
		
		/*
		 * if (user == null) { throw new UsernameNotFoundException("username " +
		 * username + " not found"); }
		 */

		return user;
	}

	private void printAllUsers() {
		try {
			User user = (User) selectFromDatabase.selectOneById(
					sqlSessionFactory, UserMapper.NAMESPACE, "selectUser", 1);
			System.out.println("********************* Username : " + user.getUsername());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
