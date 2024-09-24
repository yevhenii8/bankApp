package org.domahaiev.bankapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateProductPersistDTO;
import org.domahaiev.bankapp.model.Product;
import org.domahaiev.bankapp.model.enums.ProductName;
import org.domahaiev.bankapp.model.enums.ProductStatus;
import org.domahaiev.bankapp.exceptions.ClientAlreadyDeactivatedException;
import org.domahaiev.bankapp.exceptions.ProductAlreadyExistsException;
import org.domahaiev.bankapp.exceptions.ProductNotFoundException;
import org.domahaiev.bankapp.exceptions.errorMessages.ErrorMessage;
import org.domahaiev.bankapp.mapper.ProductMapper;
import org.domahaiev.bankapp.repository.ProductRepository;
import org.domahaiev.bankapp.service.interf.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createProduct(CreateProductPersistDTO createProductPersistDTO) {
        productRepository.getProductByProductName(ProductName.valueOf(createProductPersistDTO.getProductName()))
                .ifPresent(product -> {
                    throw new ProductAlreadyExistsException(ErrorMessage.PRODUCT_ALREADY_EXISTS);
                });

        Product product = productMapper.productFromProductDTO(createProductPersistDTO);
        productRepository.save(product);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deactivateProduct(String id) {
        Product product = getProductById(id);
        if (product.getProductStatus().equals(ProductStatus.CLOSED))
            throw new ClientAlreadyDeactivatedException(ErrorMessage.PRODUCT_ALREADY_DEACTIVATED);

        product.setProductStatus(ProductStatus.CLOSED);
        productRepository.save(product);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Product getProductById(String id) {
        return productRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Product getProductByProductName(String productName) {
        return productRepository.getProductByProductName(ProductName.valueOf(productName))
                .orElseThrow(() -> new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));
    }
}