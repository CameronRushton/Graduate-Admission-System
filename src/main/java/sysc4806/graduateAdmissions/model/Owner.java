package sysc4806.graduateAdmissions.model;

import lombok.Getter;

/**
 * This enumeration lists possible owners of the targets of an operation.
 * These are used in the creation of privileges. The owner in a privilege
 * will specify which targets (applications, interests, etc.) are able to be
 * modified by a particular role.
 *
 * @author luke
 */
public enum Owner {
    SELF("self"),
    ALL_PROFS("all professors"),
    ALL_STUDENTS("all students");

    @Getter
    private final String description;

    Owner(String description) {
        this.description = description;
    }
}
