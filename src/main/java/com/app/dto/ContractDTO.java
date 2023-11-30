package com.app.dto;

import com.app.models.Contract;
import com.app.models.Devis;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {


    @NotNull(message="Contract date is required")
    private Date contractDate;

    @NotNull(message="End date is required")
    private Date endDate;

    private boolean isArchived;

//    @NotNull(message="devis is required")
    private DevisDTO devis;

    public Contract mapToEntity() {
        return Contract.builder()
                .contractDate(contractDate)
                .endDate(endDate)
                .devis(devis.MapToDevis())
                .build();
    }
}
