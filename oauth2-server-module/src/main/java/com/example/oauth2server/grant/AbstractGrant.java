package com.example.oauth2server.grant;

import com.example.oauth2server.dto.TokenDto;
import com.example.oauth2server.repository.ClientRepository;
import com.example.oauth2server.repository.RefreshTokenRepository;

public abstract class AbstractGrant implements Grantable {
    private ClientRepository clientRepository;
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public TokenDto issueToken() {
        return null;
    }
}
