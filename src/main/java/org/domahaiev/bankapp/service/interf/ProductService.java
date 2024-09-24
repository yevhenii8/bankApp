package org.domahaiev.bankapp.service.interf;

import org.domahaiev.bankapp.dto.CreateProductPersistDTO;
import org.domahaiev.bankapp.model.Product;

public interface ProductService {

    Product getProductById(String id);

    void createProduct(CreateProductPersistDTO createProductPersistDTO);

    Product getProductByProductName(String productName);

    void deactivateProduct(String id);
}
