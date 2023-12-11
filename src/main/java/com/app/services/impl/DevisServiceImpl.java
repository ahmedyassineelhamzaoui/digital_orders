package com.app.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.models.Demande;
import com.app.models.Devis;
import com.app.models.User;
import com.app.models.enums.DemandeStatus;
import com.app.models.enums.DevisStatus;
import com.app.repositories.DevisRepository;
import com.app.services.DevisService;

@Service
public class DevisServiceImpl implements DevisService {

	@Autowired
	private DevisRepository devisRepository;

	@Autowired 
	private DemandeServiceImpl demandeServiceImpl;
	
	@Override
	public List<Devis> getAllDevis() {
		return devisRepository.findAll();
	}

	@Override
	public ResponseEntity<Map<String,Object>> addDevis(Devis devis) {
		Map<String,Object> response = new HashMap<String, Object>();
		List<Demande> mydemandes = new ArrayList<>();
		for(Demande d:devis.getDemandes()) {
			Demande demandeToFind = demandeServiceImpl.getDemandeById(d.getId()).get();
			if(demandeToFind == null) {
				response.put("status", "error");
				response.put("message", "demande that has the id "+demandeToFind.getId()+" not exist");
		    	return ResponseEntity.badRequest().body(response);
			}
			if(demandeToFind.getDevis() != null) {
				response.put("status", "error");
				response.put("message", "demande that has the id "+demandeToFind.getId()+" already belong to a devis");
		    	return ResponseEntity.badRequest().body(response);
			}
			if(demandeToFind.getDemandeStatus() == DemandeStatus.ACCEPTED) {
			    if(demandeServiceImpl.getDemandeById(devis.getDemandes().get(0).getId()).get().getUser() != demandeToFind.getUser()) {				    
			    	response.put("status", "error");
					response.put("message","All demandes must for the same user");
					return ResponseEntity.badRequest().body(response);
			    }
			    mydemandes.add(demandeToFind);
			}else {
				response.put("status", "error");
				response.put("message","All Demandes status must be accepted ");
				return ResponseEntity.badRequest().body(response);
			}
        }
		Devis devisSaved = devisRepository.save(devis);
		for(Demande d:mydemandes) {
			Demande demande = demandeServiceImpl.getDemandeById(d.getId()).get();
			demande.setDemandeCost(demande.getEquipment().getRentalPrice());
			demandeServiceImpl.updateDemande2(d.getId(),demande.mapToDemandeDTO());
        	demande.setDevis(devisSaved);	
        	demandeServiceImpl.updateDemande(d.getId(), demande);
		}
		response.put("status", "success");
		response.put("message", "devis added successfuly");
    	return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Map<String,Object>> updateDevis(UUID id ,String status) {
		Map<String,Object> response = new HashMap<String, Object>();
		Optional<Devis> devisToFind = getDevisById(id);
		if(devisToFind.isEmpty()) {
			response.put("status", "error");
			response.put("message","devis width id:'"+id+"' not found");
			return ResponseEntity.badRequest().body(response);
		}
		DevisStatus devisStatus = DevisStatus.valueOf(status); 
		devisToFind.get().setDevisStatus(devisStatus);
		response.put("status", "success");
		response.put("message","status of devis has been updated successfuly");
		response.put("devis", devisToFind);
		return ResponseEntity.ok(response);
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Devis> getDevisById(UUID id) {
		Optional<Devis> devisToFind = devisRepository.findById(id);
		if(!devisToFind.isEmpty()) {
			return devisToFind;
		}
		return null;
	}
	
	

}
