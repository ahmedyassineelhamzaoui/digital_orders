package com.app.services.impl;

import com.app.models.Contract;
import com.app.repositories.ContractRepository;
import com.app.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    public ContractRepository contractRepository;

    @Override
    public List<Contract> getAllContracts(){return contractRepository.findAll();}
}
