package com.app.services.impl;

import com.app.dto.DemandeDTO;
import com.app.models.Demande;
import com.app.models.Equipment;
import com.app.models.User;
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
//        User user = userServiceImpl.findUser(demande.getUser().getId()).orElseThrow(()->new NoSuchElementException("this user doesn't exist"));
//        Equipment equipment = equipmentServiceImpl.getEquipmentById(demande.getEquipment().getId()).orElseThrow(()->new RuntimeException("this equipment doesn't exist"));
//        if(!allreadyReserved(demande.getEquipment().getId() , demande.getStartDate() ,demande.getEndDate()).isEmpty()){
//            response.put("status", "error");
//            response.put("message", "this Equipment is already reserved");
//            return ResponseEntity.badRequest().body(response);
//        }
        DemandeDTO demandeDTO =demandeRepository.save(demande).mapToDemandeDTO();
        response.put("status", "success");
        response.put("message", "your demande is created successfuly ");
        response.put("demande",demandeDTO);
        return ResponseEntity.ok(response);


//        User user = equipmentServiceImpl.findUserByName(demandeDto.getUser());
//        Demande demande = Demande.builder()
//                .demandeStatus(demandeRepository.findByStatus())
//                .user(user)
//                .equipment(equipment)
//                .startDate(startDate)
//                .endDate(endDate)
//                .demandeCost(demandeCost).build();
//        return demandeRepository.save(demande);
//        return null;
    }

    private List<Demande> allreadyReserved(UUID equipmentnId, Date startDate, Date endDate) {
        return null;
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
        return demandeRepository.findById(id);
    }
}
