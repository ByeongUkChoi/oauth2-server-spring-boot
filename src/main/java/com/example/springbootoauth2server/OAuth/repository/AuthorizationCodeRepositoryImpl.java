package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.AuthorizationCode;
import com.byeongukchoi.oauth2.server.repository.AuthorizationCodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorizationCodeRepositoryImpl implements AuthorizationCodeRepository {

    @Override
    public AuthorizationCode getNewCode() {
        return null;
    }

    @Override
    public AuthorizationCode findByCodeAndClientId(String code, String clientId) {
        return null;
    }

    @Override
    public void save(AuthorizationCode authorizationCode) {

    }
}
