package com.example.springbootoauth2server.OAuth.controller;

import com.byeongukchoi.oauth2.server.application.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * redirect를 위해 oauth client가 필요해서 만듬
 */
@RequiredArgsConstructor
@RestController
public class ClientController {

    private RestTemplate restTemplate;

    /**
     * 로그인 완료 redirect 받을 api
     * code를 가지고 token을 요청한다.
     * @param code
     * @return
     */
    @GetMapping("/oauth/callback")
    public TokenDto callback(@RequestParam(value = "code", required = false) String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "http://localhost:8080/oauth2/callback");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/oauth/token", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            String body = response.getBody();
            return null;
            //return gson.fromJson(response.getBody(), OAuthToken.class);
        }
        return null;
    }
}
