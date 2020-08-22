package com.example.springbootoauth2server.admin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Administrator {
    @Id
    @Column(length = 40)
    private String username;
    private String password;
    private String name;
}
