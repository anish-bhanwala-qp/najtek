package najtek.domain.school.staffmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import najtek.infra.user.User;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * Created by anish on 29/8/16.
 */
public class SchoolStaffMember {

    private long id;
    private long userId;
    private long schoolId;

    @NotNull
    private transient String username;
    private transient String password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    @JsonProperty
    public String fullName() {
        return (StringUtils.isEmpty(firstName) ? "" : firstName) +
                (StringUtils.isEmpty(middleName) ? "" : middleName) +
                (StringUtils.isEmpty(lastName) ? "" : lastName);
    }
}
