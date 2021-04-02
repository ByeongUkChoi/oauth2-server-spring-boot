package com.example.springbootoauth2server.OAuth.controller;

import com.byeongukchoi.oauth2.server.application.dto.AuthorizationRequestDto;
import com.byeongukchoi.oauth2.server.application.dto.TokenDto;
import com.example.springbootoauth2server.OAuth.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private OAuthService oAuthService;

    /**
     * 인증 코드 요청
     * spring security에서 로그인 된 사용자만 접속 가능
     * redirect_uri로 코드를 담아 리다이렉트 시킴
     * client_id 앱 생성 시 발급 받은 REST API 키
     * redirect_uri 코드를 리다이렉트 해줄 URI
     * response_type "code"로 고정
     */
    @GetMapping("/authorize")
    public RedirectView requestAuth(HttpServletRequest request,
                                    @RequestParam(value = "client_id", required = true) String clientId,
                                    @RequestParam(value = "redirect_uri", required = true) String redirectUri,
                                    @RequestParam(value = "response_type", required = true) String responseType,
                                     RedirectAttributes redirectAttributes) throws Exception {

        if(! responseType.equals("code")) {
            throw new Exception("Invalid Response type");
        }

        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();

        AuthorizationRequestDto authorizationRequestDto = AuthorizationRequestDto.builder()
                .clientId(clientId)
                .redirectUri(redirectUri)
                .username(username)
                .build();

        return oAuthService.getAuthorizationCode(authorizationRequestDto, redirectAttributes);
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
     *
     *  토큰 발급
     *  grant_type, client_id, redirect_uri, code, client_secret
     *  토큰 갱신
     *  grant_type, client_id, refresh_token, client_secret
     */
    @PostMapping("/token")
    public TokenDto getToken(@RequestParam(value = "grant_type", required = true) String grantType,
                             @RequestParam(value = "client_id", required = true) String clientId,
                             @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                             @RequestParam(value = "code", required = false) String code,
                             @RequestParam(value = "refresh_token", required = false) String refreshToken,
                             @RequestParam(value = "client_secret", required = false) String clientSecret) throws Exception {

        AuthorizationRequestDto authorizationRequestDto = AuthorizationRequestDto.builder()
                .grantType(grantType)
                .clientId(clientId)
                .redirectUri(redirectUri)
                .code(code)
                .refreshToken(refreshToken)
                .clientSecret(clientSecret)
                .build();

        return oAuthService.issueToken(authorizationRequestDto);
    }
}
