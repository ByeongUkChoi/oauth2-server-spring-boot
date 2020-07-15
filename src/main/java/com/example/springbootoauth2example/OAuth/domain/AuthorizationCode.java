package com.example.springbootoauth2example.OAuth.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * CREATE TABLE oauth_authorization_codes (
 *   code  VARCHAR(40)     NOT NULL,
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
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // @Entity를 위해 필요함. private여도 insert는 작동하나 public/protected로 하라고 나옴
@AllArgsConstructor(access = AccessLevel.PRIVATE)   // @Builder를 위해 필요함
@Entity
public class AuthorizationCode {
    @Id
    private String code;
    private String clientId;
    private long memberId;
    private String redirectUri;
    private int expires;
}
