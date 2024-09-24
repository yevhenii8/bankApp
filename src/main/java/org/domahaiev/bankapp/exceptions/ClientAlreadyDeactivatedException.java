package org.domahaiev.bankapp.exceptions;

public class ClientAlreadyDeactivatedException extends RuntimeException {

    public ClientAlreadyDeactivatedException(String message) { super(message); }
}
