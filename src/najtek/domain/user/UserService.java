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
import java.util.regex.Pattern;

/**
 * Created by anish on 27/8/16.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements HttpSessionListener {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z0-9_-]{3,127}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-z0-9_-]{7,127}$");


    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private OrganizationService organizationService;


    private User user;

    public void addToCache(User user) {
        this.user = user;
    }

    public User getUser() {
        /*if (user != null) {
            logger.info("User Service. Getting user by email");
            user = userDao.selectByUsername(email);
        }*/
        return user;
    }

   /* public List<UserRole> getUserRoleList() {
        if (userRoleList == null) {
            logger.info("Fetching UserRoleList from database");
            userRoleList = userRoleDao.selectByUserId(user.getId());
        }
        return userRoleList;
    }*/

    public List<User> findUsersWithUsernameLike(String username) {
        return userDao.findUsersWithUsernameLike(username);
    }

    public User findUserWithUsername(String username) {
        return userDao.findUserWithUsername(username);
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

    private User getUserById(long id) {
        return userDao.selectById(id);
    }

    public User updateUser(User userFromJSON) throws ValidationException {
        User user = getUserById(userFromJSON.getId());

        validateAndSetUserProperties(user, userFromJSON);

        userDao.update(user);
        return user;
    }

    private void validateAndSetUserProperties(User userFromDb, User userFromJSON) throws ValidationException {
        boolean isUsernameEdited = !userFromDb.getUsername().equals(userFromJSON.getUsername());
        if (isUsernameEdited) {
            validateUsernameFormat(userFromJSON.getUsername());
            validateUsernameDoesNotExist(userFromJSON.getUsername());
            userFromDb.setUsername(userFromJSON.getUsername());
        }
        boolean isPasswordEdited = !StringUtils.isEmpty(userFromJSON.getPassword()) &&
                !userFromDb.getPassword().equals(userFromJSON.getPassword());
        if (isPasswordEdited) {
            validatePasswordFormat(userFromJSON.getPassword());
            userFromDb.setPassword(userFromJSON.getPassword());
        }

        userFromDb.setFirstName(userFromJSON.getFirstName());
        userFromDb.setMiddleName(userFromJSON.getMiddleName());
        userFromDb.setLastName(userFromJSON.getLastName());
    }

    private void validateAddUser(User user) throws ValidationException {
        validateUsernameFormat(user.getUsername());
        validatePasswordFormat(user.getPassword());
        validateUsernameDoesNotExist(user.getUsername());

        Organization organization = organizationService.getById(user.getOrganizationId());
        Assert.notNull(organization, "Organization should not be null");
    }

    private void validateUsernameFormat(String username) throws ValidationException {
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            FieldError fieldError = new FieldError("User",
                    "username", "user.username.invalid.format.error");
            throw new ValidationException(fieldError);
        }
    }

    private void validatePasswordFormat(String password) throws ValidationException {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            FieldError fieldError = new FieldError("User",
                    "password", "user.password.invalid.format.error");
            throw new ValidationException(fieldError);
        }
    }

    private void validateUsernameDoesNotExist(String username) throws ValidationException {
        List<User> users = findUsersWithUsernameLike(username);
        if (!users.isEmpty()) {
            FieldError fieldError = new FieldError("User",
                    "username", "user.username.already.exists.error");
            throw new ValidationException(fieldError);
        }
    }

    private String getUsernameFromEmailAddress(String emailAddress) {
        return emailAddress.split("@")[0];
    }

   /* public List<NavigationLink> getNavigationLinkList() {
        List<NavigationLink> navigationLinkList = new ArrayList<>();
        for (UserRole userRole: getUserRoleList()) {
            if (userRole.getRole() == Role.ADMIN) {
                navigationLinkList.add(new NavigationLink("Admin", "/admin"));
            }
        }
        return navigationLinkList;
    }*/

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
