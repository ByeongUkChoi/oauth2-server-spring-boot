package com.example.springbootoauth2server.admin.service;

import com.example.springbootoauth2server.OAuth.domain.AccessTokenEntity;
import com.example.springbootoauth2server.OAuth.domain.AuthorizationCodeEntity;
import com.example.springbootoauth2server.OAuth.domain.ClientEntity;
import com.example.springbootoauth2server.OAuth.domain.RefreshTokenEntity;
import com.example.springbootoauth2server.OAuth.dto.AccessTokenDto;
import com.example.springbootoauth2server.OAuth.dto.AuthorizationCodeDto;
import com.example.springbootoauth2server.OAuth.dto.ClientDto;
import com.example.springbootoauth2server.OAuth.dto.RefreshTokenDto;
import com.example.springbootoauth2server.OAuth.repository.AccessTokenRepository;
import com.example.springbootoauth2server.OAuth.repository.AuthorizationCodeRepository;
import com.example.springbootoauth2server.OAuth.repository.ClientRepository;
import com.example.springbootoauth2server.OAuth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public Page<ClientDto> getClientDtoPages(Pageable pageable) {
        Page<ClientEntity> clients = clientRepository.findAll(pageable);
        return clients.map(client -> modelMapper.map(client, ClientDto.class));
    }

    public Page<AuthorizationCodeDto> getAuthorizationCodeDtoPages(Pageable pageable) {
        Page<AuthorizationCodeEntity> authorizationCodeEntities = authorizationCodeRepository.findAll(pageable);
        return authorizationCodeEntities.map(authorizationCode -> modelMapper.map(authorizationCode, AuthorizationCodeDto.class));
    }

    public Page<AccessTokenDto> getAccessTokenDtoPages(Pageable pageable) {
        Page<AccessTokenEntity> accessTokens = accessTokenRepository.findAll(pageable);
        return accessTokens.map(accessToken -> modelMapper.map(accessToken, AccessTokenDto.class));
    }

    public Page<RefreshTokenDto> getRefreshTokenDtoPages(Pageable pageable) {
        Page<RefreshTokenEntity> refreshTokens = refreshTokenRepository.findAll(pageable);
        return refreshTokens.map(refreshToken -> modelMapper.map(refreshToken, RefreshTokenDto.class));
    }
}
