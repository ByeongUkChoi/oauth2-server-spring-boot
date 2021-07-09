package com.example.springbootoauth2server.admin.repository;

import com.example.springbootoauth2server.admin.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {
    Administrator findByUsername(String username);
}
