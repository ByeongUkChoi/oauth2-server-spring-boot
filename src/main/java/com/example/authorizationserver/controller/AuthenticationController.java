package com.example.authorizationserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public String login(@RequestParam("id") String id,
                        @RequestParam("password") String password,
                        @RequestParam("continue") String continueUrl,
                        RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("code", "code123");
        //TODO: redirect continueUrl
        return "redirect:https://naver.com";
    }
}
