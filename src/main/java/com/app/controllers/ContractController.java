package com.app.controllers;

import com.app.models.Contract;
import com.app.services.impl.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @PostMapping("/save")
    public ResponseEntity<Contract> saveContract(@RequestBody Contract contract) {
        Contract savedContract = contractServiceImpl.saveContract(contract);
        return new ResponseEntity<>(savedContract, HttpStatus.CREATED);
    }


}
