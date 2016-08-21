package najtek.database.mapper.user;

import najtek.domain.user.Organization;

import org.apache.ibatis.annotations.*;

@Mapper
public interface OrganizationMapper {
	public static final String NAMESPACE = "najtek.database.mapper.user.OrganizationMapper";
	public static final String DEFAULT_DATABASE_HANDLER = "#{defaultDatabase "
			+ ", javaType=String, jdbcType=VARCHAR"
			+ ", typeHandler=najtek.database.mapper.user.AppDatabaseTypeHandler}";

	@Select("select * from organization where id = #{id}")
	Organization selectById(long id);

	@Update("update organization set name = #{name}, "
			+ "primary_user_id = #{primaryUserId}, default_database = "
			+ DEFAULT_DATABASE_HANDLER + " " + "where id = #{id}")
	void update(Organization organization);

	@Insert("insert into organization (name, primary_user_id, default_database) "
			+ "values (#{name}, #{primaryUserId}, "
			+ DEFAULT_DATABASE_HANDLER + ")")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(Organization organization);
}
