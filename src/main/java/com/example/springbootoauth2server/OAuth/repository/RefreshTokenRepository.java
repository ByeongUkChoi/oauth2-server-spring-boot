package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.domain.RefreshToken;
import com.example.springbootoauth2server.OAuth.entity.RefreshTokenEntity;
import com.example.springbootoauth2server.OAuth.repository.custom.RefreshTokenCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String>, RefreshTokenCustomRepository<RefreshToken, String> {
}
