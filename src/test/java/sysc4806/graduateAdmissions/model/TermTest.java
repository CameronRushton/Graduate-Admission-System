package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private int year = 2020;

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
        assertNull(term.getId());
        assertNull(term.getActive());
        assertNull(term.getDeadline());
        assertNull(term.getSeason());
        assertEquals(0, term.getYear());
    }

    /**
     * Create a Term with all arguments and verify correct defaults are set.
     */
    @Test
    public void testAllArgsConstructor() {
        term = new Term(1L, true, deadline, season, year);

        assertEquals(id, term.getId());
        assertTrue(term.getActive());
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

        assertNull(term.getId());
        assertTrue(term.getActive());
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

        assertNull(term.getId());
        assertTrue(term.getActive());
        assertNull(term.getDeadline());
        assertNull(term.getSeason());
        assertEquals(0, term.getYear());
    }

    /**
     * Test that setDeadline sets a deadline.
     */
    @Test
    public void testSetDeadline() {
        term.setDeadline(deadline);

        assertNull(term.getId());
        assertNull(term.getActive());
        assertEquals(deadline, term.getDeadline());
        assertNull(term.getSeason());
        assertEquals(0, term.getYear());
    }

    /**
     * Test that setSeason sets the season.
     */
    @Test
    public void testSetSeason() {
        term.setSeason(season);

        assertNull(term.getId());
        assertNull(term.getActive());
        assertNull(term.getDeadline());
        assertEquals(season, term.getSeason());
        assertEquals(0, term.getYear());
    }

    /**
     * Test that setYear sets the year.
     */
    @Test
    public void testSetYear() {
        term.setYear(year);

        assertNull(term.getId());
        assertNull(term.getActive());
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

        assertEquals(term, identicalTerm);
    }

    /**
     * Test equals is false when comparing against a Term with different Season, deadline, year and id.
     */
    @Test
    public void testNotEquals() throws ParseException {
        term = new Term(id, true, deadline, season, year);
        Term differentTerm = new Term(2L, false, new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"), Season.SUMMER, 2019);

        assertNotEquals(term, differentTerm);
    }
}
