package com.example.springbootoauth2server.OAuth.dao;

import com.example.springbootoauth2server.OAuth.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Client findByClientIdAndMemberId(String clientId, long memberId);
}
