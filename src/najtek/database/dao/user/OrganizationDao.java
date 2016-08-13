package najtek.database.dao.user;

import najtek.database.mapper.user.OrganizationMapper;
import najtek.domain.user.Organization;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizationDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public Organization selectById(long id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			OrganizationMapper mapper = session.getMapper(OrganizationMapper.class);
			return mapper.selectById(id);
		} finally {
			session.close();
		}

	}

	public void update(Organization organization) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			OrganizationMapper mapper = session.getMapper(OrganizationMapper.class);
			mapper.update(organization);
		} finally {
			session.close();
		}
	}

	public void insert(Organization organization) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			OrganizationMapper mapper = session.getMapper(OrganizationMapper.class);
			mapper.insert(organization);
		} finally {
			session.close();
		}
	}
}
