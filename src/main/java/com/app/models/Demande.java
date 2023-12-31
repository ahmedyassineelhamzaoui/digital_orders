package com.app.models;

import java.util.Date;
import java.util.UUID;

import com.app.dto.DemandeDTO;
import com.app.dto.DemandeDTO2;
import com.app.dto.DemandeRequstDTO;
import com.app.dto.EquipmentDTO;
import com.app.models.enums.DemandeStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="demandes")
public class Demande {

	@Id
  @GeneratedValue(generator = "uuid2")
	private UUID id;
	
  @NotNull(message ="Status is required")
	private DemandeStatus demandeStatus;
    

    @NotNull(message ="user is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
	private User user;
    
    @NotNull(message ="equipment to be rented is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="equipment_id")
    private Equipment equipment;

    
  @NotNull(message ="start date is required")
	@Future(message = "Start date must be in the future")
	private Date startDate;
	
	@Future(message = "End date must be in the future")
	@NotNull(message ="End date is required")
	private Date endDate;
	
	private Double demandeCost;
	
  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
  @JoinColumn(name = "devis_id" )

  private Devis devis;


	public DemandeDTO mapToDemandeDTO(){
		return DemandeDTO.builder()
                .id(id)
				.demandeStatus(demandeStatus)
				.user(user.getName())
				.equipment(equipment.toDto2())
				.demandeCost(demandeCost)
				.startDate(startDate)
				.endDate(endDate)
				.build();
	}
	public DemandeDTO2 mapToDemandeDTO2(){
		return DemandeDTO2.builder()
				.startDate(startDate)
				.endDate(endDate)
				.build();
	}

    public DemandeRequstDTO mapToDemandeRequstDTO(){
        return DemandeRequstDTO.builder()
                .userId(user.getId())
                .equipmentId(equipment.getId())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
