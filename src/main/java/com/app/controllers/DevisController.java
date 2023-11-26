package com.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.DevisDTO;
import com.app.services.impl.DevisServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class DevisController {

	@Autowired
	private DevisServiceImpl devisService;
	
	@PostMapping("/devis")
	public ResponseEntity<Map<String,Object>> addDevis(@Valid @RequestBody DevisDTO devisDto){
		
		return null;
	}
}
