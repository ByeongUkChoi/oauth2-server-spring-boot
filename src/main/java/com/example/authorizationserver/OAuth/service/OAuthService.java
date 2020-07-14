package com.example.authorizationserver.OAuth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.authorizationserver.OAuth.dao.AuthorizationCodeRepository;
import com.example.authorizationserver.OAuth.dao.ClientRepository;
import com.example.authorizationserver.OAuth.dao.RefreshTokenRepository;
import com.example.authorizationserver.OAuth.domain.AuthorizationCode;
import com.example.authorizationserver.OAuth.domain.Client;
import com.example.authorizationserver.OAuth.domain.RefreshToken;
import com.example.authorizationserver.OAuth.dto.TokenDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class OAuthService {

    @Autowired
    private AuthorizationCodeRepository authorizationCodeRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    //application.properties 사용
    @Value("${authorizationServer.authorizationCode.expiresIn}")
    private int authorizationCodeExpiresIn;
    @Value("${authorizationServer.accessToken.expiresIn}")
    private int accessTokenExpiresIn;
    @Value("${authorizationServer.refreshToken.expiresIn}")
    private int refreshTokenExpiresIn;

    /**
     * AuthorizationCode를 생성하여 DB 저장하고 code 반환
     * authorize_code 발급
     */
    public String getAuthorizationCode(long memberId, String clientId, String redirectUri) {
        
        // TODO: client 검증
        Client client = clientRepository.findByClientIdAndMemberId(clientId, memberId);

        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);

        // 코드 랜덤으로 생성
        String code = RandomStringUtils.randomAlphanumeric(86);

        AuthorizationCode authorizationCode = AuthorizationCode.builder()
                .clientId(clientId)
                .memberId(memberId)
                .redirectUri(redirectUri)
                .expires(currentTimestamp + authorizationCodeExpiresIn)
                .code(code).build();

        // authorize code insert
        authorizationCodeRepository.save(authorizationCode);

        return code;
    }

    /**
     * 토큰 발급
     * @return
     * @param clientId
     * @param redirectUri
     * @param code
     * @param clientSecret
     */
    public TokenDto getToken(String clientId, String redirectUri, String code, String clientSecret) {

        Client client = clientRepository.getOne(clientId);
        // TODO: secret 검증

        AuthorizationCode authorizationCode = authorizationCodeRepository.findByCodeAndClientId(code, clientId);
        // TODO: 만료시간 및 검증

        return generateToken();
    }

    /**
     * 토큰 갱신
     * @param clientId
     * @param refreshToken
     * @param clientSecret
     * @return
     */
    public TokenDto refreshToken(String clientId, String refreshToken, String clientSecret) {

        Client client = clientRepository.getOne(clientId);
        // TODO: secret 검증

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByRefreshTokenAndClientId(refreshToken, clientId);
        // TODO: 만료시간 및 검증

        return generateToken();
    }

    /**
     * 토큰 생성
     * @return TokenDto
     */
    private TokenDto generateToken() {
        Algorithm algorithm = Algorithm.HMAC512("secret");  // TODO: sign값 상수로
        Date currentDate = new Date();

        // expires_in은 만료 시간
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.SECOND, accessTokenExpiresIn);
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
                .expires_in(accessTokenExpiresIn)
                .build();
    }
}