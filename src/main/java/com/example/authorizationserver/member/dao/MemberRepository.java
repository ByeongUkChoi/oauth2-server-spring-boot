package com.example.authorizationserver.member.dao;

import com.example.authorizationserver.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
    Member findByUsername(String username);
}
