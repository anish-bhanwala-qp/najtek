package najtek.domain.user;

import javax.validation.constraints.NotNull;

import najtek.domain.common.DomainObject;

public class UserRole implements DomainObject {
	private long id;
	
	@NotNull
	private Role role;
		
	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}	
}
