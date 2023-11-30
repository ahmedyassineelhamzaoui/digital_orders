package com.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.app.dto.DemandeRequstDTO;
import com.app.models.enums.DemandeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.app.models.Category;
import com.app.models.Demande;
import com.app.services.impl.CategoryServiceImpl;
import com.app.services.impl.DemandeServiceImpl;
import jakarta.validation.Valid;
import com.app.dto.DemandeDTO;
import com.app.models.Demande;
import com.app.services.impl.DemandeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DemandeController {
	
        @Autowired
        private DemandeServiceImpl demandeServiceImpl ;

        @PostMapping("/demande")
        public ResponseEntity<Map<String,Object>>  createDemand(@RequestBody DemandeRequstDTO demande){
            return demandeServiceImpl.createDemand(demande);
        }
        @GetMapping("/demandes")
        public List<DemandeDTO> getAllDemandes(){
                List<Demande> demandeList =demandeServiceImpl.getAllDemandes();
               return demandeList.stream().map(
                        demande -> demande.mapToDemandeDTO()
                ).collect(Collectors.toList());
        }
        @DeleteMapping("/deleteDemande/{id}")
        public ResponseEntity<Map<String,Object>> deleteDemande(@PathVariable UUID id){
            return demandeServiceImpl.deleteDemande(id);
        }
        @PutMapping("/updateDemandeStatus/{id}")
        public ResponseEntity<Map<String , Object>> updateDemande(@PathVariable UUID id , @RequestBody DemandeDTO demande){
            return demandeServiceImpl.updateDemande2(id , demande);
        }
        @GetMapping("/getDemande/{id}")
        public DemandeDTO getDemandeById(@PathVariable UUID id){
            return demandeServiceImpl.getDemandeById(id).get().mapToDemandeDTO();
        }
}
