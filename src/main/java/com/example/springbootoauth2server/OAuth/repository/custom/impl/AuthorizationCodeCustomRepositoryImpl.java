package com.example.springbootoauth2server.OAuth.repository.custom.impl;

import com.byeongukchoi.oauth2.server.entity.AuthorizationCode;
import com.example.springbootoauth2server.OAuth.entity.AuthorizationCodeEntity;
import com.example.springbootoauth2server.OAuth.repository.custom.AuthorizationCodeCustomRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class AuthorizationCodeCustomRepositoryImpl implements AuthorizationCodeCustomRepository<AuthorizationCode, String> {

    //application.properties 사용
    @Value("${authorizationServer.authorizationCode.expiresIn}")
    private int authorizationCodeExpiresIn;

    @Autowired
    private EntityManager entityManager;

    @Override
    public AuthorizationCode getNewCode(String clientId, String username, String redirectUri) {

        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);

        // 코드 랜덤으로 생성
        String code = RandomStringUtils.randomAlphanumeric(86);

        AuthorizationCode authorizationCode = AuthorizationCodeEntity.builder()
                .clientId(clientId)
                .username(username)
                .redirectUri(redirectUri)
                .expiredAt(currentTimestamp + authorizationCodeExpiresIn)
                .createdAt(currentTimestamp)
                .code(code).build();
        return authorizationCode;
    }

    @Override
    public AuthorizationCode findByCodeAndClientId(String code, String clientId) {
        AuthorizationCode authorizationCode = entityManager.find(AuthorizationCodeEntity.class, code);
        // TODO: clientId 맞는지 확인 필요
        return authorizationCode;
    }

    @Override
    @Transactional
    public AuthorizationCode save(AuthorizationCode authorizationCode) {
        entityManager.persist((AuthorizationCodeEntity)authorizationCode);
        return authorizationCode;
    }

    @Override
    @Transactional
    public void expireCode(AuthorizationCode authorizationCode) {
        AuthorizationCodeEntity authorizationCodeEntity = (AuthorizationCodeEntity) authorizationCode;
        authorizationCodeEntity.expire();
        entityManager.persist(authorizationCodeEntity);
    }
}
