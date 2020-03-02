package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sysc4806.graduateAdmissions.model.Department;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.repositories.InterestRepository;

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
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById((long)id));
    }

    @ResponseBody
    @GetMapping("/department")
    public ResponseEntity getInterestsOfDepartment(@RequestParam() Department department) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findByDepartment(department));
    }
}
