package sysc4806.graduateAdmissions.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Department;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.repositories.InterestRepository;

import java.util.Optional;

/**
 * This controller specifies CRUD operations on the Interest class
 *
 * @author luke
 */
@RestController
@RequestMapping("/interest/")
public class InterestController {
    @Autowired
    InterestRepository repo;

    /**
     * get Interest objects as JSON, either by ID or all at once
     *
     * @param id can be optionally specified to get a specific Interest, otherwise all are retrieved
     * @return JSON containing the Interest(s)
     */
    @GetMapping("")
    public ResponseEntity getInterest(@RequestParam(required=false) Long id) {
        if(id == null)
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
        else
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id));
    }

    /**
     * get all Interests objects as JSON that have a specified department
     *
     * @param department the department to get Interests for
     * @return JSON containing the interests
     */
    @GetMapping("department")
    public ResponseEntity getInterestsOfDepartment(@RequestParam() Department department) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findByDepartment(department));
    }

    /**
     * adds a new Interest to the data base
     *
     * @param interest the Interest to store
     * @return a response indicating the success of the operation
     */
    @PostMapping("create")
    public ResponseEntity createInterest(@RequestBody Interest interest) {
        repo.save(interest);
        return ResponseEntity.ok("interest successfully added");
    }

    /**
     * deletes an Interest from the database specified by name
     *
     * @param id the id of the Interest to delete
     * @return  a response indicating the success of the operation
     */
    @DeleteMapping("{id}")
    @CrossOrigin
    public ResponseEntity deleteInterest(@PathVariable("id") Long id) {
        val interest = repo.findById(id);
        if(interest.isPresent()){
            repo.delete(interest.get());
            return ResponseEntity.ok("interest " + interest.get().getKeyword() +
                    " in " + interest.get().getDepartment() + " successfully deleted");
        } else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * updates an Interest in the database
     *
     * @param interest the interest to update
     * @return a message signifying the operation's success
     */
    @PostMapping("update")
    public ResponseEntity updateInterest(@RequestBody Interest interest) {
        repo.save(interest);
        return ResponseEntity.ok("interest successfully updated");
     }
}
