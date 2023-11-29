package com.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.DevisDTO;
import com.app.models.Demande;
import com.app.models.Devis;
import com.app.services.DevisService;
import com.app.services.impl.DemandeServiceImpl;
import com.app.services.impl.DevisServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class DevisController {

	@Autowired
	private DevisServiceImpl devisService;
	@Autowired
	private DemandeServiceImpl demandeServiceImpl;
	
	@PostMapping("/devis")
    public ResponseEntity<Map<String, Object>> addDevis(@Valid @RequestBody DevisDTO devisDto) {
        return devisService.addDevis(devisDto.MapToDevis());
    }
	@GetMapping("/devis")
	public ResponseEntity<List<Devis>> getAllDevis(){
		List<Devis> deviss = devisService.getAllDevis();
		return ResponseEntity.ok(deviss);
	}
	@GetMapping("/devis/{id}")
	public ResponseEntity<Map<String,Object>> getDevisById(@PathVariable UUID id){
		return devisService.getDevisById(id);
	}
	
}
