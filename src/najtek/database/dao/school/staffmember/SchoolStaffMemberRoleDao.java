package najtek.database.dao.school.staffmember;

import najtek.database.common.DatabaseInsert;
import najtek.database.common.DatabaseSelect;
import najtek.database.common.DatabaseUpdate;
import najtek.database.mapper.school.staffmember.SchoolStaffMemberRoleMapper;
import najtek.domain.school.staffmember.SchoolStaffMemberRole;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anish on 30/8/16.
 */
@Repository
public class SchoolStaffMemberRoleDao {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public List<SchoolStaffMemberRole> selectByUserId(long userId) {
        DatabaseSelect select = new DatabaseSelect() {
            @Override
            public Object processSelect(SqlSession session) {
                return getMapper(session).selectByUserId(userId);
            }
        };

        return (List<SchoolStaffMemberRole>) select.fire(sqlSessionFactory);
    }

    public void delete(SchoolStaffMemberRole schoolStaffMemberRole) {
        DatabaseUpdate update = new DatabaseUpdate() {
            @Override
            public void process(SqlSession session) {
                getMapper(session).delete(schoolStaffMemberRole.getId());
            }
        };

        update.fire(sqlSessionFactory);
    }

    public void insert(SchoolStaffMemberRole schoolStaffMemberRole) {
        DatabaseInsert insert = new DatabaseInsert() {
            @Override
            public void process(SqlSession session) {
                getMapper(session).insert(schoolStaffMemberRole);
            }
        };

        insert.fire(sqlSessionFactory);
    }

    private SchoolStaffMemberRoleMapper getMapper(SqlSession session) {
        return session.getMapper(SchoolStaffMemberRoleMapper.class);
    }
}
