package org.domahaiev.bankapp.mapper;

import org.domahaiev.bankapp.dto.CreateAgreementPersistDTO;
import org.domahaiev.bankapp.model.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementMapper {

    @Mapping(target = "agreementStatus", constant = "ACTIVE")
    Agreement agreementFromAgreementDTO(CreateAgreementPersistDTO createAgreementPersistDTO);

    CreateAgreementPersistDTO agreementDTOFromAgreement(Agreement agreement);
}
