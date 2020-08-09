package com.example.springbootoauth2server.OAuth.repository.jpaRepository;

import com.byeongukchoi.oauth2.server.entity.RefreshToken;
import com.example.springbootoauth2server.OAuth.entity.RefreshTokenImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenImpl, String> {
    public RefreshToken findByTokenAndClientId(String refreshToken, String clientId);
}
