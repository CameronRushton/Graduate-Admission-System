package sysc4806.graduateAdmissions.model;

import lombok.Getter;

/**
 * This enumeration lists possible system operations to be used in the creation of privileges.
 *
 * @author luke
 */
public enum Operation {
    CREATE("create"),
    READ("read"),
    UPDATE("update"),
    DELETE("delete");

    @Getter
    private final String description;

    Operation(String description) {
        this.description = description;
    }
}
