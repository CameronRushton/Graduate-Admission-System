package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the Term class
 *
 * @author Eric
 */
public class TermTest {

    private Term term;
    private Long id = 1L;
    private Date deadline;
    private Season season = Season.WINTER;
    private String year = "year";

    public TermTest() throws ParseException {
        deadline = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
    }


    @BeforeEach
    void setUp() {
        term = new Term();
    }

    /**
     * Create a Term without arguments and verify correct defaults are set.
     */
    @Test
    public void testNoArgsConstructor() {
        assertNotNull(term);
        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertNull(term.getDeadline());
        assertNull(term.getSeason());
        assertNull(term.getYear());
    }

    /**
     * Create a Term with all arguments and verify correct defaults are set.
     */
    @Test
    public void testAllArgsConstructor() {
        term = new Term(1L, true, deadline, season, year);

        assertEquals(id, term.getId());
        assertTrue(term.isActive());
        assertEquals(deadline, term.getDeadline());
        assertEquals(season, term.getSeason());
        assertEquals(year, term.getYear());
    }

    /**
     * Create a Term with all arguments except the id and verify correct defaults are set.
     */
    @Test
    public void testNoIDConstructor() {
        term = new Term(deadline, season, year, true);

        assertNotNull(term.getId());
        assertTrue(term.isActive());
        assertEquals(deadline, term.getDeadline());
        assertEquals(season, term.getSeason());
        assertEquals(year, term.getYear());
    }

    /**
     * Test that setActive sets it active.
     */
    @Test
    public void testSetActive() {
        term.setActive(true);

        assertNotNull(term.getId());
        assertTrue(term.isActive());
        assertNull(term.getDeadline());
        assertNull(term.getSeason());
        assertNull(term.getYear());
    }

    /**
     * Test that setDeadline sets a deadline.
     */
    @Test
    public void testSetDeadline() {
        term.setDeadline(deadline);

        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertEquals(deadline, term.getDeadline());
        assertNull(term.getSeason());
        assertNull(term.getYear());
    }

    /**
     * Test that setSeason sets the season.
     */
    @Test
    public void testSetSeason() {
        term.setSeason(season);

        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertNull(term.getDeadline());
        assertEquals(season, term.getSeason());
        assertNull(term.getYear());
    }

    /**
     * Test that setYear sets the year.
     */
    @Test
    public void testSetYear() {
        term.setYear(year);

        assertNotNull(term.getId());
        assertFalse(term.isActive());
        assertNull(term.getDeadline());
        assertNull(term.getSeason());
        assertEquals(year, term.getYear());
    }

    /**
     *  Test equals is true given two identical Terms.
     */
    @Test
    public void testEquals() {
        term = new Term(id, true, deadline, season, year);
        Term identicalTerm = new Term(id, true, deadline, season, year);

        assertTrue(term.equals(identicalTerm));
    }

    /**
     * Test equals is false when comparing against a Term with different Season, deadline, year and id.
     */
    @Test
    public void testNotEquals() throws ParseException {
        term = new Term(id, true, deadline, season, year);
        Term differentTerm = new Term(2L, false, new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"), Season.SUMMER, "different-year");

        assertFalse(term.equals(differentTerm));
    }
}
