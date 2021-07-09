package com.example.springbootoauth2server.OAuth.repository.custom.impl;

import com.byeongukchoi.oauth2.server.application.dto.AuthorizationRequestDto;
import com.byeongukchoi.oauth2.server.domain.AccessToken;
import com.example.springbootoauth2server.OAuth.domain.AccessTokenEntity;
import com.example.springbootoauth2server.OAuth.repository.custom.AccessTokenCustomRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class AccessTokenCustomRepositoryImpl implements AccessTokenCustomRepository<AccessToken, String> {
    //application.properties 사용
    @Value("${authorizationServer.accessToken.expiresIn}")
    private int accessTokenExpiresIn;

    @Autowired
    private EntityManager entityManager;

    @Override
    public AccessToken getNewToken(AuthorizationRequestDto authorizationRequestDto) {
        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);

        // 토큰 랜덤으로 생성
        String token = RandomStringUtils.randomAlphanumeric(54);

        AccessToken accessToken = AccessTokenEntity.builder()
                .token(token)
                .clientId(authorizationRequestDto.getClientId())
                .username(authorizationRequestDto.getUsername())
                .expiredAt(currentTimestamp + accessTokenExpiresIn)
                .createdAt(currentTimestamp)
                .build();
        return accessToken;
    }

    @Override
    @Transactional
    public void saveNewToken(AccessToken accessToken) {
        entityManager.persist(accessToken);
    }
}
