package sysc4806.graduateAdmissions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.graduateAdmissions.model.Status;

/**
 * A controller to retrieve the available Statuses in the system
 *
 * @author luke
 *
 */
@RestController
public class StatusController {

    /**
     * Provides all the available statuses in the system as JSON
     *
     * @return JSON containing every status name
     */
    @GetMapping("/statuses")
    public ResponseEntity getStatuses() {
        return ResponseEntity.status(HttpStatus.OK).body(Status.values());
    }
}

