package org.domahaiev.bankapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateAgreementPersistDTO;
import org.domahaiev.bankapp.model.Agreement;
import org.domahaiev.bankapp.model.enums.AgreementStatus;
import org.domahaiev.bankapp.exceptions.AgreementAlreadyTerminatedException;
import org.domahaiev.bankapp.exceptions.AgreementNotFoundException;
import org.domahaiev.bankapp.exceptions.errorMessages.ErrorMessage;
import org.domahaiev.bankapp.mapper.AgreementMapper;
import org.domahaiev.bankapp.repository.AgreementRepository;
import org.domahaiev.bankapp.service.interf.AccountService;
import org.domahaiev.bankapp.service.interf.AgreementService;
import org.domahaiev.bankapp.service.interf.ManagerService;
import org.domahaiev.bankapp.service.interf.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final AccountService accountService;
    private final ProductService productService;
    private final ManagerService managerService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createAgreement(CreateAgreementPersistDTO createAgreementPersistDTO) {
        Agreement agreement = agreementMapper.agreementFromAgreementDTO(createAgreementPersistDTO);

        assignAccount(agreement, createAgreementPersistDTO.getAccountId());
        assignProduct(agreement, createAgreementPersistDTO.getProductId());
        assignManager(agreement);

        agreementRepository.save(agreement);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void terminateAgreement(String id) {
        Agreement agreement = getAgreementById(id);
        if (agreement.getAgreementStatus().equals(AgreementStatus.TERMINATED)) throw new AgreementAlreadyTerminatedException(ErrorMessage.AGREEMENT_ALREADY_TERMINATED);

        agreement.setAgreementStatus(AgreementStatus.TERMINATED);
        agreementRepository.delete(agreement);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Agreement getAgreementById(String id) {
        return agreementRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AgreementNotFoundException(ErrorMessage.AGREEMENT_NOT_FOUND));
    }

    @Override
    public void assignAccount(Agreement agreement, String accountId) {
        agreement.setAccount(accountService.getAccountById(accountId));
    }

    @Override
    public void assignProduct(Agreement agreement, String productId) {
        agreement.setProduct(productService.getProductById(productId));
    }

    @Override
    public void assignManager(Agreement agreement) {
        managerService.getManagerById(String.valueOf(agreement.getAccount().getClient().getManager().getId()));
    }
}