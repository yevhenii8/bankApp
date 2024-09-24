package org.domahaiev.bankapp.controller.rest;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateAgreementPersistDTO;
import org.domahaiev.bankapp.model.Agreement;
import org.domahaiev.bankapp.service.interf.AgreementService;
import org.domahaiev.bankapp.validation.annotation.UUIDChecker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "agreement_controller")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agreements")
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping("/id/{id}")
    @Operation(summary = "get agreement by id",
            description = "the method returns the agreement by its id in the form of JSON object",
            externalDocs =
            @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "agreement found"),
                    @ApiResponse(responseCode = "404", description = "agreement not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Agreement> getAgreementById(@UUIDChecker @PathVariable(name = "id") String id) {
        return ResponseEntity.ok(agreementService.getAgreementById(id));
    }

    //http://localhost:8080/api/agreements/id/2c7f6e5b-63d4-4e2e-8b1c-cd8d66a57d36

    @PostMapping
    @Operation(
            summary = "create agreement",
            description = "The method create the agreement by its agreementDTO from JSON object",
            externalDocs =
            @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "agreement found"),
                    @ApiResponse(responseCode = "409", description = "agreement already exist")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> createAgreement(@RequestBody CreateAgreementPersistDTO createAgreementPersistDTO) {
        agreementService.createAgreement(createAgreementPersistDTO);
        return new ResponseEntity<>("agreement successfully created", HttpStatus.CREATED);
    }

    //http://localhost:8080/api/agreements

    @PutMapping("/terminate/{id}")
    @Operation(summary = "deactivate agreement by id",
            description = "deactivate agreement by id",
            externalDocs = @ExternalDocumentation(
                    description = "Here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "agreement successfully deactivated"),
                    @ApiResponse(responseCode = "404", description = "agreement not found"),
                    @ApiResponse(responseCode = "409", description = "agreement already deactivated")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> terminateAgreement(@UUIDChecker @PathVariable(name = "id") String id) {
        agreementService.terminateAgreement(id);
        return new ResponseEntity<>("agreement successfully deactivated", HttpStatus.OK);
    }

    //http://localhost:8080/api/agreements/terminate/2c7f6e5b-63d4-4e2e-8b1c-cd8d66a57d36

}