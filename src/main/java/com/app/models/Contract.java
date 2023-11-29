package com.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="contracts")
public class Contract {

	@Id
	@GeneratedValue(generator = "uuid2")
	private UUID id;
	
	@NotNull(message="Contract date is required")
	private Date contractDate;
	
	@NotNull(message="End date is required")
	private Date endDate;

	@Builder.Default
	private boolean isArchived= false;
	
	@OneToOne
	@JoinColumn(name="devis_id")
	private Devis devis;


}
