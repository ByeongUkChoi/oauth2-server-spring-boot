package com.example.springbootoauth2example.OAuth.dao;

import com.example.springbootoauth2example.OAuth.domain.AuthorizationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationCodeRepository extends JpaRepository<AuthorizationCode, String>{
    AuthorizationCode findByCodeAndClientId(String code, String clientId);
}
