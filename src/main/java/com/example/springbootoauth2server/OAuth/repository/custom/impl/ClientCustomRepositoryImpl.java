package com.example.springbootoauth2server.OAuth.repository.custom.impl;

import com.byeongukchoi.oauth2.server.entity.Client;
import com.example.springbootoauth2server.OAuth.entity.ClientEntity;
import com.example.springbootoauth2server.OAuth.repository.custom.ClientCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ClientCustomRepositoryImpl implements ClientCustomRepository<Client, String> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Client getOne(String id) {
        return entityManager.find(ClientEntity.class, id);
    }
}
