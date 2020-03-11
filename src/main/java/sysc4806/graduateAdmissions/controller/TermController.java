package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.repositories.TermRepositoryDAO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author crushton
 */
@RestController
@RequestMapping("/terms")
public class TermController {

    private final TermRepositoryDAO termRepositoryDAO;

    @Autowired
    public TermController(TermRepositoryDAO termRepositoryDAO) {
        this.termRepositoryDAO = termRepositoryDAO;
    }

    /**
     * GET /terms
     */
    @GetMapping
    public ResponseEntity queryAllTerms() {
        List<Term> terms = termRepositoryDAO.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(terms);
    }

    /**
     * GET /terms/{id}
     */
    @GetMapping(path="/{id}")
    public ResponseEntity queryTerm(@PathVariable("id") Long termId) {
        Optional<Term> term = termRepositoryDAO.findById(termId);
        return ResponseEntity.status(term.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(term.orElse(null));
    }

    /**
     * POST /terms
     */
    @PostMapping
    public ResponseEntity createTerm(@Valid @RequestBody Term term) {
        termRepositoryDAO.save(term);
        return ResponseEntity.status(HttpStatus.OK).body(term);
    }

    /**
     * DELETE /terms/{id}
     */
    @DeleteMapping(path="/{id}")
    public ResponseEntity createTerm(@PathVariable("id") Long termId) {
        if (!termRepositoryDAO.existsById(termId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        termRepositoryDAO.deleteById(termId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * PUT /terms
     */
    @PutMapping
    public ResponseEntity updateTerm(@Valid @RequestBody Term newTerm) {
        return ResponseEntity.status(HttpStatus.OK).body(termRepositoryDAO.findById(newTerm.getId()).map(term -> {
            term.setSeason(newTerm.getSeason());
            term.setYear(newTerm.getYear());
            term.setDeadline(newTerm.getDeadline());
            term.setActive(newTerm.isActive());
            return termRepositoryDAO.save(term);
        }).orElseGet(() -> termRepositoryDAO.save(newTerm)));
    }
}
