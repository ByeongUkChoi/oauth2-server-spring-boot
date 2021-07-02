package com.example.springbootoauth2server.admin.entity;

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
