package com.example.springbootoauth2server.OAuth.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * CREATE TABLE oauth_refresh_tokens (
 *   token             VARCHAR(400)    NOT NULL PRIMARY KEY,
 *   client_id         VARCHAR(32)     NOT NULL,
 *   username          VARCHAR(40)     NOT NULL,
 *   access_token      VARCHAR(400),
 *   expired_at        TIMESTAMP       NOT NULL,
 *   created_at        TIMESTAMP       NOT NULL,
 * );
 */
@Entity
public class RefreshToken {
    @Id
    private String token;
    private String clientId;
    private String username;
    private String accessToken;
    private int expiredAt;
    private int createdAt;
}
