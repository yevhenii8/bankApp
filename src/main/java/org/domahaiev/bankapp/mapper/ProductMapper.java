package org.domahaiev.bankapp.mapper;

import org.domahaiev.bankapp.dto.CreateProductPersistDTO;
import org.domahaiev.bankapp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

   @Mapping(target = "productStatus", constant = "ACTIVE")
    Product productFromProductDTO(CreateProductPersistDTO createProductPersistDTO);

   CreateProductPersistDTO productDTOFromProduct(Product product);
}