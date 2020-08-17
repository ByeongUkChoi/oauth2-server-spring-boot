package com.example.springbootoauth2server.OAuth.service;

import com.example.springbootoauth2server.OAuth.entity.AccessTokenImpl;
import com.example.springbootoauth2server.OAuth.entity.AuthorizationCodeImpl;
import com.example.springbootoauth2server.OAuth.entity.ClientImpl;
import com.example.springbootoauth2server.OAuth.entity.RefreshTokenImpl;
import com.example.springbootoauth2server.OAuth.repository.AccessTokenRepository;
import com.example.springbootoauth2server.OAuth.repository.AuthorizationCodeRepository;
import com.example.springbootoauth2server.OAuth.repository.ClientRepository;
import com.example.springbootoauth2server.OAuth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ClientRepository clientRepository;
    private final AuthorizationCodeRepository authorizationCodeRepository;
    private final AccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public Page<ClientImpl> getClients(Pageable pageable) {
        Page<ClientImpl> clients = clientRepository.findAll(pageable);
        return clients;
    }

    public Page<AuthorizationCodeImpl> getAuthorizationCodes(Pageable pageable) {
        return authorizationCodeRepository.findAll(pageable);
    }

    public Page<AccessTokenImpl> getAccessTokens(Pageable pageable) {
        return accessTokenRepository.findAll(pageable);
    }

    public Page<RefreshTokenImpl> getRefreshTokens(Pageable pageable) {
        return refreshTokenRepository.findAll(pageable);
    }
}
