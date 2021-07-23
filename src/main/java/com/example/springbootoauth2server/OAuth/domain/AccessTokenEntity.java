package com.example.springbootoauth2server.OAuth.domain;

import com.byeongukchoi.oauth2.server.domain.AccessToken;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * CREATE TABLE oauth_access_tokens (
 *   token             VARCHAR(400)    NOT NULL PRIMARY KEY,
 *   client_id         VARCHAR(32)     NOT NULL,
 *   username          VARCHAR(40)     NOT NULL,
 *   expired_at        TIMESTAMP       NOT NULL,
 *   created_at        TIMESTAMP       NOT NULL,
 * );
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // @Entity를 위해 필요함. private여도 insert는 작동하나 public/protected로 하라고 나옴
@Entity(name = "oauth_access_tokens")
public class AccessTokenEntity implements AccessToken {
    @Id
    @Column(length = 54)
    private String token;
    private String clientId;
    private String username;
    private int expiredAt;
    private int createdAt;

    @Builder
    public AccessTokenEntity(@NonNull String token,
                             @NonNull String clientId,
                             @NonNull String username,
                             @NonNull int expiredAt,
                             @NonNull int createdAt) {
        this.token = token;
        this.clientId = clientId;
        this.username = username;
        this.expiredAt = expiredAt;
        this.createdAt = createdAt;
    }

    @Override
    public boolean isExpired() {
        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
        return currentTimestamp > expiredAt;
    }

    @Override
    public int getExpiresIn() {
        return expiredAt - createdAt;
    }
}
