package org.domahaiev.bankapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateManagerPersistDTO;
import org.domahaiev.bankapp.model.Manager;
import org.domahaiev.bankapp.model.enums.Status;
import org.domahaiev.bankapp.exceptions.ManagerAlreadyDeactivatedException;
import org.domahaiev.bankapp.exceptions.ManagerAlreadyExistsException;
import org.domahaiev.bankapp.exceptions.ManagerNotFoundException;
import org.domahaiev.bankapp.exceptions.errorMessages.ErrorMessage;
import org.domahaiev.bankapp.mapper.ManagerMapper;
import org.domahaiev.bankapp.repository.ManagerRepository;
import org.domahaiev.bankapp.service.interf.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createManager(CreateManagerPersistDTO createManagerPersistDTO) {
        managerRepository.getManagerBySocialSecurityNumber(createManagerPersistDTO.getSocialSecurityNumber())
                .ifPresent(manager -> {
                    throw new ManagerAlreadyExistsException(ErrorMessage.MANAGER_ALREADY_EXISTS);
                });

        Manager manager = managerMapper.managerFromManagerDTO(createManagerPersistDTO);
        managerRepository.save(manager);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deactivateManager(String id) {
        Manager manager = getManagerById(id);
        if (manager.getManagerStatus().equals(Status.CLOSED))
            throw new ManagerAlreadyDeactivatedException(ErrorMessage.MANAGER_ALREADY_DEACTIVATED);

        manager.getClients()
                .forEach(client -> client.setManager(getManagerWithLeastClients()));
        manager.setManagerStatus(Status.CLOSED);
        managerRepository.save(manager);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Manager getManagerById(String id) {
        return managerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ManagerNotFoundException(ErrorMessage.MANAGER_NOT_FOUND));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Manager getManagerBySSN(String ssn) {
        return managerRepository.getManagerBySocialSecurityNumber(ssn)
                .orElseThrow(() -> new ManagerNotFoundException(ErrorMessage.MANAGER_NOT_FOUND));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Manager getManagerWithLeastClients() {
        return managerRepository.findManagerWithLeastClients()
                .orElseThrow(() -> new ManagerNotFoundException(ErrorMessage.MANAGER_NOT_FOUND));
    }
}