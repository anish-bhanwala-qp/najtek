package najtek.database.dao.school.staffmember;

import najtek.database.common.DatabaseInsert;
import najtek.database.common.DatabaseSelect;
import najtek.database.mapper.school.staffmember.SchoolStaffMemberMapper;
import najtek.domain.school.staffmember.SchoolStaffMember;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anish on 30/8/16.
 */
@Repository
public class SchoolStaffMemberDao {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public SchoolStaffMember selectById(long id, long schoolId) {
        DatabaseSelect select = new DatabaseSelect() {
            @Override
            public Object processSelect(SqlSession session) {
                SchoolStaffMemberMapper mapper = session.getMapper(SchoolStaffMemberMapper.class);
                return mapper.selectById(id, schoolId);
            }
        };

        return (SchoolStaffMember) select.fire(sqlSessionFactory);
    }

    public List<SchoolStaffMember> selectBySchoolId(long schoolId) {
        DatabaseSelect select = new DatabaseSelect() {
            @Override
            public Object processSelect(SqlSession session) {
                SchoolStaffMemberMapper mapper = session.getMapper(SchoolStaffMemberMapper.class);
                return mapper.selectBySchoolId(schoolId);
            }
        };

        return (List<SchoolStaffMember>)select.fire(sqlSessionFactory);
    }

    public void insert(SchoolStaffMember schoolStaffMember) {
        DatabaseInsert insert = new DatabaseInsert() {
            @Override
            public void process(SqlSession session) {
                SchoolStaffMemberMapper mapper = session.getMapper(SchoolStaffMemberMapper.class);
                mapper.insert(schoolStaffMember);
            }
        };

        insert.fire(sqlSessionFactory);
    }
}
