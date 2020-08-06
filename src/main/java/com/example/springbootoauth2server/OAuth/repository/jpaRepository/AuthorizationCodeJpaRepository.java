package com.example.springbootoauth2server.OAuth.repository.jpaRepository;

import com.example.springbootoauth2server.OAuth.entity.AuthorizationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationCodeJpaRepository extends JpaRepository<AuthorizationCode, String> {
    AuthorizationCode findByCodeAndClientId(String code, String clientId);
}
