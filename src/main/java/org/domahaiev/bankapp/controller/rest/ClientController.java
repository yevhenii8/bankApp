package org.domahaiev.bankapp.controller.rest;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateClientPersistDTO;
import org.domahaiev.bankapp.model.Client;
import org.domahaiev.bankapp.service.interf.ClientService;
import org.domahaiev.bankapp.validation.annotation.SSNChecker;
import org.domahaiev.bankapp.validation.annotation.UUIDChecker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "client-controller")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/ssn/{ssn}")
    @Operation(summary = "get client by ssn",
            description = "the method returns the client by its ssn in the form of JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "client found"),
                    @ApiResponse(responseCode = "404", description = "client not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Client> getClientSSN(@SSNChecker @PathVariable(name = "ssn") String ssn) {
        return ResponseEntity.ok(clientService.getClientBySSN(ssn));
    }

    //http://localhost:8080/api/clients/ssn/888-99-0000

    @GetMapping("/id/{id}")
    @Operation(summary = "get client by id",
            description = "the method returns the client by its id in the form of JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "client found"),
                    @ApiResponse(responseCode = "404", description = "client not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Client> getClientById(@UUIDChecker @PathVariable(name = "id") String id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    //http://localhost:8080/api/clients/id/2b5c8e6a-4d2e-4d83-9c15-8e1a4a6b8d7f

    @PostMapping
    @Operation(summary = "create client",
            description = "the method create the client by its clientDTO from JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "client created"),
                    @ApiResponse(responseCode = "409", description = "client already exist")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> createClient(@RequestBody CreateClientPersistDTO createClientPersistDTO) {
        clientService.createClient(createClientPersistDTO);
        return new ResponseEntity<>("client successfully created", HttpStatus.CREATED);
    }

    //http://localhost:8080/api/clients

    //{
    //   "firstName": "Yevhenii",
    //   "lastName": "Domahaiev",
    //   "socialSecurityNumber": "123-12-1234",
    //   "email": "yevheniidomahaiev@gmail.com",
    //   "phone": "+1 (414) 497 0021",
    //   "address": "Howell ave 2580",
    //   "taxCode": "53207"
    //}

    @PutMapping("/deactivate/{id}")
    @Operation(summary = "deactivate client by id",
            description = "deactivate client by id",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "client successfully deactivated"),
                    @ApiResponse(responseCode = "404", description = "client not found"),
                    @ApiResponse(responseCode = "409", description = "client already deactivated")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> deactivateClientById(@UUIDChecker @PathVariable(name = "id") String id) {
        clientService.deactivateClient(id);
        return new ResponseEntity<>("client successfully deactivated", HttpStatus.OK);
    }

    //http://localhost:8080/api/clients/deactivate/2b5c8e6a-4d2e-4d83-9c15-8e1a4a6b8d7f

}