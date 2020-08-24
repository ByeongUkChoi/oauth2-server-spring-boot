package com.example.springbootoauth2server.OAuth.repository.custom.impl;

import com.byeongukchoi.oauth2.server.entity.Client;
import com.example.springbootoauth2server.OAuth.entity.ClientEntity;
import com.example.springbootoauth2server.OAuth.repository.custom.ClientCustomRepository;
import org.apache.commons.lang3.RandomStringUtils;
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

    // TODO: 인터페이스에 선언 해야함
    public ClientEntity getNewClient(String name, String redirectUri, boolean hasClientSecret, String username) {
        // 토큰 랜덤으로 생성
        String clientId = RandomStringUtils.randomAlphanumeric(32);

        ClientEntity clientEntity = ClientEntity.builder()
                .clientId(clientId)
                .name(name)
                .redirectUri(redirectUri)
                .username(username)
                .build();

        if(hasClientSecret) {
            String clientSecret = RandomStringUtils.randomAlphanumeric(32);
            clientEntity.setClientSecret(clientSecret);
        }
        return clientEntity;
    }
}
