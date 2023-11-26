package com.app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.app.models.Devis;

public interface DevisService {

	List<Devis> getAllDevis();
	
	Devis addDevis(Devis devis);
	
	Devis updateDevis(Devis devis);
	
	void delete(UUID id);
	
	Optional<Devis> getDevisById(UUID id);
}
