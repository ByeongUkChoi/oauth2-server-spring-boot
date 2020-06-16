package com.example.authorizationserver.Authenticatioin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 로그인 컨트롤러
 * oauth 서버와 다른 타 서버여도 됨
 */
@Controller
public class AuthenticationController {
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
                        RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addAttribute("code", "code123");

        String decodedContinueUrl = URLDecoder.decode(continueUrl, StandardCharsets.UTF_8.toString());

        UriComponents uriComponents = UriComponentsBuilder.fromUriString(decodedContinueUrl).build();
        MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();
        String redirectUrl = queryParams.getFirst("redirect_uri");

        return "redirect:" + redirectUrl;
    }
}
