package sysc4806.graduateAdmissions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import sysc4806.graduateAdmissions.model.Department;
import sysc4806.graduateAdmissions.model.Interest;
import sysc4806.graduateAdmissions.repositories.InterestRepository;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the InterestController using MockMVC and a mocked InterestRepository
 *
 * @author luke
 */
@SpringBootTest
@AutoConfigureMockMvc
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

        when(repo.findAll()).thenReturn(interests);
        when(repo.findByDepartment(Department.SYSC)).thenReturn(interests.subList(0, 3));
        when(repo.findByDepartment(Department.MAAE)).thenReturn(Collections.singletonList(interests.get(3)));
        when(repo.findByDepartment(Department.SREE)).thenReturn(Collections.singletonList(interests.get(4)));
        for(int i = 0; i <= 4; i++) {
            when(repo.findById((long) i)).thenReturn(Optional.of(interests.get(i)));
            doNothing().when(repo).deleteById((long) i);
        }
    }

    /**
     * helper method to convert objects into JSON format
     *
     * @param o the object to convert to JSON format
     * @return a string contain the JSON for Object o
     * @throws JsonProcessingException when JSON writing fails
     */
    private String toJson(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }

    /**Test the retrieval of all interests*/
    @Test
    public void testGetAllInterests() throws Exception {
        this.mockMvc.perform(get("/interest/"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(interests)));
    }

    /**Test the retrieval of specific interests by id*/
    @Test
    public void testGetInterestById() throws Exception {
        for(Interest interest : interests){
            this.mockMvc.perform(get("/interest/?id="+interest.getId()))
                    .andExpect(status().isOk())
                    .andExpect(content().json(toJson(interest)));
        }
    }

    /**Test the retrieval of all interests listed under a particular department*/
    @Test
    public void testGetInterestByDepartment() throws Exception {
        this.mockMvc.perform(get("/interest/department?department=SYSC"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(interests.subList(0, 3))));
    }
}