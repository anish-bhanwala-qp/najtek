package najtek.infra.user;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import najtek.domain.common.DomainObject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements UserDetails, DomainObject {

	private static final long serialVersionUID = -2980558110010612251L;
	
	private long id;
	
	@NotNull
	private String emailAddress;
	private String firstName;
	private String middleName;
	private String lastName;
	
	@JsonIgnore
	@NotNull
	private String password;
	
	public User() {}
	
	public User(String username, String password) {
		this.emailAddress = username;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	
	@Override
	public String getUsername() {
		return emailAddress;
	}

	public void setUsername(String username) {
		this.emailAddress = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
}
