package org.domahaiev.bankapp.exceptions;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
