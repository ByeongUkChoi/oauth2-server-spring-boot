package com.example.authorizationserver.OAuth;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@Entity
public class AuthorizeCode {
    @Id
    private String clientId;
    private String autorizeCode;
    private LocalDateTime expirationTime;
}
