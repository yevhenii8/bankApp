package org.domahaiev.bankapp.service.interf;

import org.domahaiev.bankapp.dto.CreateTransactionPersistDTO;
import org.domahaiev.bankapp.model.Transaction;

public interface TransactionService {

    Transaction getTransactionById(String id);

    void createTransaction(CreateTransactionPersistDTO createTransactionPersistDTO);

    void assignAccount(Transaction transaction, String accountId);
}