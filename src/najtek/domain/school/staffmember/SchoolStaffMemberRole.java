package najtek.domain.school.staffmember;

import javax.validation.constraints.NotNull;

/**
 * Created by anish on 30/8/16.
 */
public class SchoolStaffMemberRole {
    private long id;

    @NotNull
    private StaffMemberRole staffMemberRole;

    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StaffMemberRole getStaffMemberRole() {
        return staffMemberRole;
    }

    public void setStaffMemberRole(StaffMemberRole staffMemberRole) {
        this.staffMemberRole = staffMemberRole;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}