package com.nicoletti.hubspot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicoletti.hubspot.auth.TokenStore;
import com.nicoletti.hubspot.dto.ContactResponseDTO;
import com.nicoletti.hubspot.dto.CreateContactRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HubspotClientService {

    private final TokenStore tokenStore;
    private final ObjectMapper objectMapper;

    public ContactResponseDTO createContact(CreateContactRequestDTO dto) {
        String url = "https://api.hubapi.com/crm/v3/objects/contacts";

        Map<String, Object> properties = new HashMap<>();
        properties.put("firstname", dto.getFirstName());
        properties.put("lastname", dto.getLastName());
        properties.put("email", dto.getEmail());

        Map<String, Object> payload = new HashMap<>();
        payload.put("properties", properties);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tokenStore.getToken("default"));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        String responseBody = restTemplate.exchange(url, HttpMethod.POST, request, String.class).getBody();

        try {
            JsonNode json = objectMapper.readTree(responseBody);
            return jsonContractToContractDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao parsear resposta do HubSpot", e);
        }
    }

    public List<ContactResponseDTO> listContacts() {
        String accessToken = tokenStore.getToken("default");

        if (accessToken == null) {
            throw new RuntimeException("Access token não encontrado. Faça login primeiro.");
        }

        String url = "https://api.hubapi.com/crm/v3/objects/contacts?limit=100";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        String responseBody = restTemplate.exchange(url, HttpMethod.GET, request, String.class).getBody();

        List<ContactResponseDTO> contatos = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode results = root.get("results");
            results.forEach(node -> contatos.add(this.jsonContractToContractDto(node)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao parsear resposta do HubSpot", e);
        }

        return contatos;
    }

    private ContactResponseDTO jsonContractToContractDto(JsonNode json) {
        ContactResponseDTO contact = new ContactResponseDTO();
        contact.setId(json.get("id").asText());
        JsonNode props = json.get("properties");
        contact.setEmail(props.get("email").asText());
        contact.setFirstName(props.get("firstname").asText());
        contact.setLastName(props.get("lastname").asText());
        contact.setCreatedAt(json.get("createdAt").asText());
        return contact;
    }

}
