package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.Client;
import com.byeongukchoi.oauth2.server.repository.ClientRepository;
import com.example.springbootoauth2server.OAuth.repository.jpaRepository.ClientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private ClientJpaRepository clientJpaRepository;

    @Override
    public Client getOne(String id) {
        return clientJpaRepository.getOne(id);
    }
}
