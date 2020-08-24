package com.example.springbootoauth2server.member.controller;

import com.example.springbootoauth2server.member.dto.ApplicationDto;
import com.example.springbootoauth2server.member.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 클라이언트 등록 및 설정 컨트롤러
 */
@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    /**
     * 대시보드
     * @return
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "app/dashbord";
    }
    /**
     * 앱 등록 페이지
     * @return
     */
    @GetMapping("/regist")
    public String registForm() {
        return "member/app/registForm";
    }
    /**
     * 앱 등록
     * @return
     */
    @PostMapping("/regist")
    public String regist(@ModelAttribute ApplicationDto applicationDto) {
        applicationService.createApplication(applicationDto);
        return "member/app/registResult";
    }
}
