package com.app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.app.models.Equipment;

public interface EquipmentService {

	List<Equipment> getAllEquipment();

    Optional<Equipment> getEquipmentById(UUID equipmentId);

    Equipment addEquipment(Equipment equipment);

    Equipment updateEquipment(UUID equipmentId, Equipment updatedEquipment);

    void deleteEquipment(UUID equipmentId);
}
