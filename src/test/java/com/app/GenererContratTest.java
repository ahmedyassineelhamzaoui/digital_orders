package com.app;

import com.app.models.Contract;
import com.app.models.Devis;
import com.app.models.enums.DevisStatus;
import com.app.repositories.ContractRepository;
import com.app.repositories.DevisRepository;
import com.app.services.impl.ContractServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class GenererContratTest {
    @MockBean
    private ContractRepository contractRepository;

    @MockBean
    private DevisRepository devisRepository;

    @Autowired
    private ContractServiceImpl contractServiceImpl;

    @Test
    void CreateValidContract(){
        UUID devisId = UUID.randomUUID();

        Devis devis = new Devis();
        devis.setDevisStatus(DevisStatus.ACCEPTED);

        Contract contract = new Contract();
        contract.setContractDate(new Date());
        contract.setEndDate(new Date());


        when(devisRepository.findById(devisId)).thenReturn(Optional.of(devis));
        when(contractRepository.save(contract)).thenReturn(contract);


        Contract savedContract = contractServiceImpl.saveContractwithcheck(contract, devisId);


        assertNotNull(savedContract);


    }
}
