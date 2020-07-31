package com.example.springbootoauth2example.member.service;

import com.example.springbootoauth2example.member.dao.MemberRepository;
import com.example.springbootoauth2example.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 로그인
     * @param username 사용자 아이디
     * @param password 사용자 패스워드
     * @return
     */
    public Member login(String username, String password) throws Exception {
        Member member = memberRepository.findByUsername(username);
        if(member == null) {
            // 아이디 틀릴 경우 예외처리
            throw new Exception();
        }

        // 패스워드 틀릴 시 에러처리
        if(member.validatePassword(password) == false) {
            throw new Exception();
        }

        return member;
    }
}
