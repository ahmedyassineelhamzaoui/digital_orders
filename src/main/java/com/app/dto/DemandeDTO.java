package com.app.dto;

import com.app.models.Demande;
import com.app.models.Equipment;
import com.app.models.User;
import com.app.models.enums.DemandeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDTO {

	private UUID id;
    private DemandeStatus demandeStatus;
    private String user;
    private EquipmentResponseDTO equipment ;
    private Date startDate;
    private Date endDate ;
	public Demande mapToDemandeEntity(){
		return Demande.builder()
                .demandeStatus(demandeStatus)
				.equipment(equipment.toEntity())
				.startDate(startDate)
				.endDate(endDate)
				.build();
	}

}
