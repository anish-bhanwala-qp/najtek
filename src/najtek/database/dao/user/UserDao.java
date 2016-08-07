package najtek.database.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import najtek.database.common.DatabaseQueryUtil;
import najtek.database.mapper.user.UserMapper;
import najtek.infra.user.User;

@Repository
public class UserDao {

	@Autowired
	private DatabaseQueryUtil executeDatabaseQuery;

	public User selectById(long id) {
		User user = new User();
		user.setId(id);
		return (User) executeDatabaseQuery.selectOne(user,
				UserMapper.NAMESPACE, "selectById");
	}

	public User selectByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		return (User) executeDatabaseQuery.selectOne(user,
				UserMapper.NAMESPACE, "selectByUsername");
	}
}
