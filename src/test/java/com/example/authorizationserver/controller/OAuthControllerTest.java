package com.example.authorizationserver.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureRestDocs
public class OAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void requestAuthTest() throws Exception {

        this.mockMvc.perform(get("/oauth/authorize")
                .param("client_id", "testClientId")
                .param("redirect_uri", "http://localhost:8080/client")
                .param("response_type", "code"))
                .andExpect(status().isOk())
                .andDo(document("request-auth", requestParameters(
                        parameterWithName("client_id").description("앱 생성 시 발급 받은 REST API 키"),
                        parameterWithName("redirect_uri").description("코드를 리다이렉트 해줄 URI"),
                        parameterWithName("response_type").description("\"code\"로 고정")
                )));
    }

    @Test
    public void getTokenTest() throws Exception {

        this.mockMvc.perform(post("/oauth/token")
                .param("grant_type", "authorization_code")
                .param("client_id", "testClientId")
                .param("redirect_uri", "http://localhost:8080/client")
                .param("code", "testCode"))
                .andExpect(status().isOk())
                .andDo(document("get-token", requestParameters(
                        parameterWithName("grant_type").description("\"authorization_code\"로 고정"),
                        parameterWithName("client_id").description("앱 생성 시 발급 받은 REST API"),
                        parameterWithName("redirect_uri").description("코드가 리다이렉트된 URI"),
                        parameterWithName("code").description("코드 받기 요청으로 얻은 인증 코드")
                )));
    }

}
