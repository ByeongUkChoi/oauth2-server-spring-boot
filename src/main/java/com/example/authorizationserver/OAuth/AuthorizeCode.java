package com.example.authorizationserver.OAuth;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
public class AuthorizeCode {
    @Id
    private int authorizeCodeId;
    private String clientId;
    private String autorizeCode;
    private LocalDateTime expirationTime;
}
