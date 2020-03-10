package sysc4806.graduateAdmissions.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sysc4806.graduateAdmissions.model.Term;

import java.util.List;

@Repository
public class TermRepository {

    private final TermRepositoryDAO termRepositoryDAO;

    @Autowired
    public TermRepository(TermRepositoryDAO termRepositoryDAO) {
        this.termRepositoryDAO = termRepositoryDAO;
    }

    public void save(Term term) {
        this.termRepositoryDAO.save(term);
    }

    public List<Term> findAllTerms() {
        return termRepositoryDAO.findAll();
    }

    public Term findTermById(Long id) {
        return termRepositoryDAO.findById(id).orElse(null);
    }
}
