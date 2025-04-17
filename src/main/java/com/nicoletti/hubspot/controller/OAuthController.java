package com.nicoletti.hubspot.controller;

import com.nicoletti.hubspot.config.HubspotProperties;
import com.nicoletti.hubspot.dto.TokenResponseDTO;
import com.nicoletti.hubspot.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final HubspotProperties hubspotProperties;
    private final OAuthService oauthService;

    @GetMapping("/authorize")
    public ResponseEntity<String> getAuthorizationUrl() {
        String url = String.format(
                "%s?client_id=%s&redirect_uri=%s&scope=%s&response_type=code",
                hubspotProperties.getAuthUrl(),
                hubspotProperties.getClientId(),
                URLEncoder.encode(hubspotProperties.getRedirectUri(), StandardCharsets.UTF_8),
                URLEncoder.encode(hubspotProperties.getScopes(), StandardCharsets.UTF_8)
        );

        return ResponseEntity.ok(url);
    }

    @GetMapping("/callback")
    public ResponseEntity<TokenResponseDTO> handleCallback(@RequestParam("code") String code) {
        TokenResponseDTO token = oauthService.exchangeCodeForToken(code);
        return ResponseEntity.ok(token);
    }

}
