package com.example.springbootoauth2server.OAuth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.byeongukchoi.oauth2.server.dto.AuthorizationRequestDto;
import com.byeongukchoi.oauth2.server.dto.TokenDto;
import com.byeongukchoi.oauth2.server.grant.AbstractGrant;
import com.byeongukchoi.oauth2.server.grant.AuthorizationCodeGrant;
import com.byeongukchoi.oauth2.server.grant.RefreshTokenGrant;
import com.example.springbootoauth2server.OAuth.dao.AccessTokenRepository;
import com.example.springbootoauth2server.OAuth.dao.AuthorizationCodeRepository;
import com.example.springbootoauth2server.OAuth.dao.ClientRepository;
import com.example.springbootoauth2server.OAuth.dao.RefreshTokenRepository;
import com.example.springbootoauth2server.OAuth.entity.AuthorizationCode;
import com.example.springbootoauth2server.OAuth.entity.Client;
import com.example.springbootoauth2server.OAuth.entity.RefreshToken;
import com.example.springbootoauth2server.member.entity.Member;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

@Service
public class OAuthService {

    @Autowired
    private AuthorizationCodeRepository authorizationCodeRepository;
    @Autowired
    private AccessTokenRepository accessTokenRepository;
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
     * 로그인 여부를 파악하여 리다이렉트 시킴
     * @param request
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    public RedirectView getAuthorizationCode(HttpServletRequest request,
                                             RedirectAttributes redirectAttributes) throws Exception {

        String responseType = request.getParameter("response_type");
        if( ! responseType.equals("code")) {
            throw new Exception("Invalid Response type");
        }

        // 인증 서버와 로그인 서버가 다를 경우 쿠키에서 받아온 값으로 로그인 서버에 로그인 정보 확인 및 사용자 정보 가져옴. 현재는 같은 서버이므로 세션으로 확인이 가능하다.
        // 세션으로 로그인 여부 확인
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        // 로그인이 되어있지 않은 경우 로그인으로 redirect. 현재 접속 uri를 넘김
        if(member == null) {
            String currentUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
            redirectAttributes.addAttribute("continue", currentUrl);
            return new RedirectView("/login");
        }

        // 로그인이 되어있는 경우
        String clientId = request.getParameter("client_id");
        String redirectUri = request.getParameter("redirect_uri");
        String code = getAuthorizationCode(member.getMemberId(), clientId, redirectUri);
        redirectAttributes.addAttribute("code", code);
        return new RedirectView(redirectUri);
    }

    /**
     * AuthorizationCode를 생성하여 DB 저장하고 code 반환
     * authorize_code 발급
     */
    private String getAuthorizationCode(long memberId, String clientId, String redirectUri) {
        
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
     * @param authorizationRequestDto
     * @return
     * @throws Exception
     */
    public TokenDto issueToken(AuthorizationRequestDto authorizationRequestDto) throws Exception {

        // TODO: 1. client 검증


        // 2. 토큰 발급
        // grant_type == 'authorization_code' : 토큰 발급, grant_type == 'refresh_token' : 토큰 갱신
        // TODO: 상수로 변경하거나 함수로 변경해야함
        String grantType = authorizationRequestDto.getGrantType();
        AbstractGrant grant = null;
        if (grantType.equals("authorization_code")) {
            grant = new AuthorizationCodeGrant(authorizationCodeRepository, accessTokenRepository, refreshTokenRepository);
        } else if (grantType.equals("refresh_token")) {
            grant = new RefreshTokenGrant(accessTokenRepository, refreshTokenRepository);
        } else {
            throw new Exception();
        }
        TokenDto token = grant.issueToken(authorizationRequestDto);

        return token;
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