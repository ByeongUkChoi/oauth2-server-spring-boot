package com.example.authorizationserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest
@AutoConfigureWebClient(registerRestTemplate = true)    // 이렇게 해줘야 restTemplate가 생성되면서 테스트가 됨
public class WebLayerTest {

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        server.expect(requestTo("/"))
                .andRespond(withSuccess("{\"message\":\"Hello, World\"}", MediaType.APPLICATION_JSON));
    }
}
