package org.domahaiev.bankapp.repository;

import org.domahaiev.bankapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> getClientBySocialSecurityNumber(String socialSecurityNumber);
}
