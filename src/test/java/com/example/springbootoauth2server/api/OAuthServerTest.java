package com.example.springbootoauth2server.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
public class OAuthServerTest {
    @Autowired
    protected MockMvc mockMvc;

    /**
     * 인증 코드 요청 테스트
     * 로그인 여부에 따라 리다이렉트 페이지가 다르다.
     * @throws Exception
     */
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
}
