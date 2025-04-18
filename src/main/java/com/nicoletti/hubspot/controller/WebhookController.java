package com.nicoletti.hubspot.controller;

import com.nicoletti.hubspot.service.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody List<Map<String, Object>> payloadList) {
        payloadList.forEach(webhookService::processWebhook);
        return ResponseEntity.ok("Webhook recebido com sucesso");
    }
}
