package najtek.domain.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import najtek.database.common.AppDatabase;
import najtek.domain.common.DomainObject;

public class Organization implements DomainObject {

	private long id;
	
	@NotNull(message = "generic.name.required.error")
	@Size(min = 3, max = 64, message = "generic.name.min.length.error")
	private String name;
	
	private AppDatabase defaultDatabase;
	
	private long primaryUserId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrimaryUserId() {
		return primaryUserId;
	}

	public void setPrimaryUserId(long primaryUserId) {
		this.primaryUserId = primaryUserId;
	}

	public AppDatabase getDefaultDatabase() {
		return defaultDatabase;
	}

	public void setDefaultDatabase(AppDatabase defaultDatabase) {
		this.defaultDatabase = defaultDatabase;
	}	
}
