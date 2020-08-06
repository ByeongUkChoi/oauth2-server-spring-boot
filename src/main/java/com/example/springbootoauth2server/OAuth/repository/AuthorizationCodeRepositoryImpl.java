package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.AuthorizationCode;
import com.byeongukchoi.oauth2.server.repository.AuthorizationCodeRepository;
import com.example.springbootoauth2server.OAuth.repository.jpaRepository.AuthorizationCodeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorizationCodeRepositoryImpl implements AuthorizationCodeRepository {

    @Autowired
    private AuthorizationCodeJpaRepository authorizationCodeJpaRepository;

    @Override
    public AuthorizationCode getNewCode() {
        return null;
    }

    @Override
    public AuthorizationCode findByCodeAndClientId(String code, String clientId) {
        AuthorizationCode authorizationCode = authorizationCodeJpaRepository.findByCodeAndClientId(code, clientId);
        return authorizationCode;
    }

    @Override
    public void save(AuthorizationCode authorizationCode) {
        authorizationCodeJpaRepository.save((com.example.springbootoauth2server.OAuth.entity.AuthorizationCode) authorizationCode);
    }
}
