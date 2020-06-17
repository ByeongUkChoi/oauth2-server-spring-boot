package com.example.authorizationserver.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String name;

    // 암호 일치 여부
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
