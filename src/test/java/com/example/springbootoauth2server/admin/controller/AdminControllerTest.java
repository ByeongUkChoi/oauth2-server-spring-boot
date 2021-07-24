package com.example.springbootoauth2server.admin.controller;

import com.example.springbootoauth2server.OAuth.domain.ClientEntity;
import com.example.springbootoauth2server.OAuth.repository.ClientRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest(AdminController.class)
//@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    @WithMockUser(roles = "ADMIN")
    void getClients() throws Exception {

        final String clientId1 = "cbw-client";
        final String name1 = "client-name";
        final String redirectUri1 = "https://";
        final String username1 = "cbw-user";

        final String clientId2 = "cbw-client2";
        final String name2 = "client-name2";
        final String redirectUri2 = "https://2";
        final String username2 = "cbw-user2";

        ClientEntity clientEntity1 = ClientEntity.builder()
                .clientId(clientId1)
                .clientSecret("client-secret")
                .name(name1)
                .redirectUri(redirectUri1)
                .grantTypes("grant-a")
                .username(username1)
                .build();
        ClientEntity clientEntity2 = ClientEntity.builder()
                .clientId(clientId2)
                .clientSecret("client-secret2")
                .name(name2)
                .redirectUri(redirectUri2)
                .grantTypes("grant-b")
                .username(username2)
                .build();

        List<ClientEntity> clientEntities = new ArrayList<>(Arrays.asList(clientEntity1, clientEntity2));
        Page<ClientEntity> clients = new PageImpl(clientEntities);
        when(clientRepository.findAll(any(Pageable.class))).thenReturn(clients);

        MvcResult result = mockMvc.perform(get("/admin/clients"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String body = result.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(body);
        JSONArray contents = (JSONArray) jsonObject.get("content");
        JSONObject content1 = (JSONObject) contents.get(0);
        JSONObject content2 = (JSONObject) contents.get(1);

        assertTrue(clientId1.equals(content1.get("clientId")));
        assertTrue(name1.equals(content1.get("name")));
        assertTrue(redirectUri1.equals(content1.get("redirectUri")));
        assertTrue(username1.equals(content1.get("username")));

        assertTrue(clientId2.equals(content2.get("clientId")));
        assertTrue(name2.equals(content2.get("name")));
        assertTrue(redirectUri2.equals(content2.get("redirectUri")));
        assertTrue(username2.equals(content2.get("username")));

        //.andExpect(MockMvcResultMatchers.content().json("{\"content\":[{\"clientId\":\"cbw-client\",\"clientSecret\":\"secret123\",\"name\":\"cbw-app\",\"redirectUri\":\"https://oauth.pstmn.io/v1/callback\",\"grantTypes\":\"Authorization Code\",\"username\":\"cbw0916\"}],\"pageable\":{\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"offset\":0,\"pageSize\":20,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"totalPages\":1,\"totalElements\":1,\"last\":true,\"size\":20,\"number\":0,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"numberOfElements\":1,\"first\":true,\"empty\":false}"));
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