package com.nicoletti.hubspot.controller;

import com.nicoletti.hubspot.dto.CreateContactRequestDTO;
import com.nicoletti.hubspot.service.HubspotClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final HubspotClientService hubspotClientService;

    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody CreateContactRequestDTO dto) {
        return hubspotClientService.createContact(dto);
    }

    @GetMapping
    public ResponseEntity<String> listContacts() {
        return hubspotClientService.listContacts();
    }

}
