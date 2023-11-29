package com.app.services;

import com.app.dto.DemandeDTO;
import com.app.models.Demande;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface DemandeService {


//    public Demande createDemand(DemandeDTO demandeDto);
    public ResponseEntity<Map<String,Object>> createDemand(Demande demande);
    public List<Demande> getAllDemandes();
    public void deleteDemande(UUID id);
    public Demande updateDemande(UUID demandId , Demande demande);
    public Optional<Demande> getDemandeById(UUID id);


}
