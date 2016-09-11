package najtek.domain.user;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
	ADMIN("Admin"),
	STAFF_MEMBER("Staff Member"),
    PARENT("Parent");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }
}
