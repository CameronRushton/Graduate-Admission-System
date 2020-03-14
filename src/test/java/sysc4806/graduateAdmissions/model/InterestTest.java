package sysc4806.graduateAdmissions.model;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Interest class. Since we are using lombok to generate the
 * constructors, getters, setters, etc. they are all explicitly tested here.
 *
 * @author luke
 */
class InterestTest {
    private Interest interest;
    private long ID = 5;
    private Department department = Department.SYSC;
    private String keyword = "web dev";

    @BeforeEach
    public void setUp(){
        interest = Interest.builder().build();
    }

    /**Test to ensure default values of fields of an Interest are correct*/
    @Test
    public void testNoArgsConstructor(){
        assertNotNull(interest);
        assertNull(interest.getDepartment());
        assertNull(interest.getKeyword());
        assertEquals(0, interest.getId());
    }

    /**Test to ensure that the all args constructor correctly sets fields*/
    @Test
    public void testArgsConstructor(){
        interest = new Interest(ID, department, keyword);
        assertEquals(ID, interest.getId());
        assertEquals(department, interest.getDepartment());
        assertEquals(keyword, interest.getKeyword());
    }

    /**Test to ensure that the builder correctly sets fields*/
    @Test
    public void testBuilder(){
        interest = Interest.builder().id(ID).department(department)
                .keyword(keyword).build();
        assertEquals(ID, interest.getId());
        assertEquals(department, interest.getDepartment());
        assertEquals(keyword, interest.getKeyword());
    }

    /**Test that the setID method correctly sets the id field*/
    @Test
    public void setId(){
        interest.setId(ID);
        assertEquals(ID, interest.getId());
    }

    /**Test that the setDepartment method correctly sets the department field*/
    @Test
    public void setDepartment(){
        interest.setDepartment(department);
        assertEquals(department, interest.getDepartment());
    }

    /**Test that the setKeyword method correctly sets the keyword field*/
    @Test
    public void setKeyword(){
        interest.setKeyword(keyword);
        assertEquals(keyword, interest.getKeyword());
    }

    /**Test that the generated toString behaves as expected*/
    @Test
    public void testToString(){
        interest = new Interest(ID, department, keyword);
        assertEquals("Interest(id=5, department=SYSC, keyword=web dev)",
                interest.toString());
    }

    /**Test that two Privilege objects with identical fields are considered equal*/
    @Test
    public void testEquals(){
        val interest1 = new Interest(ID, department, keyword);
        val interest2 = new Interest(ID, department, keyword);
        assertEquals(interest1, interest2);
    }

    /**Test that two Privilege objects with all fields equal except their ids are still considered equal*/
    @Test
    public void testEqualsDifferentID(){
        val interest1 = new Interest(ID, department, keyword);
        val interest2 = new Interest(ID + 42, department, keyword);
        assertEquals(interest1, interest2);
    }

    /**Test that objects with different field values are not considered equal*/
    @Test
    public void testNotEquals(){
        val interest1 = new Interest(ID, department, keyword);
        assertNotEquals(interest1, interest);
    }
}