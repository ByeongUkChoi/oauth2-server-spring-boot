package com.example.springbootoauth2server.member.controller;

import com.example.springbootoauth2server.member.entity.Member;
import com.example.springbootoauth2server.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 로그인 컨트롤러
 * oauth 서버와 다른 타 서버인 것처럼 하려고 함
 */
@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private MemberService memberService;

    /**
     * 로그인 페이지
     * @param continueUrl
     */
    @GetMapping()
    public String loginForm(@RequestParam(value = "continue", required = false) String continueUrl,
                            Model model) {
        //  로그인 페이지 반환
        model.addAttribute("continue", continueUrl);
        return "member/login";
    }
    /**
     * 로그인
     */
    @PostMapping("/authenticate")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "continue", required = false) String continueUrl,
                        HttpServletRequest request) throws Exception {

        // 로그인
        Member member = memberService.login(username, password);
        // 세션에 로그인 정보 저장
        HttpSession session = request.getSession();
        session.setAttribute("member", member);

        if(continueUrl != null) {
            return "redirect:" + continueUrl;
        }
        return "/";
    }
}
