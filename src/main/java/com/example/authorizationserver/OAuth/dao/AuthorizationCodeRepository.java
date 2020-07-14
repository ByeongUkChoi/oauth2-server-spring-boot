package com.example.authorizationserver.OAuth.dao;

import com.example.authorizationserver.OAuth.domain.AuthorizationCode;
import com.example.authorizationserver.OAuth.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationCodeRepository extends JpaRepository<AuthorizationCode, String>{
    AuthorizationCode findByCodeAndClientId(String code, String clientId);
}
