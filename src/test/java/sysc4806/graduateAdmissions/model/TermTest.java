package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TermTest {

    private Term term;
    private Long id = 1L;
    private String deadline = "deadline";
    private String semester = "semester";
    private String year = "year";


    @BeforeEach
    void setUp() {
        term = new Term();
    }

    @Test
    public void testNoArgsConstructor(){
        assertNotNull(term);
        assertNotNull(term.getId());
        assertEquals(id, term.getId());
        assertFalse(term.isActive());
        assertNull(term.getDeadline());
        assertNull(term.getSemester());
        assertNull(term.getYear());
    }

    @Test
    public void testAllArgsConstructor(){
        term = new Term(deadline, semester, year, true);

        assertNotNull(term);
        assertNotNull(term.getId());
        assertTrue(term.isActive());
        assertEquals(deadline, term.getDeadline());
        assertEquals(semester, term.getSemester());
        assertEquals(year, term.getYear());
    }

    @Test
    public void testSetActive(){
        term.setActive(true);

        assertNotNull(term.getId());
        assertTrue(term.isActive());
        assertNull(term.getDeadline());
        assertNull(term.getSemester());
        assertNull(term.getYear());
    }

    @Test
    public void testSetDeadline(){
        term.setDeadline(deadline);

        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertEquals(deadline, term.getDeadline());
        assertNull(term.getSemester());
        assertNull(term.getYear());
    }

    @Test
    public void testSetSemester(){
        term.setSemester(semester);

        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertNull(term.getDeadline());
        assertEquals(semester, term.getSemester());
        assertNull(term.getYear());
    }

    @Test
    public void testSetYear(){
        term.setYear(year);

        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertNull(term.getDeadline());
        assertNull(term.getSemester());
        assertEquals(year, term.getYear());
    }

    @Test
    public void testEquals(){
        term = new Term(deadline, semester, year, true);
        term.setId(id);
        Term identicalTerm = new Term(deadline, semester, year, true);
        identicalTerm.setId(id);

        assertTrue(term.equals(identicalTerm));
    }

    @Test
    public void testNotEquals(){
        term = new Term(deadline, semester, year, true);
        term.setId(id);
        Term differentTerm = new Term("different-deadline", "different-semester", "different-year", false);
        term.setId(2L);

        assertFalse(term.equals(differentTerm));
    }
}
