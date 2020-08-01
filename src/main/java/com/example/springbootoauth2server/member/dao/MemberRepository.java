package com.example.springbootoauth2server.member.dao;

import com.example.springbootoauth2server.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
    Member findByUsername(String username);
}
