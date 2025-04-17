package com.nicoletti.hubspot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class WebhookService {

    public void processWebhook(Map<String, Object> payload) {
        log.info("Recebido Webhook de criação de contato: {}", payload);

        // Exemplo de extração de evento:
        // payload pode ser uma lista de eventos [{ "eventType": "contact.creation", "objectId": 1234, ... }]
        // Aqui você pode validar tipo de evento e tratar

        // TODO: Validar assinatura, se necessário
        // TODO: Processar dados relevantes (ex: salvar em banco ou outra ação)
    }
}
