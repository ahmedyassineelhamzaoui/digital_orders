package com.app.services;

import com.app.models.Contract;

import java.util.List;
import java.util.UUID;

public interface ContractService {

    List<Contract> getAllContracts();

    Contract saveContract(Contract contract);
    void archiveContract(UUID contractId);

}
