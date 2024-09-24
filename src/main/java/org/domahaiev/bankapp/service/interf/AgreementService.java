package org.domahaiev.bankapp.service.interf;

import org.domahaiev.bankapp.dto.CreateAgreementPersistDTO;
import org.domahaiev.bankapp.model.Agreement;

public interface AgreementService {

    Agreement getAgreementById(String id);

    void createAgreement(CreateAgreementPersistDTO createAgreementPersistDTO);

    void assignAccount(Agreement agreement, String accountId);

    void assignProduct(Agreement agreement, String productId);

    void assignManager(Agreement agreement);

    void terminateAgreement(String id);
}
