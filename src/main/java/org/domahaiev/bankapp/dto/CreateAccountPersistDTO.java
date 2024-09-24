package org.domahaiev.bankapp.dto;

import lombok.Data;

@Data
public class CreateAccountPersistDTO {

    String username;
    String password;
    String accountType;
    String accountCurrency;
    String clientId;
}
