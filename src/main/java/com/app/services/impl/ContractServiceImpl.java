package com.app.services.impl;

import com.app.dto.ContractDTO;
import com.app.models.Contract;
import com.app.models.Devis;
import com.app.models.enums.DevisStatus;
import com.app.repositories.ContractRepository;
import com.app.repositories.DevisRepository;
import com.app.services.ContractService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    public ContractRepository contractRepository;

    @Autowired
    private DevisRepository devisRepository;

    @Override
    public List<Contract> getAllContracts(){return contractRepository.findAll();}
    @Override
    public Optional<Contract> getContractById(UUID id) {
        return contractRepository.findById(id);
    }
    public Contract saveContract(Contract contract) {

        return contractRepository.save(contract);
    }




    // Method to archive a contract by ID
    public void archiveContract(UUID contractId) {
        Optional<Contract> optionalContract = contractRepository.findById(contractId);

        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();

            if (!contract.isArchived()) {
                contract.setArchived(true);
                contractRepository.save(contract);
            }
        } else {
            throw new EntityNotFoundException("Contract with ID " + contractId + " not found");
        }
    }


}
