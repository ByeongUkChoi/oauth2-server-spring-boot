package com.example.authorizationserver.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private String id;
    private String username;
    private String password;
    private String name;
}
