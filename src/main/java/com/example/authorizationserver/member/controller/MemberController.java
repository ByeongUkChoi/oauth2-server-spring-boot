package com.example.authorizationserver.member.controller;

import com.example.authorizationserver.member.domain.Member;
import com.example.authorizationserver.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 로그인 컨트롤러
 * oauth 서버와 다른 타 서버인 것처럼 하려고 함
 */
@Controller
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    /**
     * 로그인 페이지
     * @param continueUrl
     */
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "continue", required = false) String continueUrl,
                            Model model) {
        //  로그인 페이지 반환
        model.addAttribute("continue", continueUrl);
        return "login";
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
