package com.nicoletti.hubspot.controller;

import com.nicoletti.hubspot.service.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping
    public ResponseEntity<Void> handleWebhook(@RequestBody List<Map<String, Object>> payloadList) {
        payloadList.forEach(webhookService::processWebhook);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
