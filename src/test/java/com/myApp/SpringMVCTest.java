package com.myApp;

import com.myApp.controller.BuddyController;
import com.myApp.model.BuddyInfo;
import com.myApp.model.BuddyInfoRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class SpringMVCTest {

    @Autowired
    private MockMvc mockMvc;

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

    // How do I add a payload?
    @Test
    public void testCreatePostEndpoint() throws Exception {
        this.mockMvc.perform(post("/create")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testDeleteEndpoint() throws Exception {
        this.mockMvc.perform(delete("/delete?id=1")).andDo(print()).andExpect(status().isOk());
    }
}
