package org.domahaiev.bankapp.exceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String message) {
        super(message);
    }
}
