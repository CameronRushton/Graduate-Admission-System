package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Session class
 *
 * @author luke
 */
class SessionTest {
    Session session1, session2;
    User user1, user2;
    String id1, id2;

    @BeforeEach
    void setUp() {
        user1 = new User("Luke","Skywalker", "email", new Role(), new HashSet<>(), new HashSet<>());
        user2 = new User("Darth","Vader", "email", new Role(), new HashSet<>(), new HashSet<>());
        id1 = "mario";
        id2 = "luigi";
        session1 = new Session(id1, user1);
        session2 = new Session(id2, user2);
    }

    /**Test to ensure default values of fields of a Session are set up correctly by the no args constructor*/
    @Test
    public void testNoArgsConstructor(){
        session1 = new Session();
        assertNotNull(session1);
        assertNull(session1.getId());
        assertNull(session1.getUser());
    }

    /**Test to ensure that the all args constructor correctly sets fields*/
    @Test
    public void testArgsConstructor(){
        assertEquals(id1, session1.getId());
        assertEquals(user1, session1.getUser());
    }

    /**Test to ensure that the builder correctly sets fields*/
    @Test
    public void testBuilder(){
        session1 = Session.builder().id(id1).user(user2).build();
        assertEquals(id1, session1.getId());
        assertEquals(user2, session1.getUser());
    }

    /**Test that the set id method correctly sets the id field*/
    @Test
    public void setId(){
        session1.setId(id2);
        assertEquals(id2, session1.getId());
    }

    /**Test that the set user method correctly sets the applicant field*/
    @Test
    public void setApplicant(){
        session1.setUser(user2);
        assertEquals(user2, session1.getUser());
    }

    /**Test that the generated toString behaves as expected*/
    @Test
    public void testToString(){
        assertFalse(session1.toString().isEmpty());
    }

    /**Test that two Session objects with identical fields are considered equal*/
    @Test
    public void testEquals(){
        session2 = new Session(id1, user1);
       assertEquals(session1, session2);
    }

    /**Test that two Session objects with different fields are not considered equal*/
    @Test
    public void testNotEquals(){
        assertNotEquals(session1, session2);
    }
}