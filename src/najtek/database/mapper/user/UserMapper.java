package najtek.database.mapper.user;

import org.apache.ibatis.annotations.*;

import najtek.infra.user.User;

@Mapper
public interface UserMapper {
	String NAMESPACE = "najtek.database.mapper.user.UserMapper";
	String TABLE_NAME = "user";

	@Select("select * from user where id = #{id}")
	User selectById(long id);

	@Select("select * from user where email_address = #{username}")
	User selectByUsername(String username);

	@Update("update user set email_address = "
			+ "#{username}, password = #{password},"
			+ "first_name = #{firstName}, middle_name = #{middleName},"
			+ "last_name = #{lastName}, organization_id = #{organization_id}"
			+ "where id = #{id}")
	void update(User user);

	@Insert("insert into user (email_address, password, first_name,"
			+ "middle_name, last_name, creation_timestamp, organization_id)"
			+ "values (#{emailAddress}, #{firstName}, "
			+ "#{middleName}, #{lastName}, #{creationTimestamp}, #{organizationId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(User user);
}
