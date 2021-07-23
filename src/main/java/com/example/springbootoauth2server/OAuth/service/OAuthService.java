package com.example.springbootoauth2server.OAuth.service;

import com.byeongukchoi.oauth2.server.application.dto.AuthorizationRequestDto;
import com.byeongukchoi.oauth2.server.application.dto.TokenDto;
import com.byeongukchoi.oauth2.server.domain.AuthorizationCode;
import com.byeongukchoi.oauth2.server.domain.Client;
import com.byeongukchoi.oauth2.server.application.AbstractGrant;
import com.byeongukchoi.oauth2.server.application.AuthorizationCodeGrant;
import com.byeongukchoi.oauth2.server.application.RefreshTokenGrant;
import com.byeongukchoi.oauth2.server.error.exception.OAuth2ServerException;
import com.example.springbootoauth2server.OAuth.repository.AccessTokenRepository;
import com.example.springbootoauth2server.OAuth.repository.AuthorizationCodeRepository;
import com.example.springbootoauth2server.OAuth.repository.ClientRepository;
import com.example.springbootoauth2server.OAuth.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class OAuthService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AuthorizationCodeRepository authorizationCodeRepository;
    @Autowired
    private AccessTokenRepository accessTokenRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    /**
     * 로그인 여부를 파악하여 리다이렉트 시킴
     * @param authorizationRequestDto
     * @param redirectAttributes
     * @return
     */
    public RedirectView getAuthorizationCode(AuthorizationRequestDto authorizationRequestDto,
                                             RedirectAttributes redirectAttributes) {

        Client client = clientRepository.getOne(authorizationRequestDto.getClientId());
        if(client == null || ! client.verifyClient(authorizationRequestDto)) {
            throw new IllegalArgumentException("Invalid client");
        }

        AuthorizationCode authorizationCode = authorizationCodeRepository.getNewCode(authorizationRequestDto.getClientId(), authorizationRequestDto.getUsername(), authorizationRequestDto.getRedirectUri());

        // authorize code insert
        authorizationCodeRepository.save(authorizationCode);

        redirectAttributes.addAttribute("code", authorizationCode.getCode());
        return new RedirectView(authorizationRequestDto.getRedirectUri());
    }

    /**
     * 토큰 발급 clientId, redirectUri, code, clientSecret
     * 토큰 갱신 clientId, refreshToken, clientSecret
     * @param authorizationRequestDto
     * @return
     * @throws OAuth2ServerException
     */
    public TokenDto issueToken(AuthorizationRequestDto authorizationRequestDto) throws OAuth2ServerException {

        // TODO: 1. client 검증
        Client client = clientRepository.getOne(authorizationRequestDto.getClientId());
        if(client == null || ! client.verifyClient(authorizationRequestDto)) {
            throw new IllegalArgumentException("Invalid client");
        }

        // 2. 토큰 발급
        // TODO: 상수로 변경하거나 함수로 변경해야함
        String grantType = authorizationRequestDto.getGrantType();
        AbstractGrant grant;
        if (grantType.equals("authorization_code")) {
            grant = new AuthorizationCodeGrant(authorizationCodeRepository, accessTokenRepository, refreshTokenRepository);
        } else if (grantType.equals("refresh_token")) {
            grant = new RefreshTokenGrant(accessTokenRepository, refreshTokenRepository);
        } else {
            throw new IllegalArgumentException("Invalid Grant Type");
        }
        TokenDto token = grant.issueToken(authorizationRequestDto);

        return token;
    }
}
