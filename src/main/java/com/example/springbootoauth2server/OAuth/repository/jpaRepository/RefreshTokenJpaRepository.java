package com.example.springbootoauth2server.OAuth.repository.jpaRepository;

import com.example.springbootoauth2server.OAuth.entity.RefreshTokenImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenImpl, String> {
}
