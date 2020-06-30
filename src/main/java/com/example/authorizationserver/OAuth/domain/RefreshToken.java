package com.example.authorizationserver.OAuth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * CREATE TABLE oauth_refresh_tokens (
 *   refresh_token       VARCHAR(40)     NOT NULL,
 *   client_id           VARCHAR(80)     NOT NULL,
 *   member_id           BIGINT,
 *   expires             TIMESTAMP       NOT NULL,
 *   scope               VARCHAR(4000), // 사용하지 않음
 *   PRIMARY KEY (refresh_token)
 * );
 */
@Entity
public class RefreshToken {
    @Id
    private String refreshToken;
    private String clientId;
    private long memberId;
    private int expires;
}
