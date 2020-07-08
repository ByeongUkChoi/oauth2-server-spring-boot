package com.example.authorizationserver.OAuth.controller;

import com.example.authorizationserver.OAuth.dto.TokenDto;
import com.example.authorizationserver.OAuth.service.OAuthService;
import com.example.authorizationserver.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class OAuthController {

    private OAuthService oAuthService;

    /**
     * 인증 코드 요청
     * 로그인 되어있지 않으면 로그인 페이지로 리다이렉트 시키고
     * 로그인 되어있으면 redirect_uri로 코드를 담아 리다이렉트 시킴
     * @param clientId 앱 생성 시 발급 받은 REST API 키
     * @param redirectUri 코드를 리다이렉트 해줄 URI
     * @param responseType "code"로 고정
     */
    @GetMapping("/oauth/authorize")
    public RedirectView requestAuth(@RequestParam("client_id") String clientId,
                                    @RequestParam("redirect_uri") String redirectUri,
                                    @RequestParam("response_type") String responseType,
                                    HttpServletRequest request,
                                    RedirectAttributes redirectAttributes) throws IOException {

        // 세션으로 로그인 여부 확인
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        // 로그인이 되어있지 않은 경우 로그인으로 redirect. 현재 접속 uri를 넘김
        if(member == null) {
            String currentUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
            redirectAttributes.addAttribute("continue", currentUrl);
            return new RedirectView("/login");
        }

        // 로그인이 되어있는 경우
        String code = oAuthService.getAuthorizationCode(member.getMemberId(), clientId, redirectUri);
        redirectAttributes.addAttribute("code", code);
        return new RedirectView(redirectUri);
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
    @PostMapping("/oauth/token")
    public TokenDto getToken(@RequestParam(value = "grant_type", required = true) String grantType,
                             @RequestParam(value = "client_id", required = true) String clientId,
                             @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                             @RequestParam(value = "code", required = false) String code,
                             @RequestParam(value = "refresh_token", required = false) String refreshToken,
                             @RequestParam(value = "client_secret", required = false) String clientSecret) throws Exception {

        // grant_type == 'authorization_code' : 토큰 발급, grant_type == 'refresh_token' : 토큰 갱신
        // TODO: 상수로 변경하거나 함수로 변경해야함
        TokenDto token;
        if (grantType.equals("authorization_code")) {
            token = oAuthService.getToken();
        } else if (grantType.equals("refresh_token")) {
            // TODO: 토큰 갱신 함수 사용해야함
            token = oAuthService.getToken();
        } else {
            throw new Exception();
        }


        // TODO: 검증 부분 추가 혹은 검증 부분도 서비스에서 해야함
        // TODO: 토큰 발급 시 authorize_code 검증 (만료 시간도)
        // TODO: 토큰 발급하면서 refresh_token insert
        // TokenDto token = oAuthService.토큰발급(cleintId, redirectUri, code, refreshToken,);

        return token;
    }
}
