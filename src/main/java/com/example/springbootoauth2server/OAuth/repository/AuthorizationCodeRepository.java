package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.AuthorizationCode;
import com.example.springbootoauth2server.OAuth.entity.AuthorizationCodeImpl;
import com.example.springbootoauth2server.OAuth.repository.custom.AuthorizationCodeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationCodeRepository extends JpaRepository<AuthorizationCodeImpl, String>, AuthorizationCodeCustomRepository<AuthorizationCode, String> {
}
