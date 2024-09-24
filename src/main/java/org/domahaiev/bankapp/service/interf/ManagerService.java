package org.domahaiev.bankapp.service.interf;

import org.domahaiev.bankapp.dto.CreateManagerPersistDTO;
import org.domahaiev.bankapp.model.Manager;

public interface ManagerService {

    Manager getManagerWithLeastClients();

    Manager getManagerById(String id);

    void createManager(CreateManagerPersistDTO createManagerPersistDTO);

    Manager getManagerBySSN(String ssn);

    void deactivateManager(String id);
}
