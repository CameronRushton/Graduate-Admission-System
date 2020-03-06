package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@Controller
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
    @ResponseBody
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
    @ResponseBody
    @GetMapping("/department")
    public ResponseEntity getInterestsOfDepartment(@RequestParam() Department department) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findByDepartment(department));
    }

    /**
     * serves a form to create a new Interest
     *
     * @param model the Model to pass values to the thymeleaf template
     * @return the name of the form to create a new Interest
     */
    @GetMapping("/create")
    public String createInterestForm(Model model) {
        model.addAttribute("interest", Interest.builder().build());
        model.addAttribute("postLocation", "/interest/create");
        model.addAttribute("operation", "Create");
        return "interest/setInterestFields";
    }

    /**
     * adds a new Interest to the data base
     *
     * @param interest the Interest to store
     * @param model the Model to pass values to the thymeleaf template
     * @return the name of the result page for adding the template
     */
    @PostMapping("/create")
    public String createInterest(@ModelAttribute Interest interest, Model model) {
        repo.save(interest);
        model.addAttribute("message", "interest successfully added");
        return "interest/result";
    }

    /**
     * deletes an Interest from the database specified by name
     *
     * @param id the id of the Interest to delete
     * @param model the Model to pass values to the thymeleaf template
     * @return the name of the page that displays the result of the deletion
     */
    @DeleteMapping("/delete/{id}")
    public String deleteInterest(@PathVariable("id") Long id, Model model) {
        Optional<Interest> interest = repo.findById(id);
        if(interest.isPresent()){
            model.addAttribute("message", "interest " + interest.get().getKeyword() +
                    " in " + interest.get().getDepartment() + " successfully deleted");
            repo.delete(interest.get());
        } else{
            model.addAttribute("message", "specified interest not found");
        }
        return "interest/result";
    }

    /**
     * provides a form to update an Interest at a specified location
     *
     * @param id the id of the buddy to update
     * @param model the Model to pass values to the thymeleaf template
     * @return the name of the form to use to update the Interest
     */
    @GetMapping("/update")
    public String updateInterestForm(@RequestParam long id, Model model){
        Optional<Interest> interest = repo.findById(id);
        if(interest.isPresent()) {
            model.addAttribute("interest", interest.get());
            model.addAttribute("id", interest.get().getId());
            model.addAttribute("postLocation", "/interest/update");
            model.addAttribute("operation", "Update");
            return "interest/setInterestFields";
        } else{
            model.addAttribute("message", "specified interest not found");
            return "interest/result";
        }
    }

    /**
     * updates an Interest in the database
     *
     * @param interest the interest to update
     * @param model the Model to pass values to the thymeleaf template
     * @return the name of the page to display the result of the update
     */
    @PostMapping("/update")
    public String updateInterest(@ModelAttribute Interest interest, Model model) {
        repo.save(interest);
        model.addAttribute("message", "interest successfully updated");
        return "interest/result";
    }

    /**
     * provides a page to view all interests
     *
     * @return the name of the page which shows all interests
     */
    @GetMapping("/view")
    public String getInterestPage(){
        return "interest/view";
    }
}
