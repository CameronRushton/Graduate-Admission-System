package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
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
@RequestMapping("/interest")
public class InterestController {
    @Autowired
    InterestRepository repo;

    /**
     * get Interest objects as JSON, either by ID or all at once
     *
     * @param id can be optionally specified to get a specific Interest, otherwise all are retrieved
     * @return JSON containing the Interest(s)
     */
    @GetMapping("/")
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
    @GetMapping("/department")
    public ResponseEntity getInterestsOfDepartment(@RequestParam() Department department) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findByDepartment(department));
    }

    /**
     * provides JSON info to populate a form for creating new interests
     *
     * @return JSON info to populate a form for creating new interests
     */
    @GetMapping("/create")
    public ResponseEntity createInterestForm() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("postLocation", "/interest/update");
        headers.add("operation", "Update");
        return new ResponseEntity(Interest.builder().build(), headers, HttpStatus.OK);
    }

    /**
     * adds a new Interest to the data base
     *
     * @param interest the Interest to store
     * @return a response indicating the success of the operation
     */
    @PostMapping("/create")
    public ResponseEntity createInterest(@ModelAttribute Interest interest) {
        repo.save(interest);
        return ResponseEntity.ok("interest successfully added");
    }

    /**
     * deletes an Interest from the database specified by name
     *
     * @param id the id of the Interest to delete
     * @return  a response indicating the success of the operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInterest(@PathVariable("id") Long id) {
        Optional<Interest> interest = repo.findById(id);
        if(interest.isPresent()){
            repo.delete(interest.get());
            return ResponseEntity.ok("interest " + interest.get().getKeyword() +
                    " in " + interest.get().getDepartment() + " successfully deleted");
        } else{
            return ResponseEntity.ok("specified interest not found");
        }
    }

    /**
     * provides JSON info to populate a form for updating an interest
     *
     * @param id the id of the buddy to update
     * @return JSON info to populate a form for updating an interest
     */
    @GetMapping("/update")
    public ResponseEntity updateInterestForm(@RequestParam long id){
        Optional<Interest> interest = repo.findById(id);
        if(interest.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("postLocation", "/interest/update");
            headers.add("operation", "Update");
            return new ResponseEntity(interest.get(), headers, HttpStatus.OK);
        } else{
           return ResponseEntity.ok("specified interest not found");
        }
    }

    /**
     * updates an Interest in the database
     *
     * @param interest the interest to update
     * @return a message signifying the operation's success
     */
    @PostMapping("/update")
    public ResponseEntity updateInterest(@ModelAttribute Interest interest) {
        repo.save(interest);
        return ResponseEntity.ok("interest successfully updated");
     }
}
