package com.example.springbootoauth2server.admin.cofnig;

import com.example.springbootoauth2server.admin.service.AdministratorUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdministratorUserDetailsService administratorUserDetailsService;

    @Autowired
    private PasswordEncoder adminPasswordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .authorizeRequests().anyRequest().hasRole("ADMIN")
                .and().formLogin().loginPage("/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
                .permitAll()
                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login")
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(administratorUserDetailsService)
                .passwordEncoder(adminPasswordEncoder);
        //auth.authenticationProvider()
    }
}
