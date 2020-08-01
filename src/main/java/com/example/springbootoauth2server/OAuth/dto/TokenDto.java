package com.example.springbootoauth2server.OAuth.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * {
 *     "access_token":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
 *     "token_type":"bearer",
 *     "refresh_token":"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",
 *     "expires_in":43199,
 *     //"scope":"Basic_Profile"
 * }
 */

@Builder
@Getter     // controller에서 반환 될때 변환 되어야 해서 필요함
public class TokenDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    //private String scope;
}
