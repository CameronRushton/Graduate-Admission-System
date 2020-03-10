package sysc4806.graduateAdmissions.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sysc4806.graduateAdmissions.model.Season;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.repositories.TermRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
// @DataJpaTest <- TODO: Figure out why termRepository isnt injected when using this and how to fix.
public class TermJPATest {

    @Autowired
    private TermRepository termRepository;

    @Test
    public void persistNewTerm() {
        Term term = Term.builder().deadline("someDate").active(false).season(Season.SUMMER).year("2020").build();
        termRepository.save(term);
        List<Term> terms = termRepository.findAllTerms();
        assertFalse(terms.isEmpty());
        assertNotNull(terms.get(0));
    }


}