package najtek.database.mapper.school.staffmember;

import najtek.domain.school.staffmember.SchoolStaffMemberRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by anish on 30/8/16.
 */
@Mapper
public interface SchoolStaffMemberRoleMapper {
    public static final String NAMESPACE =
            "najtek.database.mapper.school.staffmember.SchoolStaffMemberRoleMapper";
    public static final String TABLE_NAME = "school_staff_member_role";

    @Select("select * from " + TABLE_NAME + " where user_id = #{userId}")
    List<SchoolStaffMemberRole> selectByUserId(long userId);

    @Delete("delete from " + TABLE_NAME + " where id = #{id}")
    void delete(long id);

    @Insert("insert into " + TABLE_NAME +
            " (user_id, role) values (#{userId}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(SchoolStaffMemberRole schoolStaffMemberRole);
}
