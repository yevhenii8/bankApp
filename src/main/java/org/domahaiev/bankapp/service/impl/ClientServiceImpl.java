package org.domahaiev.bankapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.dto.CreateClientPersistDTO;
import org.domahaiev.bankapp.model.Client;
import org.domahaiev.bankapp.model.enums.Status;
import org.domahaiev.bankapp.exceptions.ClientAlreadyDeactivatedException;
import org.domahaiev.bankapp.exceptions.ClientAlreadyExistsException;
import org.domahaiev.bankapp.exceptions.ClientAlreadyHasManagerException;
import org.domahaiev.bankapp.exceptions.ClientNotFoundException;
import org.domahaiev.bankapp.exceptions.errorMessages.ErrorMessage;
import org.domahaiev.bankapp.mapper.ClientMapper;
import org.domahaiev.bankapp.repository.ClientRepository;
import org.domahaiev.bankapp.service.interf.ClientService;
import org.domahaiev.bankapp.service.interf.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ManagerService managerService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createClient(CreateClientPersistDTO createClientPersistDTO) {
        clientRepository.getClientBySocialSecurityNumber(createClientPersistDTO.getSocialSecurityNumber())
                .ifPresent(client -> {
                    throw new ClientAlreadyExistsException(ErrorMessage.CLIENT_ALREADY_EXISTS);
                });

        Client client = clientMapper.clientFromClientDTO(createClientPersistDTO);
        assignManager(client);
        clientRepository.save(client);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deactivateClient(String id) {
        Client client = getClientById(id);
        if (client.getClientStatus().equals(Status.CLOSED))
            throw new ClientAlreadyDeactivatedException(ErrorMessage.CLIENT_ALREADY_DEACTIVATED);

        client.setClientStatus(Status.CLOSED);
        client.getAccounts()
                .forEach(account -> account.setAccountStatus(Status.CLOSED));
        client.setManager(null);

        clientRepository.save(client);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Client getClientById(String id) {
        return clientRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ClientNotFoundException(ErrorMessage.CLIENT_NOT_FOUND));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Client getClientBySSN(String socialSecurityNumber) {
        return clientRepository.getClientBySocialSecurityNumber(socialSecurityNumber)
                .orElseThrow(() -> new ClientNotFoundException(ErrorMessage.CLIENT_NOT_FOUND));
    }

    @Override
    public void assignManager(Client client) {
        if (client.getManager() == null) {
            client.setManager(managerService.getManagerWithLeastClients());
        } else throw new ClientAlreadyHasManagerException(ErrorMessage.CLIENT_ALREADY_HAS_MANAGER);
    }
}
