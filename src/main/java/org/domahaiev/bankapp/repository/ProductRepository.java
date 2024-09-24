package org.domahaiev.bankapp.repository;

import org.domahaiev.bankapp.model.Product;
import org.domahaiev.bankapp.model.enums.ProductName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> getProductByProductName(ProductName productName);
}
