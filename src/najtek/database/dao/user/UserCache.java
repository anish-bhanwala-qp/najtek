package najtek.database.dao.user;

import najtek.domain.user.Organization;
import najtek.domain.user.UserRole;
import najtek.infra.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by anish on 21/8/16.
 */
public class UserCache {
    private Logger logger = LoggerFactory.getLogger(UserCache.class);

    private UserRoleDao userRoleDao;

    private OrganizationDao organizationDao;

    private User user;
    private List<UserRole> userRoles;
    private Organization organization;

    public UserCache(UserRoleDao userRoleDao, OrganizationDao organizationDao, User user) {
        this.userRoleDao = userRoleDao;
        this.organizationDao = organizationDao;
        this.user = user;

        logger.info("************ UserCache Instance Created");
    }

    public List<UserRole> getUserRoles() {
        if (userRoles == null) {
            userRoles = userRoleDao.selectByUserId(user.getId());
            logger.info("___________________________________________userRoles Size: \n" +
                    userRoles.size());
        }

        return userRoles;
    }

    public Organization getOrganization() {
        if (organization == null) {
            organization = organizationDao.selectById(user.getOrganizationId());
        }

        return organization;
    }
}
