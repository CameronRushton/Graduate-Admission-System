package sysc4806.graduateAdmissions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.graduateAdmissions.model.Department;

/**
 * A controller to retrieve the available Departments in the system
 *
 * @author luke
 *
 */
@RestController
public class DepartmentController {

    /**
     * Provides all the available departments in the system as JSON
     *
     * @return JSON containing every department name
     */
    @GetMapping("/departments")
    public ResponseEntity getDepartments() {
        return ResponseEntity.status(HttpStatus.OK).body(Department.values());
    }
}
