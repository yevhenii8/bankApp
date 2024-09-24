package org.domahaiev.bankapp.repository;

import org.domahaiev.bankapp.model.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgreementRepository extends JpaRepository<Agreement, UUID> {

}
