package org.domahaiev.bankapp.mapper;

import org.domahaiev.bankapp.dto.CreateAccountPersistDTO;
import org.domahaiev.bankapp.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountStatus", constant = "ACTIVE")
    @Mapping(target = "balance", constant = "0")
    Account accountFromAccountDTO(CreateAccountPersistDTO createAccountPersistDTO);

    CreateAccountPersistDTO AccountDTOFromAccount(Account account);
}
