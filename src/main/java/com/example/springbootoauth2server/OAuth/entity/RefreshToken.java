package com.example.springbootoauth2server.OAuth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * CREATE TABLE oauth_refresh_tokens (
 *   token             VARCHAR(400)    NOT NULL PRIMARY KEY,
 *   client_id         VARCHAR(32)     NOT NULL,
 *   user_id           BIGINT          NOT NULL,
 *   access_token      VARCHAR(400),
 *   expired_at        TIMESTAMP       NOT NULL,
 *   created_at        TIMESTAMP       NOT NULL,
 * );
 */
@Entity
public class RefreshToken implements com.byeongukchoi.oauth2.server.entity.RefreshToken {
    @Id
    private String token;
    private String clientId;
    @Column(name = "user_id")
    private long memberId;
    private String accessToken;
    private int expiredAt;
    private int createdAt;

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public Boolean isExpired() {
        return null;
    }
}
