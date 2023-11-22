package com.app.models;


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
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name="equipments_demande")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDemande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message ="equipment is required")
	@ManyToOne
	@JoinColumn(name="equipment_id")
	private Equipment equipment;
	
	@NotNull(message ="demande is required")
	@ManyToOne
	@JoinColumn(name ="demande_id")
	private Demande demande;
	
	@NotNull(message ="start date is required")
	@Future(message = "Start date must be in the future")
	private Date startDate;
	
	@Future(message = "End date must be in the future")
	private Date endDate;
	
	@NotNull(message="price is required")
	private Double equipmentCost;
}
