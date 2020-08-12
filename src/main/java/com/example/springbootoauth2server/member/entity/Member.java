package com.example.springbootoauth2server.member.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATE TABLE members (
 *   username            VARCHAR(40),
 *   password            VARCHAR(40),
 *   name                VARCHAR(40),
 *   scope               VARCHAR(1000), // 사용하지 않음
 *   PRIMARY KEY (username)
 * );
 */
@Entity
public class Member {

    @Id
    private String username;
    private String password;
    private String name;

//    @OneToMany(mappedBy = "member")
//    private List<MemberRole> memberProducts = new ArrayList<>();
    //private Set<MemberRole> memberProducts;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // 암호 일치 여부
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

}
