package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import com.google.common.collect.Sets;
import org.springframework.boot.test.context.assertj.ApplicationContextAssert;

/**
 * Tests the functionality of the User class
 *
 * @author Kevin Sun
 */
public class UserTest {

    private long id = 1234;
    private User user, professor;
    private String firstName = "John";
    private String lastName = "Smith";
    private String email = "JohnSmith@gmail.com";
    private String password = "password";
    private Role role, profRole;
    private Privilege createSelfApplication, updateSelfApplication;;
    private Set<Privilege> privileges;
    private Set<Interest> interests;
    private Application application;
    private Term term = new Term("Tommorrow!", Season.FALL, "2020", true);
    private Set<Application> applications; //need to be changed to actual applications object


    @BeforeEach
    void setUp() {
        createSelfApplication = Privilege.builder().id(42)
                .operation(Operation.CREATE).owner(Owner.SELF)
                .target(Target.APPLICATION).build();
        updateSelfApplication = Privilege.builder().id(9)
                .operation(Operation.UPDATE).owner(Owner.SELF)
                .target(Target.APPLICATION).build();
        privileges = Sets.newHashSet(createSelfApplication, updateSelfApplication);
        role = new Role("Student", privileges);
        profRole = new Role("Professor", privileges);
        professor = new User(id, firstName, lastName, email, password, profRole, interests, applications);
        Set<User> professors = new HashSet<User>();
        professors.add(professor);
        application = new Application(5, user, term, Department.SYSC, "Software Engineering", professors, Status.INCOMPLETE, 10, "resume.pdf");
        user = new User();
    }


    /** Create a User without arguments and verify correct defaults are set */
    @Test
    public void testNoArgsConstructor() {
        assertNotNull(user);
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
        assertNull(user.getInterests());
        assertNull(user.getApplications());
    }

    @Test
    /* Create a user with all arguments and verify correct defaults are set */
    public void testAllArgsConstructor() {
        interests = new HashSet<Interest>();
        Interest i = new Interest(5, Department.SYSC, "Web Dev");
        interests.add(i);
        applications = new HashSet<Application>();

        user = new User(id, firstName, lastName, email, password, role, interests, applications);
        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
        assertEquals(interests, user.getInterests());
        assertEquals(applications, user.getApplications());
    }

    @Test
    /* Create a user with all arguments except the id and verify correct defaults are set */
    public void testNoIDConstructor() {
        user = new User(firstName, lastName, email, password, role, interests, applications);


        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
        assertEquals(interests, user.getInterests());
        assertEquals(applications, user.getApplications());
    }

    @Test
    /* Test that setFirstName sets the first name */
    public void testSetFirstName() {
        user.setFirstName("Bob");

        assertEquals("Bob", user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
        assertNull(user.getInterests());
        assertNull(user.getApplications());
    }

    @Test
    /* Test that setLastName sets a last name */
    public void testSetLastName() {
        user.setLastName("Brown");

        assertNull(user.getFirstName());
        assertEquals("Brown", user.getLastName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
        assertNull(user.getInterests());
        assertNull(user.getApplications());
    }

    @Test
    /* Test that setEmail sets the email */
    public void testSetEmail() {
        user.setEmail("newEmail@gmail.com");

        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertEquals("newEmail@gmail.com", user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
        assertNull(user.getInterests());
        assertNull(user.getApplications());
    }

    @Test
    /* Test that setPassword sets the password */
    public void testSetPassword() {
        user.setPassword("newPassword");

        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getEmail());
        assertEquals("newPassword", user.getPassword());
        assertNull(user.getRole());
        assertNull(user.getInterests());
        assertNull(user.getApplications());
    }

    @Test
    /* Test that setRole sets the role */
    public void testSetRole() {
        user.setRole(role);

        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertEquals(role, user.getRole());
        assertEquals(interests, user.getInterests());
        assertEquals(applications, user.getApplications());
    }

    @Test
    /* Test that setInterests sets the interests */
    public void testSetInterests() {
        interests = new HashSet<Interest>();
        Interest i = new Interest(5, Department.SYSC, "Web Dev");
        interests.add(i);
        user.setInterests(interests);

        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull( user.getRole());
        assertEquals(interests, user.getInterests());
        assertNull(user.getApplications());
    }

    @Test
    /* Test that setPassword sets the password */
    public void testSetApplications() {
        applications = new HashSet<Application>();

        applications.add(application);
        user.setApplications(applications);

        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull( user.getRole());
        assertNull(user.getInterests());
        assertEquals(applications, user.getApplications());
    }

    @Test
    /* Test equals is true given two identical users */
    public void testEquals() {
        user = new User(firstName, lastName, email, password, role, interests, applications);
        User identicalUser = new User(firstName, lastName, email, password, role, interests, applications);

        assertTrue(user.equals(identicalUser));
    }

    @Test
    /* Test equals is false when comparing against a user with different LastName, deadline, year and id */
    public void testNotEquals() {
        user = new User(firstName, lastName, email, password, role, interests, applications);
        User differentUser = new User("Jane", "Doe", "JaneDoe@gmail.com", password, role, interests, applications);

        assertFalse(user.equals(differentUser));
    }
}
