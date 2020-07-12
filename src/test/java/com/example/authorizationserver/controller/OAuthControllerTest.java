package com.example.authorizationserver.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class OAuthControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    // TODO: 테스트 케이스 클래스명을 컨트롤러가 아닌 api 혹은 feature로 변경 해야 의미가 더 맞을것 같다.
    @Test
    public void requestAuthTest() throws Exception {

        this.mockMvc.perform(get("/oauth/authorize")
                .param("client_id", "cbw-client")
                .param("redirect_uri", "https://oauth.pstmn.io/v1/callback")
                .param("response_type", "code"))
                .andExpect(status().is3xxRedirection())
                .andDo(document("request-auth", requestParameters(
                        parameterWithName("client_id").description("앱 생성 시 발급 받은 REST API 키"),
                        parameterWithName("redirect_uri").description("코드를 리다이렉트 해줄 URI"),
                        parameterWithName("response_type").description("\"code\"로 고정")
                )));
    }

    //@Test
//    public void getTokenTest() throws Exception {
//
//        this.mockMvc.perform(post("/oauth/token")
//                .param("grant_type", "authorization_code")
//                .param("client_id", "testClientId")
//                .param("redirect_uri", "http://localhost:8080/client")
//                .param("code", "testCode"))
//                .andExpect(status().isOk())
//                .andDo(document("get-token", requestParameters(
//                        parameterWithName("grant_type").description("\"authorization_code\"로 고정"),
//                        parameterWithName("client_id").description("앱 생성 시 발급 받은 REST API"),
//                        parameterWithName("redirect_uri").description("코드가 리다이렉트된 URI"),
//                        parameterWithName("code").description("코드 받기 요청으로 얻은 인증 코드")
//                )));
//    }

}
