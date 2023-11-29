package com.app.services;

import com.app.dto.DemandeDTO;
import com.app.models.Demande;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DemandeService {


    public DemandeDTO createDemand(DemandeDTO demandeDto);
    public List<Demande> getAllDemandes();
    public void deleteDemande(UUID id);
    public Demande updateDemande(UUID demandId , Demande demande);
    public Optional<Demande> getDemandeById(UUID id);


}
