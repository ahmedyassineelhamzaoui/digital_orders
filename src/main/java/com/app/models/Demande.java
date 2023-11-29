package com.app.models;

import java.util.Date;
import java.util.UUID;

import com.app.dto.DemandeDTO;
import com.app.dto.EquipmentDTO;
import com.app.models.enums.DemandeStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name="demandes")
@Builder
public class Demande {

	@Id
    @GeneratedValue(generator = "uuid2")
	private UUID id;
	
    @NotNull(message ="Status is required")
	private DemandeStatus demandeStatus;
    
    @NotNull(message ="user is required")
    @ManyToOne
    @JoinColumn(name ="user_id")
	private User user;
    
    @NotNull(message ="equipment to be rented is required")
    @ManyToOne
    @JoinColumn(name="equipment_id")
    private Equipment equipment;
    
    @NotNull(message ="start date is required")
	@Future(message = "Start date must be in the future")
	private Date startDate;
	
	@Future(message = "End date must be in the future")
	@NotNull(message ="End date is required")
	private Date endDate;
	
	private Double demandeCost;
	
	@ManyToOne
	@JoinColumn(name = "devis_id")
	private Devis devis;

	public DemandeDTO mapToDemandeDTO(){
		return DemandeDTO.builder()
				.demandeStatus(demandeStatus)
				.user(user.maptoDto())
				.equipment(equipment.toDto())
				.startDate(startDate)
				.endDate(endDate)
				.demandeCost(demandeCost)
				.build();
	}
}
