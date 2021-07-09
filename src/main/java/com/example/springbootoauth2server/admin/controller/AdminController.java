package com.example.springbootoauth2server.admin.controller;

import com.example.springbootoauth2server.OAuth.dto.AccessTokenDto;
import com.example.springbootoauth2server.OAuth.dto.AuthorizationCodeDto;
import com.example.springbootoauth2server.OAuth.dto.ClientDto;
import com.example.springbootoauth2server.OAuth.dto.RefreshTokenDto;
import com.example.springbootoauth2server.OAuth.domain.AccessTokenEntity;
import com.example.springbootoauth2server.OAuth.domain.AuthorizationCodeEntity;
import com.example.springbootoauth2server.OAuth.domain.ClientEntity;
import com.example.springbootoauth2server.OAuth.domain.RefreshTokenEntity;
import com.example.springbootoauth2server.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ModelMapper modelMapper;

    private final int DASHBOARD_PAGE_SIZE = 5;

    /**
     * 관리자 로그인 페이지
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login() {

        //mav.setViewName("oauth/admin/admin");
        return new ModelAndView("admin/login");
    }
    /**
     * 관리자 메인 페이지
     * @return
     */
    @GetMapping("/dashboard")
    public ModelAndView admin(ModelAndView mav) {
        // 첫 페이지 정보
        Pageable pageable = PageRequest.of(0, DASHBOARD_PAGE_SIZE);

        Page<ClientEntity> clients = adminService.getClients(pageable);
        Page<ClientDto> clientDtoPage = clients.map(client -> modelMapper.map(client, ClientDto.class));

        Page<AuthorizationCodeEntity> authorizationCodes = adminService.getAuthorizationCodes(pageable);
        Page<AuthorizationCodeDto> authorizationCodeDtoPage = authorizationCodes.map(authorizationCode -> modelMapper.map(authorizationCode, AuthorizationCodeDto.class));

        Page<AccessTokenEntity> accessTokens = adminService.getAccessTokens(pageable);
        Page<AccessTokenDto> accessTokenDtoPage = accessTokens.map(accessToken -> modelMapper.map(accessToken, AccessTokenDto.class));

        Page<RefreshTokenEntity> refreshTokens = adminService.getRefreshTokens(pageable);
        Page<RefreshTokenDto> refreshTokenDtoPage = refreshTokens.map(refreshToken -> modelMapper.map(refreshToken, RefreshTokenDto.class));

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

        Page<ClientEntity> clients = adminService.getClients(pageable);
        Page<ClientDto> clientDtoPage = clients.map(client -> modelMapper.map(client, ClientDto.class));

        return clientDtoPage;
    }

    @GetMapping("/authorization-codes")
    public Page<AuthorizationCodeDto> getAuthorizationCodes(final Pageable pageable) {

        Page<AuthorizationCodeEntity> authorizationCodes = adminService.getAuthorizationCodes(pageable);
        Page<AuthorizationCodeDto> authorizationCodeDtoPage = authorizationCodes.map(authorizationCode -> modelMapper.map(authorizationCode, AuthorizationCodeDto.class));

        return authorizationCodeDtoPage;
    }

    @GetMapping("/access-tokens")
    public Page<AccessTokenEntity> getAccessTokens() {

        return null;
    }

    @GetMapping("/refresh-tokens")
    public Page<RefreshTokenEntity> getRefreshTokens() {

        return null;
    }
}
