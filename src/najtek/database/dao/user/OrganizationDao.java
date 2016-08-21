package najtek.database.dao.user;

import najtek.database.common.DatabaseInsert;
import najtek.database.common.DatabaseSelect;
import najtek.database.common.DatabaseUpdate;
import najtek.database.mapper.user.OrganizationMapper;
import najtek.domain.user.Organization;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public Organization selectById(long id) {
		DatabaseSelect select = new DatabaseSelect() {
			@Override
			public Object processSelect(SqlSession session) {
				OrganizationMapper mapper = session
						.getMapper(OrganizationMapper.class);
				return mapper.selectById(id);
			}
		};

		return (Organization)select.fire(sqlSessionFactory);
	}

    public Organization selectByName(String name) {
        DatabaseSelect select = new DatabaseSelect() {
            @Override
            public Object processSelect(SqlSession session) {
                OrganizationMapper mapper = session
                        .getMapper(OrganizationMapper.class);
                return mapper.selectByName(name);
            }
        };

        return (Organization)select.fire(sqlSessionFactory);
    }

	public List<Organization> selectAll() {
		DatabaseSelect select = new DatabaseSelect() {
			@Override
			public Object processSelect(SqlSession session) {
				OrganizationMapper mapper = session
						.getMapper(OrganizationMapper.class);
				return mapper.selectAll();
			}
		};

		return (List<Organization>)select.fire(sqlSessionFactory);
	}

	public void update(Organization organization) {
		DatabaseUpdate update = new DatabaseUpdate() {
			@Override
			public void process(SqlSession session) {
				OrganizationMapper mapper = session
						.getMapper(OrganizationMapper.class);
				mapper.update(organization);
			}
		};

		update.fire(sqlSessionFactory);
	}

	public void insert(Organization organization) {
		DatabaseInsert insert = new DatabaseInsert() {
			@Override
			public void process(SqlSession session) {
				OrganizationMapper mapper = session
						.getMapper(OrganizationMapper.class);
				mapper.insert(organization);
			}
		};

		insert.fire(sqlSessionFactory);
	}
}
