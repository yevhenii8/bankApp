package org.domahaiev.bankapp.exceptions;

public class AccountAlreadyDeactivatedException extends RuntimeException {

    public AccountAlreadyDeactivatedException(String message) {
        super(message);
    }
}
