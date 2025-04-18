package com.nicoletti.hubspot.dto;

import lombok.Data;

@Data
public class ContactResponseDTO {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String createdAt;

}
