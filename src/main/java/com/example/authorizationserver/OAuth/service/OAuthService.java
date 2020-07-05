package com.example.authorizationserver.OAuth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.authorizationserver.OAuth.dao.AuthorizationCodeRepository;
import com.example.authorizationserver.OAuth.domain.AuthorizationCode;
import com.example.authorizationserver.OAuth.dto.TokenDto;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
public class OAuthService {

        private AuthorizationCodeRepository authorizationCodeRepository;

        /**
         * AuthorizationCode를 생성하여 DB 저장하고 code 반환
         * authorize_code 발급
         */
        public String getAuthorizationCode(long memberId, String clientId, String redirectUri) {

            // 만료 시간 설정. 현재 시간 + 60분
            int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
            int expirationTimestamp = 60 * 60;  // authorize_code 만료 시간 60분 ( TODO: 상수로 분리 시켜야함)

            // 코드 랜덤으로 생성
            String code = RandomStringUtils.randomAlphanumeric(86);

            AuthorizationCode authorizationCode = AuthorizationCode.builder()
                    .clientId(clientId)
                    .memberId(memberId)    // TODO: test
                    .redirectUri(redirectUri)
                    .expires(currentTimestamp + expirationTimestamp)
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

        // expires_in은 만료 시간

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