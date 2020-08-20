package com.example.springbootoauth2server.member.controller;

import com.example.springbootoauth2server.member.dto.MemberDto;
import com.example.springbootoauth2server.member.service.MemberUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUserDetailsService memberUserDetailsService;

    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }
    /**
     * 회원가입 페이지
     * @return
     */
    @GetMapping("/join")
    public String joinForm() {
        return "member/joinForm";
    }
    /**
     * 회원가입
     * @return
     */
    @PostMapping("/join")
    public String join(@ModelAttribute MemberDto memberDto) {

        // TODO: 성공 및 실패 에러 처리 필요
        memberUserDetailsService.signUp(memberDto);

        return "member/joinResult";
    }
}
