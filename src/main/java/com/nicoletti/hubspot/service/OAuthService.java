package com.nicoletti.hubspot.service;

import com.nicoletti.hubspot.config.HubspotProperties;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final HubspotProperties hubspotProperties;

    public OAuthService(HubspotProperties hubspotProperties) {
        this.hubspotProperties = hubspotProperties;
    }

    public String getClientId() {
        return hubspotProperties.getClientId();
    }

}
