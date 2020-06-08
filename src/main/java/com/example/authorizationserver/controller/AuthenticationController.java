package com.example.authorizationserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String loginForm(@RequestParam(value = "continue", required = false) String continueUrl) {
        // TODO: 로그인 페이지 반환
        return "login";
    }

    /**
     * 로그인
     */
    @PostMapping("/authenticate")
    public void login() {
    }
}
