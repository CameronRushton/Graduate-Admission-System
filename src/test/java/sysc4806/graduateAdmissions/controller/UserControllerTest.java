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
import sysc4806.graduateAdmissions.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sysc4806.graduateAdmissions.utilities.Utility.toJson;

/**
 * Tests for UserController
 *
 * @author Kevin Sun
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private long id;
    private User user, professor;
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
    private List<User> users;
    private final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository repository;

    @SneakyThrows
    @BeforeEach
    void setUpMocks() {
        id = 1234;
        firstName = "John";
        lastName = "Smith";
        email = "JohnSmith@gmail.com";
        password = "password";
        term = new Term(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"), Season.FALL, "2020", true);

        createSelfApplication = Privilege.builder().id(42)
                .operation(Operation.CREATE).owner(Owner.SELF)
                .target(Target.APPLICATION).build();
        updateSelfApplication = Privilege.builder().id(9)
                .operation(Operation.UPDATE).owner(Owner.SELF)
                .target(Target.APPLICATION).build();
        privileges = Sets.newHashSet(createSelfApplication, updateSelfApplication);
        role = Role.builder().roleName("Student").privileges(privileges).build();
        profRole = Role.builder().roleName("Professor").privileges(privileges).build();
        professor = new User(id, firstName, lastName, email, password, profRole, interests, applications);
        Set<User> professors = new HashSet<User>();
        professors.add(professor);
        application = new Application(5, user, term, Department.SYSC, "Software Engineering", professors, Status.INCOMPLETE, 10, "resume.pdf");
        interests = new HashSet<Interest>();
        Interest i = new Interest(5, Department.SYSC, "Web Dev");
        interests.add(i);
        applications = new HashSet<Application>();
        users = new ArrayList<>();
        user = new User(id, firstName, lastName, email, password, role, interests, applications);
        users.add(user);
        users.add(user);

        when(repository.findByInterests(i)).thenReturn(users);
        when(repository.findByRole(role)).thenReturn(users);

    }


    /* Test get user with id */
    @Test
    public void testGetUserById() throws Exception {
        when(repository.findById(0L)).thenReturn(Optional.of(users.get(0)));
        this.mockMvc.perform(get("/user/?id=0"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(users.get(0))));
    }

    /* Test get user without id */
    @Test
    public void testGetAllUsers() throws Exception {
        when(repository.findAll()).thenReturn(users);
        this.mockMvc.perform(get("/user/"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(users)));
    }

    /* Test getUserOfRole */
    @Test
    public void testGetUserOfRole() throws Exception {
        this.mockMvc.perform(get("/user/role?role=Student"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(users.get(0))));
    }

    /* Test getUserOfInterest */
    @Test
    public void testGetUserOfInterest() throws Exception {
        this.mockMvc.perform(get("/user/interest?interest=Student"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(users.get(0))));
    }

    /* Test createUser */
    @Test
    public void testCreateUser() throws Exception {
        MvcResult result = mockMvc.perform(post("/user/create").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Privilege.builder().operation(Operation.READ).owner(Owner.SELF).target(Target.USER).build())))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("User added"));
    }

    /* Test delete existing User */
    @Test
    public void testDeleteUser() throws Exception {
        MvcResult result = mockMvc.perform(delete("/user/{id}", "5"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("User with id 5 deleted"));
    }

    /* Test delete User that does not exist */
    @Test
    public void testDeleteUserDoesNotExist() throws Exception {
        mockMvc.perform(delete("/user/{id}", "999"))
                .andExpect(status().isNotFound());
    }

    /* Test update User */
    @Test
    public void testUpdateUser() throws Exception {
        mockMvc.perform(post("/user/update").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Privilege.builder().owner(Owner.SELF).target(Target.APPLICATION).build())))
                .andExpect(status().isOk());
    }
}
