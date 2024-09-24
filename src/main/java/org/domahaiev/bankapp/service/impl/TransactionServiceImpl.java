package org.domahaiev.bankapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateTransactionPersistDTO;
import org.domahaiev.bankapp.model.Transaction;
import org.domahaiev.bankapp.exceptions.TransactionCouldNotBeCreatedException;
import org.domahaiev.bankapp.exceptions.TransactionNotFoundException;
import org.domahaiev.bankapp.exceptions.errorMessages.ErrorMessage;
import org.domahaiev.bankapp.mapper.TransactionMapper;
import org.domahaiev.bankapp.repository.TransactionRepository;
import org.domahaiev.bankapp.service.interf.AccountService;
import org.domahaiev.bankapp.service.interf.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createTransaction(CreateTransactionPersistDTO createTransactionPersistDTO) {
        Transaction transaction = transactionMapper.transactionFromTransactionDTO(createTransactionPersistDTO);

        assignAccount(transaction, createTransactionPersistDTO.getAccountId());
        if (transaction.getAccount().getBalance() < transaction.getAmount())
            throw new TransactionCouldNotBeCreatedException(ErrorMessage.INSUFFICIENT_BALANCE);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TransactionNotFoundException(ErrorMessage.TRANSACTION_NOT_FOUND));
    }

    @Override
    public void assignAccount(Transaction transaction, String id) {
        transaction.setAccount(accountService.getAccountById(id));
    }
}