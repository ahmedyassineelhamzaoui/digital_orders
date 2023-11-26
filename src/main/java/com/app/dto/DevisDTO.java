package com.app.dto;

import java.util.List;
import com.app.models.Demande;
import com.app.models.enums.DevisStatus;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

public class DevisDTO {

	
	@NotNull(message="terms is required")
	private String Terms;
	
	@NotNull(message="devis status is required")
	private DevisStatus devisStatus;
	
	@OneToMany(mappedBy = "devis")
	private List<Demande> demandes;
}
