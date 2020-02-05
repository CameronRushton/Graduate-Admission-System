package com.myApp;

import com.myApp.controller.BuddyController;
import com.myApp.model.BuddyInfo;
import com.myApp.model.BuddyInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebMvcTest
public class SpringMVCTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private BuddyInfoRepository repo;

    @Test
    public void testEndpoint() throws Exception {
//        BuddyInfo buddy = new BuddyInfo("name", "", "", 33);
//        List myBuddies = new ArrayList<BuddyInfo>();
//        myBuddies.add(buddy);
//        when(repo.findAll()).thenReturn(myBuddies);
//        this.mockMvc.perform(get("/buddies")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Kim")));
        this.mockMvc.perform(get("/buddies")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testCreateEndpoint() throws Exception {
        this.mockMvc.perform(get("/create")).andDo(print()).andExpect(status().isOk());
    }

    // How do I add a payload?
    @Test
    public void testCreatePostEndpoint() throws Exception {
        this.mockMvc.perform(post("/create")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testDeleteEndpoint() throws Exception {
        this.mockMvc.perform(get("/delete?id=1")).andDo(print()).andExpect(status().isOk());
    }

//    @Autowired
//    private TestRestTemplate restTemplate;
//    private String port = "8080";
//
//    @Test
//    public void returnDefaultMessage() throws Exception {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Kim");
//    }
    

}
