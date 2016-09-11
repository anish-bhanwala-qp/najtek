package najtek.domain.school.staffmember;

import najtek.database.dao.school.staffmember.SchoolStaffMemberDao;
import najtek.domain.school.SchoolService;
import najtek.domain.user.UserService;
import najtek.infra.user.User;
import najtek.infra.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.FieldError;

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

    @Autowired
    private UserService userService;

    private Map<Long, List<SchoolStaffMember>> schoolStaffMemberMap;

    public void clearCache(long schoolId) {
        if (isStaffMemberListInCache(schoolId)) {
            populateStaffMemberListFromDatabaseToCache(schoolId);
        }
    }

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

    public SchoolStaffMember getStaffMemberByUserId(long userId,
                                                    long organizationId,
                                                    long schoolId) {
        for (SchoolStaffMember schoolStaffMember : getStaffMemberListBySchoolId(schoolId, organizationId)) {
            if (schoolStaffMember.getUserId() == userId) {
                return schoolStaffMember;
            }
        }
        return null;
    }

    public SchoolStaffMember addStaffMemberToSchool(SchoolStaffMember schoolStaffMember,
                                                    long organizationId) throws ValidationException  {
        assertSchoolNotNull(organizationId, schoolStaffMember.getSchoolId());

        User user = validateAndGetUser(schoolStaffMember, organizationId);
        schoolStaffMember.setUserId(user.getId());

        schoolStaffMemberDao.insert(schoolStaffMember);

        clearCache(schoolStaffMember.getSchoolId());

        return getStaffMemberById(schoolStaffMember.getId(),
                organizationId, schoolStaffMember.getSchoolId());
    }

    private User validateAndGetUser(SchoolStaffMember schoolStaffMember,
                                    long organizationId) throws ValidationException {
        User user = userService.findUserWithUsername(schoolStaffMember.getUsername());
        if (user == null) {
            user = populateUser(new User(), schoolStaffMember);
            user.setOrganizationId(organizationId);

            userService.addUser(user);
        } else {
            if (user.getOrganizationId() != organizationId) {
                FieldError fieldError = new FieldError("StaffMember",
                        "username", "user.username.in.different.organization.error");
                throw new ValidationException(fieldError);
            }

            SchoolStaffMember existingStaffMember = getStaffMemberByUserId(user.getId(),
                    user.getOrganizationId(), schoolStaffMember.getSchoolId());
            if (existingStaffMember != null) {
                FieldError fieldError = new FieldError("StaffMember",
                        "username", "school.staffmember.already.exists.error");
                throw new ValidationException(fieldError);
            }
        }

        return user;
    }

    private User populateUser(User user, SchoolStaffMember schoolStaffMember) {
        user.setUsername(schoolStaffMember.getUsername());
        user.setPassword(schoolStaffMember.getPassword());
        user.setFirstName(schoolStaffMember.getFirstName());
        user.setMiddleName(schoolStaffMember.getMiddleName());
        user.setLastName(schoolStaffMember.getLastName());
        user.setEmailAddress(schoolStaffMember.getEmailAddress());

        return user;
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
