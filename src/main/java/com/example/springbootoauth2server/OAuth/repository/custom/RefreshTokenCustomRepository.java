package com.example.springbootoauth2server.OAuth.repository.custom;

import com.byeongukchoi.oauth2.server.domain.RefreshToken;
import com.byeongukchoi.oauth2.server.domain.repository.RefreshTokenRepository;

public interface RefreshTokenCustomRepository<T extends RefreshToken, ID> extends RefreshTokenRepository<T, ID> {
}
