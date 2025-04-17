package com.nicoletti.hubspot.auth;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {

    private final ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();

    public void storeToken(String accessToken) {
        this.storeToken("default", accessToken);
    }

    public void storeToken(String key, String accessToken) {
        tokenMap.put(key, accessToken);
    }

    public String getToken() {
        return this.getToken("default");
    }

    public String getToken(String key) {
        String accessToken = tokenMap.get(key);
        if (accessToken == null) {
            throw new RuntimeException("Access token não encontrado. Faça login primeiro.");
        }
        return accessToken;
    }

    public void clearToken(String key) {
        tokenMap.remove(key);
    }
}
