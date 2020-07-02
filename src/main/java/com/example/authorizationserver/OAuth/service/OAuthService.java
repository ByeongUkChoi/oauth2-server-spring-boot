package com.example.authorizationserver.OAuth.service;

import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.example.authorizationserver.OAuth.dto.TokenDto;
import com.example.authorizationserver.OAuth.dao.AuthorizationCodeRepository;
import com.example.authorizationserver.OAuth.domain.AuthorizationCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

        @Autowired
        private AuthorizationCodeRepository authorizationCodeRepository;

        /**
         * AuthorizationCode를 생성하여 DB 저장하고 code 반환
         * authorize_code 발급
         */
        public String getAuthorizationCode(String clientId, String redirectUri) {
                // 코드 랜덤으로 생성해야함
                String code = "test-authorize-code";
                AuthorizationCode authorizationCode = AuthorizationCode.builder()
                        .clientId(clientId)
                        .memberId(90001)    // TODO: test
                        .redirectUri(redirectUri)
                        .expires(123123123)
                        .code(code).build();
                // authorize code insert
                authorizationCodeRepository.save(authorizationCode);

                return code;
        }

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