package com.app.services.impl;

import com.app.dto.DemandeDTO;
import com.app.models.Demande;
import com.app.models.User;
import com.app.repositories.DemandeRepository;
import com.app.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DemandeServiceImpl implements DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;
    @Override
    public DemandeDTO createDemand(DemandeDTO demandeDto) {
        User user = demandeRepository.getUserByName(demandeDto.getUser());
//        return demandeRepository.save(demande);
//        Demande demande = Demande.builder().demandeStatus().build();
        return demandeDto;
    }

    @Override
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    @Override
    public void deleteDemande(UUID id) {
            demandeRepository.deleteById(id);
    }

    @Override
    public Demande updateDemande(UUID demandId, Demande demande) {
        return null;
    }

    @Override
    public Optional<Demande> getDemandeById(UUID id) {
        return Optional.empty();
    }
}
