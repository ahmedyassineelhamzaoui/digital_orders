package com.app.services.impl;

import com.app.dto.DemandeDTO;
import com.app.models.Demande;
import com.app.models.Equipment;
import com.app.models.User;
import com.app.models.enums.DemandeStatus;
import com.app.repositories.DemandeRepository;
import com.app.services.DemandeService;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

//import static com.app.dto.DemandeDTO.mapDemandetoDto;

@Service
public class DemandeServiceImpl implements DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private EquipmentServiceImpl equipmentServiceImpl;
    @Override
    public ResponseEntity<Map<String,Object>> createDemand(Demande demande) {
        Map<String,Object> response = new HashMap<String, Object>();
        User user = userServiceImpl.findUser(demande.getUser().getId()).orElseThrow(()->new NoSuchElementException("this user doesn't exist"));
        Equipment equipment = equipmentServiceImpl.getEquipmentById(demande.getEquipment().getId()).orElseThrow(()->new NoSuchElementException("this equipment doesn't exist"));
        List<Demande> demandeList = allreadyReserved(demande.getEquipment().getId() , demande.getStartDate() ,demande.getEndDate());
        if(!demandeList.isEmpty()){
            response.put("status", "error");
            response.put("message", "this Equipment is already reserved");
            response.put("damande", demandeList.stream().map(demande1 -> demande1.mapToDemandeDTO2() ));
            return ResponseEntity.badRequest().body(response);
        }
        demande.setDemandeStatus(DemandeStatus.PENDING);
        demande.setEquipment(equipment);
        demande.setUser(user);
        DemandeDTO demandeDTO =demandeRepository.save(demande).mapToDemandeDTO();
        response.put("status", "success");
        response.put("message", "your demande is created successfuly ");
        response.put("demande",demandeDTO);
        return ResponseEntity.ok(response);

    }

    private List<Demande> allreadyReserved(UUID equipmentnId, Date startDate, Date endDate) {
        return demandeRepository.getRentedEquipment(equipmentnId,startDate , endDate , DemandeStatus.ACCEPTED);
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
    public void updateDemande(UUID id, Demande updatedDemande) {
        Optional<Demande> existingDemande = demandeRepository.findById(id);
        if (existingDemande.isEmpty()) {
            demandeRepository.save(existingDemande.get());
        }
    }

    @Override
    public Optional<Demande> getDemandeById(UUID id) {
        return demandeRepository.findById(id);
    }
}
