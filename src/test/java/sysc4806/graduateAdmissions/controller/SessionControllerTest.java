package sysc4806.graduateAdmissions.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SessionControllerTest {
    private final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SessionController sessionController;

    @Test
    public void testBadLoginToken() throws Exception {
            mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8)
                .content("invalid token"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testValidLoginToken() throws Exception {
        String mockToken = "valid token";
        Payload p = new Payload();
        p.set("email", "fake@gmail.com");
        p.set("name", "john smith");
        when(sessionController.verifyToken(mockToken)).thenReturn(p);

        mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8)
                .content(mockToken))
                .andExpect(status().isOk());
    }
}