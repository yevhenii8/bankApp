package org.domahaiev.bankapp.controller.exceptionHandler;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.domahaiev.bankapp.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler({
            ClientNotFoundException.class,
            AccountNotFoundException.class,
            AgreementNotFoundException.class,
            ManagerNotFoundException.class,
            ProductNotFoundException.class,
            TransactionNotFoundException.class
    })
    @ApiResponse(responseCode = "404", description = "Not Found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    })
    public ResponseEntity<String> handleClientNotFoundException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers -> headers.setContentType(MediaType.APPLICATION_JSON))
                .body(ex.getMessage());
    }

    @ExceptionHandler({
            ClientAlreadyDeactivatedException.class,
            AccountAlreadyDeactivatedException.class,
            AgreementAlreadyTerminatedException.class,
            ManagerAlreadyDeactivatedException.class,
            ProductAlreadyDeactivatedException.class
    })
    public ResponseEntity<String> handleClientAlreadyDeactivatedException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .headers(headers -> headers.setContentType(MediaType.APPLICATION_JSON))
                .body(ex.getMessage());
    }

    @ExceptionHandler({
            ClientAlreadyExistsException.class,
            ManagerAlreadyExistsException.class,
            ProductAlreadyExistsException.class,
            AccountAlreadyExistsException.class,

    })
    public ResponseEntity<String> handleClientAlreadyExistsException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .headers(headers -> headers.setContentType(MediaType.APPLICATION_JSON))
                .body(ex.getMessage());
    }

    @ExceptionHandler(ClientAlreadyHasManagerException.class)
    public ResponseEntity<String> handleClientAlreadyHasManagerException(ClientAlreadyHasManagerException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers -> headers.setContentType(MediaType.APPLICATION_JSON))
                .body(ex.getMessage());
    }

    @ExceptionHandler(TransactionCouldNotBeCreatedException.class)
    public ResponseEntity<String> handleTransactionCouldNotBeCreatedException(TransactionCouldNotBeCreatedException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .headers(headers -> headers.setContentType(MediaType.APPLICATION_JSON))
                .body(ex.getMessage());
    }
}