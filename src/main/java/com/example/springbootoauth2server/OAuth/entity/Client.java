package com.example.springbootoauth2server.OAuth.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * CREATE TABLE oauth_clients (
 *   client_id         VARCHAR(86)     NOT NULL PRIMARY KEY,
 *   client_secret     VARCHAR(32),
 *   redirect_uri      VARCHAR(500),
 *   grant_type        VARCHAR(20),
 * );
 */

@Entity
public class Client implements com.byeongukchoi.oauth2.server.entity.Client {
    @Id
    private String clientId;
    private String clientSecret;
    private String redirectUri;     // 여기 있어야하는지 잘 모르겠음. => 카카오의 경우 redirectUri 값을 확인함. 있어야함
    private String grantType;

    @Override
    public void verifyClient(String redirectUri, String clientSecret) {

    }
}
