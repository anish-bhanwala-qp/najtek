package najtek.database.mapper.user;

import najtek.infra.user.User;

public interface UserMapper {	
	public static final String NAMESPACE = "najtek.database.mapper.user.UserMapper";
	
	User selectUser(long id);
}
