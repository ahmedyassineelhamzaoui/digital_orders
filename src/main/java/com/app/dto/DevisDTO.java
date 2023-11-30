package com.app.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.app.models.Demande;
import com.app.models.Devis;
import com.app.models.enums.DevisStatus;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
//	public Devis MapToDevis() {
//		return Devis.builder()
//				.devisStatus(devisStatus)
//				.Terms(Terms)
//				.demandes(demandes.stream().map(DemandeDTO::mapToDemandeEntity).collect(Collectors.toList()))
//				.build();
//	}
}
