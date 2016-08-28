package najtek.domain.school;

import najtek.database.dao.school.SchoolDao;
import najtek.domain.user.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anish on 28/8/16.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SchoolService {
    private final static Logger logger = LoggerFactory.getLogger(SchoolService.class);
    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private OrganizationService organizationService;

    private Map<Long, List<School>> organizationSchoolMap;

    public List<School> getSchoolListByOrganizationId(long organizationId) {
        assertOrganizationNotNull(organizationId);

        if (isSchoolListInCache(organizationId)) {
            logger.info("************Get school list cache**************");
            return getSchoolListFromCache(organizationId);
        }

        return populateSchoolListFromDatabaseToCache(organizationId);
    }

    public School getSchoolById(long organizationId, long id) {
        for (School school : getSchoolListByOrganizationId(organizationId)) {
            if (school.getId() == id) {
                return school;
            }
        }
        return null;
    }

    public void add(School school) {
        assertOrganizationNotNull(school.getOrganizationId());
        schoolDao.insert(school);

        addSchoolToCache(school);
    }

    private void addSchoolToCache(School school) {
        getSchoolListFromCache(school.getOrganizationId()).add(school);
    }

    private void assertOrganizationNotNull(long organizationId) {
        Assert.notNull(organizationService.getById(organizationId),
                "Organization should not be null");
    }

    private void checkAndInitOrganizationSchoolCache() {
        if (organizationSchoolMap == null) {
            organizationSchoolMap = new HashMap<>();
        }
    }

    private List<School> getSchoolListFromCache(long organizationId) {
        List<School> schoolList = getOrganizationSchoolCache().get(organizationId);
        if (schoolList == null) {
            schoolList = populateSchoolListFromDatabaseToCache(organizationId);
        }

        return schoolList;
    }

    private List<School> populateSchoolListFromDatabaseToCache(long organizationId) {
        List<School> schoolList = getSchoolListFromDatabase(organizationId);
        addSchoolListToCache(organizationId, schoolList);
        return schoolList;
    }

    private boolean isSchoolListInCache(long organizationId) {
        return (getOrganizationSchoolCache().get(organizationId) != null);
    }

    private List<School> getSchoolListFromDatabase(long organizationId) {
        return schoolDao.selectByOrganizationId(organizationId);
    }

    private void addSchoolListToCache(long organizationId, List<School> schoolList) {
        logger.info("************Populated school list cache**************");
        getOrganizationSchoolCache().put(organizationId, schoolList);
    }

    private Map<Long, List<School>> getOrganizationSchoolCache() {
        checkAndInitOrganizationSchoolCache();
        return organizationSchoolMap;
    }
}
