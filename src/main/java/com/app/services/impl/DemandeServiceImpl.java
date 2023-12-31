package com.app.services.impl;

import com.app.dto.DemandeDTO;
import com.app.dto.DemandeRequstDTO;
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
    public ResponseEntity<Map<String,Object>> createDemand(DemandeRequstDTO demandeRequstDTO) {
        Map<String,Object> response = new HashMap<String, Object>();
        User user = userServiceImpl.findUser(demandeRequstDTO.getUserId()).orElseThrow(()->new NoSuchElementException("this user doesn't exist"));
        Equipment equipment = equipmentServiceImpl.getEquipmentById(demandeRequstDTO.getEquipmentId()).orElseThrow(()->new NoSuchElementException("this equipment doesn't exist"));
        List<Demande> demandeList = allreadyReserved(demandeRequstDTO.getEquipmentId() ,demandeRequstDTO.getStartDate() ,demandeRequstDTO.getEndDate());
        if(!demandeList.isEmpty()){
            response.put("status", "error");
            response.put("message", "this Equipment is already reserved");
            response.put("damande", demandeList.stream().map(demande1 -> demande1.mapToDemandeDTO2() ));
            return ResponseEntity.badRequest().body(response);
        }
        if(demandeRequstDTO.getStartDate().after(demandeRequstDTO.getEndDate())){
            response.put("status", "error");
            response.put("message", "Start Date shouldn't be after End Date");
            return ResponseEntity.badRequest().body(response);
        }
        Demande demande =demandeRequstDTO.mapDemandeReqtoEntity();
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
    public ResponseEntity<Map<String,Object>> deleteDemande(UUID id) {
        Demande demande = demandeRepository.findById(id).orElseThrow(()->new NoSuchElementException("there is no demnde with this id"));
        Map<String ,Object> response = new HashMap<>();
        response.put("Status" , "Ok");
        response.put("Message" , "Your demande has been deleted successfuly");
        demandeRepository.deleteById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public void updateDemande(UUID id, Demande updatedDemande) {
        Optional<Demande> existingDemande = demandeRepository.findById(id);
        existingDemande.ifPresent(demande -> demandeRepository.save(demande));
    }

    @Override
    public Optional<Demande> getDemandeById(UUID id) {
        return demandeRepository.findById(id);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateDemande2(UUID demandeId, DemandeDTO demandeDTO) {
        Map<String, Object> response = new HashMap<>();
        Demande oldDemande = getDemandeById(demandeId).orElseThrow(()->new NoSuchElementException("there is no demande with this id"));
        if(demandeDTO.getDemandeStatus() != null){
            oldDemande.setDemandeStatus(demandeDTO.getDemandeStatus());
        }
        if(demandeDTO.getEquipment() != null){
            Equipment equipment = equipmentServiceImpl.getEquipmentById(demandeDTO.getEquipment().getId()).orElseThrow(()->new NoSuchElementException("this equipment doesn't exist"));
            oldDemande.setEquipment(demandeDTO.getEquipment().toEntity());
        }
        if(demandeDTO.getDemandeStatus() != null){
            oldDemande.setDemandeStatus(demandeDTO.getDemandeStatus());
        }
        if(demandeDTO.getStartDate()!= null){
            List<Demande> demandeList = allreadyReserved(oldDemande.getEquipment().getId() , demandeDTO.getStartDate() ,oldDemande.getEndDate());
            if(!demandeList.isEmpty()){
                response.put("status", "error");
                response.put("message", "this Equipment is already reserved in this date");
                response.put("damande", demandeList.stream().map(demande1 -> demande1.mapToDemandeDTO2() ));
                return ResponseEntity.badRequest().body(response);
            }
            oldDemande.setStartDate(demandeDTO.getStartDate());
        }
        if(demandeDTO.getEndDate()!= null){
            List<Demande> demandeList = allreadyReserved(oldDemande.getEquipment().getId() , oldDemande.getStartDate() ,demandeDTO.getEndDate());
            if(!demandeList.isEmpty()){
                response.put("status", "error");
                response.put("message", "this Equipment is already reserved in this date");
                response.put("damande", demandeList.stream().map(demande1 -> demande1.mapToDemandeDTO2() ));
                return ResponseEntity.badRequest().body(response);
            }
            oldDemande.setEndDate(demandeDTO.getEndDate());
        }
        demandeRepository.save(oldDemande);
        response.put("status", "Ok");
        response.put("message", "Demande has breen Updated Successfuly");
        return ResponseEntity.ok(response);
//        return null;
    }
}
