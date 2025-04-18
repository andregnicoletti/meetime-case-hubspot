package com.nicoletti.hubspot.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class WebhookService {

    public void processWebhook(Map<String, Object> payload) {

        log.info("Recebido Webhook de criação de contato: {}", new JSONObject(payload).toString(4));

    }
}
