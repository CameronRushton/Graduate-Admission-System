package sysc4806.graduateAdmissions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sysc4806.graduateAdmissions.model.BuddyInfo;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc
//@SpringBootTest
public class SpringMVCTest {

//    @Autowired
//    private MockMvc mockMvc;

//    @Test
//    public void testEndpoint() throws Exception {
//        BuddyInfo buddy = new BuddyInfo("name", "", "", 33);
//        List myBuddies = new ArrayList<BuddyInfo>();
//        myBuddies.add(buddy);
//        when(repo.findAll()).thenReturn(myBuddies);
//        this.mockMvc.perform(get("/buddies")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Kim")));
//        this.mockMvc.perform(get("/buddies")).andDo(print()).andExpect(status().isOk());
//    }

//    @Test
//    public void testCreatePostEndpoint() throws Exception {
//        MediaType APPLICATION_JSON_UTF8 =
//                new MediaType(MediaType.APPLICATION_JSON.getType(),
//                        MediaType.APPLICATION_JSON.getSubtype(),
//                        StandardCharsets.UTF_8);
//
//        BuddyInfo buddyInfo = new BuddyInfo();
//        buddyInfo.setName("bob");
//        buddyInfo.setAddress("address");
//        buddyInfo.setPhoneNumber("number");
//        buddyInfo.setAge(21);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson=ow.writeValueAsString(buddyInfo);
//
//        mockMvc.perform(post("/create").contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testDeleteEndpoint() throws Exception {
//        this.mockMvc.perform(delete("/delete?id=1")).andDo(print()).andExpect(status().isOk());
//    }
}
