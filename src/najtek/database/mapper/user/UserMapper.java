package najtek.database.mapper.user;

import najtek.domain.user.UserRole;
import org.apache.ibatis.annotations.*;

import najtek.infra.user.User;

import java.util.List;

@Mapper
public interface UserMapper {
	String NAMESPACE = "najtek.database.mapper.user.UserMapper";
	String TABLE_NAME = "user";
    String SELECT_COLUMNS = "id, " +
            "username, " +
            "password, " +
            "email_address as emailAddress," +
            "first_name as firstName, " +
            "middle_name as middleName, " +
            "last_name as lastName, " +
            "creation_timestamp as creationTimestamp, " +
            "organization_id as organizationId ";

    @Select("select * from user_role where user_id = #{userId}")
    List<UserRole> selectUserRoles(long userId);

	@Select("select " + SELECT_COLUMNS + " from user where id = #{id}")
    @Results(value = {
            @Result(property="id", column = "id"),
            @Result(property="username", column = "username"),
            @Result(property="password", column = "password"),
            @Result(property="emailAddress", column = "email_address"),
            @Result(property="firstName", column = "first_name"),
            @Result(property="middleName", column = "middle_name"),
            @Result(property="lastName", column = "last_name"),
            @Result(property="creationTimestamp", column = "creation_timestmap"),
            @Result(property="organizationId", column = "organization_id"),
            @Result(property="userRoles", column="id", javaType= List.class, many=@Many(select="selectUserRoles"))
    })
    User selectById(long id);

	@Select("select " + SELECT_COLUMNS + " from user where username = #{username}")
    @Results(value = {
            @Result(property="id", column = "id"),
            @Result(property="username", column = "username"),
            @Result(property="password", column = "password"),
            @Result(property="emailAddress", column = "email_address"),
            @Result(property="firstName", column = "first_name"),
            @Result(property="middleName", column = "middle_name"),
            @Result(property="lastName", column = "last_name"),
            @Result(property="creationTimestamp", column = "creation_timestmap"),
            @Result(property="organizationId", column = "organization_id"),
            @Result(property="userRoles", column="id", javaType= List.class, many=@Many(select="selectUserRoles"))
    })
	User selectByUsername(String username);

    @Select("select " + SELECT_COLUMNS + " from user where username like #{username}")
    List<User> findUsersWithUsernameLike(String username);

    @Select("select " + SELECT_COLUMNS + " from user where email_address like #{emailAddress}")
    List<User> findUsersWithEmailAddressLike(String emailAddress);

	@Select("select " + SELECT_COLUMNS + " from user where username like #{username}")
    @Results(value = {
            @Result(property="id", column = "id"),
            @Result(property="username", column = "username"),
            @Result(property="password", column = "password"),
            @Result(property="emailAddress", column = "email_address"),
            @Result(property="firstName", column = "first_name"),
            @Result(property="middleName", column = "middle_name"),
            @Result(property="lastName", column = "last_name"),
            @Result(property="creationTimestamp", column = "creation_timestmap"),
            @Result(property="organizationId", column = "organization_id"),
            @Result(property="userRoles", column="id", javaType= List.class, many=@Many(select="selectUserRoles"))
    })
	User findUserWithUsername(String username);

    @Select("select " + SELECT_COLUMNS + " from user where organization_id like #{organizationId}")
    @Results(value = {
            @Result(property="id", column = "id"),
            @Result(property="username", column = "username"),
            @Result(property="password", column = "password"),
            @Result(property="emailAddress", column = "email_address"),
            @Result(property="firstName", column = "first_name"),
            @Result(property="middleName", column = "middle_name"),
            @Result(property="lastName", column = "last_name"),
            @Result(property="creationTimestamp", column = "creation_timestmap"),
            @Result(property="organizationId", column = "organization_id"),
            @Result(property="userRoles", column="id", javaType= List.class, many=@Many(select="selectUserRoles"))
    })
    List<User> findUsersWithOrganizationId(long organizationId);

	@Update("update user set username = #{username}, " +
            "password = #{password}, " +
            "email_address = #{emailAddress}, " +
            "first_name = #{firstName}, " +
            "middle_name = #{middleName}, " +
            "last_name = #{lastName}, " +
            "organization_id = #{organizationId} " +
            "where id = #{id}")
	void update(User user);

	@Insert("insert into user (username, password, email_address, first_name,"
			+ "middle_name, last_name, creation_timestamp, organization_id)"
			+ "values (#{username}, #{password}, #{emailAddress}, #{firstName}, "
			+ "#{middleName}, #{lastName}, #{creationTimestamp}, #{organizationId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(User user);
}
