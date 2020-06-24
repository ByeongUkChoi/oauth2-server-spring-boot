package com.example.authorizationserver.OAuth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizeCodeRepository extends JpaRepository<AuthorizeCode, Integer>{
}
