package com.example.authorizationserver.OAuth.service;

import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.example.authorizationserver.OAuth.dto.TokenDto;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    /**
     * 토큰 발급
     * @return
     */
	public TokenDto getToken() {
        Algorithm algorithm = Algorithm.HMAC512("secret");
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.SECOND, 3600);
        Date expiredDate = calendar.getTime();

        String accessToken = JWT.create()
                .withIssuer("auth0")
                .withIssuedAt(currentDate)
                .withExpiresAt(expiredDate)
                .sign(algorithm);

        return TokenDto.builder()
                .access_token(accessToken)
                .token_type("bearer")
                .refresh_token("this_is_refresh_token")
                .expires_in(3600)
                .build();
	}
}