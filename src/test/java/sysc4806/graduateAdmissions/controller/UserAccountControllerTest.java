package sysc4806.graduateAdmissions.controller;

import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import sysc4806.graduateAdmissions.model.*;
import sysc4806.graduateAdmissions.repositories.RoleRepository;
import sysc4806.graduateAdmissions.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sysc4806.graduateAdmissions.utilities.Utility.toJson;

/**
 * Tests for UserController
 *
 * @author Kevin Sun
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserAccountControllerTest {
    private long id;
    private UserAccount userAccount, professor;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role, profRole;
    private Privilege createSelfApplication, updateSelfApplication;;
    private Set<Privilege> privileges;
    private Set<Interest> interests;
    private Application application;
    private Set<Application> applications;
    private Term term;
    private List<UserAccount> userAccounts;
    private final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository repository;
    @MockBean
    private RoleRepository roleRepository;

    @SneakyThrows
    @BeforeEach
    void setUpMocks() {
        id = 1234;
        firstName = "John";
        lastName = "Smith";
        email = "JohnSmith@gmail.com";
        password = "password";
        term = new Term(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"), Season.FALL, 2020, true);

        createSelfApplication = Privilege.builder().id(42)
                .operation(Operation.CREATE).owner(Owner.SELF)
                .target(Target.APPLICATION).build();
        updateSelfApplication = Privilege.builder().id(9)
                .operation(Operation.UPDATE).owner(Owner.SELF)
                .target(Target.APPLICATION).build();
        privileges = Sets.newHashSet(createSelfApplication, updateSelfApplication);
        role = Role.builder().roleName("Student").privileges(privileges).build();
        profRole = Role.builder().roleName("Professor").privileges(privileges).build();
        professor = new UserAccount(id, firstName, lastName, email, profRole, interests, applications);
        Set<UserAccount> professors = new HashSet<UserAccount>();
        professors.add(professor);
        application = new Application(5, userAccount, term, Department.SYSC, "Software Engineering", professors, Status.INCOMPLETE, 10, "resume.pdf");
        interests = new HashSet<Interest>();
        Interest i = new Interest(5, Department.SYSC, "Web Dev");
        interests.add(i);
        applications = new HashSet<Application>();
        userAccounts = new ArrayList<>();
        userAccount = new UserAccount(id, firstName, lastName, email, role, interests, applications);
        userAccounts.add(userAccount);
        userAccounts.add(userAccount);

    }

    /**
     * Test get user with id
     */
    @Test
    public void testGetUserById() throws Exception {
        when(repository.findById(1234L)).thenReturn(Optional.of(userAccounts.get(0)));
        this.mockMvc.perform(get("/users/{id}", "1234"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(userAccounts.get(0))));
    }

    /**
     * Test get user with id
     */
    @Test
    public void testGetUserIdNotFound() throws Exception {
        when(repository.findById(1234L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/users/{id}", "1111"))
                .andExpect(status().isNotFound());
    }

    /** Test get user without id */

    @Test
    public void testGetAllUsers() throws Exception {
        when(repository.findAll()).thenReturn(userAccounts);
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(userAccounts)));
    }

    /** Test getUserOfRole */
    @Test
    public void testGetUserOfRole() throws Exception {
        Role role = Role.builder().roleName("STUDENT").privileges(privileges).build();
        when(roleRepository.findByRoleName(role.getRoleName())).thenReturn(Optional.of(role));
        when(repository.findByRole(role)).thenReturn(userAccounts);
        this.mockMvc.perform(get("/users/roles?roleName=STUDENT"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(userAccounts)));
    }

//    /** Test getUserOfInterest */
//    @Test
//    public void testGetUserOfInterest() throws Exception {
//        this.mockMvc.perform(get("/user/interest?interest=Student"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(toJson(users.get(0))));
//    }

    /** Test createUser */
    @Test
    public void testCreateUser() throws Exception {
        UserAccount testUserAccount = UserAccount.builder().firstName(firstName).lastName(lastName).email(email).role(role).interests(interests).applications(applications).build();
        when(repository.save(testUserAccount)).thenReturn(testUserAccount);
        MvcResult result = mockMvc.perform(post("/users").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        testUserAccount)))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("John"));
    }

    /** Test delete existing User */
    @Test
    public void testDeleteUser() throws Exception {
        UserAccount testUserAccount = UserAccount.builder().id(1L).firstName(firstName).lastName(lastName).email(email).role(role).interests(interests).applications(applications).build();
        when(repository.findById(1L)).thenReturn(Optional.of(testUserAccount));
        MvcResult result = mockMvc.perform(delete("/users/{id}", "1"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("User with id 1 deleted"));
    }

    /** Test delete User that does not exist */
    @Test
    public void testDeleteUserDoesNotExist() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/users/{id}", "999"))
                .andExpect(status().isNotFound());
    }

    /** Test update User */
    @Test
    public void testUpdateUser() throws Exception {
        UserAccount testUserAccount = UserAccount.builder().id(1L).firstName("Will").lastName(lastName).email(email).role(role).interests(interests).applications(applications).build();
        UserAccount newUserAccount = UserAccount.builder().id(1L).firstName("Chad").lastName("Banana").build();
        when(repository.findById(1L)).thenReturn(Optional.of(testUserAccount));
        when(repository.save(testUserAccount)).thenReturn(newUserAccount);
        mockMvc.perform(put("/users").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        testUserAccount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Chad"));

    }

    /** Test update User */
    @Test
    public void testUpdateUserNotFound() throws Exception {
        UserAccount testUserAccount = UserAccount.builder().id(1L).firstName("Will").lastName(lastName).email(email).role(role).interests(interests).applications(applications).build();
        when(repository.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(put("/users").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        testUserAccount)))
                .andExpect(status().isNotFound());

    }
}
