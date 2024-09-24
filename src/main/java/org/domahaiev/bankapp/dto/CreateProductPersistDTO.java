package org.domahaiev.bankapp.dto;

import lombok.Data;

@Data
public class CreateProductPersistDTO {

    String productName;
    String interestRate;
    String productLimit;
}
