package sysc4806.graduateAdmissions.model;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the User class
 *
 * @author Kevin Sun
 */
public class UserAccountTest {
    private long id = 1324;
    private UserAccount userAccount, professor;
    private String firstName = "John";
    private String lastName = "Smith";
    private String email = "JohnSmith@gmail.com";
    private Role role, profRole;
    private Privilege createSelfApplication, updateSelfApplication;
    private Set<Privilege> privileges;
    private Set<Interest> interests;
    private Application application;
    private Term term = new Term(new SimpleDateFormat("yyyy-MM-dd").
            parse("2020-01-01"), Season.FALL, 2020, true);
    private Set<Application> applications;

    public UserAccountTest() throws ParseException {}

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
        professor = new UserAccount(id, firstName, lastName, email, profRole, interests, applications);
        Set<UserAccount> professors = new HashSet<>();
        professors.add(professor);
        application = new Application(5, userAccount, term, Department.SYSC, "Software Engineering", professors, Status.INCOMPLETE, 10, "resume.pdf");
        userAccount = new UserAccount();
    }


    /** Create a User without arguments and verify correct defaults are set */
    @Test
    public void testNoArgsConstructor() {
        assertNotNull(userAccount);
        assertNull(userAccount.getFirstName());
        assertNull(userAccount.getLastName());
        assertNull(userAccount.getEmail());
        assertNull(userAccount.getRole());
        assertNull(userAccount.getInterests());
        assertNull(userAccount.getApplications());
    }

    @Test
    /* Create a user with all arguments and verify correct defaults are set */
    public void testAllArgsConstructor() {
        interests = new HashSet<>();
        Interest i = new Interest(5, Department.SYSC, "Web Dev");
        interests.add(i);
        applications = new HashSet<>();

        userAccount = new UserAccount(id, firstName, lastName, email, role, interests, applications);
        assertEquals(id, userAccount.getId());
        assertEquals(firstName, userAccount.getFirstName());
        assertEquals(lastName, userAccount.getLastName());
        assertEquals(email, userAccount.getEmail());
        assertEquals(role, userAccount.getRole());
        assertEquals(interests, userAccount.getInterests());
        assertEquals(applications, userAccount.getApplications());
    }

    @Test
    /* Create a user with all arguments except the id and verify correct defaults are set */
    public void testNoIDConstructor() {
        userAccount = new UserAccount(firstName, lastName, email, role, interests, applications);


        assertEquals(firstName, userAccount.getFirstName());
        assertEquals(lastName, userAccount.getLastName());
        assertEquals(email, userAccount.getEmail());
        assertEquals(role, userAccount.getRole());
        assertEquals(interests, userAccount.getInterests());
        assertEquals(applications, userAccount.getApplications());
    }

    @Test
    /* Test that setFirstName sets the first name */
    public void testSetFirstName() {
        userAccount.setFirstName("Bob");

        assertEquals("Bob", userAccount.getFirstName());
        assertNull(userAccount.getLastName());
        assertNull(userAccount.getEmail());
        assertNull(userAccount.getRole());
        assertNull(userAccount.getInterests());
        assertNull(userAccount.getApplications());
    }

    @Test
    /* Test that setLastName sets a last name */
    public void testSetLastName() {
        userAccount.setLastName("Brown");

        assertNull(userAccount.getFirstName());
        assertEquals("Brown", userAccount.getLastName());
        assertNull(userAccount.getEmail());
        assertNull(userAccount.getRole());
        assertNull(userAccount.getInterests());
        assertNull(userAccount.getApplications());
    }

    @Test
    /* Test that setEmail sets the email */
    public void testSetEmail() {
        userAccount.setEmail("newEmail@gmail.com");
        assertNull(userAccount.getFirstName());
        assertNull(userAccount.getLastName());
        assertEquals("newEmail@gmail.com", userAccount.getEmail());
        assertNull(userAccount.getRole());
        assertNull(userAccount.getInterests());
        assertNull(userAccount.getApplications());
    }

    @Test
    /* Test that setPassword sets the password */
    public void testSetPassword() {
        assertNull(userAccount.getFirstName());
        assertNull(userAccount.getLastName());
        assertNull(userAccount.getEmail());
        assertNull(userAccount.getRole());
        assertNull(userAccount.getInterests());
        assertNull(userAccount.getApplications());
    }

    @Test
    /* Test that setRole sets the role */
    public void testSetRole() {
        userAccount.setRole(role);

        assertNull(userAccount.getFirstName());
        assertNull(userAccount.getLastName());
        assertNull(userAccount.getEmail());
        assertEquals(role, userAccount.getRole());
        assertEquals(interests, userAccount.getInterests());
        assertEquals(applications, userAccount.getApplications());
    }

    @Test
    /* Test that setInterests sets the interests */
    public void testSetInterests() {
        interests = new HashSet<>();
        Interest i = new Interest(5, Department.SYSC, "Web Dev");
        interests.add(i);
        userAccount.setInterests(interests);

        assertNull(userAccount.getFirstName());
        assertNull(userAccount.getLastName());
        assertNull(userAccount.getEmail());
        assertNull( userAccount.getRole());
        assertEquals(interests, userAccount.getInterests());
        assertNull(userAccount.getApplications());
    }

    @Test
    /* Test that setPassword sets the password */
    public void testSetApplications() {
        applications = new HashSet<>();

        applications.add(application);
        userAccount.setApplications(applications);

        assertNull(userAccount.getFirstName());
        assertNull(userAccount.getLastName());
        assertNull(userAccount.getEmail());
        assertNull( userAccount.getRole());
        assertNull(userAccount.getInterests());
        assertEquals(applications, userAccount.getApplications());
    }

    @Test
    /* Test equals is true given two identical users */
    public void testEquals() {
        userAccount = new UserAccount(firstName, lastName, email, role, interests, applications);
        UserAccount identicalUserAccount = new UserAccount(firstName, lastName, email, role, interests, applications);

        assertEquals(userAccount, identicalUserAccount);
    }

    @Test
    /* Test equals is false when comparing against a user with different LastName, deadline, year and id */
    public void testNotEquals() {
        userAccount = new UserAccount(firstName, lastName, email, role, interests, applications);
        UserAccount differentUserAccount = new UserAccount("Jane", "Doe", "JaneDoe@gmail.com", role, interests, applications);

        assertNotEquals(userAccount, differentUserAccount);
    }
}
