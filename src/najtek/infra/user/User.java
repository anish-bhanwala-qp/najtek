package najtek.infra.user;

import java.util.*;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import najtek.database.dao.user.OrganizationDao;
import najtek.database.dao.user.UserCache;
import najtek.database.dao.user.UserDao;
import najtek.database.dao.user.UserRoleDao;
import najtek.domain.common.DomainObject;

import najtek.domain.user.Organization;
import najtek.domain.user.Role;
import najtek.domain.user.UserRole;
import najtek.infra.navigation.NavigationLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements UserDetails, DomainObject {

    private static final long serialVersionUID = -2980558110010612251L;

    private long id;

    @NotNull
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;

    private long organizationId;

    @JsonIgnore
    @NotNull
    private String password;

    /*All Non-serializable properties here*/
    private transient UserCache userCache;

    public User() {}


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmailAddress() {
        return username;
    }

    public void setEmailAddress(String emailAddress) {
        this.username = emailAddress;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public List<UserRole> getUserRoles() {
        return userCache.getUserRoles();
    }

    public void initUserCache(UserRoleDao userRoleDao,
                              OrganizationDao organizationDao) {
        userCache = new UserCache(userRoleDao, organizationDao, this);
    }

    public Organization getOrganization() {
        return userCache.getOrganization();
    }

    @JsonProperty("navLinks")
    public List<NavigationLink> setupNavigationLinks() {
        List<NavigationLink> navigationLinks = new ArrayList<>();
        for (UserRole userRole: getUserRoles()) {
            if (userRole.getRole() == Role.ADMIN) {
                navigationLinks.add(new NavigationLink("Admin", "/admin"));
            }
        }
        return navigationLinks;
    }
}
