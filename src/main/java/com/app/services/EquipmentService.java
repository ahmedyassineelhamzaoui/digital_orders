package com.app.services;

import java.util.List;
import java.util.Optional;

import com.app.models.Equipment;

public interface EquipmentService {

	List<Equipment> getAllEquipment();

    Optional<Equipment> getEquipmentById(Long equipmentId);

    Equipment addEquipment(Equipment equipment);

    Equipment updateEquipment(Long equipmentId, Equipment updatedEquipment);

    void deleteEquipment(Long equipmentId);
}
