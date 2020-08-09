package com.example.springbootoauth2server.OAuth.entity;

import com.byeongukchoi.oauth2.server.entity.AuthorizationCode;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE oauth_authorization_codes (
 *   code              VARCHAR(86)     NOT NULL PRIMARY KEY,
 *   client_id         VARCHAR(32)     NOT NULL,
 *   username          VARCHAR(40)     NOT NULL,
 *   redirect_uri      VARCHAR(500),
 *   expired_at        TIMESTAMP       NOT NULL,
 *   created_at        TIMESTAMP       NOT NULL,
 * );
 */

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // @Entity를 위해 필요함. private여도 insert는 작동하나 public/protected로 하라고 나옴
@AllArgsConstructor(access = AccessLevel.PRIVATE)   // @Builder를 위해 필요함
@Entity
@Table(name = "oauth_authorization_codes")
public class AuthorizationCodeImpl implements AuthorizationCode {
    @Id
    private String code;
    private String clientId;
    private String username;
    private String redirectUri;
    private int expiredAt;
    private int createdAt;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Boolean isExpired() {
        // TODO: 구현해야함
        return false;
    }

    public void expire() {
        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
        expiredAt = currentTimestamp - 1;
    }
}
