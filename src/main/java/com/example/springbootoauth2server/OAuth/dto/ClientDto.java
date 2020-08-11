package com.example.springbootoauth2server.OAuth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    //private String grantTypes;
}
