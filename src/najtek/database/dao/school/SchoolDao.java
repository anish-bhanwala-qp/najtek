package najtek.database.dao.school;

import najtek.database.common.DatabaseInsert;
import najtek.database.common.DatabaseSelect;
import najtek.database.common.DatabaseUpdate;
import najtek.database.mapper.school.SchoolMapper;
import najtek.domain.school.School;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anish on 23/8/16.
 */
@Repository
public class SchoolDao {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public School selectById(long id, long organizationId) {
        DatabaseSelect select = new DatabaseSelect() {
            @Override
            public Object processSelect(SqlSession session) {
                SchoolMapper mapper = session.getMapper(SchoolMapper.class);
                return mapper.selectById(id, organizationId);
            }
        };

        return (School) select.fire(sqlSessionFactory);
    }

    public List<School> selectByOrganizationId(long organizationId) {
        DatabaseSelect select = new DatabaseSelect() {
            @Override
            public Object processSelect(SqlSession session) {
                SchoolMapper mapper = session.getMapper(SchoolMapper.class);
                return mapper.selectByOrganizationId(organizationId);
            }
        };

        return (List<School>)select.fire(sqlSessionFactory);
    }

   /* public List<School> selectAll() {
        DatabaseSelect select = new DatabaseSelect() {
            @Override
            public Object processSelect(SqlSession session) {
                SchoolMapper mapper = session
                        .getMapper(SchoolMapper.class);
                return mapper.selectAll();
            }
        };

        return (List<School>)select.fire(sqlSessionFactory);
    }*/

    public void update(School school) {
        DatabaseUpdate update = new DatabaseUpdate() {
            @Override
            public void process(SqlSession session) {
                SchoolMapper mapper = session.getMapper(SchoolMapper.class);
                mapper.update(school);
            }
        };

        update.fire(sqlSessionFactory);
    }

    public void insert(School school) {
        DatabaseInsert insert = new DatabaseInsert() {
            @Override
            public void process(SqlSession session) {
                SchoolMapper mapper = session.getMapper(SchoolMapper.class);
                mapper.insert(school);
            }
        };

        insert.fire(sqlSessionFactory);
    }
}
