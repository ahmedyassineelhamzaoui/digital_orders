package com.app.services.impl;

import com.app.dto.ContractDTO;
import com.app.models.Contract;
import com.app.repositories.ContractRepository;
import com.app.services.ContractService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    public ContractRepository contractRepository;

    @Override
    public List<Contract> getAllContracts(){return contractRepository.findAll();}

    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }



}
