package org.domahaiev.bankapp.dto;

import lombok.Data;

@Data
public class CreateClientPersistDTO {

    String firstName;
    String lastName;
    String socialSecurityNumber;
    String email;
    String phone;
    String address;
    String taxCode;
}
