package najtek.database.dao.user;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import najtek.database.common.DatabaseQueryUtil;
import najtek.database.mapper.user.UserMapper;
import najtek.infra.user.User;

@Repository
public class UserDao {

	@Autowired
	private DatabaseQueryUtil executeDatabaseQuery;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public User selectById(long id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			return mapper.selectById(id);
		} finally {
			session.close();
		}

	}

	public User selectByUsername(String username) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			return mapper.selectByUsername(username);
		} finally {
			session.close();
		}
	}

	public void update(User user) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.update(user);
		} finally {
			session.close();
		}
	}

	public void insert(User user) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.insert(user);
		} finally {
			session.close();
		}
	}
}
