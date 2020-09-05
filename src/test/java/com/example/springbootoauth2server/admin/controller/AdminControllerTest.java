package com.example.springbootoauth2server.admin.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
//@WebMvcTest(AdminController.class)
//@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    void getClients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        // TODO: 검증을 해야함
    }

    @Test
    void getAuthorizationCodes() {
    }

    @Test
    void getAccessTokens() {
    }

    @Test
    void getRefreshTokens() {
    }
}