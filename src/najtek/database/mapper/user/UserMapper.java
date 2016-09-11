package najtek.database.mapper.user;

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

	@Select("select " + SELECT_COLUMNS + " from user where id = #{id}")
	User selectById(long id);

	@Select("select " + SELECT_COLUMNS + " from user where username = #{username}")
	User selectByUsername(String username);

    @Select("select " + SELECT_COLUMNS + " from user where username like #{username}")
    List<User> findUsersWithUsernameLike(String username);

    @Select("select " + SELECT_COLUMNS + " from user where email_address like #{emailAddress}")
    List<User> findUsersWithEmailAddressLike(String emailAddress);

	@Select("select " + SELECT_COLUMNS + " from user where username like #{username}")
	User findUserWithUsername(String username);

    @Select("select " + SELECT_COLUMNS + " from user where organization_id like #{organizationId}")
    List<User> findUsersWithOrganizationId(long organizationId);

	@Update("update user set username = #{username}, " +
            "password = #{password}," +
            "email_address = #{emailAddress}," +
            "first_name = #{firstName}, middle_name = #{middleName}," +
            "last_name = #{lastName}, organization_id = #{organization_id}" +
            "where id = #{id}")
	void update(User user);

	@Insert("insert into user (username, password, email_address, first_name,"
			+ "middle_name, last_name, creation_timestamp, organization_id)"
			+ "values (#{username}, #{password}, #{emailAddress}, #{firstName}, "
			+ "#{middleName}, #{lastName}, #{creationTimestamp}, #{organizationId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(User user);
}
