package com.app.services;

import com.app.dto.DemandeDTO;
import com.app.models.Demande;
import com.app.models.enums.DemandeStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface DemandeService {


//    public Demande createDemand(DemandeDTO demandeDto);
    public ResponseEntity<Map<String,Object>> createDemand(Demande demande);
    public List<Demande> getAllDemandes();
    public ResponseEntity<Map<String,Object>> deleteDemande(UUID id);
    public Demande updateDemande(UUID demandId , Demande demande);
    public Optional<Demande> getDemandeById(UUID id);
    public ResponseEntity<Map<String,String>> updateDemandeStatus(UUID demandeId , DemandeStatus demandeStatus);

}
