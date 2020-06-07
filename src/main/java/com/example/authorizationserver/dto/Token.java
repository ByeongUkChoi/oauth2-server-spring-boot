package com.example.authorizationserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {
 *     "access_token":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
 *     "token_type":"bearer",
 *     "refresh_token":"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",
 *     "expires_in":43199,
 *     "scope":"Basic_Profile"
 * }
 */

@AllArgsConstructor
public class Token {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    //private String scope;
}
