package com.app.dto;

import com.app.models.Demande;
import com.app.models.Equipment;
import com.app.models.User;
import com.app.models.enums.DemandeStatus;
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

    private DemandeStatus demandeStatus;
    private UserDTO user;
    private EquipmentDTO equipment ;
    private Date startDate;
    private Date endDate ;
	public Demande mapToDemandeEntity(){
		return Demande.builder().demandeStatus(demandeStatus)
				.user(user.mapToEntity())
				.equipment(equipment.toEntity())
				.startDate(startDate)
				.endDate(endDate)
				.build();
	}

}
