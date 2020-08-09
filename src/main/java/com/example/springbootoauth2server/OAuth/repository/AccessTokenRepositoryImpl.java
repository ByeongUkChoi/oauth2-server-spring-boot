package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.dto.AuthorizationRequestDto;
import com.byeongukchoi.oauth2.server.entity.AccessToken;
import com.byeongukchoi.oauth2.server.repository.AccessTokenRepository;
import com.example.springbootoauth2server.OAuth.entity.AccessTokenImpl;
import com.example.springbootoauth2server.OAuth.repository.jpaRepository.AccessTokenJpaRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class AccessTokenRepositoryImpl implements AccessTokenRepository {

    //application.properties 사용
    @Value("${authorizationServer.accessToken.expiresIn}")
    private int accessTokenExpiresIn;

    @Autowired
    private AccessTokenJpaRepository accessTokenJpaRepository;

    @Override
    public AccessToken getNewToken(AuthorizationRequestDto authorizationRequestDto) {
        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);

        // 토큰 랜덤으로 생성
        String token = RandomStringUtils.randomAlphanumeric(86);

        AccessToken accessToken = AccessTokenImpl.builder()
                .token(token)
                .clientId(authorizationRequestDto.getClientId())
                .username(authorizationRequestDto.getUsername())
                .expiredAt(currentTimestamp + accessTokenExpiresIn)
                .createdAt(currentTimestamp)
                .build();
        return accessToken;
    }

    @Override
    public void saveNewToken(AccessToken accessToken) {
        accessTokenJpaRepository.save((AccessTokenImpl) accessToken);
    }
}
