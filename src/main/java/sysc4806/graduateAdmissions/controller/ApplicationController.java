package sysc4806.graduateAdmissions.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Application;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.model.UserAccount;
import sysc4806.graduateAdmissions.repositories.ApplicationRepository;
import sysc4806.graduateAdmissions.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * This controller specifies CRUD operations on the application class
 *
 * @author Madelyn
 */
@RestController
@Slf4j
@RequestMapping("/application/")
public class ApplicationController {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * get Application objects as JSON, either by ID or all at once
     *
     * @param id can be optionally specified to get a specific Application, otherwise all are retrieved
     * @return JSON containing the Application(s)
     */
    @GetMapping("")
    public ResponseEntity getApplication(@RequestParam(required=false) Long id) {
        if(id == null)
            return ResponseEntity.status(HttpStatus.OK).body(applicationRepository.findAll());
        else
            return ResponseEntity.status(HttpStatus.OK).body(applicationRepository.findById(id));
    }

    /**
     * get all Application objects as JSON that have a specified applicant
     *
     * @param id the ID of applicant that the applications belong to
     * @return JSON containing the application(s)
     */
    @GetMapping("applicant")
    public ResponseEntity getApplicationsOfApplicant(@RequestParam() Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findById(id).get().getApplications());
    }

    /**
     * adds a new Application to the data base
     *
     * @param application the Interest to store
     * @return a response indicating the success of the operation
     */
    @PostMapping("create")
    public ResponseEntity createApplication(@RequestBody Application application) {
        applicationRepository.save(application);
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
        val application = applicationRepository.findById(id);
        if(application.isPresent()){
            applicationRepository.deleteById(id);
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
        applicationRepository.save(application);
        return ResponseEntity.ok("application successfully updated");
    }

    /**
     * updates an Application  status in the database
     *
     * @param application the Application to update
     * @return a message signifying the operation's success
     */
    @PostMapping("update/status")
    public ResponseEntity updateApplicationStatus(@RequestBody Application application) {
        val appInDB = applicationRepository.findById(application.getId()).get();
        appInDB.setStatus(application.getStatus());
        applicationRepository.save(appInDB);
        return ResponseEntity.ok("application successfully updated");
    }

    /**
     * get all Applications that request a specific prof
     *
     * @param id the ID the prof
     * @return JSON containing the application(s)
     */
    @GetMapping("requested-prof")
    public ResponseEntity getApplicationsForRequestedProf(@RequestParam() Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(applicationRepository.findByProfessors_id(id));
    }

    /**
     * get all Applications that match a user's interests
     *
     * @param id the ID the prof
     * @return JSON containing the application(s)
     */
    @GetMapping("similar-interests")
    public ResponseEntity getApplicationsWithMatchingInterests(@RequestParam() Long id) {
        val profInterests = userRepository.findById(id).get().getInterests();
        var students = new HashSet<UserAccount>();
        for(Interest interest : profInterests)
            students.addAll(userRepository.findByInterests(interest));

        var applications = new ArrayList<Application>();
        for(UserAccount student : students)
            applications.addAll(student.getApplications());

        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }
}
