package najtek.database.dao.user;

import najtek.database.mapper.user.UserRoleMapper;
import najtek.domain.user.UserRole;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public UserRole selectById(long id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserRoleMapper mapper = session.getMapper(UserRoleMapper.class);
			return mapper.selectByUserId(id);
		} finally {
			session.close();
		}

	}

	public void delete(UserRole userRole) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserRoleMapper mapper = session.getMapper(UserRoleMapper.class);
			mapper.delete(userRole.getId());
		} finally {
			session.close();
		}
	}

	public void insert(UserRole userRole) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserRoleMapper mapper = session.getMapper(UserRoleMapper.class);
			mapper.insert(userRole);
		} finally {
			session.close();
		}
	}
}
