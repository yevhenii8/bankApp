package org.domahaiev.bankapp.exceptions;

public class ClientAlreadyHasManagerException extends RuntimeException{
    public ClientAlreadyHasManagerException(String message) {
        super(message);
    }
}
