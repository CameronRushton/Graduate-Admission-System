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
import sysc4806.graduateAdmissions.repositories.TermRepositoryDAO;

import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sysc4806.graduateAdmissions.utilities.Utility.toJson;

/**
 * @author crushton
 */
@AutoConfigureMockMvc
@SpringBootTest
public class TermControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TermRepositoryDAO repository;

    @Test
    public void testGetTermWithId() throws Exception {
        Term term = Term.builder()
                .id(0L)
                .active(true)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .season(Season.SUMMER)
                .year("1234")
                .build();
        when(repository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(term));
        mockMvc.perform(get("/terms/{id}", term.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testGetTermNotFound() throws Exception {
        Term term = Term.builder()
                .id(0L)
                .active(true)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .season(Season.SUMMER)
                .year("1234")
                .build();
        when(repository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());
        mockMvc.perform(get("/terms/{id}", term.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(404));
    }

    @Test
    public void testCreateTerm() throws Exception {
        Term term = Term.builder()
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .active(true)
                .season(Season.SUMMER)
                .year("1234")
                .build();
        mockMvc.perform(post("/terms")
                .content(toJson(term))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testCreateTermNoDeadline() throws Exception {
        Term term = Term.builder()
                .id(1L)
                .active(true)
                .season(Season.SUMMER)
                .year("1234")
                .build();
        mockMvc.perform(post("/terms")
                .content(toJson(term))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    public void testCreateTermNoSeason() throws Exception {
        Term term = Term.builder()
                .id(1L)
                .active(true)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .year("1234")
                .build();
        mockMvc.perform(post("/terms")
                .content(toJson(term))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    public void testCreateTermNoActiveStatus() throws Exception {
        Term term = Term.builder()
                .id(1L)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .season(Season.SUMMER)
                .year("1234")
                .build();
        mockMvc.perform(post("/terms")
                .content(toJson(term))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void testCreateTermNoYear() throws Exception {
        Term term = Term.builder()
                .id(1L)
                .active(true)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .season(Season.SUMMER)
                .build();
        mockMvc.perform(post("/terms")
                .content(toJson(term))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    public void testUpdateTerm() throws Exception {
        Term term = Term.builder()
                .id(1L)
                .active(true)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .season(Season.SUMMER)
                .year("1234")
                .build();
        mockMvc.perform(put("/terms")
                .content(toJson(term))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void testDeleteTermNotExist() throws Exception {
        mockMvc.perform(delete("/terms/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(404));
    }

    @Test
    public void testDeleteTerm() throws Exception {
        doNothing().when(repository).deleteById(any(Long.class));
        when(repository.existsById(any(Long.class))).thenReturn(true);
        mockMvc.perform(delete("/terms/{id}", 0L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }
}
