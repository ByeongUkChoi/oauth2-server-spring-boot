package com.example.springbootoauth2server.member.entity;

import javax.persistence.*;

@Entity
public class MemberRole {
    // TODO: id없애고 username과 role_id로 복합키 해야함
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "username")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
