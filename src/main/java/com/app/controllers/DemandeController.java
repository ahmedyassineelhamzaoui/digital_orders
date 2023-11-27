package com.app.controllers;

import com.app.dto.DemandeDTO;
import com.app.models.Demande;
import com.app.services.impl.DemandeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DemandeController {
        @Autowired
        private DemandeServiceImpl demandeServiceImpl ;

        @PostMapping("/demande")
        public DemandeDTO createDemand(@RequestBody DemandeDTO demande){
            return demandeServiceImpl.createDemand(demande);
        }


        @GetMapping("/demandes")
        public List<DemandeDTO> getAllDemandes(){
                List<Demande> demandeList =demandeServiceImpl.getAllDemandes();
               return demandeList.stream().map(
                        demande -> new DemandeDTO(demande.getDemandeStatus().name(),
                                demande.getUser().getName(),
                                demande.getEquipment(),
                                demande.getStartDate(),
                                demande.getEndDate(),
                                demande.getDemandeCost())
                ).collect(Collectors.toList());
        }

        @DeleteMapping("/deleteDemande/{id}")
        public void deleteDemande(@PathVariable UUID id){
            demandeServiceImpl.deleteDemande(id);
        }

}
