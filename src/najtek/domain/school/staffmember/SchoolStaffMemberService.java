package najtek.domain.school.staffmember;

import najtek.database.dao.school.staffmember.SchoolStaffMemberDao;
import najtek.domain.school.SchoolService;
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
 * Created by anish on 29/8/16.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SchoolStaffMemberService {
    private final static Logger logger = LoggerFactory.getLogger(SchoolStaffMemberService.class);
    @Autowired
    private SchoolStaffMemberDao schoolStaffMemberDao;

    @Autowired
    private SchoolService schoolService;

    private Map<Long, List<SchoolStaffMember>> schoolStaffMemberMap;

    public List<SchoolStaffMember> getStaffMemberListBySchoolId(long schoolId,
                                                                long organizationId) {
        assertSchoolNotNull(organizationId, schoolId);

        if (isStaffMemberListInCache(schoolId)) {
            logger.info("************Get SchoolStaffMember list cache**************");
            return getStaffMemberListFromCache(schoolId);
        }

        return populateStaffMemberListFromDatabaseToCache(schoolId);
    }

    public SchoolStaffMember getStaffMemberById(long id,
                                                long organizationId,
                                                long schoolId) {
        for (SchoolStaffMember schoolStaffMember : getStaffMemberListBySchoolId(schoolId, organizationId)) {
            if (schoolStaffMember.getId() == id) {
                return schoolStaffMember;
            }
        }
        return null;
    }

    public void addStaffMemberToSchool(SchoolStaffMember schoolStaffMember, long organizationId) {
        assertSchoolNotNull(organizationId, schoolStaffMember.getSchoolId());
        schoolStaffMemberDao.insert(schoolStaffMember);

        addStaffMemberToCache(schoolStaffMember);
    }

    private void addStaffMemberToCache(SchoolStaffMember schoolStaffMember) {
        getStaffMemberListFromCache(schoolStaffMember.getSchoolId()).add(schoolStaffMember);
    }

    private void assertSchoolNotNull(long organizationId, long schoolId) {
        Assert.notNull(schoolService.getSchoolById(organizationId, schoolId),
                "Organization should not be null");
    }

    private void checkAndInitSchoolStaffMemberCache() {
        if (schoolStaffMemberMap == null) {
            schoolStaffMemberMap = new HashMap<>();
        }
    }

    private List<SchoolStaffMember> getStaffMemberListFromCache(long schoolId) {
        List<SchoolStaffMember> schoolStaffMemberList = getSchoolStaffMemberCache().get(schoolId);
        if (schoolStaffMemberList == null) {
            schoolStaffMemberList = populateStaffMemberListFromDatabaseToCache(schoolId);
        }

        return schoolStaffMemberList;
    }

    private List<SchoolStaffMember> populateStaffMemberListFromDatabaseToCache(long schoolId) {
        List<SchoolStaffMember> schoolStaffMemberList = getStaffMemberListFromDatabase(schoolId);
        addStaffMemberListToCache(schoolId, schoolStaffMemberList);
        return schoolStaffMemberList;
    }

    private boolean isStaffMemberListInCache(long schoolId) {
        return (getSchoolStaffMemberCache().get(schoolId) != null);
    }

    private List<SchoolStaffMember> getStaffMemberListFromDatabase(long schoolId) {
        return schoolStaffMemberDao.selectBySchoolId(schoolId);
    }

    private void addStaffMemberListToCache(long schoolId, List<SchoolStaffMember> schoolStaffMemberList) {
        logger.info("************Populated staffMember list cache**************");
        getSchoolStaffMemberCache().put(schoolId, schoolStaffMemberList);
    }

    private Map<Long, List<SchoolStaffMember>> getSchoolStaffMemberCache() {
        checkAndInitSchoolStaffMemberCache();
        return schoolStaffMemberMap;
    }
}
