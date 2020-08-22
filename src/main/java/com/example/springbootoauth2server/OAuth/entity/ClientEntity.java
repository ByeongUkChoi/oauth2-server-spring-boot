package com.example.springbootoauth2server.OAuth.entity;

import com.byeongukchoi.oauth2.server.entity.Client;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE oauth_clients (
 *   client_id         VARCHAR(86)      NOT NULL PRIMARY KEY,
 *   client_secret     VARCHAR(32),
 *   name              VARCHAR(64)      NOT NULL,
 *   redirect_uri      VARCHAR(500),
 *   grant_types       VARCHAR(20),
 *   username          VARCHAR(40)      NOT NULL
 * );
 */

@Getter
@Entity
@Table(name = "oauth_clients")
public class ClientEntity implements Client {
    @Id
    @Column(length = 86)
    private String clientId;
    private String clientSecret;
    private String name;
    private String redirectUri;     // 여기 있어야하는지 잘 모르겠음. => 카카오의 경우 redirectUri 값을 확인함. 있어야함
    private String grantTypes;
    private String username;

    @Override
    public boolean verifyClient(String redirectUri, String clientSecret) {
        // 값이 비어 있으면 안됨
        if(this.redirectUri == null || redirectUri == null) {
            return false;
        }
        if( ! this.redirectUri.equals(redirectUri)) {
            return false;
        }
        // clientSecret 같은 경우 값이 있는 경우는 검사를 해야하고, 값이 없는 경우는 clientSecret을 사용하지 않는 경우임
        if(this.clientSecret != null && clientSecret != null && this.clientSecret.equals(clientSecret)) {
            return false;
        }
        return true;
    }
}
