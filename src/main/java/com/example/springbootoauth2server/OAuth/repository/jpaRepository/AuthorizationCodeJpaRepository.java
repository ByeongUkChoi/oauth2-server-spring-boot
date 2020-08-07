package com.example.springbootoauth2server.OAuth.repository.jpaRepository;

import com.example.springbootoauth2server.OAuth.entity.AuthorizationCodeImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationCodeJpaRepository extends JpaRepository<AuthorizationCodeImpl, String> {
    AuthorizationCodeImpl findByCodeAndClientId(String code, String clientId);
}
