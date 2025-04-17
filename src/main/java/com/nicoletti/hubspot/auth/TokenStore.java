package com.nicoletti.hubspot.auth;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {

    private final ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();

    public void storeToken(String key, String accessToken) {
        tokenMap.put(key, accessToken);
    }

    public String getToken(String key) {
        return tokenMap.get(key);
    }

    public void clearToken(String key) {
        tokenMap.remove(key);
    }
}
