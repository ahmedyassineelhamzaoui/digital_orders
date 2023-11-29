package com.app.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.app.models.Devis;

public interface DevisService {

	List<Devis> getAllDevis();
	
	ResponseEntity<Map<String,Object>> addDevis(Devis devis);
	
	Devis updateDevis(Devis devis);
	
	void delete(UUID id);
	
	ResponseEntity<Map<String,Object>> getDevisById(UUID id);
}
