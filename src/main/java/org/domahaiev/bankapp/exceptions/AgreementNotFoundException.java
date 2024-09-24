package org.domahaiev.bankapp.exceptions;

public class AgreementNotFoundException extends RuntimeException {
    public AgreementNotFoundException(String message) {
        super(message);
    }
}
