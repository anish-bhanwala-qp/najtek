package najtek.domain.user;

import najtek.database.dao.user.UserDao;
import najtek.database.dao.user.UserRoleDao;
import najtek.infra.navigation.NavigationLink;
import najtek.infra.user.User;
import najtek.infra.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anish on 27/8/16.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements HttpSessionListener {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private OrganizationService organizationService;


    private User user;
    private List<UserRole> userRoleList;

    public void addToCache(User user) {
        this.user = user;
        this.user.setUserRoleList(getUserRoleList());
    }

    public User getUser() {
        /*if (user != null) {
            logger.info("User Service. Getting user by email");
            user = userDao.selectByUsername(email);
        }*/
        return user;
    }

    public List<UserRole> getUserRoleList() {
        if (userRoleList == null) {
            logger.info("Fetching UserRoleList from database");
            userRoleList = userRoleDao.selectByUserId(user.getId());
        }
        return userRoleList;
    }

    public List<User> findUsersWithUsernameLike(String username) {
        return userDao.findUsersWithUsernameLike(username);
    }

    public List<User> findUsersWithEmailAddressLike(String emailAddress) {
        return userDao.findUsersWithEmailAddressLike(emailAddress);
    }

    public List<User> findUsersWithOrganizationId(long organizationId) {
        return userDao.findUsersWithOrganizationId(organizationId);
    }

    public User addUser(User user) throws ValidationException {
        validateAddUser(user);

        userDao.insert(user);
        return user;
    }

    private void validateAddUser(User user) throws ValidationException {
        String username = user.getUsername();
        List<User> users = findUsersWithUsernameLike(username);
        if (!users.isEmpty()) {
            FieldError fieldError = new FieldError("User",
                    "username", "user.username.already.exists.error");
            throw new ValidationException(fieldError);
        }
        Organization organization = organizationService.getById(user.getOrganizationId());
        Assert.notNull(organization, "Organization should not be null");
    }

    private String getUsernameFromEmailAddress(String emailAddress) {
        return emailAddress.split("@")[0];
    }

    public List<NavigationLink> getNavigationLinkList() {
        List<NavigationLink> navigationLinkList = new ArrayList<>();
        for (UserRole userRole: getUserRoleList()) {
            if (userRole.getRole() == Role.ADMIN) {
                navigationLinkList.add(new NavigationLink("Admin", "/admin"));
            }
        }
        return navigationLinkList;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("New Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Session Destroyed");
    }

    /*@Override
    public void onApplicationEvent(ApplicationEvent appEvent) {
        if (appEvent instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
            User user = (User) event.getAuthentication().getPrincipal();
            addToCache(user);
            logger.info("User Authenticated");
        }
    }*/
}
