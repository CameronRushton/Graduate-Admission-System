package sysc4806.graduateAdmissions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sysc4806.graduateAdmissions.model.Term;

/**
 * Data Access Object for Term in the persistence layer for database CRUD functions.
 */
@Repository
public interface TermRepositoryDAO extends JpaRepository<Term, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Term t set t.deadline=:termDeadline where t.id=:termId")
    void updateDeadline(@Param("termDeadline") String termDeadline, @Param("termId") Long termId);
}
