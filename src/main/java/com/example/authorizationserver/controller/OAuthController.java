package com.example.authorizationserver.controller;

import com.example.authorizationserver.dto.Token;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class OAuthController {

    /**
     * 인증 코드 요청
     * 로그인 되어있지 않으면 로그인 페이지로 리다이렉트 시킴
     * @param clientId 앱 생성 시 발급 받은 REST API 키
     * @param redirectUri 코드를 리다이렉트 해줄 URI
     * @param responseType "code"로 고정
     */
    @GetMapping("/oauth/authorize")
    public void requestAuth(@RequestParam("client_id") String clientId,
                              @RequestParam("redirect_uri") String redirectUri,
                              @RequestParam("response_type") String responseType,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        // TODO: 테스트로 clientId, response_type 검사하지 않음

        String currentUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
        String encodedCurrentUrl = URLEncoder.encode(currentUrl, StandardCharsets.UTF_8.toString());
        response.sendRedirect("/login?continue=" + encodedCurrentUrl);
    }

    // TODO: require = false 제거하기
    @PostMapping("/oauth/token")
    public Token getToken(@RequestParam(value = "grant_type", required = false) String grantType,
                          @RequestParam(value = "client_id", required = false) String clientId,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                          @RequestParam(value = "code", required = false) String code) {
        Token token = Token.builder()
                .access_token("this_is_access_token")
                .token_type("bearer")
                .refresh_token("this_is_refresh_token")
                .expires_in(3600)
                .build();
        return token;
    }
}
