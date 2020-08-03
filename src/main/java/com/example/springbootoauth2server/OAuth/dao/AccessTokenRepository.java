package com.example.springbootoauth2server.OAuth.dao;

import com.example.springbootoauth2server.OAuth.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken, String>, com.byeongukchoi.oauth2.server.repository.AccessTokenRepository {
}
