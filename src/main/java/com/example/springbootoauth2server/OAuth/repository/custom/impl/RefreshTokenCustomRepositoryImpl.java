package com.example.springbootoauth2server.OAuth.repository.custom.impl;

import com.byeongukchoi.oauth2.server.dto.AuthorizationRequestDto;
import com.byeongukchoi.oauth2.server.entity.RefreshToken;
import com.example.springbootoauth2server.OAuth.entity.RefreshTokenEntity;
import com.example.springbootoauth2server.OAuth.repository.custom.RefreshTokenCustomRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class RefreshTokenCustomRepositoryImpl implements RefreshTokenCustomRepository<RefreshToken, String> {

    //application.properties 사용
    @Value("${authorizationServer.refreshToken.expiresIn}")
    private int refreshTokenExpiresIn;

    @Autowired
    private EntityManager entityManager;

    @Override
    public RefreshToken getNewToken(AuthorizationRequestDto authorizationRequestDto, String accessToken) {

        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);

        // 토큰 랜덤으로 생성
        String token = RandomStringUtils.randomAlphanumeric(86);

        RefreshToken refreshToken = RefreshTokenEntity.builder()
                .token(token)
                .clientId(authorizationRequestDto.getClientId())
                .username(authorizationRequestDto.getUsername())
                .accessToken(accessToken)
                .expiredAt(currentTimestamp + refreshTokenExpiresIn)
                .createdAt(currentTimestamp)
                .build();
        return refreshToken;
    }

    @Override
    @Transactional
    public void saveNewToken(RefreshToken refreshToken) {
        entityManager.persist((RefreshTokenEntity) refreshToken);
    }

    @Override
    public RefreshToken findByTokenAndClientId(String refreshToken, String clientId) {
        //RefreshToken refreshTokenEntity = entityManager.findByTokenAndClientId(refreshToken, clientId);
        RefreshToken refreshTokenEntity = entityManager.find(RefreshTokenEntity.class, refreshToken);
        // TODO: code로 검증해야함
        return refreshTokenEntity;
    }

    @Override
    @Transactional
    public void expireToken(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = (RefreshTokenEntity) refreshToken;
        refreshTokenEntity.expire();
        entityManager.persist(refreshTokenEntity);
    }
}
