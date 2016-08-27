package najtek.domain.user;

import najtek.database.dao.user.OrganizationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by anish on 27/8/16.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrganizationService {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    private OrganizationDao organizationDao;

    private List<Organization> organizationList;

    public Organization getById(long organizationId) {
        for (Organization organization : getOrganizationList()) {
            if (organization.getId() == organizationId) {
                return organization;
            }
        }

        return null;
    }

    public List<Organization> getOrganizationList() {
        if (organizationList == null) {
            logger.info("Fetching Organization list from database");
            organizationList = organizationDao.selectAll();
        }
        return organizationList;
    }

    public Organization getByName(String name, boolean checkCache) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        if (!checkCache) {
            return organizationDao.selectByName(name);
        }

        for (Organization organization : getOrganizationList()) {
            if (name.equals(organization.getName())) {
                return organization;
            }
        }
        return null;
    }

    public void add(Organization organization) {
        organizationDao.insert(organization);
        clearCache();
    }

    private void clearCache() {
        organizationList = null;
    }
}
