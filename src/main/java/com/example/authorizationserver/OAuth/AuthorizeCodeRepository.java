package com.example.authorizationserver.OAuth;

import com.example.authorizationserver.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizeCodeRepository extends JpaRepository<AuthorizeCode, Integer>{
}
