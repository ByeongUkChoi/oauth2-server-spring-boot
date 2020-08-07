package com.example.springbootoauth2server.OAuth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.byeongukchoi.oauth2.server.dto.AuthorizationRequestDto;
import com.byeongukchoi.oauth2.server.dto.TokenDto;
import com.byeongukchoi.oauth2.server.entity.AuthorizationCode;
import com.byeongukchoi.oauth2.server.entity.Client;
import com.byeongukchoi.oauth2.server.entity.RefreshToken;
import com.byeongukchoi.oauth2.server.grant.AbstractGrant;
import com.byeongukchoi.oauth2.server.grant.AuthorizationCodeGrant;
import com.byeongukchoi.oauth2.server.grant.RefreshTokenGrant;
import com.byeongukchoi.oauth2.server.repository.AccessTokenRepository;
import com.byeongukchoi.oauth2.server.repository.AuthorizationCodeRepository;
import com.byeongukchoi.oauth2.server.repository.ClientRepository;
import com.byeongukchoi.oauth2.server.repository.RefreshTokenRepository;
import com.example.springbootoauth2server.OAuth.entity.AuthorizationCodeImpl;
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
        if(responseType != null && ! responseType.equals("code")) {
            throw new Exception("Invalid Response type");
        }

        // 인증 서버와 로그인 서버가 다를 경우 쿠키에서 받아온 값으로 로그인 서버에 로그인 정보 확인 및 사용자 정보 가져옴. 현재는 같은 서버이므로 세션으로 확인이 가능하다.
        // 세션으로 로그인 여부 확인
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        RedirectView redirectView = new RedirectView();
        // 로그인이 되어있지 않은 경우 로그인으로 redirect. 현재 접속 uri를 넘김
        if(member == null) {
            String currentUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
            redirectAttributes.addAttribute("continue", currentUrl);
            redirectView.setUrl("/login");
            return redirectView;
        }

        // 로그인이 되어있는 경우
        String clientId = request.getParameter("client_id");
        String redirectUri = request.getParameter("redirect_uri");

        Client client = clientRepository.getOne(clientId);
        // TODO: client 검증
        // 현재 시간 (타임스탬프 (초))
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);

        // 코드 랜덤으로 생성
        String code = RandomStringUtils.randomAlphanumeric(86);

        // TODO: 생성을 repository에서 매개변수 받아서 getNewAuthorizationCode로 해도 좋을 듯
        AuthorizationCode authorizationCode = AuthorizationCodeImpl.builder()
                .clientId(clientId)
                .username(member.getUsername())
                .redirectUri(redirectUri)
                .expiredAt(currentTimestamp + authorizationCodeExpiresIn)
                .code(code).build();

        // authorize code insert
        authorizationCodeRepository.save(authorizationCode);

        redirectAttributes.addAttribute("code", code);
        redirectView.setUrl(redirectUri);
        return redirectView;
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


        // 2. 토큰 발급
        // grant_type == 'authorization_code' : 토큰 발급, grant_type == 'refresh_token' : 토큰 갱신
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

    /**
     * TODO: tokenRepository에서 불러올 것임. 걸로 옴겨야함 공통 로직이기 때문에 util로 빼서 호출할것
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
                .accessToken(accessToken)
                .tokenType("bearer")
                .refreshToken("this_is_refresh_token")
                .expiresIn(accessTokenExpiresIn)
                .build();
    }

}