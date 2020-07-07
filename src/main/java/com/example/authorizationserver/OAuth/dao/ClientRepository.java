package com.example.authorizationserver.OAuth.dao;

import com.example.authorizationserver.OAuth.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Client findByClientIdAndMemberId(String clientId, long memberId);
}
