package com.example.springbootoauth2server.OAuth.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * CREATE TABLE oauth_authorization_codes (
 *   code              VARCHAR(86)     NOT NULL PRIMARY KEY,
 *   client_id         VARCHAR(32)     NOT NULL,
 *   user_id           BIGINT          NOT NULL,
 *   redirect_uri      VARCHAR(500),
 *   expired_at        TIMESTAMP       NOT NULL,
 *   created_at        TIMESTAMP       NOT NULL,
 * );
 */

@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // @Entity를 위해 필요함. private여도 insert는 작동하나 public/protected로 하라고 나옴
@AllArgsConstructor(access = AccessLevel.PRIVATE)   // @Builder를 위해 필요함
@Entity
public class AuthorizationCode implements com.byeongukchoi.oauth2.server.entity.AuthorizationCode {
    @Id
    private String code;
    private String clientId;
    @Column(name = "user_id")
    private long memberId;
    private String redirectUri;
    private int expiredAt;
    private int createdAt;

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public Boolean isExpired() {
        return null;
    }
}
