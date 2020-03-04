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
}
