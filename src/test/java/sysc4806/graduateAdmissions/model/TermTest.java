package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the Term class
 *
 * @author Eric
 */
public class TermTest {

    private Term term;
    private Long id = 1L;
    private String deadline = "deadline";
    private Season season = Season.WINTER;
    private String year = "year";


    @BeforeEach
    void setUp() {
        term = new Term();
    }

    @Test
    /* Create a Term without arguments and verify correct defaults are set */
    public void testNoArgsConstructor() {
        assertNotNull(term);
        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertNull(term.getDeadline());
        assertNull(term.getSeason());
        assertNull(term.getYear());
    }

    @Test
    /* Create a Term with all arguments and verify correct defaults are set */
    public void testAllArgsConstructor() {
        term = new Term(deadline, season, year, true);

        assertNotNull(term);
        assertNotNull(term.getId());
        assertTrue(term.isActive());
        assertEquals(deadline, term.getDeadline());
        assertEquals(season, term.getSeason());
        assertEquals(year, term.getYear());
    }

    @Test
    /* Test that setActive sets it active */
    public void testSetActive() {
        term.setActive(true);

        assertNotNull(term.getId());
        assertTrue(term.isActive());
        assertNull(term.getDeadline());
        assertNull(term.getSeason());
        assertNull(term.getYear());
    }

    @Test
    /* Test that setDeadline sets a deadline */
    public void testSetDeadline() {
        term.setDeadline(deadline);

        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertEquals(deadline, term.getDeadline());
        assertNull(term.getSeason());
        assertNull(term.getYear());
    }

    @Test
    /* Test that setSeason sets the season */
    public void testSetSeason() {
        term.setSeason(season);

        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertNull(term.getDeadline());
        assertEquals(season, term.getSeason());
        assertNull(term.getYear());
    }

    @Test
    /* Test that setYear sets the year */
    public void testSetYear() {
        term.setYear(year);

        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertNull(term.getDeadline());
        assertNull(term.getSeason());
        assertEquals(year, term.getYear());
    }

    @Test
    /* Test equals is true given two identical Terms */
    public void testEquals() {
        term = new Term(deadline, season, year, true);
        term.setId(id);
        Term identicalTerm = new Term(deadline, season, year, true);
        identicalTerm.setId(id);

        assertTrue(term.equals(identicalTerm));
    }

    @Test
    /* Test equals is false when comparing against a Term with different Season, deadline, year and id */
    public void testNotEquals() {
        term = new Term(deadline, season, year, true);
        term.setId(id);
        Term differentTerm = new Term("different-deadline", Season.SUMMER, "different-year", false);
        term.setId(2L);

        assertFalse(term.equals(differentTerm));
    }
}
