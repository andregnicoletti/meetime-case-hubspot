package com.nicoletti.hubspot.controller;

import com.nicoletti.hubspot.auth.TokenStore;
import com.nicoletti.hubspot.config.HubspotProperties;
import com.nicoletti.hubspot.dto.TokenResponseDTO;
import com.nicoletti.hubspot.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private static final String URL_PATTERN = "https://app.hubspot.com/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s";

    private final HubspotProperties hubspotProperties;
    private final OAuthService oauthService;
    private final TokenStore tokenStore;

    @GetMapping("/authorize")
    public RedirectView redirectToHubspotAuth() {

        String url = String.format(
                URL_PATTERN,
                hubspotProperties.getClientId(),
                hubspotProperties.getRedirectUri(),
                hubspotProperties.getScopes()
        );

        return new RedirectView(url);
    }

    @GetMapping("/callback")
    public ResponseEntity<TokenResponseDTO> handleCallback(@RequestParam("code") String code) {
        TokenResponseDTO token = oauthService.exchangeCodeForToken(code);

        // TODO chave usada é "default" podendo ser alterada por outras
        tokenStore.storeToken("default",token.getAccessToken());

        return ResponseEntity.ok(token);
    }

}
