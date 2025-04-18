package com.nicoletti.hubspot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicoletti.hubspot.config.HubspotProperties;
import com.nicoletti.hubspot.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final HubspotProperties hubspotProperties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getClientId() {
        return hubspotProperties.getClientId();
    }

    public TokenResponseDTO exchangeCodeForToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("client_id", hubspotProperties.getClientId());
        form.add("client_secret", hubspotProperties.getClientSecret());
        form.add("redirect_uri", hubspotProperties.getRedirectUri());
        form.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        ResponseEntity<TokenResponseDTO> response = restTemplate.postForEntity(
                hubspotProperties.getTokenUrl(),
                request,
                TokenResponseDTO.class
        );

        return response.getBody();
    }

}
