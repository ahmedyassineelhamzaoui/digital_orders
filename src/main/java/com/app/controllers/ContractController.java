package com.app.controllers;

import com.app.models.Contract;
import com.app.services.impl.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ContractController {
    @Autowired
    public ContractServiceImpl contractServiceImpl;

    @GetMapping("/contracts")
    public ResponseEntity<List<Contract>> getAllContracts(){
           List<Contract> contracts= contractServiceImpl.getAllContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("/contracts/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable UUID id) {

        Optional<Contract> contract = contractServiceImpl.getContractById(id);

        if (contract.isPresent()) {
            return ResponseEntity.ok(contract.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/save")
    public ResponseEntity<Contract> saveContract(@RequestBody Contract contract) {
        Contract savedContract = contractServiceImpl.saveContract(contract);
        return new ResponseEntity<>(savedContract, HttpStatus.CREATED);
    }

    @PostMapping("/archive-contract/{contractId}")
    public ResponseEntity<String> archiveContract(@PathVariable UUID contractId) {
        contractServiceImpl.archiveContract(contractId);
        return new ResponseEntity<>("Contract archived successfully", HttpStatus.OK);
    }
}
