package com.example.springbootoauth2server.OAuth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenDto {
    private String token;
    private String clientId;
    private String username;
    private int expiredAt;
    private int createdAt;
}
