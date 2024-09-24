package org.domahaiev.bankapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateAccountPersistDTO;
import org.domahaiev.bankapp.model.Account;
import org.domahaiev.bankapp.model.enums.Status;
import org.domahaiev.bankapp.exceptions.AccountAlreadyDeactivatedException;
import org.domahaiev.bankapp.exceptions.AccountAlreadyExistsException;
import org.domahaiev.bankapp.exceptions.AccountNotFoundException;
import org.domahaiev.bankapp.exceptions.errorMessages.ErrorMessage;
import org.domahaiev.bankapp.mapper.AccountMapper;
import org.domahaiev.bankapp.repository.AccountRepository;
import org.domahaiev.bankapp.service.interf.AccountService;
import org.domahaiev.bankapp.service.interf.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final AccountMapper accountMapper;
    private final ClientService clientService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createAccount(CreateAccountPersistDTO createAccountPersistDTO) {
        accountRepository.getAccountByUsername(createAccountPersistDTO.getUsername())
                .ifPresent(account -> {
                    throw new AccountAlreadyExistsException(ErrorMessage.ACCOUNT_ALREADY_EXISTS);
                });

        Account account = accountMapper.accountFromAccountDTO(createAccountPersistDTO);

        String encryptedPassword = bCryptPasswordEncoder.encode(createAccountPersistDTO.getPassword());
        account.setPassword(encryptedPassword);

        assignClient(account, createAccountPersistDTO.getClientId());

        accountRepository.save(account);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void deactivateAccount(String id) {
        Account account = getAccountById(id);
        if (account.getAccountStatus().equals(Status.CLOSED))
            throw new AccountAlreadyDeactivatedException(ErrorMessage.ACCOUNT_ALREADY_DEACTIVATED);

        account.setAccountStatus(Status.CLOSED);

        accountRepository.save(account);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Account getAccountById(String id) {
        return accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Account getAccountByUsername(String username) {
        return accountRepository.getAccountByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public String getAccountBalanceById(String id) {
        Account account = getAccountById(id);

        return String.valueOf(account.getBalance());
    }

    @Override
    public void assignClient(Account account, String clientId) {
        account.setClient(clientService.getClientById(clientId));
    }
}