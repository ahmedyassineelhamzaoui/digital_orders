package com.app.controllers;

import com.app.models.Contract;
import com.app.services.impl.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
