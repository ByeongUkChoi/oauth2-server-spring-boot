package com.example.authorizationserver.OAuth.dao;

import com.example.authorizationserver.OAuth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String>{
}
