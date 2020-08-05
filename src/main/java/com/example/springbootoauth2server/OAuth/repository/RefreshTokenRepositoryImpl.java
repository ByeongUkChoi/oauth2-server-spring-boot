package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.RefreshToken;
import com.byeongukchoi.oauth2.server.repository.RefreshTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    @Override
    public RefreshToken getNewToken() {
        return null;
    }

    @Override
    public void saveNewToken(RefreshToken refreshToken) {

    }

    @Override
    public RefreshToken findByTokenAndClientId(String refreshToken, String clientId) {
        return null;
    }
}
