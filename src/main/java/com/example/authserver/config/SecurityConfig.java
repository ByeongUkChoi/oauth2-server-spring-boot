package com.example.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity  // 모든 접근에 대하여 인가(Authorization) 필요
public class SecurityConfig extends WebSecurityConfigurerAdapter {
}
