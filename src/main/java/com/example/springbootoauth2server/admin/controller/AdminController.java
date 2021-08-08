package com.example.springbootoauth2server.admin.controller;

import com.example.springbootoauth2server.OAuth.domain.AccessTokenEntity;
import com.example.springbootoauth2server.OAuth.domain.RefreshTokenEntity;
import com.example.springbootoauth2server.OAuth.dto.AccessTokenDto;
import com.example.springbootoauth2server.OAuth.dto.AuthorizationCodeDto;
import com.example.springbootoauth2server.OAuth.dto.ClientDto;
import com.example.springbootoauth2server.OAuth.dto.RefreshTokenDto;
import com.example.springbootoauth2server.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final int DASHBOARD_PAGE_SIZE = 5;

    /**
     * 관리자 로그인 페이지
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("admin/login");
        return mav;
    }
    /**
     * 관리자 메인 페이지
     * @return
     */
    @GetMapping("/dashboard")
    public ModelAndView admin(ModelAndView mav) {
        // 첫 페이지 정보
        Pageable pageable = PageRequest.of(0, DASHBOARD_PAGE_SIZE);

        Page<ClientDto> clientDtoPage = adminService.getClientDtoPages(pageable);
        Page<AuthorizationCodeDto> authorizationCodeDtoPage = adminService.getAuthorizationCodeDtoPages(pageable);
        Page<AccessTokenDto> accessTokenDtoPage = adminService.getAccessTokenDtoPages(pageable);
        Page<RefreshTokenDto> refreshTokenDtoPage = adminService.getRefreshTokenDtoPages(pageable);

        mav.addObject("clients", clientDtoPage);
        mav.addObject("authorizationCodes", authorizationCodeDtoPage);
        mav.addObject("accessTokens", accessTokenDtoPage);
        mav.addObject("refreshTokens", refreshTokenDtoPage);

        mav.addObject("dashboardPageSize", DASHBOARD_PAGE_SIZE);

        mav.setViewName("admin/dashboard");
        return mav;
    }

    @GetMapping("/clients")
    public Page<ClientDto> getClients(final Pageable pageable) {
        return adminService.getClientDtoPages(pageable);
    }

    @GetMapping("/authorization-codes")
    public Page<AuthorizationCodeDto> getAuthorizationCodes(final Pageable pageable) {
        return adminService.getAuthorizationCodeDtoPages(pageable);
    }

    @GetMapping("/access-tokens")
    public Page<AccessTokenEntity> getAccessTokens() {
        return new PageImpl<>(new ArrayList<>());
    }

    @GetMapping("/refresh-tokens")
    public Page<RefreshTokenEntity> getRefreshTokens() {
        return new PageImpl<>(new ArrayList<>());
    }
}
