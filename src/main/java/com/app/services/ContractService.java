package com.app.services;

import com.app.models.Contract;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractService {

    List<Contract> getAllContracts();

    Optional<Contract> getContractById(UUID id);

    Contract saveContract(Contract contract);
    void archiveContract(UUID contractId);

}
