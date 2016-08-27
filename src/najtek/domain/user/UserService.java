package najtek.domain.user;

import najtek.database.dao.user.UserDao;
import najtek.database.dao.user.UserRoleDao;
import najtek.infra.navigation.NavigationLink;
import najtek.infra.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
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
