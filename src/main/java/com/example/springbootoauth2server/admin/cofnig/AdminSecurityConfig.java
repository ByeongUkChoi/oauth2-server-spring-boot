package com.example.springbootoauth2server.admin.cofnig;

import com.example.springbootoauth2server.admin.service.AdministratorUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdministratorUserDetailsService administratorUserDetailsService;

    // TODO: 어플리케이션 분리를 위해 일부로 중복 생성. 분리 후 이름 변경
    @Bean(name = "adminPasswordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage("/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
                .permitAll()
                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login");
        http.csrf().disable();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(administratorUserDetailsService)
                .passwordEncoder(passwordEncoder());
        //auth.authenticationProvider()
    }
}
