package sysc4806.graduateAdmissions.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import sysc4806.graduateAdmissions.dto.TermDTO;
import sysc4806.graduateAdmissions.mapper.TermMapper;
import sysc4806.graduateAdmissions.model.Season;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.repositories.TermRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sysc4806.graduateAdmissions.utilities.Utility.toJson;

/**
 * @author crushton
 */
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class TermControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TermMapper termMapper;

    @MockBean
    TermRepository repository;

    @Test
    public void testGetTerms() throws Exception {
        Term term = Term.builder()
                .id(1000L)
                .active(true)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .season(Season.SUMMER)
                .year("1234")
                .build();
        List<Term> terms = new ArrayList<>();
        terms.add(term);
        when(repository.findAll()).thenReturn(terms);
        mockMvc.perform(get("/terms")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testGetTermWithId() throws Exception {
        Term term = Term.builder()
                .id(1000L)
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
                .id(1000L)
                .active(true)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .season(Season.SUMMER)
                .year("1234")
                .build();
        when(repository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());
        mockMvc.perform(get("/terms/{id}", term.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void testCreateTerm() throws Exception {
        TermDTO termDTO = TermDTO.builder()
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-01"))
                .active(true)
                .season(Season.SUMMER)
                .year("1234")
                .build();
        when(repository.save(any(Term.class))).thenReturn(termMapper.map(termDTO));
        mockMvc.perform(post("/terms")
                .content(toJson(termDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));
    }

    @Test
    public void testUpdateTerm() throws Exception {
        TermDTO termDTO = TermDTO.builder()
                .termId(1000L)
                .active(true)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .season(Season.SUMMER)
                .year("1234")
                .build();
        when(repository.findById(termDTO.getTermId())).thenReturn(Optional.of(termMapper.map(termDTO)));
        termDTO.setYear("2021");
        when(repository.save(any(Term.class))).thenReturn(termMapper.map(termDTO));
        mockMvc.perform(put("/terms")
                .content(toJson(termDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.year").value("2021"));
    }

    @Test
    public void testUpdateTermDoesNotExist() throws Exception {
        TermDTO termDTO = TermDTO.builder()
                .termId(1099L)
                .active(true)
                .deadline(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"))
                .season(Season.SUMMER)
                .year("1234")
                .build();
        mockMvc.perform(put("/terms")
                .content(toJson(termDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void testDeleteTermNotExist() throws Exception {
        mockMvc.perform(delete("/terms/{id}", 1001L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void testDeleteTerm() throws Exception {
        doNothing().when(repository).deleteById(any(Long.class));
        when(repository.existsById(any(Long.class))).thenReturn(true);
        mockMvc.perform(delete("/terms/{id}", 1000L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}
