package com.example.springbootoauth2server.admin.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Administrator {
    @Id
    @Column(length = 40)
    private String username;
    private String password;
    private String name;
}
