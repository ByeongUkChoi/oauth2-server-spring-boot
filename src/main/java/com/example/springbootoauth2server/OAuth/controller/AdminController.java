package com.example.springbootoauth2server.OAuth.controller;

import com.example.springbootoauth2server.OAuth.entity.AccessTokenImpl;
import com.example.springbootoauth2server.OAuth.entity.AuthorizationCodeImpl;
import com.example.springbootoauth2server.OAuth.entity.ClientImpl;
import com.example.springbootoauth2server.OAuth.entity.RefreshTokenImpl;
import com.example.springbootoauth2server.OAuth.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 관리자 메인 페이지
     * @return
     */
    @GetMapping("")
    public ModelAndView admin(ModelAndView mav) {
        // TODO: 관리자 여부 확인

        mav.setViewName("oauth/admin/admin");

        mav.addObject("test", "testabcd");

        return mav;
    }

    @GetMapping("/clients")
    public Page<ClientImpl> getClients(final Pageable pageable) {

        Page<ClientImpl> clients = adminService.getClients(pageable);

        return clients;
    }

    @GetMapping("/authorization-codes")
    public Page<AuthorizationCodeImpl> getAuthorizationCodes(final Pageable pageable) {

        Page<AuthorizationCodeImpl> authorizationCodes = adminService.getAuthorizationCodes(pageable);

        return authorizationCodes;
    }

    @GetMapping("/access-tokens")
    public Page<AccessTokenImpl> getAccessTokens() {

        return null;
    }

    @GetMapping("/refresh-tokens")
    public Page<RefreshTokenImpl> getRefreshTokens() {

        return null;
    }
}
