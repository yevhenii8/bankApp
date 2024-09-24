package org.domahaiev.bankapp.controller.rest;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateProductPersistDTO;
import org.domahaiev.bankapp.model.Product;
import org.domahaiev.bankapp.service.interf.ProductService;
import org.domahaiev.bankapp.validation.annotation.UUIDChecker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "product-controller")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/id/{id}")
    @Operation(summary = "get product by id",
            description = "the method returns the product by its id in the form of JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "product found"),
                    @ApiResponse(responseCode = "404", description = "product not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Product> getProductById(@UUIDChecker @PathVariable(name = "id") String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    //http://localhost:8080/api/products/id/5d9c4e1a-2b7e-4f6b-8c3a-1e9f2d7b5c4e

    @PostMapping
    @Operation(summary = "create product",
            description = "the method create the product by its productDTO from JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "product created"),
                    @ApiResponse(responseCode = "409", description = "product already exist")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> createProduct(@RequestBody CreateProductPersistDTO createProductPersistDTO) {
        productService.createProduct(createProductPersistDTO);
        return new ResponseEntity<>("product successfully created", HttpStatus.CREATED);
    }

    //http://localhost:8080/api/products


    @GetMapping("/productName/{productName}")
    @Operation(summary = "get product by productName",
            description = "the method returns the product by its productName in the form of JSON object",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "product found"),
                    @ApiResponse(responseCode = "404", description = "product not found")
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<Product> getProductByProductName(@PathVariable(name = "productName") String productName) {
        return ResponseEntity.ok(productService.getProductByProductName(productName));
    }

    //http://localhost:8080/api/products/productName/Product1

    @PutMapping("/deactivate/{id}")
    @Operation(summary = "deactivate product by id",
            description = "deactivate product by id",
            externalDocs = @ExternalDocumentation(
                    description = "here's all the documentation",
                    url = "https://google.com/"
            ),
            responses = {
                    @ApiResponse(responseCode = "404", description = "product not found"),
                    @ApiResponse(responseCode = "409", description = "product already deactivated"),
                    @ApiResponse(responseCode = "200", description = "product successfully deactivated"),
            },
            security = {
                    @SecurityRequirement(name = "требования к безопасности")
            }
    )
    public ResponseEntity<String> deactivateProduct(@UUIDChecker @PathVariable(name = "id") String id) {
        productService.deactivateProduct(id);
        return new ResponseEntity<>("product successfully deactivated", HttpStatus.OK);
    }

    //http://localhost:8080/api/products/deactivate

}