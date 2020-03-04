package sysc4806.graduateAdmissions.model;

import lombok.Getter;

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
