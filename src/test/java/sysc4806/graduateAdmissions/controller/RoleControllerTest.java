package sysc4806.graduateAdmissions.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import sysc4806.graduateAdmissions.model.*;
import sysc4806.graduateAdmissions.repositories.RoleRepository;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sysc4806.graduateAdmissions.utilities.Utility.toJson;

/**
 * Test class for RoleController
 *
 * @author Eric
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RoleControllerTest {
    private List<Role> roles;
    private final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoleRepository repository;

    @BeforeEach
    void setUpMocks() {
        Set<Privilege> studentPrivileges = new HashSet<>();
        studentPrivileges.add(Privilege.builder().id(1000L).owner(Owner.ALL_STUDENTS).build());
        Set<Privilege> adminPrivileges = new HashSet<>();
        studentPrivileges.add(Privilege.builder().id(1001L).owner(Owner.SELF).build());
        Set<Privilege> professorPrivileges = new HashSet<>();
        studentPrivileges.add(Privilege.builder().id(1002L).owner(Owner.ALL_PROFS).build());

        roles = Arrays.asList(
                Role.builder().roleName("apple").privileges(studentPrivileges).build(),
                Role.builder().roleName("banana").privileges(adminPrivileges).build(),
                Role.builder().roleName("orange").privileges(professorPrivileges).build());

        when(repository.findAll()).thenReturn(roles);
        when(repository.findByRoleName("banana")).thenReturn(Optional.of(roles.get(1)));
        when(repository.findByRoleName("apple")).thenReturn(Optional.of(roles.get(0)));
    }

    /* Test get roles without id */
    @Test
    public void testGetAllRoles() throws Exception {
        this.mockMvc.perform(get("/role/"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(roles)));
    }

    /* Test getting role by name */
    @Test
    public void testGetRoleByName() throws Exception {
        this.mockMvc.perform(get("/role/?name=banana"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(roles.get(1))));
    }

    /* Test create Role */
    @Test
    public void testCreateRole() throws Exception {
        Set<Privilege> privileges = new HashSet<>();
        privileges.add(Privilege.builder().id(1000L).owner(Owner.ALL_STUDENTS).build());
        MvcResult result = mockMvc.perform(post("/role/create").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Role.builder().roleName("new").privileges(privileges).build())))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Role added"));
    }

    /* Test delete existing Role */
    @Test
    public void testDeleteRole() throws Exception {
        MvcResult result = mockMvc.perform(delete("/role/{name}", "apple"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("apple Role deleted"));
    }

    /* Test delete Role that does not exist */
    @Test
    public void testDeleteRoleDoesNotExist() throws Exception {
        mockMvc.perform(delete("/role/{name}", "guava"))
                .andExpect(status().isNotFound());
    }

    /* Test update Role */
    @Test
    public void testUpdateRole() throws Exception {
        mockMvc.perform(post("/role/update").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Role.builder().roleName("apple").build())))
                .andExpect(status().isOk());
    }

    /* Test add privilege too Role */
    @Test
    public void testAddPrivilegeToRole() throws Exception {
        MvcResult result = mockMvc.perform(post("/role/add?name=banana").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Privilege.builder().owner(Owner.ALL_STUDENTS).target(Target.APPLICATION).operation(Operation.UPDATE).id(1001L).build())))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("added privilege 1001 too Role"));
    }

    /* Test remove privilege from Role */
    @Test
    public void testRemovePrivilegeFromRole() throws Exception {
        MvcResult result = mockMvc.perform(post("/role/remove?name=banana").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Privilege.builder().owner(Owner.ALL_STUDENTS).target(Target.APPLICATION).operation(Operation.UPDATE).id(1001L).build())))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("removed privilege 1001 from Role"));
    }
}
