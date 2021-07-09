package com.example.springbootoauth2server.member.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@Getter
public class Member {

    @Id
    @Column(length = 40)
    private String username;
    @Setter
    private String password;
    private String name;
}
