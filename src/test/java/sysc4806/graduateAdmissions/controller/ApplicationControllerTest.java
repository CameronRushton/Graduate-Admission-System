package sysc4806.graduateAdmissions.controller;

import lombok.SneakyThrows;
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
import sysc4806.graduateAdmissions.model.*;
import sysc4806.graduateAdmissions.repositories.ApplicationRepository;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sysc4806.graduateAdmissions.utilities.Utility.toJson;

/**
 * Tests for the ApplicationController/ApplicationRepository retrieval functions using MockMVC and a mocked
 * ApplicationRepository
 *
 * @author Madelyn
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.ANY)
public class ApplicationControllerTest {

    private final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);
    private List<Application> applications;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ApplicationRepository repo;

    private long ID;
    private User applicant1;
    private User applicant2;
    private Term term;
    private String degree;
    private Set<User> professors;
    private Status status;
    private double gpa;
    private String resumeFileName;

    @BeforeEach
    @SneakyThrows
    void setUpMocks() {
        applicant1 = new User();
        applicant1.setId(1);
        applicant2 = new User();
        term = new Term(new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSSX").parse("2020-08-0100:01:00.000+0000"), Season.FALL, 2020, true);
        degree = "test case";
        status = Status.INCOMPLETE;
        professors = new HashSet<>();
        gpa = 10.2;
        resumeFileName = "resume.pdf";

        applications = Arrays.asList(
                new Application(0, applicant1, term, Department.ELEC, degree,  professors, status, gpa, resumeFileName),
                new Application(1, applicant1, term, Department.SYSC, degree,  professors, status, gpa, resumeFileName),
                new Application(2, applicant2, term, Department.SREE, degree,  professors, status, gpa, resumeFileName));

        when(repo.findAll()).thenReturn(applications);
        when(repo.findByApplicant_id(applicant1.getId())).thenReturn(applications.subList(0, 2));
        when(repo.findByApplicant_id(applicant2.getId())).thenReturn(Collections.singletonList(applications.get(2)));
        for(int i = 0; i < 3; i++) {
            when(repo.findById((long) i)).thenReturn(Optional.of(applications.get(i)));
            doNothing().when(repo).deleteById((long) i);
        }
    }

    /**Test the retrieval of all applications*/
    @Test
    public void testGetAllApplications() throws Exception {
        this.mockMvc.perform(get("/application/"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(applications)));
    }

    /**Test the retrieval of a specific application by id*/
    @Test
    public void testGetApplicationById() throws Exception {
        for(Application application : applications){
            this.mockMvc.perform(get("/application/?id="+application.getId()))
                    .andExpect(status().isOk())
                    .andExpect(content().json(toJson(application)));
        }
    }

    /**Test the retrieval of all applications listed under a particular applicant*/
    @Test
    public void testGetApplicationByApplicant() throws Exception {
        this.mockMvc.perform(get("/application/applicant?id="+applicant1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(applications.subList(0, 2))));
    }

    /**Test the creation of a new application object via post*/
    @Test
    public void testPostNewApplication() throws Exception {
        val result = mockMvc.perform(post("/application/create").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Application.builder().id(3).applicant(applicant2).department(Department.MAAE).build())))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("application successfully added"));
    }

    /**Test the deletion of an Application object via delete*/
    @Test
    public void testDeleteApplication() throws Exception {
        val result = mockMvc.perform(delete("/application/{id}", "1"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("application to test case in SYSC for FALL 2020 successfully deleted"));
    }

    /**Test Application deletion failing due to the Application not existing*/
    @Test
    public void testDeleteApplicationDoesNotExist() throws Exception {
        mockMvc.perform(delete("/application/{id}", "42"))
                .andExpect(status().isNotFound());
    }

    /**Test application update*/
    @Test
    public void testUpdateApplication() throws Exception {
        mockMvc.perform(post("/application/update").contentType(APPLICATION_JSON_UTF8)
                .content(toJson(
                        Application.builder().applicant(applicant2).department(Department.MAAE).build())))
                .andExpect(status().isOk());
    }
}

