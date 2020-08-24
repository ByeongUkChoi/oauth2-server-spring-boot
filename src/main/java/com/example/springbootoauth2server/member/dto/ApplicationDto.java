package com.example.springbootoauth2server.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationDto {
    private String name;
    private String redirectUri;
    private boolean clientSecret;
}
