package com.example.springbootoauth2server.member.service;

import com.example.springbootoauth2server.member.dto.MemberDto;
import com.example.springbootoauth2server.member.entity.Member;
import com.example.springbootoauth2server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder memberPasswordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException(username + "is not found.");
        }

        // TODO: 권한 넣어주기
        List<GrantedAuthority> authorities = new ArrayList<>();
        // TODO: test
        //authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User(member.getUsername(), memberPasswordEncoder.encode(member.getPassword()), authorities);

        return user;
    }

    /**
     * 회원 가입
     * @param memberDto
     */
    public void signUp(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        memberRepository.save(member);
    }
}
