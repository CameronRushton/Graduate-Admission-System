package sysc4806.graduateAdmissions.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import sysc4806.graduateAdmissions.model.Season;
import sysc4806.graduateAdmissions.model.Term;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TermControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTermWithId() throws Exception {
        Term term = Term.builder().id(1L).active(true).deadline("never").season(Season.SUMMER).year("1234").build();
        // Test using the endpoint with mocked, running spring instance
        // Should be some incoming DTO
        mockMvc.perform(get("/terms/{id}", term.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }

}
