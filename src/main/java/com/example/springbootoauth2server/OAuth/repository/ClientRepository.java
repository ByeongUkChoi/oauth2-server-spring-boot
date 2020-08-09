package com.example.springbootoauth2server.OAuth.repository;

import com.byeongukchoi.oauth2.server.entity.Client;
import com.example.springbootoauth2server.OAuth.entity.ClientImpl;
import com.example.springbootoauth2server.OAuth.repository.custom.ClientCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientImpl, String>, ClientCustomRepository<Client, String> {
}
