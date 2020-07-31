package com.example.springbootoauth2example.member.dao;

import com.example.springbootoauth2example.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
    Member findByUsername(String username);
}
