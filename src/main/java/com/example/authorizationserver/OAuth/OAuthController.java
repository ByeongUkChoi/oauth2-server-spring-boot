package com.example.authorizationserver.OAuth;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OAuthService oAuthService;

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

    /**
     * 토큰 발급(발급, 갱신)
     * @param grantType     authorization_code / refresh_token (발급, 갱신)
     * @param clientId      앱 생성 시 발급 받은 REST API (발급, 갱신 시)
     * @param redirectUri   코드가 리다이렉트된 URI (발급 시)
     * @param code          코드 받기 요청으로 얻은 인증 코드 (발급 시)
     * @param refreshToken  토큰 발급 시 응답으로 받은 refresh_token. Access Token을 갱신하기 위해 사용 (갱신 시)
     * @param clientSecret  토큰 발급 시, 보안을 강화하기 위해 추가 확인하는 코드(보안 기능 ON일 경우 필수 설정 해야함) (발급, 갱신 시)
     * @return
     */
    @PostMapping("/oauth/token")
    public Token getToken(@RequestParam(value = "grant_type", required = true) String grantType,
                          @RequestParam(value = "client_id", required = true) String clientId,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                          @RequestParam(value = "code", required = false) String code,
                          @RequestParam(value = "refresh_token", required = false) String refreshToken,
                          @RequestParam(value = "client_secret", required = false) String clientSecret) {

        // TODO: 검증 부분 추가 혹은 검증 부분도 서비스에서 해야함
        
        Token token = oAuthService.getToken();
        return token;
    }
}
