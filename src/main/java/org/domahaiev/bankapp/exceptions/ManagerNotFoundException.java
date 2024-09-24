package org.domahaiev.bankapp.exceptions;

public class ManagerNotFoundException extends RuntimeException {

    public ManagerNotFoundException(String message) {
        super(message);
    }
}
