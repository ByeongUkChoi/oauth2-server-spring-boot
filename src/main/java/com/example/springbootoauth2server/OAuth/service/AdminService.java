package com.example.springbootoauth2server.OAuth.service;

import com.example.springbootoauth2server.OAuth.entity.AuthorizationCodeImpl;
import com.example.springbootoauth2server.OAuth.entity.ClientImpl;
import com.example.springbootoauth2server.OAuth.repository.AuthorizationCodeRepository;
import com.example.springbootoauth2server.OAuth.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AuthorizationCodeRepository authorizationCodeRepository;

    public Page<ClientImpl> getClients(Pageable pageable) {
        Page<ClientImpl> clients = clientRepository.findAll(pageable);
        return clients;
    }

    public Page<AuthorizationCodeImpl> getAuthorizationCodes(Pageable pageable) {
        return authorizationCodeRepository.findAll(pageable);
    }

}
