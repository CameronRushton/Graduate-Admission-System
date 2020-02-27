package sysc4806.graduateAdmissions.model;

import lombok.Getter;

/**
 * This enumeration lists possible targets for operations specified by the
 * Operation enumeration. These are the types of objects that the operation
 * would be performed on. These are used in the creation of privileges.
 *
 * @author luke
 */
public enum Target {
    APPLICATION("application"),
    INTEREST("interest"),
    TERM("term"),
    USER("user");

    @Getter
    private final String description;

    Target(String description) {
        this.description = description;
    }
}
