package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sysc4806.graduateAdmissions.model.Term;

/**
 * Data Access Object for Term in the persistence layer for database CRUD functions.
 */
@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
}
