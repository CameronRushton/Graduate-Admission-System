package sysc4806.graduateAdmissions.controller;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.repositories.TermRepository;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;

@RestController
public class TermController {

    private final TermRepository termRepository;

    @Autowired
    public TermController(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    /**
     * GET /terms
     */
    @GetMapping(path="/terms")
    public ResponseEntity queryAllTerms() {
        List<Term> terms = termRepository.findAllTerms();
        return ResponseEntity.status(HttpStatus.OK).body(terms);
    }

    /**
     * GET /terms/{id}
     */
    @GetMapping(path="/terms/{id}")
    public ResponseEntity queryTerm(@PathVariable("id") Long termId) {
        Term term = termRepository.findTermById(termId);
        return ResponseEntity.status(Objects.isNull(term) ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(term);
    }

    /**
     * POST /terms
     */
    @PostMapping(path="/terms")
    public ResponseEntity createTerm(@Valid @RequestBody Term term) {
        termRepository.save(term);
        return ResponseEntity.status(HttpStatus.OK).body(term);
    }

}
