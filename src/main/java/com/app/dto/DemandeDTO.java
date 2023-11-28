package com.app.dto;

import com.app.models.Demande;
import com.app.models.Equipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDTO {

    private String demandeStatus;
    private String user;
    private Equipment equipment ;
    private Date startDate;
    private Date endDate ;
    private Double demandeCost;

//    public DemandeDTO(String demandeStatus, String user, Equipment equipment, Date startDate, Date endDate, Double demandeCost) {
//        this.demandeStatus = demandeStatus;
//        this.user = user;
//        this.equipment = equipment;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.demandeCost = demandeCost;
//    }
    public static DemandeDTO mapDemandetoDto(Demande demande) {
		return DemandeDTO.builder()
				.demandeStatus(demande.getDemandeStatus().name())
				.user(demande.getUser().getName())
				.equipment(demande.getEquipment())
				.startDate(demande.getStartDate())
				.endDate(demande.getEndDate())
				.demandeCost(demande.getDemandeCost()).build();
	}

}
