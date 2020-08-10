package com.example.springbootoauth2server.OAuth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationCodeDto {
    private String code;
    private String clientId;
    private String username;
    private String redirectUri;
    // expiredAt, createdAt
}
