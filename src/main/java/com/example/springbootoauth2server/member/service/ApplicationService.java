package com.example.springbootoauth2server.member.service;

import com.example.springbootoauth2server.OAuth.entity.ClientEntity;
import com.example.springbootoauth2server.OAuth.repository.ClientRepository;
import com.example.springbootoauth2server.member.dto.ApplicationDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ClientRepository clientRepository;

    @Transactional
    public ClientEntity createApplication(ApplicationDto applicationDto) {

        // clienti id 랜덤으로 생성
        String clientId = RandomStringUtils.randomAlphanumeric(32);

        ClientEntity clientEntity = ClientEntity.builder()
                .clientId(clientId)
                .name(applicationDto.getName())
                .redirectUri(applicationDto.getRedirectUri())
                .username(applicationDto.getUsername())
                .build();
        if(applicationDto.isClientSecret()) {
            String clientSecret = RandomStringUtils.randomAlphanumeric(32);
            clientEntity.setClientSecret(clientSecret);
        }

        return clientRepository.save(clientEntity);
    }

    public List<ClientEntity> getApplications() {
        List<ClientEntity> clients = clientRepository.findAll();
        return clients;
    }
}
