package com.example.springbootoauth2server.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {

    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("member/login");
    }
}
