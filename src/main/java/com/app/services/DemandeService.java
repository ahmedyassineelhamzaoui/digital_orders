package com.app.services;

import com.app.dto.DemandeDTO;
import com.app.dto.DemandeRequstDTO;
import com.app.models.Demande;
import com.app.models.enums.DemandeStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface DemandeService {


//    public Demande createDemand(DemandeDTO demandeDto);
    public ResponseEntity<Map<String,Object>> createDemand(DemandeRequstDTO demandeRequstDTO);
    public List<Demande> getAllDemandes();
    public ResponseEntity<Map<String,Object>> deleteDemande(UUID id);
    public void updateDemande(UUID demandId , Demande demande);
    public Optional<Demande> getDemandeById(UUID id);
    public ResponseEntity<Map<String, Object>> updateDemande2(UUID demandeId, DemandeDTO demande);


}
