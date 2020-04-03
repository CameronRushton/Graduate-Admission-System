package sysc4806.graduateAdmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysc4806.graduateAdmissions.dto.TermDTO;

import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.repositories.TermRepository;
import sysc4806.graduateAdmissions.service.TermManager;

import java.util.List;
import java.util.Optional;

/**
 * @author crushton
 */
@RestController
@RequestMapping("/terms")
public class TermController {

    private TermManager termManager;
    private TermRepository termRepository;

    @Autowired
    public TermController(TermManager termManager,
                          TermRepository termRepository) {
        this.termManager = termManager;
        this.termRepository = termRepository;
    }

    /**
     * GET /terms
     */
    @GetMapping
    public ResponseEntity queryAllTerms() {
        List<Term> terms = termRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(terms);
    }

    /**
     * GET /terms/{id}
     */
    @GetMapping(path="/{id}")
    public ResponseEntity queryTerm(@PathVariable("id") Long termId) {
        Optional<Term> term = termRepository.findById(termId);
        return ResponseEntity.status(term.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(term.orElse(null));
    }

    /**
     * POST /terms
     */
    @PostMapping
    public ResponseEntity createTerm(@RequestBody TermDTO term) {
        Optional<TermDTO> response = termManager.createTerm(term);
        if (response.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        // User can set an id, but it shouldn't be in the docs
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Term already exists; remove id field.");
    }
//
    /**
     * DELETE /terms/{id}
     */
    @DeleteMapping(path="/{id}")
    @CrossOrigin
    public ResponseEntity deleteTerm(@PathVariable("id") Long termId) {
        if (!termRepository.existsById(termId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        termRepository.deleteById(termId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * PUT /terms
     */
    @PutMapping
    @CrossOrigin
    public ResponseEntity updateTerm(@RequestBody TermDTO termDTO) {
        Optional<TermDTO> response = termManager.updateTerm(termDTO);
        if (response.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
