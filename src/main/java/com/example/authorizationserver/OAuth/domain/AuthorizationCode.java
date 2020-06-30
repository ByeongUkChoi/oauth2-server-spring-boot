package com.example.authorizationserver.OAuth.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * CREATE TABLE oauth_authorization_codes (
 *   authorization_code  VARCHAR(40)     NOT NULL,
 *   client_id           VARCHAR(80)     NOT NULL,
 *   member_id           BIGINT,
 *   redirect_uri        VARCHAR(2000),
 *   expires             TIMESTAMP       NOT NULL,
 *   scope               VARCHAR(4000), // 사용하지 않음
 *   id_token            VARCHAR(1000), // 사용하지 않음
 *   PRIMARY KEY (authorization_code)
 * );
 */

@Builder
@Entity
public class AuthorizationCode {
    @Id
    private String authorizationCode;
    private String clientId;
    private long memberId;
    private String redirectUri;
    private int expires;
}
