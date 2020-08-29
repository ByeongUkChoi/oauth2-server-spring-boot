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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

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
     * @param request
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    public RedirectView getAuthorizationCode(HttpServletRequest request,
                                             RedirectAttributes redirectAttributes) throws Exception {

        String responseType = request.getParameter("response_type");
        if(responseType != null && ! responseType.equals("code")) {
            throw new Exception("Invalid Response type");
        }

        // spring security의 userDetails의 username 가져오기
        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();
        String clientId = request.getParameter("client_id");
        String redirectUri = request.getParameter("redirect_uri");
        String clientSecret = request.getParameter("client_secret");

        Client client = clientRepository.getOne(clientId);
        if( ! client.verifyClient(redirectUri, clientSecret)) {
            throw new Exception("Invalid client");
        }


        // TODO: test
        AuthorizationCode authorizationCode = authorizationCodeRepository.getNewCode(clientId, username, redirectUri);

        // authorize code insert
        authorizationCodeRepository.save(authorizationCode);

        redirectAttributes.addAttribute("code", authorizationCode.getCode());
        return new RedirectView(redirectUri);
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
        if(client == null) {
            throw new Exception("Invalid client id");
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
