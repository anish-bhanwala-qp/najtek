package najtek.database.dao.user;

import najtek.database.common.DatabaseInsert;
import najtek.database.common.DatabaseSelect;
import najtek.database.common.DatabaseUpdate;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import najtek.database.mapper.user.UserMapper;
import najtek.infra.user.User;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class UserDao {
	private final Logger logger = LoggerFactory.getLogger(UserDao.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private UserMapper getMapper(SqlSession session) {
		return session.getMapper(UserMapper.class);
	}

	public User selectById(long id) {
		DatabaseSelect select = new DatabaseSelect() {
			@Override
			public Object processSelect(SqlSession session) {
				return getMapper(session).selectById(id);
			}
		};

		return (User)select.fire(sqlSessionFactory);
	}

	public User selectByUsername(String username) {
		DatabaseSelect select = new DatabaseSelect() {
			@Override
			public Object processSelect(SqlSession session) {
				logger.info("************EXECUTING selectByUsername COMMAND**************");
				return  getMapper(session).selectByUsername(username);
			}
		};

		return (User)select.fire(sqlSessionFactory);
	}

	public List<User> findUsersWithUsernameLike(String username) {
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		final String usernameLike = "%" + username + "%";
		DatabaseSelect select = new DatabaseSelect() {
			@Override
			public Object processSelect(SqlSession session) {
				logger.info("************EXECUTING findUsernameLike COMMAND**************");
				return  getMapper(session).findUsersWithUsernameLike(usernameLike);
			}
		};

		return (List<User>)select.fire(sqlSessionFactory);
	}

	public List<User> findUsersWithEmailAddressLike(String emailAddress) {
		if (StringUtils.isEmpty(emailAddress)) {
			return null;
		}
		final String emailAddressLike = "%" + emailAddress + "%";
		DatabaseSelect select = new DatabaseSelect() {
			@Override
			public Object processSelect(SqlSession session) {
				logger.info("************EXECUTING findUsersWithEmailAddressLike COMMAND**************");
				return  getMapper(session).findUsersWithEmailAddressLike(emailAddressLike);
			}
		};

		return (List<User>)select.fire(sqlSessionFactory);
	}

    public List<User> findUsersWithOrganizationId(long organizationId) {
        DatabaseSelect select = new DatabaseSelect() {
            @Override
            public Object processSelect(SqlSession session) {
                logger.info("************EXECUTING findUsersWithOrganizationId COMMAND**************");
                return  getMapper(session).findUsersWithOrganizationId(organizationId);
            }
        };

        return (List<User>)select.fire(sqlSessionFactory);
    }

	public void update(User user) {
		DatabaseUpdate update = new DatabaseUpdate() {
			@Override
			public void process(SqlSession session) {
				getMapper(session).update(user);
			}
		};

		update.fire(sqlSessionFactory);
	}

	public void insert(User user) {
		DatabaseInsert insert = new DatabaseInsert() {
			@Override
			public void process(SqlSession session) {
				getMapper(session).insert(user);
			}
		};

		insert.fire(sqlSessionFactory);
	}
}
