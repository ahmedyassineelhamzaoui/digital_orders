package com.app.controllers;

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

import static com.app.dto.DemandeDTO.mapDemandetoDto;

@RestController
@RequestMapping("/api")
public class DemandeController {
        @Autowired
        private DemandeServiceImpl demandeServiceImpl ;

//        @PostMapping("/demande")
//        public Demande createDemand(@RequestBody DemandeDTO demandeDto){
//            return demandeServiceImpl.createDemand(demandeDto);
//        }

        @PostMapping("/demande")
        public ResponseEntity<Map<String,Object>> createDemand(@RequestBody Demande demande){
            return demandeServiceImpl.createDemand(demande);
        }
        @GetMapping("/demandes")
        public List<DemandeDTO> getAllDemandes(){
                List<Demande> demandeList =demandeServiceImpl.getAllDemandes();
               return demandeList.stream().map(
                        demande -> mapDemandetoDto(demande)
                ).collect(Collectors.toList());
        }

        @DeleteMapping("/deleteDemande/{id}")
        public void deleteDemande(@PathVariable UUID id){
            demandeServiceImpl.deleteDemande(id);
        }

        @PutMapping("/updateDemande/{id}")
        public DemandeDTO updateDemande(@PathVariable UUID demandeId , @RequestBody DemandeDTO demandeDTO){
            return null;
        }

}
