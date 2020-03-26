package sysc4806.graduateAdmissions.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Application;
import sysc4806.graduateAdmissions.repositories.ApplicationRepository;

/**
 * This controller specifies CRUD operations on the application class
 *
 * @author Madelyn
 */
@RestController
@RequestMapping("/application/")
public class ApplicationController {
    @Autowired
    ApplicationRepository repo;

    /**
     * get Application objects as JSON, either by ID or all at once
     *
     * @param id can be optionally specified to get a specific Application, otherwise all are retrieved
     * @return JSON containing the Application(s)
     */
    @GetMapping("")
    public ResponseEntity getApplication(@RequestParam(required=false) Long id) {
        if(id == null)
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
        else
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id));
    }

    /**
     * get all Application objects as JSON that have a specified applicant
     *
     * @param id the ID of applicant that the applications belong to
     * @return JSON containing the application(s)
     */
    @GetMapping("applicant")
    public ResponseEntity getApplicationsOfApplicant(@RequestParam() Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findByApplicant_id(id));
    }

    /**
     * adds a new Application to the data base
     *
     * @param application the Interest to store
     * @return a response indicating the success of the operation
     */
    @PostMapping("create")
    public ResponseEntity createApplication(@RequestBody Application application) {
        repo.save(application);
        return ResponseEntity.ok("application successfully added");
    }

    /**
     * deletes an Application from the database specified by name
     *
     * @param id the id of the application to delete
     * @return  a response indicating the success of the operation
     */
    @DeleteMapping("{id}")
    @CrossOrigin
    public ResponseEntity deleteApplication(@PathVariable("id") Long id) {
        val application = repo.findById(id);
        if(application.isPresent()){
            repo.delete(application.get());
            return ResponseEntity.ok("application to " + application.get().getDegree() +
                    " in " + application.get().getDepartment() +
                    " for " + application.get().getTerm().getSeason() +
                    " " + application.get().getTerm().getYear() +
                    " successfully deleted");
        } else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * updates an Application in the database
     *
     * @param application the Application to update
     * @return a message signifying the operation's success
     */
    @PostMapping("update")
    public ResponseEntity updateApplication(@RequestBody Application application) {
        repo.save(application);
        return ResponseEntity.ok("application successfully updated");
    }
}
