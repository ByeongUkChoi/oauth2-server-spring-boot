package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.RefreshToken;
import com.byeongukchoi.oauth2.server.repository.RefreshTokenRepository;
import com.example.springbootoauth2server.OAuth.entity.RefreshTokenImpl;
import com.example.springbootoauth2server.OAuth.repository.jpaRepository.RefreshTokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    @Autowired
    private RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public RefreshToken getNewToken() {
        RefreshToken refreshToken = RefreshTokenImpl.builder()
                .token("test_refresh")
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
}
