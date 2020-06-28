package com.example.authorizationserver.OAuth;

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

@Getter
@Builder
public class TokenDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    //private String scope;
}
