package sysc4806.graduateAdmissions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.graduateAdmissions.model.Operation;

/**
 * Controller to retrieve Operations
 *
 * @author Eric
 */
@RestController
public class OperationController {

    /**
     * Provides Operation in the system as JSON
     *
     * @return JSON containing every Operation
     */
    @GetMapping("/operations")
    public ResponseEntity getOperations() {
        return ResponseEntity.status(HttpStatus.OK).body(Operation.values());
    }
}
