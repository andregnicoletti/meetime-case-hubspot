package com.nicoletti.hubspot.service;

import com.nicoletti.hubspot.auth.TokenStore;
import com.nicoletti.hubspot.dto.CreateContactRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HubspotClientService {

    private final TokenStore tokenStore;

    public ResponseEntity<String> createContact(CreateContactRequestDTO dto) {
        String url = "https://api.hubapi.com/crm/v3/objects/contacts";

        Map<String, Object> properties = new HashMap<>();
        properties.put("firstname", dto.getFirstName());
        properties.put("lastname", dto.getLastName());
        properties.put("email", dto.getEmail());

        Map<String, Object> payload = new HashMap<>();
        payload.put("properties", properties);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tokenStore.getToken("default"));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }

    public ResponseEntity<String> listContacts() {
        String accessToken = tokenStore.getToken("default");

        if (accessToken == null) {
            throw new RuntimeException("Access token não encontrado. Faça login primeiro.");
        }

        String url = "https://api.hubapi.com/crm/v3/objects/contacts?limit=10";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
    }
}
