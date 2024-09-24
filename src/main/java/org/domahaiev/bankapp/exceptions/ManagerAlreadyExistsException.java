package org.domahaiev.bankapp.exceptions;

public class ManagerAlreadyExistsException extends RuntimeException {

    public ManagerAlreadyExistsException(String message) {
        super(message);
    }
}
