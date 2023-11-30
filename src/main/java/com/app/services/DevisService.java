package com.app.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.app.models.Devis;

public interface DevisService {

	List<Devis> getAllDevis();
	
	ResponseEntity<Map<String,Object>> addDevis(Devis devis);
	
	ResponseEntity<Map<String,Object>> updateDevis(UUID id,String status);
	
	void delete(UUID id);
	
	Optional<Devis> getDevisById(UUID id);
}
