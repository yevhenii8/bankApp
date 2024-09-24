package org.domahaiev.bankapp.service.interf;

import org.domahaiev.bankapp.dto.CreateClientPersistDTO;
import org.domahaiev.bankapp.model.Client;

public interface ClientService {

    Client getClientById(String id);

    Client getClientBySSN(String ssn);

    void createClient(CreateClientPersistDTO createClientPersistDTO);

    void deactivateClient(String id);

    void assignManager(Client client);
}
