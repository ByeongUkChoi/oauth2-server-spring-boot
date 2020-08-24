package com.example.springbootoauth2server.member.service;

import com.example.springbootoauth2server.OAuth.entity.ClientEntity;
import com.example.springbootoauth2server.OAuth.repository.ClientRepository;
import com.example.springbootoauth2server.OAuth.repository.custom.impl.ClientCustomRepositoryImpl;
import com.example.springbootoauth2server.member.dto.ApplicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    // TODO: 해당 기능 인터페이스에 선언 예정
    private final ClientCustomRepositoryImpl clientCustomRepository;
    private final ClientRepository clientRepository;

    public void createApplication(ApplicationDto applicationDto) {
        ClientEntity clientEntity = clientCustomRepository.getNewClient(applicationDto.getName(), applicationDto.getRedirectUri(), applicationDto.isClientSecret());
        ClientEntity save = clientRepository.save(clientEntity);
    }
}
