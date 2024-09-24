package org.domahaiev.bankapp.repository;

import org.domahaiev.bankapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> getAccountByUsername(String username);
}
