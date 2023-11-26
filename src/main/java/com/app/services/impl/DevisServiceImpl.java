package com.app.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Devis;
import com.app.repositories.DevisRepository;
import com.app.services.DevisService;

@Service
public class DevisServiceImpl implements DevisService {

	@Autowired
	private DevisRepository devisRepository;

	@Override
	public List<Devis> getAllDevis() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Devis addDevis(Devis devis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Devis updateDevis(Devis devis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Devis> getDevisById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	

}
