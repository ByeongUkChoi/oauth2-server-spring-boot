package com.example.oauth2server.grant;

import com.example.oauth2server.dto.TokenDto;

public interface Grantable {
    /**
     * 토큰 발급
     * TODO:매개변수 추가 해야함
     * @return
     */
    public TokenDto issueToken();
}
