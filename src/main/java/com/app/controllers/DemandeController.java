package com.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.Category;
import com.app.models.Demande;
import com.app.services.impl.CategoryServiceImpl;
import com.app.services.impl.DemandeServiceImpl;

import jakarta.validation.Valid;
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
