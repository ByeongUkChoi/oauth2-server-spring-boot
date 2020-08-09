package com.example.springbootoauth2server.OAuth.repository.custom;

import com.byeongukchoi.oauth2.server.entity.AccessToken;
import com.byeongukchoi.oauth2.server.repository.AccessTokenRepository;

public interface AccessTokenCustomRepository<T extends AccessToken, ID> extends AccessTokenRepository<T, ID> {
}
