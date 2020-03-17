package sysc4806.graduateAdmissions.model;

import lombok.Getter;


/**
 * An enumeration describing the status of an application in the system. Each status shows a state an application can
 * be in. As an application moves threw the system its status is updated.
 *
 * @author madelynkrasnay
 */
public enum Status {
    INCOMPLETE("incomplete"),
    SUBMITTED("submitted to administration"),
    PENDINGAPROVAL("pending professor's approval"),
    PENDINGREVIEW("pending administrative review"),
    APPROVEDFUNDING("approved with funding"),
    APPROVEDNOFUNDING("approved with no funding"),
    DENIED("denied");

    @Getter
    private final String description;

    Status(String description) {
        this.description = description;
    }

}
