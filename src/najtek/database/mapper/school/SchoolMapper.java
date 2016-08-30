package najtek.database.mapper.school;

import najtek.domain.school.School;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by anish on 23/8/16.
 */
@Mapper
public interface SchoolMapper {
    String NAMESPACE = "najtek.database.mapper.school.SchoolMapper";
    String SELECT_COLUMNS = "id, name, primary_user_id as primaryUserId, " +
            "organization_id as organizationId";

    @Select("select " + SELECT_COLUMNS + " from school " +
            "where id = #{id} and organization_id = #{organizationId}")
    School selectById(long id, long organizationId);

    @Select("select " + SELECT_COLUMNS + " from school " +
            "where organization_id = #{organizationId}")
    List<School> selectByOrganizationId(long organizationId);

    /*@Select("select * from organization")
    List<School> selectAll();*/

    @Update("update school set name = #{name}, "
            + "primary_user_id = #{primaryUserId} where id = #{id}")
    void update(School school);

    @Insert("insert into school (name, primary_user_id, organization_id) "
            + "values (#{name}, #{primaryUserId},  #{organizationId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(School school);
}
