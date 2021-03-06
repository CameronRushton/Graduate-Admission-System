package sysc4806.graduateAdmissions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import sysc4806.graduateAdmissions.repositories.PrivilegeRepository;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sysc4806.graduateAdmissions.utilities.Utility.toJson;

/**
 * Tests for PrivilegeController
 *
 * @author Eric
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class PrivilegeControllerTest {
    private List<Privilege> privileges;
    private final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PrivilegeRepository repository;
    private String privilegeJson;

    @BeforeEach
    void setUpMocks() {
        privileges = Arrays.asList(
                Privilege.builder().id(1000L).operation(Operation.READ).target(Target.APPLICATION).owner(Owner.ALL_PROFS).build(),
                Privilege.builder().id(1001L).operation(Operation.CREATE).target(Target.ROLE).owner(Owner.ALL_PROFS).build(),
                Privilege.builder().id(1002L).operation(Operation.DELETE).target(Target.TERM).owner(Owner.ALL_PROFS).build(),
                Privilege.builder().id(1003L).operation(Operation.READ).target(Target.APPLICATION).owner(Owner.SELF).build(),
                Privilege.builder().id(1004L).operation(Operation.UPDATE).target(Target.INTEREST).owner(Owner.ALL_STUDENTS).build());

        when(repository.findByTarget(Target.TERM)).thenReturn(Collections.singletonList(privileges.get(2)));
        when(repository.findByOperation(Operation.UPDATE)).thenReturn(Collections.singletonList(privileges.get(4)));

        try {
            privilegeJson = toJson(
                    Privilege.builder().operation(Operation.READ).owner(Owner.SELF).target(Target.USER).build());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /* Test get privilege with id */
    @Test
    public void testGetPrivilegeById() throws Exception {
        when(repository.findById(1000L)).thenReturn(Optional.of(privileges.get(0)));

        this.mockMvc.perform(get("/privileges/?id=1000"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(privileges.get(0))));
    }

    /* Test get privilege without id */
    @Test
    public void testGetAllPrivileges() throws Exception {
        when(repository.findAll()).thenReturn(privileges);

        this.mockMvc.perform(get("/privileges/"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(privileges)));
    }

    /* Test getPrivilegeOfOwner */
    @Test
    public void testGetPrivilegeByOwner() throws Exception {
        when(repository.findByOwner(Owner.ALL_PROFS)).thenReturn(privileges.subList(0, 2));

        this.mockMvc.perform(get("/privileges/owner?owner=ALL_PROFS"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(privileges.subList(0, 2))));
    }

    /* Test getPrivilegeOfTarget */
    @Test
    public void testGetPrivilegeByTarget() throws Exception {
        this.mockMvc.perform(get("/privileges/target?target=TERM"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(Collections.singletonList(privileges.get(2)))));
    }

    /* Test getPrivilegeOfOperation */
    @Test
    public void testGetPrivilegeByOperation() throws Exception {
        when(repository.findById(1004L)).thenReturn(Optional.of(privileges.get(4)));

        this.mockMvc.perform(get("/privileges/operation?operation=UPDATE"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(Collections.singletonList(privileges.get(4)))));
    }

    /* Test createPrivilege */
    @Test
    public void testCreatePrivilege() throws Exception {
        MvcResult result = mockMvc.perform(post("/privileges/").contentType(APPLICATION_JSON_UTF8)
                .content(privilegeJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Privilege added"));
    }

    /* Test delete existing Privilege */
    @Test
    public void testDeletePrivilege() throws Exception {
        when(repository.findById(1004L)).thenReturn(Optional.of(privileges.get(4)));

        MvcResult result = mockMvc.perform(delete("/privileges/{id}", "1004"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("privilege with id 1004 deleted"));
    }

    /* Test delete Privilege that does not exist */
    @Test
    public void testDeletePrivilegeDoesNotExist() throws Exception {
        mockMvc.perform(delete("/privileges/{id}", "999"))
                .andExpect(status().isNotFound());
    }

    /* Test update Privilege */
    @Test
    public void testUpdatePrivilege() throws Exception {
        when(repository.findById(1000L)).thenReturn(Optional.of(privileges.get(0)));

        mockMvc.perform(put("/privileges/").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Privilege.builder().id(1000L).operation(Operation.READ).target(Target.APPLICATION).owner(Owner.ALL_STUDENTS).build())))
                .andExpect(status().isOk());
    }

    /* Test update Privilege with an invalid Privilege*/
    @Test
    public void testUpdatePrivilegeInvalidPrivilege() throws Exception {
        mockMvc.perform(put("/privileges/").contentType(APPLICATION_JSON_UTF8)
                .content("{\"id\":0,\"operation\":\"BANANA\",\"target\":\"APPLE\",\"owner\":\"LEMON\"}"))
                .andExpect(status().isBadRequest());
    }
}
