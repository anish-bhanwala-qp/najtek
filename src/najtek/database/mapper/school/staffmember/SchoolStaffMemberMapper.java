package najtek.database.mapper.school.staffmember;

import najtek.database.mapper.user.UserMapper;
import najtek.domain.school.staffmember.SchoolStaffMember;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by anish on 30/8/16.
 */
@Mapper
public interface SchoolStaffMemberMapper {
    String NAMESPACE = "najtek.database.mapper.school.staffmember.SchoolStaffMemberMapper";
    String TABLE_NAME = "school_staff_member";
    String SELECT_COLUMNS = "select " +
            SchoolStaffMemberMapper.TABLE_NAME + ".id as id, " +
            SchoolStaffMemberMapper.TABLE_NAME + ".user_id as userId, " +
            SchoolStaffMemberMapper.TABLE_NAME + ".school_id as schoolId, " +
            UserMapper.TABLE_NAME + ".email_address as emailAddress, " +
            UserMapper.TABLE_NAME + ".first_name as firstName, " +
            UserMapper.TABLE_NAME + ".middle_name as middle_Name, " +
            UserMapper.TABLE_NAME + ".last_name as lastName ";
    String SELECT_FROM = " from " + SchoolStaffMemberMapper.TABLE_NAME + ", " +
            UserMapper.TABLE_NAME + " ";
    String SELECT_WHERE = " where " +
            SchoolStaffMemberMapper.TABLE_NAME + ".school_id = #{schoolId} and " +
            SchoolStaffMemberMapper.TABLE_NAME + ".user_id = " + UserMapper.TABLE_NAME + ".id ";


    @Select(SELECT_COLUMNS + SELECT_FROM + SELECT_WHERE + " and id = #{id}")
    SchoolStaffMember selectById(long id, long schoolId);

    @Select(SELECT_COLUMNS + SELECT_FROM + SELECT_WHERE )
    List<SchoolStaffMember> selectBySchoolId(long schoolId);

   /* @Update("update " + TABLE_NAME + " set school_id = #{schoolId} where id = #{id}")
    void changeSchool(SchoolStaffMember schoolStaffMember);*/

    @Insert("insert into " + TABLE_NAME + " (user_id, school_id) "
            + "values (#{userId}, #{schoolId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(SchoolStaffMember schoolStaffMember);
}
