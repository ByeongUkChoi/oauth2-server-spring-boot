package com.example.authorizationserver.OAuth;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class AuthorizeCode {
    @Id
    private int clientId;
    private String autorizeCode;
    private LocalDateTime expirationTime;
}
