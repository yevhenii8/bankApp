package org.domahaiev.bankapp.service.interf;

import org.domahaiev.bankapp.dto.CreateAccountPersistDTO;
import org.domahaiev.bankapp.model.Account;

public interface AccountService {

    Account getAccountById(String id);

    Account getAccountByUsername(String username);

    void createAccount(CreateAccountPersistDTO createAccountPersistDTO);

    void assignClient(Account account, String clientId);

    void deactivateAccount(String id);

    String getAccountBalanceById(String id);
}
