package com.example.authorizationserver.Authenticatioin;

public class AuthenticationService {

    /**
     * 로그인
     * @param username 사용자 아이디
     * @param password 사용자 패스워드
     * @return
     */
    public boolean login(String username, String password) {

        // TODO: 로그인 처리 방식
        // Member member = DaoOrRepository.findbyUsername(username);
        // 1. 멤버 객체에 패스워드 검증 맡김 (패스워드 인코딩은 멤버객체 안에서)
        // member.validatePassword(password);

        // 2. 패스워드 가져와서 서비스에서 검증 (패스워드 인코딩도 서비스에서)
        // String memberPassword = member.getPassword();
        // if(password != memberPassword)
        return true;
    }
}
