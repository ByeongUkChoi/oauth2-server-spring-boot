package com.example.oauth2server.grant.impl;

import com.example.oauth2server.dto.TokenDto;
import com.example.oauth2server.grant.AbstractGrant;

public class RefreshTokenGrantable extends AbstractGrant {
    @Override
    public TokenDto issueToken() {
        return null;
    }
}
