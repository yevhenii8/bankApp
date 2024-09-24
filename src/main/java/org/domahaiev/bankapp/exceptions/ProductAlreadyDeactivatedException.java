package org.domahaiev.bankapp.exceptions;

public class ProductAlreadyDeactivatedException extends RuntimeException{
    public ProductAlreadyDeactivatedException(String message) {super(message);}
}
