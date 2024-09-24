package org.domahaiev.bankapp.mapper;

import org.domahaiev.bankapp.dto.CreateTransactionPersistDTO;
import org.domahaiev.bankapp.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction transactionFromTransactionDTO(CreateTransactionPersistDTO createTransactionPersistDTO);

    CreateTransactionPersistDTO transactionDTOFromTransaction(Transaction transaction);
}
