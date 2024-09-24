package org.domahaiev.bankapp.dto;

import lombok.Data;

@Data
public class CreateAgreementPersistDTO {

    String interestRate;
    String sum;
    String accountId;
    String productId;
}
