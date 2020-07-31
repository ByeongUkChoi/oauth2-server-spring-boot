package com.example.springbootoauth2example.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * CREATE TABLE members (
 *   member_id           BIGINT,
 *   username            VARCHAR(80),
 *   password            VARCHAR(80),
 *   name                VARCHAR(80),
 *   scope               VARCHAR(4000), // 사용하지 않음
 *   PRIMARY KEY (member_id)
 * );
 */
@Entity
public class Member {

    @Id
    @GeneratedValue
    private long memberId;
    private String username;
    private String password;
    private String name;

    public long getMemberId() {
        return memberId;
    }

    // 암호 일치 여부
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
