package com.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CategoryDTO;
import com.app.dto.EquipmentDTO;
import com.app.models.Category;
import com.app.models.Equipment;
import com.app.models.enums.EquipmentStatus;
import com.app.services.impl.CategoryServiceImpl;
import com.app.services.impl.EquipmentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EquipmentController {

	@Autowired
	private EquipmentServiceImpl equipmentServiceImpl;
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping("/equipment")
	public ResponseEntity<Map<String,Object>> searchUserByQuerys(@RequestParam String givenType){
		Map<String,Object> response = new HashMap<String, Object>();
		List<Equipment> equipments = equipmentServiceImpl.searchEquipment(givenType);
		if(equipments.isEmpty()) {
			response.put("status","info");
	        response.put("message","no equipment found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
        response.put("status","success");
        response.put("equipments",equipments);
		return ResponseEntity.ok(response);

		
	}
	@PostMapping("/equipment")
	public ResponseEntity<Map<String,Object>> addEquipment(@Valid @RequestBody Equipment equipment,BindingResult bindingResult ){
		Map<String,Object> response = new HashMap<String, Object>();
		if(bindingResult.hasErrors()) {
			response.put("status", "error");
            response.put("message", "Validation failed");
            Map<String, String> errors = new HashMap<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("error number", 422);
            return ResponseEntity.badRequest().body(response);
			
		}
	    Category category = equipment.getCategory();
		 Optional<Category> optionalCategory = categoryServiceImpl.getCategoryById(category.getId());
		    if (optionalCategory.isEmpty()) {
		        response.put("status", "error");
		        response.put("message", "Category not found");
		        response.put("error number", 404);
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		    }
		try {
			Equipment createdEquipment = equipmentServiceImpl.addEquipment(equipment);
		    EquipmentDTO equipmentDTO = mapToDTO(createdEquipment);

			response.put("status", "success");
			response.put("message", "equipment created successfuly");
			response.put("Equipment",equipmentDTO);
			return ResponseEntity.ok(response);
		}catch(DataIntegrityViolationException e) {
			response.put("status", "error");
			response.put("message", "registration number already exist");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
		
	}
	
	
	
	@PutMapping("/equipment/{id}")
	public ResponseEntity<Map<String, Object>> updateEquipment(
	        @PathVariable Long id,
	        @RequestBody Equipment equipment) {
	    Map<String, Object> response = new HashMap<>();

	    Optional<Equipment> optionalEquipment = equipmentServiceImpl.getEquipmentById(id);
	    if (optionalEquipment.isEmpty()) {
	        response.put("status", "error");
	        response.put("message", "Equipment not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    Optional<Category> optionalCategory = categoryServiceImpl.getCategoryById(equipment.getCategory().getId());
	    if (optionalCategory.isEmpty()) {
	        response.put("status", "error");
	        response.put("message", "Category not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    try {
	        EquipmentStatus equipmentStatus = equipment.getEquipmentStatus();
	        equipment.setEquipmentStatus(equipmentStatus);
	    } catch (IllegalArgumentException e) {
	        response.put("status", "error");
	        response	.put("message", "Invalid equipment status");
	        return ResponseEntity.badRequest().body(response);
	    }
	    if(equipment.getRegistrationNumber().isEmpty()) {
	    	response.put("status", "error");
	        response.put("message", "please provide a registration number");
	        return ResponseEntity.badRequest().body(response);
	    }
	    try {
	        Equipment updatedEquipment = equipmentServiceImpl.updateEquipment(id, equipment);
	        response.put("status", "success");
	        response.put("message", "Equipment updated successfully");
	        response.put("Equipment", updatedEquipment);
	        return ResponseEntity.ok(response);
	    } catch (DataIntegrityViolationException e) {
	        response.put("status", "error");
	        response.put("message", "Equipment with this registration " + equipment.getRegistrationNumber() + " already exists");
	        return ResponseEntity.badRequest().body(response);
	    }
	}

	@DeleteMapping("/equipment/{id}")
	public ResponseEntity<Map<String,Object>> deleteEquipment(@PathVariable Long id){
		Map<String,Object> response = new HashMap<String, Object>();
	    Optional<Equipment> optionalEquipment = equipmentServiceImpl.getEquipmentById(id);
		if (optionalEquipment.isEmpty()) {
	        response.put("status", "error");
	        response.put("message", "Equipment that you want to delete not found");
	        return ResponseEntity.badRequest().body(response);
	    }	    
	    equipmentServiceImpl.deleteEquipment(id);

		response.put("status", "success");
		response.put("message", "equipment deleted successfuly");
		return ResponseEntity.ok(response);
	}
	@GetMapping("/equipments")
	public ResponseEntity<Map<String,Object>> getAllEquipments(){
		Map<String,Object> response = new HashMap<String, Object>();
		List<Equipment> equipments = equipmentServiceImpl.getAllEquipment();
		if(equipments.isEmpty()) {
			response.put("status", "info");
			response.put("message", "no equipment found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		response.put("status", "success");
		response.put("equipments",equipments );
		return ResponseEntity.ok(response);
	}

	private EquipmentDTO mapToDTO(Equipment equipment) {
	    EquipmentDTO equipmentDTO = new EquipmentDTO();
	    equipmentDTO.setId(equipment.getId());
	    equipmentDTO.setRegistrationNumber(equipment.getRegistrationNumber());
	    equipmentDTO.setRentalPrice(equipment.getRentalPrice());
	    equipmentDTO.setName(equipment.getName());

	    CategoryDTO categoryDTO = new CategoryDTO();
	    Category category = categoryServiceImpl.getCategoryById(equipment.getCategory().getId()).get();
	    if (category != null) {
	        categoryDTO.setId(category.getId());
	        categoryDTO.setName(category.getName());
	    }
	    equipmentDTO.setCategory(categoryDTO);

	    return equipmentDTO;
	}
}
