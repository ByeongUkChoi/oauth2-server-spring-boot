package com.example.springbootoauth2server.admin.service;

import com.example.springbootoauth2server.admin.domain.Administrator;
import com.example.springbootoauth2server.admin.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministratorUserDetailsService implements UserDetailsService {

    private final AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = administratorRepository.findByUsername(username);
        if (administrator == null) {
            throw new UsernameNotFoundException(username + "is not found.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        User user = new User(administrator.getUsername(), administrator.getPassword(), authorities);

        return user;
    }
}
