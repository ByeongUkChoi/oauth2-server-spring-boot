package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.Client;
import com.byeongukchoi.oauth2.server.repository.ClientRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    @Override
    public Client findById(String clientId) {
        return null;
    }
}
