package com.example.springbootoauth2server.OAuth.service;

import com.byeongukchoi.oauth2.server.dto.AuthorizationRequestDto;
import com.byeongukchoi.oauth2.server.dto.TokenDto;
import com.byeongukchoi.oauth2.server.entity.AuthorizationCode;
import com.byeongukchoi.oauth2.server.entity.Client;
import com.byeongukchoi.oauth2.server.grant.AbstractGrant;
import com.byeongukchoi.oauth2.server.grant.AuthorizationCodeGrant;
import com.byeongukchoi.oauth2.server.grant.RefreshTokenGrant;
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
     * @throws Exception
     */
    public RedirectView getAuthorizationCode(AuthorizationRequestDto authorizationRequestDto,
                                             RedirectAttributes redirectAttributes) throws Exception {

        Client client = clientRepository.getOne(authorizationRequestDto.getClientId());
        if(client == null || ! client.verifyClient(authorizationRequestDto)) {
            throw new Exception("Invalid client");
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
     * @throws Exception
     */
    public TokenDto issueToken(AuthorizationRequestDto authorizationRequestDto) throws Exception {

        // TODO: 1. client 검증
        Client client = clientRepository.getOne(authorizationRequestDto.getClientId());
        if(client == null || ! client.verifyClient(authorizationRequestDto)) {
            throw new Exception("Invalid client");
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
            throw new Exception("Invalid Grant Type");
        }
        TokenDto token = grant.issueToken(authorizationRequestDto);

        return token;
    }
}
