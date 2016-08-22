package najtek.domain.school;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by anish on 23/8/16.
 */
public class School {

    private long id;

    @NotNull(message = "generic.name.required.error")
    @Size(min = 3, max = 64, message = "generic.name.min.max.length.error")
    private String name;

    private long primaryUserId;

    private long organizationId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrimaryUserId() {
        return primaryUserId;
    }

    public void setPrimaryUserId(long primaryUserId) {
        this.primaryUserId = primaryUserId;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }
}
