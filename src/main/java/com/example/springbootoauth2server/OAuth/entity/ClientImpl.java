package com.example.springbootoauth2server.OAuth.entity;

import com.byeongukchoi.oauth2.server.entity.Client;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE oauth_clients (
 *   client_id         VARCHAR(86)     NOT NULL PRIMARY KEY,
 *   client_secret     VARCHAR(32),
 *   redirect_uri      VARCHAR(500),
 *   grant_types        VARCHAR(20),
 * );
 */

@Getter
@Entity
@Table(name = "oauth_clients")
public class ClientImpl implements Client {
    @Id
    private String clientId;
    private String clientSecret;
    private String redirectUri;     // 여기 있어야하는지 잘 모르겠음. => 카카오의 경우 redirectUri 값을 확인함. 있어야함
    private String grantTypes;

    @Override
    public boolean verifyClient(String redirectUri, String clientSecret) {
        if(this.redirectUri != null && redirectUri != null && this.redirectUri.equals(redirectUri)) {
            return false;
        }
        if(this.clientSecret != null && clientSecret != null && this.clientSecret.equals(clientSecret)) {
            return false;
        }
        return true;
    }
}
