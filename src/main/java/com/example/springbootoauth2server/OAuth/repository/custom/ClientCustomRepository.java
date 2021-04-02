package com.example.springbootoauth2server.OAuth.repository.custom;

import com.byeongukchoi.oauth2.server.domain.Client;
import com.byeongukchoi.oauth2.server.domain.repository.ClientRepository;

public interface ClientCustomRepository<T extends Client, ID> extends ClientRepository<T, ID> {
}
