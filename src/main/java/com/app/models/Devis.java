package com.app.models;

import java.util.List;
import java.util.UUID;

import com.app.dto.DevisDTO;
import com.app.models.enums.DevisStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="devis")
@Builder
public class Devis {

	@Id
    @GeneratedValue(generator = "uuid2")
	private UUID id;
	
	private String Terms;
	
	private DevisStatus devisStatus;
	
    @JsonManagedReference
    @OneToMany(mappedBy = "devis" ,fetch = FetchType.LAZY)
	private List<Demande> demandes;
    
	public DevisDTO DevisToDTO(){
		return DevisDTO.builder()
				.devisStatus(devisStatus)
				.Terms(Terms)
				.demandes(demandes)
				.build();
	}
}
