package com.example.authorizationserver.authentication.controller;

import com.example.authorizationserver.OAuth.dao.AuthorizeCodeRepository;
import com.example.authorizationserver.OAuth.domain.AuthorizationCode;
import com.example.authorizationserver.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 로그인 컨트롤러
 * oauth 서버와 다른 타 서버여도 됨
 */
@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    // TODO: 서비스레이어에서 처리하도록 분리 예정
    @Autowired
    private AuthorizeCodeRepository authorizeCodeRepository;


    /**
     * 로그인 페이지
     * @param continueUrl
     */
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "continue", required = false) String continueUrl,
                            Model model) {
        // TODO: 로그인 페이지 반환
        model.addAttribute("continue", continueUrl);
        return "login";
    }

    /**
     * 로그인
     */
    @PostMapping("/authenticate")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("continue") String continueUrl,
                        RedirectAttributes redirectAttributes) throws Exception {

        // 로그인 실패시 에러처리
        if (authenticationService.login(username, password) == false) {
            throw new Exception();
        }


        // TODO: service 안에서 동작 하도록 service 함수로 분리
        String decodedContinueUrl = URLDecoder.decode(continueUrl, StandardCharsets.UTF_8.toString());

        UriComponents uriComponents = UriComponentsBuilder.fromUriString(decodedContinueUrl).build();
        MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();
        String redirectUri = queryParams.getFirst("redirect_uri");

        String code = "test-authorize-code";

        // TODO: 코드 생성기가 있으면 좋을것 같음. 생성기로 만들고 getCode 함수로 코드를 가져와 반환함
        // authorize_code 생성 및 반환
        String clientId = queryParams.getFirst("client_id");
        AuthorizationCode authorizationCode = AuthorizationCode.builder()
                .clientId(clientId)
                .memberId(90001)    // TODO: test
                .redirectUri(redirectUri)
                .expires(123123123)
                .code(code).build();
        // authorize code insert
        authorizeCodeRepository.save(authorizationCode);

        redirectAttributes.addAttribute("code", code);

        return "redirect:" + redirectUri;
    }
}
