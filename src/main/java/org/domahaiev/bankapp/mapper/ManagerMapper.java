package org.domahaiev.bankapp.mapper;

import org.domahaiev.bankapp.dto.CreateManagerPersistDTO;
import org.domahaiev.bankapp.model.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    @Mapping(target = "managerStatus", constant = "ACTIVE")
    Manager managerFromManagerDTO(CreateManagerPersistDTO createManagerPersistDTO);

    CreateManagerPersistDTO managerDTOFromManager(Manager manager);
}
