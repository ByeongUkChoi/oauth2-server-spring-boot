package com.example.springbootoauth2server.admin.controller;

import com.example.springbootoauth2server.OAuth.entity.ClientEntity;
import com.example.springbootoauth2server.OAuth.repository.ClientRepository;
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

        ClientEntity clientEntity1 = ClientEntity.builder()
                .clientId("cbw-client")
                .clientSecret("client-secret")
                .name("client-name")
                .redirectUri("https://")
                .grantTypes("grant-a")
                .username("cbw-user")
                .build();
        ClientEntity clientEntity2 = ClientEntity.builder()
                .clientId("cbw-client2")
                .clientSecret("client-secret2")
                .name("client-name2")
                .redirectUri("https://2")
                .grantTypes("grant-b")
                .username("cbw-user2")
                .build();
        List<ClientEntity> clientEntities = new ArrayList<>(Arrays.asList(clientEntity1, clientEntity2));
        Page<ClientEntity> clients = new PageImpl(clientEntities);
        when(clientRepository.findAll(any(Pageable.class))).thenReturn(clients);

        MvcResult result = mockMvc.perform(get("/admin/clients"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        String a = content;
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