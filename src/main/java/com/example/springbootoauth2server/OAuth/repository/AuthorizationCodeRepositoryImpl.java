package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.AuthorizationCode;
import com.byeongukchoi.oauth2.server.repository.AuthorizationCodeRepository;
import com.example.springbootoauth2server.OAuth.entity.AuthorizationCodeImpl;
import com.example.springbootoauth2server.OAuth.repository.jpaRepository.AuthorizationCodeJpaRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorizationCodeRepositoryImpl implements AuthorizationCodeRepository {

    //application.properties 사용
    @Value("${authorizationServer.authorizationCode.expiresIn}")
    private int authorizationCodeExpiresIn;

    @Autowired
    private AuthorizationCodeJpaRepository authorizationCodeJpaRepository;

    @Override
    public AuthorizationCode getNewCode(String clientId, String username, String redirectUri) {

        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);

        // 코드 랜덤으로 생성
        String code = RandomStringUtils.randomAlphanumeric(86);

        AuthorizationCode authorizationCode = AuthorizationCodeImpl.builder()
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
        AuthorizationCode authorizationCode = authorizationCodeJpaRepository.findByCodeAndClientId(code, clientId);
        return authorizationCode;
    }

    @Override
    public void save(AuthorizationCode authorizationCode) {
        authorizationCodeJpaRepository.save((AuthorizationCodeImpl) authorizationCode);
    }

    @Override
    public void expireCode(AuthorizationCode authorizationCode) {
        AuthorizationCodeImpl authorizationCodeImpl = (AuthorizationCodeImpl) authorizationCode;
        authorizationCodeImpl.expire();
        authorizationCodeJpaRepository.save(authorizationCodeImpl);
    }
}
