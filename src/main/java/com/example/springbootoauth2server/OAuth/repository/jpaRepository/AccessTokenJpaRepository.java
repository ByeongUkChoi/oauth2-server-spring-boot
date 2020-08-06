package com.example.springbootoauth2server.OAuth.repository.jpaRepository;

import com.example.springbootoauth2server.OAuth.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenJpaRepository extends JpaRepository<AccessToken, String> {
}
