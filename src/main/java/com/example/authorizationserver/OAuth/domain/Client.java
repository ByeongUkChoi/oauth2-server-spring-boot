package com.example.authorizationserver.OAuth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * CREATE TABLE oauth_clients (
 *   client_id             VARCHAR(80)   NOT NULL,
 *   client_secret         VARCHAR(80),
 *   redirect_uri          VARCHAR(2000),
 *   grant_types           VARCHAR(80),
 *   scope                 VARCHAR(4000),   // 사용하지 않음
 *   member_id             BIGINT,
 *   PRIMARY KEY (client_id)
 * );
 */

@Entity
public class Client {
    @Id
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String grantType;
    private long memberId;
}
