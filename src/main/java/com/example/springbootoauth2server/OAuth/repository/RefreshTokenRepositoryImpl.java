package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.dto.AuthorizationRequestDto;
import com.byeongukchoi.oauth2.server.entity.RefreshToken;
import com.byeongukchoi.oauth2.server.repository.RefreshTokenRepository;
import com.example.springbootoauth2server.OAuth.entity.RefreshTokenImpl;
import com.example.springbootoauth2server.OAuth.repository.jpaRepository.RefreshTokenJpaRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    //application.properties 사용
    @Value("${authorizationServer.refreshToken.expiresIn}")
    private int refreshTokenExpiresIn;

    @Autowired
    private RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public RefreshToken getNewToken(AuthorizationRequestDto authorizationRequestDto, String accessToken) {

        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);

        // 토큰 랜덤으로 생성
        String token = RandomStringUtils.randomAlphanumeric(86);

        RefreshToken refreshToken = RefreshTokenImpl.builder()
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
    public void saveNewToken(RefreshToken refreshToken) {

    }

    @Override
    public RefreshToken findByTokenAndClientId(String refreshToken, String clientId) {
        return null;
    }

    @Override
    public void expireToken(RefreshToken refreshToken) {
        RefreshTokenImpl refreshTokenImpl = (RefreshTokenImpl) refreshToken;
        refreshTokenImpl.expire();
        refreshTokenJpaRepository.save(refreshTokenImpl);

    }
}
