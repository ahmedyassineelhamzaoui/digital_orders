package com.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.Category;
import com.app.models.Demande;
import com.app.services.impl.CategoryServiceImpl;
import com.app.services.impl.DemandeServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class DemandeController {

	@Autowired
	private DemandeServiceImpl demandeServiceImpl; 
	
	@GetMapping("/demandes")
	public ResponseEntity<Map<String,Object>> getAllDemandes(){
		Map<String,Object> response = new HashMap<String, Object>();
		List<Demande> demandes = demandeServiceImpl.getAllDemandes();
		response.put("status", "success");
        response.put("message", "category added successfuly");
        response.put("Demandes",demandes );
        return ResponseEntity.ok(response);
	}
	@PostMapping("/demandes")
	public ResponseEntity<Map<String,Object>> addCategory(@RequestBody Demande demande){
		Map<String,Object> response = new HashMap<String, Object>();
	
	    try {
		    Demande demandeToAdd = demandeServiceImpl.addDemande(demande);
            response.put("status", "success");
            response.put("message", "category added successfuly");
            response.put("Demande", demandeToAdd);
            return ResponseEntity.ok(response);
	    }catch(DataIntegrityViolationException e) {
	    	response.put("status", "error");
	    	response.put("message", e.getMessage());
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	    }
	}
}
