package org.domahaiev.bankapp.exceptions;

public class TransactionCouldNotBeCreatedException extends RuntimeException {
    public TransactionCouldNotBeCreatedException(String message) {
        super(message);
    }

}
