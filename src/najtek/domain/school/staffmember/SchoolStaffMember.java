package najtek.domain.school.staffmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import najtek.infra.user.User;
import org.springframework.util.StringUtils;

/**
 * Created by anish on 29/8/16.
 */
public class SchoolStaffMember {

    private long id;
    private long userId;
    private long schoolId;

    private transient String emailAddress;
    private transient String firstName;
    private transient String middleName;
    private transient String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    @JsonProperty
    public String fullName() {
        return (StringUtils.isEmpty(firstName) ? "" : firstName) +
                (StringUtils.isEmpty(middleName) ? "" : middleName) +
                (StringUtils.isEmpty(lastName) ? "" : lastName);
    }
}
