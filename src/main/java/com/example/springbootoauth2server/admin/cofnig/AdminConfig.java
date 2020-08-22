package com.example.springbootoauth2server.admin.cofnig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    // TODO: 어플리케이션 분리를 위해 일부로 중복 생성. 분리 후 이름 변경
    @Bean(name = "adminPasswordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
