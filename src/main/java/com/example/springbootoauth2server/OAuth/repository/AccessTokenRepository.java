package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.AccessToken;
import com.example.springbootoauth2server.OAuth.entity.AccessTokenEntity;
import com.example.springbootoauth2server.OAuth.repository.custom.AccessTokenCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessTokenEntity, String>, AccessTokenCustomRepository<AccessToken, String> {
}
