package najtek.database.mapper.user;

import najtek.domain.user.UserRole;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRoleMapper {
	public static final String NAMESPACE = "najtek.database.mapper.user.UserRoleMapper";	

	@Select("select * from user_role where user_id = #{userId}")
    List<UserRole> selectByUserId(long userId);

	@Delete("delete from user_role where id = #{id}")
	void delete(long id);

	@Insert("insert into user_role (user_id, role) values (#{userId}, #{role})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(UserRole userRole);
}
