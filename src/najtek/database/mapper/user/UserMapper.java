package najtek.database.mapper.user;

import najtek.infra.user.User;

public interface UserMapper {
	public static final String NAMESPACE = "najtek.database.mapper.user.UserMapper";

	User selectById(long id);

	User selectByUsername(String username);
}
