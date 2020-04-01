package sysc4806.graduateAdmissions.controller;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sysc4806.graduateAdmissions.model.Department;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.repositories.InterestRepository;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sysc4806.graduateAdmissions.utilities.Utility.toJson;

/**
 * Tests for the InterestController using MockMVC and a mocked InterestRepository
 *
 * @author luke
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class InterestControllerTest {
    private final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);
    private List<Interest> interests;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InterestRepository repo;

    @BeforeEach
    void setUpMocks() {
        interests = Arrays.asList(
                Interest.builder().id(0).department(Department.SYSC).keyword("cybersecurity").build(),
                Interest.builder().id(1).department(Department.SYSC).keyword("testing").build(),
                Interest.builder().id(2).department(Department.SYSC).keyword("machine_learning").build(),
                Interest.builder().id(3).department(Department.MAAE).keyword("gears").build(),
                Interest.builder().id(4).department(Department.SREE).keyword("grilled_cheese").build());
    }

    /**Test the retrieval of all interests*/
    @Test
    public void testGetAllInterests() throws Exception {
        when(repo.findAll()).thenReturn(interests);
        this.mockMvc.perform(get("/interest/"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(interests)));
    }

    /**Test the retrieval of specific interests by id*/
    @Test
    public void testGetInterestById() throws Exception {
        for(Interest interest : interests) {
            when(repo.findById(interest.getId())).thenReturn(Optional.of(interest));
            this.mockMvc.perform(get("/interest/?id="+interest.getId()))
                    .andExpect(status().isOk())
                    .andExpect(content().json(toJson(interest)));
        }
    }

    /**Test the retrieval of all interests listed under a particular department*/
    @Test
    public void testGetInterestByDepartment() throws Exception {
        when(repo.findByDepartment(Department.SYSC)).thenReturn(interests.subList(0, 3));
        this.mockMvc.perform(get("/interest/department?department=SYSC"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(interests.subList(0, 3))));
    }

    /**Test the creation of a new Interest object via post*/
    @Test
    public void testPostNewInterest() throws Exception {
        Interest newInterest = Interest.builder().department(Department.MAAE).keyword("wheels").build();
        when(repo.save(newInterest)).thenReturn(newInterest);
        mockMvc.perform(post("/interest/create").contentType(APPLICATION_JSON_UTF8)
            .content(toJson(newInterest)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.department").value("MAAE"))
            .andExpect(jsonPath("$.keyword").value("wheels"));
    }

    /**Test the deletion of an Interest object via delete*/
    @Test
    public void testDeleteInterest() throws Exception {
        when(repo.findById(3L)).thenReturn(Optional.of(interests.get(3)));
        doNothing().when(repo).deleteById(3L);
        val result = mockMvc.perform(delete("/interest/{id}", "3"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("interest gears in MAAE successfully deleted"));
    }

    /**Test Interest deletion failing due to the Interest not existing*/
    @Test
    public void testDeleteInterestDoesNotExist() throws Exception {
        doNothing().when(repo).deleteById(42L);
        mockMvc.perform(delete("/interest/{id}", "42"))
                .andExpect(status().isNotFound());
    }

    /**Test Interest update*/
    @Test
    public void testUpdateInterest() throws Exception {
        when(repo.findByDepartment(Department.MAAE)).thenReturn(Collections.singletonList(interests.get(3)));
        mockMvc.perform(post("/interest/update").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Interest.builder().department(Department.MAAE).keyword("wheels").build())))
                .andExpect(status().isOk());
    }
}