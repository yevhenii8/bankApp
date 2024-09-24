package org.domahaiev.bankapp.repository;

import org.domahaiev.bankapp.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {

    @Query("SELECT c.manager FROM Client c WHERE c.manager.id IN (SELECT m.id FROM c.manager m) GROUP BY c.manager.id ORDER BY COUNT(c.id) ASC LIMIT 1")

    Optional<Manager> findManagerWithLeastClients();

    Optional<Manager> getManagerBySocialSecurityNumber(String id);
}
