package com.example.authorizationserver.OAuth;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    private String clientId;
    private String clientSecret;
    private boolean security;
    private boolean enable;
}
