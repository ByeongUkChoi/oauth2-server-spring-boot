package com.example.authorizationserver.OAuth;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
public class AuthorizeCode {
    @Id
    private int authorizeCodeId;
    private String clientId;
    private String autorizeCode;
    private LocalDateTime expirationTime;
}
