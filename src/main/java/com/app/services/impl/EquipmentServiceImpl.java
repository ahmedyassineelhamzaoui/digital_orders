package com.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Equipment;
import com.app.repositories.EquipmentRepository;
import com.app.services.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Override
	public List<Equipment> getAllEquipment() {
		return equipmentRepository.findAll();
	}

	@Override
	public Optional<Equipment> getEquipmentById(Long equipmentId) {
		return equipmentRepository.findById(equipmentId);
	}

	@Override
	public Equipment addEquipment(Equipment equipment) {
		return equipmentRepository.save(equipment);
	}

	@Override
	public Equipment updateEquipment(Long equipmentId, Equipment updatedEquipment) {
		Optional<Equipment> optionalEquipment = getEquipmentById(equipmentId);
		if(optionalEquipment.isEmpty()) {
			return null;
		}
	    Equipment existingEquipment = optionalEquipment.get();

	    if (updatedEquipment.getName() != null) {
	        existingEquipment.setName(updatedEquipment.getName());
	    }

	    if (updatedEquipment.getRegistrationNumber() != null) {
	        existingEquipment.setRegistrationNumber(updatedEquipment.getRegistrationNumber());
	    }

	    if (updatedEquipment.getRentalPrice() != null) {
	        existingEquipment.setRentalPrice(updatedEquipment.getRentalPrice());
	    }
	    existingEquipment.setRegistrationNumber(updatedEquipment.getRegistrationNumber());
	    existingEquipment.setRentalPrice(updatedEquipment.getRentalPrice());
	    existingEquipment.setName(updatedEquipment.getName());
	    existingEquipment.setEquipmentStatus(updatedEquipment.getEquipmentStatus());
	    existingEquipment.setCategory(updatedEquipment.getCategory());
	    return equipmentRepository.save(existingEquipment);

	}
	public List<Equipment> searchEquipment(String keyword ) {
        return equipmentRepository.searchByGivenType(keyword, keyword);
    }
	@Override
	public void deleteEquipment(Long equipmentId) {
		 equipmentRepository.deleteById(equipmentId);
	}
}
