package org.domahaiev.bankapp.controller.rest;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateManagerPersistDTO;
import org.domahaiev.bankapp.model.Manager;
import org.domahaiev.bankapp.service.interf.ManagerService;
import org.domahaiev.bankapp.validation.annotation.SSNChecker;
import org.domahaiev.bankapp.validation.annotation.UUIDChecker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "manager-controller")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get manager by id",
            description = "The method returns the manager by its id in the form of JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "Here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Manager found"),
                    @ApiResponse(responseCode = "404", description = "Manager not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Manager> getManagerById(@UUIDChecker @PathVariable(value = "id") String id) {
        return ResponseEntity.ok(managerService.getManagerById(id));
    }

    //http://localhost:8080/api/managers/id/d0c2e8b1-3b4e-4e7a-9d63-9e6d9b3e1b12

    @GetMapping("/ssn/{ssn}")
    @Operation(summary = "Get manager by ssn",
            description = "The method returns the manager by its ssn in the form of JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "Here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Manager found"),
                    @ApiResponse(responseCode = "404", description = "Manager not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Manager> getManagerBySSN(@SSNChecker @PathVariable(value = "ssn") String ssn) {
        return ResponseEntity.ok(managerService.getManagerBySSN(ssn));
    }

    //http://localhost:8080/api/managers/ssn/429-40-2011

    @PostMapping
    @Operation(summary = "create manager",
            description = "the method create the manager by its managerDTO from JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "manager created"),
                    @ApiResponse(responseCode = "409", description = "manager already exist")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> createManager(@RequestBody CreateManagerPersistDTO createManagerPersistDTO) {
        managerService.createManager(createManagerPersistDTO);
        return new ResponseEntity<>("manager successfully created", HttpStatus.CREATED);
    }

    //http://localhost:8080/api/managers

    @PutMapping("/deactivate/{id}")
    @Operation(summary = "deactivate manager by id",
            description = "deactivate manager by id",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "manager successfully deactivated"),
                    @ApiResponse(responseCode = "404", description = "manager not found"),
                    @ApiResponse(responseCode = "409", description = "manager already deactivated")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> deactivateManager(@UUIDChecker @PathVariable(value = "id") String id) {
        managerService.deactivateManager(id);
        return new ResponseEntity<>("manager successfully deactivated", HttpStatus.OK);
    }

    //http://localhost:8080/api/managers/deactivate

}