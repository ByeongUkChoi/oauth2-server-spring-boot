package com.example.oauth2server.dto;

/**
 * 토큰 발급 시 필요한 request 값을 담은 객체
 * TODO: grantType을 이 객체안에 넣을지 말지 
 * 넣게 되면 이 객체 안에서 어떠한 타입인지 알려주는 함수를 만들 수 있고
 * 넣지 않으면 이 객체 이전에 get parameter로 검사해야 한다.
 */
public class GrantRequestDto {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String code;
    private String refreshToken;

}
