package com.app.dto;

import java.util.List;
import com.app.models.Demande;
import com.app.models.Devis;
import com.app.models.enums.DevisStatus;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public class DevisDTO {

	
	@NotNull(message="terms is required")
	private String Terms;
	
	@NotNull(message="devis status is required")
	private DevisStatus devisStatus;
	
	@OneToMany(mappedBy = "devis")
	private List<Demande> demandes;
	
	public Devis MapToDevis() {
		return Devis.builder()
				.devisStatus(devisStatus)
				.Terms(Terms)
				.demandes(demandes)
				.build();
	}
}
