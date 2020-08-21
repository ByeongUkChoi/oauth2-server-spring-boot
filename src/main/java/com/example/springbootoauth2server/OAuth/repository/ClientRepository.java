package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.Client;
import com.example.springbootoauth2server.OAuth.entity.ClientEntity;
import com.example.springbootoauth2server.OAuth.repository.custom.ClientCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, String>, ClientCustomRepository<Client, String> {
}
