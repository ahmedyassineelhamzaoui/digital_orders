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

import javax.xml.bind.ValidationException;
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
    public Optional<ContractDTO> getContractById(UUID id) {

        Optional<Contract> contract = contractRepository.findById(id);

        return contract.map(Contract::mapToDto);
    }
    public Contract saveContract(Contract contract) {

        return contractRepository.save(contract);
    }

    public Contract saveContractwithcheck(Contract contract,UUID devisId) {
        Optional<Devis> devisOptional = devisRepository.findById(devisId);
        if (devisOptional.isPresent()) {
            Devis devis = devisOptional.get();
            if (devis.getDevisStatus() == DevisStatus.ACCEPTED) {
                contract.setDevis(devis);

                if (isValidContractDates(contract)) {
                    return contractRepository.save(contract);
                } else {
                    throw new NoSuchElementException("Contract date must be before end date");
                }

//            return contractRepository.save(contract);
            }else {
            throw new NoSuchElementException("Cannot save contract for Devis with status: " + devis.getDevisStatus());
            }
        }else {
        throw new NoSuchElementException("Devis with ID " + devisId + " not found");
            }

    }
    private boolean isValidContractDates(Contract contract) {
        Date today = new Date();
        Date contractDate = contract.getContractDate();

        return today.compareTo(contractDate) <= 0 && contract.getContractDate().before(contract.getEndDate());
    }

    public void archiveContract(UUID contractId) {
        Optional<Contract> optionalContract = contractRepository.findById(contractId);

        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();

            if (!contract.isArchived()) {
                contract.setArchived(true);
                contractRepository.save(contract);
            }else {
                throw new NoSuchElementException("Contract with ID " + contractId + " is already archived");
            }
        } else {
            throw new NoSuchElementException("Contract with ID " + contractId + " not found");
        }
    }


}
