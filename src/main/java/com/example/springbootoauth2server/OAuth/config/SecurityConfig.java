package com.example.springbootoauth2server.OAuth.config;

import com.example.springbootoauth2server.member.service.MemberUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberUserDetailsService memberUserDetailsService;
    @Autowired
    private PasswordEncoder memberPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 사용자 회원가입은 세션 검증하지 않음  TODO: 추후 멤버가 분리되면 같이 분리 해야 함
                .antMatchers("/member/join/**").permitAll()
                // 코드 발급 시 로그인 되어있어야함
                .antMatchers("/oauth/authorize").hasRole("USER")
                // 나머지 oauth2는 로그인과 상관 없음
                .antMatchers("/oauth/**").permitAll()
                // test database 접속
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/member/login")  // 커스텀 로그인 페이지
                .permitAll()
                .and()
                // TODO: 왜 있는지 확인 필요
                .logout()
                .and()
                // h2 console을 이용하기 위함
                .headers().frameOptions().disable()
                .and()
                // postman에서 post로 발생 시 에러를 해결하기 위함
                .csrf().disable();
    }
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(memberUserDetailsService)
                .passwordEncoder(memberPasswordEncoder);
        //auth.authenticationProvider()
    }
}
