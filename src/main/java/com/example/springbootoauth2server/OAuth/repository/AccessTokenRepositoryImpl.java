package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.AccessToken;
import com.byeongukchoi.oauth2.server.repository.AccessTokenRepository;
import com.example.springbootoauth2server.OAuth.entity.AccessTokenImpl;
import com.example.springbootoauth2server.OAuth.repository.jpaRepository.AccessTokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccessTokenRepositoryImpl implements AccessTokenRepository {

    @Autowired
    private AccessTokenJpaRepository accessTokenJpaRepository;

    @Override
    public AccessToken getNewToken() {
        AccessToken accessToken = AccessTokenImpl.builder()
                .token("testToken")
                .build();
        return accessToken;
    }

    @Override
    public void saveNewToken(AccessToken accessToken) {

    }
}