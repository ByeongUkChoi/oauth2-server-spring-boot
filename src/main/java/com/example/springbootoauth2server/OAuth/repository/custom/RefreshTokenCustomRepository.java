package com.example.springbootoauth2server.OAuth.repository.custom;

import com.byeongukchoi.oauth2.server.entity.RefreshToken;
import com.byeongukchoi.oauth2.server.repository.RefreshTokenRepository;

public interface RefreshTokenCustomRepository<T extends RefreshToken, ID> extends RefreshTokenRepository<T, ID> {
}
