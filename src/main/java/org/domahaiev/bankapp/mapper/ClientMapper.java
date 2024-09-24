package org.domahaiev.bankapp.mapper;

import org.domahaiev.bankapp.dto.CreateClientPersistDTO;
import org.domahaiev.bankapp.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "clientStatus", constant = "ACTIVE")
    Client clientFromClientDTO(CreateClientPersistDTO createClientPersistDTO);

    CreateClientPersistDTO clientDTOFromClient(Client client);
}