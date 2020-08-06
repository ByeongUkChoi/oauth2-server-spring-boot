package com.example.springbootoauth2server.OAuth.controller;

import com.example.springbootoauth2server.OAuth.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String admin(Model model) {
        // 관리자 여부 확인

        model.addAttribute("test", "testabcd");
        // 관리자 페이지 반환
        return "oauth/admin/admin";
    }
}
