package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.repositories.TermRepository;

@RestController
public class TermController {

    private final TermRepository termRepository;

    @Autowired
    public TermController(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path="/terms/{id}", produces = "application/json")
    public ResponseEntity queryTerm(@PathVariable("id") Long termId) {
        Term term = termRepository.findTermById(termId);
        return ResponseEntity.status(HttpStatus.OK).body(term);
    }

}
