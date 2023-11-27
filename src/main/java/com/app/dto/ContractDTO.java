package com.app.dto;

import com.app.models.Contract;
import com.app.models.Devis;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public class ContractDTO {


    @NotNull(message="Contract date is required")
    private Date contractDate;

    @NotNull(message="End date is required")
    private Date endDate;

    private boolean isArchived;

//    @NotNull(message="devis is required")
    private Devis devis;

    public Contract mapToEntity() {
        return Contract.builder()
                .contractDate(contractDate)
                .endDate(endDate)
                .build();
    }
}
