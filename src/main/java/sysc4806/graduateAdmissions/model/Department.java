package sysc4806.graduateAdmissions.model;

import lombok.Getter;

/**
 * This enumeration lists possible departments in the system.
 * The list is not exhaustive of all departments in Carleton, since
 * for the purposes of this project we only need a few to show the
 * functionality.
 *
 * @author luke
 */
public enum Department {
    SYSC("systems and computer engineering"),
    ELEC("electrical engineering"),
    AERO("aerospace engineering"),
    SREE("sustainable and renewable energy engineering"),
    MAAE("mechanical engineering");

    @Getter
    private final String description;

    Department(String description) {
        this.description = description;
    }
}
