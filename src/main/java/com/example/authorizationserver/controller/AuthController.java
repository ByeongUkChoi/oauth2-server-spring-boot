package com.example.authorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/oauth/authorize")
    public void requestAuth(@RequestParam("client_id") String clientId,
                            @RequestParam("redirect_uri") String redirectUri,
                            @RequestParam("response_type") String responseType) {

    }
}
