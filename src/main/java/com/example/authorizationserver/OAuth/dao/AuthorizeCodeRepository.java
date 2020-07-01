package com.example.authorizationserver.OAuth.dao;

import com.example.authorizationserver.OAuth.domain.AuthorizationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizeCodeRepository extends JpaRepository<AuthorizationCode, String>{
}
