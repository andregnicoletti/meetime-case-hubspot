package com.nicoletti.hubspot.controller;

import com.nicoletti.hubspot.auth.TokenStore;
import com.nicoletti.hubspot.dto.CreateContactRequestDTO;
import com.nicoletti.hubspot.service.HubspotClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final HubspotClientService hubspotClientService;
    private final TokenStore tokenStore;

    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody CreateContactRequestDTO dto) {
        String accessToken = tokenStore.getToken("default");
        dto.setAccessToken(accessToken);

        if (accessToken == null) {
            throw new RuntimeException("Access token não encontrado. Faça login primeiro.");
        }

        return hubspotClientService.createContact(dto);
    }
}
