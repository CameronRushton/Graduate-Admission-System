package sysc4806.graduateAdmissions.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import sysc4806.graduateAdmissions.model.Season;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.repositories.TermRepository;

import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sysc4806.graduateAdmissions.utilities.Utility.toJson;

@AutoConfigureMockMvc
@SpringBootTest
public class TermControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TermRepository repository;

    @Test
    public void testGetTermWithId() throws Exception {
        Term term = Term.builder().id(0L).active(true).deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01")).season(Season.SUMMER).year("1234").build();
        when(repository.findTermById(any(Long.class))).thenReturn(term);
        mockMvc.perform(get("/terms/{id}", term.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testGetTermNotFound() throws Exception {
        Term term = Term.builder().id(0L).active(true).deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01")).season(Season.SUMMER).year("1234").build();
        when(repository.findTermById(any(Long.class))).thenReturn(null);
        mockMvc.perform(get("/terms/{id}", term.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(404));
    }

    @Test
    public void testCreateTerm() throws Exception {
        Term term = Term.builder().deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01")).active(true).season(Season.SUMMER).year("1234").build();
        mockMvc.perform(post("/terms")
                .content(toJson(term))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testCreateTermNoDeadline() throws Exception {
        Term term = Term.builder().id(1L).active(true).season(Season.SUMMER).year("1234").build();
        mockMvc.perform(post("/terms", term)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400));
    }
}
