package org.domahaiev.bankapp.dto;

import lombok.Data;

@Data
public class CreateTransactionPersistDTO {

    String transactionTypeStatus;
    String amount;
    String description;
    String accountId;
}
