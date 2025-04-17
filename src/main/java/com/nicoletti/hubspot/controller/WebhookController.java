package com.nicoletti.hubspot.controller;

import com.nicoletti.hubspot.service.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
        webhookService.processWebhook(payload);
        return ResponseEntity.ok("Webhook recebido com sucesso");
    }
}
