package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the User class
 *
 * @author Kevin Sun
 */
public class UserTest {

	private long id = 1234;
    private User user;
    private String firstName = "John";
    private String lastName = "Smith";
    private String email = "JohnSmith@gmail.com";
    private String password = "password"; 
    private Role role;
    private Privilege createSelfApplication, updateAllStudentsApplications;
    private Set<Privilege> privileges;
    private ArrayList<Interest> interests;
    private ArrayList<String> applications; //need to be changed to actual applications object


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
        user = new User();
    }


    /** Create a User without arguments and verify correct defaults are set */
    @Test
    public void testNoArgsConstructor() {
        assertNotNull(user);
        assertNotNull(user.getId());
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
        interests = new ArrayList<Interest>();
        Interest i = new Interest(5, Department.SYSC, "Web Dev");
        interests.add(i);
        applications = new ArrayList<String>();

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
		
		assertNotNull(user.getId());
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

        assertNotNull(user.getId());
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

		assertNotNull(user.getId());
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

		assertNotNull(user.getId());
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

		assertNotNull(user.getId());
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
        user.getRole().setRoleName("Professor");

		assertNotNull(user.getId());
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
		interests = new ArrayList<Interest>();
		Interest i = new Interest(5, Department.SYSC, "Web Dev");
		interests.add(i);
        user.setInterests(interests);

		assertNotNull(user.getId());
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
		applications = new ArrayList<String>();
		applications.add("Arts");
		applications.add("Business");
        user.setApplications(applications);

		assertNotNull(user.getId());
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
