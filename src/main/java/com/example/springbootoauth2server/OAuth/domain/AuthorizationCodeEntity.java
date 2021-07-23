package com.example.springbootoauth2server.OAuth.domain;

import com.byeongukchoi.oauth2.server.domain.AuthorizationCode;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * CREATE TABLE oauth_authorization_codes (
 *   code              VARCHAR(86)      NOT NULL PRIMARY KEY,
 *   client_id         VARCHAR(32)      NOT NULL,
 *   username          VARCHAR(40)      NOT NULL,
 *   redirect_uri      VARCHAR(500),
 *   expired_at        TIMESTAMP,      NOT NULL,
 *   created_at        TIMESTAMP,      NOT NULL,
 * );
 */

@Entity(name = "oauth_authorization_codes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // @Entity를 위해 필요함. private여도 insert는 작동하나 public/protected로 하라고 나옴
public class AuthorizationCodeEntity implements AuthorizationCode {
    @Id
    @Column(length = 86)
    private String code;
    private String clientId;
    private String username;
    private String redirectUri;
    private int expiredAt;
    private int createdAt;

    @Builder
    public AuthorizationCodeEntity(@NonNull String code,
                                   @NonNull String clientId,
                                   @NonNull String username,
                                   @NonNull String redirectUri,
                                   @NonNull int expiredAt,
                                   @NonNull int createdAt) {
        this.code = code;
        this.clientId = clientId;
        this.username = username;
        this.redirectUri = redirectUri;
        this.expiredAt = expiredAt;
        this.createdAt = createdAt;
    }

    @Override
    public boolean isExpired() {
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
        return currentTimestamp > expiredAt;
    }

    public void expire() {
        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
        expiredAt = currentTimestamp - 1;
    }
}
