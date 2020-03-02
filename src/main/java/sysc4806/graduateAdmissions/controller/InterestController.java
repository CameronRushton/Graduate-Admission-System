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

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity getInterest(@RequestParam(required=false) Long id) {
        if(id == null)
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
        else
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id));
    }

    @ResponseBody
    @GetMapping("/department")
    public ResponseEntity getInterestsOfDepartment(@RequestParam() Department department) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findByDepartment(department));
    }

    @GetMapping("/create")
    public String createInterestForm(Model model) {
        model.addAttribute("interest", Interest.builder().build());
        model.addAttribute("postLocation", "/interest/create");
        model.addAttribute("operation", "Create");
        return "interest/create";
    }

    @PostMapping("/create")
    public String createInterest(@ModelAttribute Interest interest, Model model) {
        repo.save(interest);
        model.addAttribute("message", "interest successfully added");
        return "interest/result";
    }

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

    @GetMapping("/update")
    public String updateInterestForm(@RequestParam long id, Model model){
        Optional<Interest> interest = repo.findById(id);
        if(!interest.isPresent())
            return null;
        model.addAttribute("interest", interest.get());
        model.addAttribute("id", interest.get().getId());
        model.addAttribute("postLocation", "/interest/update");
        model.addAttribute("operation", "Update");
        return "interest/create";
    }

    @PostMapping("/update")
    public String updateInterest(@ModelAttribute Interest interest, Model model) {
        repo.save(interest);
        model.addAttribute("message", "interest successfully updated");
        return "interest/result";
    }
}
