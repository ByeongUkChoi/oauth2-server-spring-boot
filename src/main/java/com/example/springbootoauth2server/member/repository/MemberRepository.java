package com.example.springbootoauth2server.member.repository;

import com.example.springbootoauth2server.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
    Member findByUsername(String username);
}
