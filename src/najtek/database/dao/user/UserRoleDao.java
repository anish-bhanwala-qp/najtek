package najtek.database.dao.user;

import najtek.database.common.DatabaseInsert;
import najtek.database.common.DatabaseSelect;
import najtek.database.common.DatabaseUpdate;
import najtek.database.mapper.user.UserRoleMapper;
import najtek.domain.user.UserRole;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDao {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public List<UserRole> selectByUserId(long userId) {
		DatabaseSelect select = new DatabaseSelect() {
			@Override
			public Object processSelect(SqlSession session) {
				return getMapper(session).selectByUserId(userId);
			}
		};

		return (List<UserRole>) select.fire(sqlSessionFactory);
	}

	public void delete(UserRole userRole) {
		DatabaseUpdate update = new DatabaseUpdate() {
			@Override
			public void process(SqlSession session) {
				getMapper(session).delete(userRole.getId());
			}
		};

		update.fire(sqlSessionFactory);
	}

	public void insert(UserRole userRole) {
		DatabaseInsert insert = new DatabaseInsert() {
			@Override
			public void process(SqlSession session) {
				getMapper(session).insert(userRole);
			}
		};

		insert.fire(sqlSessionFactory);
	}

	private UserRoleMapper getMapper(SqlSession session) {
		return session.getMapper(UserRoleMapper.class);
	}
}
