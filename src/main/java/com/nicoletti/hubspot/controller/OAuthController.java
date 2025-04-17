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

    private static final String URL_PATTERN = "https://app.hubspot.com/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s";

    private final HubspotProperties hubspotProperties;
    private final OAuthService oauthService;

    @GetMapping("/authorize")
    public ResponseEntity<String> getAuthorizationUrl() {

        String url = String.format(
                URL_PATTERN,
                hubspotProperties.getClientId(),
                hubspotProperties.getRedirectUri(),
                hubspotProperties.getScopes()
        );

        System.out.println("url: " + url);

        return ResponseEntity.ok(url);
    }

    @GetMapping("/callback")
    public ResponseEntity<TokenResponseDTO> handleCallback(@RequestParam("code") String code) {
        TokenResponseDTO token = oauthService.exchangeCodeForToken(code);
        return ResponseEntity.ok(token);
    }

}
