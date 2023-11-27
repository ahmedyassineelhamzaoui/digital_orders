package com.app.dto;

import com.app.models.Equipment;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class DemandeDTO {

    private String demandeStatus;
    private String user;
    private Equipment equipment ;
    private Date startDate;
    private Date endDate ;
    private Double demandeCost;

    public DemandeDTO(String demandeStatus, String user, Equipment equipment, Date startDate, Date endDate, Double demandeCost) {
        this.demandeStatus = demandeStatus;
        this.user = user;
        this.equipment = equipment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.demandeCost = demandeCost;
    }

}
