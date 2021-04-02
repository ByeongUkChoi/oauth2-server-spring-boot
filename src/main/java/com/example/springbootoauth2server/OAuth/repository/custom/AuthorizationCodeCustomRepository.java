package com.example.springbootoauth2server.OAuth.repository.custom;

import com.byeongukchoi.oauth2.server.domain.AuthorizationCode;
import com.byeongukchoi.oauth2.server.domain.repository.AuthorizationCodeRepository;

public interface AuthorizationCodeCustomRepository<T extends AuthorizationCode, ID> extends AuthorizationCodeRepository<T, ID> {
}
