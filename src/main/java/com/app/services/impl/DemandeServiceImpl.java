package com.app.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Demande;
import com.app.repositories.DemandeRepository;

@Service
public class DemandeServiceImpl {

	@Autowired
	private DemandeRepository demandeRepository;
	
	public Demande addDemande(Demande demande) {
		return demandeRepository.save(demande);
	}
	public List<Demande> getAllDemandes(){
		return demandeRepository.findAll();
	}
	public Optional<Demande> getOneDemandeById(UUID id){
		return demandeRepository.findById(id);
	}
	public Demande updateDemande(UUID id,Demande demande){
		return demandeRepository.save(demande);
	}
}
