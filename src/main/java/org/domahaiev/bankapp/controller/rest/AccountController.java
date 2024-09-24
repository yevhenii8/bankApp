package org.domahaiev.bankapp.controller.rest;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateAccountPersistDTO;
import org.domahaiev.bankapp.model.Account;
import org.domahaiev.bankapp.service.interf.AccountService;
import org.domahaiev.bankapp.validation.annotation.UUIDChecker;
import org.domahaiev.bankapp.validation.annotation.UsernameChecker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "account-controller")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/id/{id}")
    @Operation(summary = "get account by id",
            description = "the method returns the account by its id in the form of JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "account found"),
                    @ApiResponse(responseCode = "404", description = "account not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Account> getAccountById(@UUIDChecker @PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

//http://localhost:8080/api/accounts/id/4f8e1b1d-482d-4f51-9b16-64f6274c019e

    @PostMapping
    @Operation(summary = "create account",
            description = "the method create the account by its accountDTO from JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "account created"),
                    @ApiResponse(responseCode = "409", description = "account already exist")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountPersistDTO createAccountPersistDTO) {
        accountService.createAccount(createAccountPersistDTO);
        return new ResponseEntity<>("account successfully created", HttpStatus.CREATED);
    }

    //http://localhost:8080/api/accounts

    //{
    //        "username": "Account 16",
    //        "password": "Start234",
    //        "accountType": "ONLINE_BANKING",
    //        "accountCurrency": "USD",
    //        "clientId": "f95a4c9a-3a5b-4c22-8b56-73b2a3e831df"
    //}

    @GetMapping("/username/{username}")
    @Operation(summary = "get account by username",
            description = "the method returns the account by its username in the form of JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "account found"),
                    @ApiResponse(responseCode = "404", description = "account not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Account> getAccountByUsername(@UsernameChecker @PathVariable(name = "username") String username) {
        return ResponseEntity.ok(accountService.getAccountByUsername(username));
    }

    //http://localhost:8080/api/accounts/username/Account16

    @PutMapping("/deactivate/{id}")
    @Operation(summary = "deactivate account by id",
            description = "deactivate account by id",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "account successfully deactivated"),
                    @ApiResponse(responseCode = "404", description = "account not found"),
                    @ApiResponse(responseCode = "409", description = "account already deactivated")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> deactivateAccountById(@UUIDChecker @PathVariable(name = "id") String id) {
        accountService.deactivateAccount(id);
        return new ResponseEntity<>("account successfully deactivated", HttpStatus.OK);
    }

    //http://localhost:8080/api/accounts/deactivate/12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0

    @GetMapping("/balance/{id}")
    @Operation(summary = "get account balance by id",
            description = "the method returns the account balance by its id",
            tags = "account-controller",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "account found"),
                    @ApiResponse(responseCode = "404", description = "account not found"),
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> getAccountBalanceById(@UUIDChecker @PathVariable(name = "id") String id) {
        return ResponseEntity.ok(accountService.getAccountBalanceById(id));
    }

    //http://localhost:8080/api/accounts/balance/12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0

}