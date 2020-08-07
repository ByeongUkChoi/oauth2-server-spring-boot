package com.example.springbootoauth2server.OAuth.repository.jpaRepository;

import com.example.springbootoauth2server.OAuth.entity.ClientImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<ClientImpl, String> {
}
