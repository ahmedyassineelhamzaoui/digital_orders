package com.app.models;

import com.app.models.enums.DemandeStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demande {

	private Long id;
	private DemandeStatus demandeStatus;
	
}
