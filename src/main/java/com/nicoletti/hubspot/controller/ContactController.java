package com.nicoletti.hubspot.controller;

import com.nicoletti.hubspot.dto.ContactResponseDTO;
import com.nicoletti.hubspot.dto.CreateContactRequestDTO;
import com.nicoletti.hubspot.service.HubspotClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final HubspotClientService hubspotClientService;

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(@RequestBody CreateContactRequestDTO dto) {
        ContactResponseDTO contact = hubspotClientService.createContact(dto);
        return ResponseEntity.ok(contact);
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> listContacts() {
        List<ContactResponseDTO> contacts = hubspotClientService.listContacts();
        return ResponseEntity.ok(contacts);
    }

}
