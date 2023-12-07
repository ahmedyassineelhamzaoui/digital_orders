package com.app.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.app.models.Contract;
import com.app.utils.ContractGenerator;
import com.app.utils.DevisGenerator;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		Map<String,Object> response = new HashMap<String, Object>();
		Optional<Devis> devis = devisService.getDevisById(id);
		if(devis == null) {
			response.put("status", "error");
			response.put("message","devis not found");
			return ResponseEntity.badRequest().body(response);
		}
		response.put("status", "success");
		response.put("devis", devis.get());
		return ResponseEntity.ok(response);
	}
	@PutMapping("/devis/{id}")
	public ResponseEntity<Map<String,Object>> editDevisStatus(@PathVariable UUID id,@RequestParam(value = "status", required = false)  String status){
		if (status == null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "error");
	        response.put("message", "Missing 'status' parameter in form data");
	        return ResponseEntity.badRequest().body(response);
	    }
		return devisService.updateDevis(id,status);
	}


	@GetMapping("/exportdevis/{id}")
	public void generatePdfFile(@PathVariable UUID id, HttpServletResponse response) throws DocumentException, IOException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		DevisGenerator generator = new DevisGenerator();

		Optional<Devis> devis = devisService.getDevisById(id);
		if(devis.isPresent()) {
			generator.generate(devis.get(),response 	);
		}



	}
}
