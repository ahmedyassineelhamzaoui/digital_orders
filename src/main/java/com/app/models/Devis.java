package com.app.models;

import com.app.models.enums.DevisStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="devis")
public class Devis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="terms is required")
	private String Terms;
	
	@NotNull(message="devis status is required")
	private DevisStatus devisStatus;
	
	@ManyToOne
	@JoinColumn(name="demande_id")
	private Demande demande;
		
}
