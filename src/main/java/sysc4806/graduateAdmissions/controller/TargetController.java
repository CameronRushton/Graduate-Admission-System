package sysc4806.graduateAdmissions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.graduateAdmissions.model.Target;

/**
 * Controller to retrieve Terms
 *
 * @author Eric
 */
@RestController
public class TargetController {

    /**
     * Provides Terms in the system as JSON
     *
     * @return JSON containing every Target
     */
    @GetMapping("/targets")
    public ResponseEntity getTargets() {
        return ResponseEntity.status(HttpStatus.OK).body(Target.values());
    }
}
