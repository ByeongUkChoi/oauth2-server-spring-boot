package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.AccessToken;
import com.byeongukchoi.oauth2.server.repository.AccessTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccessTokenRepositoryImpl implements AccessTokenRepository {
    @Override
    public AccessToken getNewToken() {
        return null;
    }

    @Override
    public void saveNewToken(AccessToken accessToken) {

    }
}
