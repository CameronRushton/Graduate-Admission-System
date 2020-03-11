package sysc4806.graduateAdmissions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.graduateAdmissions.model.Owner;

/**
 * Controller for Owner enum
 *
 * @author Eric
 */
@RestController
public class OwnerController {

    /**
     * get all available owners
     *
     * @return JSON of every owner
     */
    @GetMapping("/owner")
    public ResponseEntity getOwners() {
        return ResponseEntity.status(HttpStatus.OK).body(Owner.values());
    }
}
