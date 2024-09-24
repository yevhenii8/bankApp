package org.domahaiev.bankapp.controller.rest;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateTransactionPersistDTO;
import org.domahaiev.bankapp.model.Transaction;
import org.domahaiev.bankapp.service.interf.TransactionService;
import org.domahaiev.bankapp.validation.annotation.UUIDChecker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "transaction-controller")
@Validated
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/id/{id}")
    @Operation(summary = "get transaction by id",
            description = "the method returns the transaction by its id in the form of JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "transaction found"),
                    @ApiResponse(responseCode = "404", description = "transaction not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Transaction> getTransactionById(@UUIDChecker @PathVariable(name = "id") String id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    //http://localhost:8080/api/transactions/id/1d6b9c8e-4a3f-2e7c-5b9d-8f1a7e3d2c4e

    @PostMapping
    @Operation(summary = "create transaction",
            description = "the method create the transaction by its transactionDTO from JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "transaction created"),
                    @ApiResponse(responseCode = "409", description = "transaction already exist")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionPersistDTO createTransactionPersistDTO) {
        transactionService.createTransaction(createTransactionPersistDTO);
        return new ResponseEntity("transaction successfully created", HttpStatus.CREATED);
    }

    //http://localhost:8080/api/transactions

}