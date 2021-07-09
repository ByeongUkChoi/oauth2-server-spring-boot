package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.domain.AuthorizationCode;
import com.example.springbootoauth2server.OAuth.domain.AuthorizationCodeEntity;
import com.example.springbootoauth2server.OAuth.repository.custom.AuthorizationCodeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationCodeRepository extends JpaRepository<AuthorizationCodeEntity, String>, AuthorizationCodeCustomRepository<AuthorizationCode, String> {
}
