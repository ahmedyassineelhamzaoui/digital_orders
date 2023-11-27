package com.app.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.EquipmentDTO;
import com.app.models.Category;
import com.app.models.Equipment;
import com.app.models.enums.EquipmentStatus;
import com.app.services.impl.CategoryServiceImpl;
import com.app.services.impl.EquipmentServiceImpl;
import com.app.utils.FileUploadUtil;

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
	public ResponseEntity<Map<String,Object>> addEquipment(@Valid EquipmentDTO myequipment,
			@RequestParam("registrationNumber") String registrationNumber,
			@RequestParam("rentalPrice")        Double rentalPrice,
			@RequestParam("name")               String name,
			@RequestParam("equipmentStatus")    String equipmentStatus,
			@RequestParam("category")           String category,
			@RequestParam("image") MultipartFile image)
			{
		Map<String,Object> response = new HashMap<String, Object>();
		
		
	    Equipment equipment = new Equipment();
		try {
			String fileName = StringUtils.cleanPath(image.getOriginalFilename());
	         
	        String filecode = FileUploadUtil.saveFile(fileName, image);
	        
	        if(category.trim().equals("")) {
	        	equipment.setCategory(null);
			}else {
		        Optional<Category> categoryToFind = categoryServiceImpl.getCategoryById(UUID.fromString(category));
	        	equipment.setCategory(categoryToFind.get());
			}
	        
		    equipment.setRegistrationNumber(registrationNumber);
		    equipment.setRentalPrice(rentalPrice);
		    equipment.setName(name);
		    equipment.setEquipmentStatus(EquipmentStatus.valueOf(equipmentStatus));
	        equipment.setImage(filecode+"_"+fileName);
	        
		}catch(Exception e) {
			response.put("status", "error");
			response.put("message", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
        
		response.put("status","success");
		response.put("equipment", equipmentServiceImpl.addEquipment(equipment));
		return ResponseEntity.ok(response);
	}
	
	
	
	@PutMapping("/equipment/{id}")
	public ResponseEntity<Map<String, Object>> updateEquipment(@Valid
			EquipmentDTO myequipment,
			@RequestParam("registrationNumber") String registrationNumber,
			@RequestParam("rentalPrice")        Double rentalPrice,
			@RequestParam("name")               String name,
			@RequestParam("equipmentStatus")    String equipmentStatus,
			@RequestParam("category")           String category,
			@RequestParam("image") MultipartFile image,
	        @PathVariable UUID id
	        ) {
	    Map<String, Object> response = new HashMap<>();

		Equipment e= new Equipment();
		
		if(category.trim().equals("")) {
        	e.setCategory(null);
		}else {
	        Optional<Category> categoryToFind = categoryServiceImpl.getCategoryById(UUID.fromString(category));
        	e.setCategory(categoryToFind.get());
		}
		
        try {
        	if (image != null && !image.isEmpty()) {
        		 String fileName = StringUtils.cleanPath(image.getOriginalFilename());
     			 String filecode = FileUploadUtil.saveFile(fileName, image);
     	         e.setImage(filecode+"_"+fileName);
        	}
    	   
	        e.setRegistrationNumber(registrationNumber);
	        e.setEquipmentStatus(EquipmentStatus.valueOf(equipmentStatus));
		    e.setRentalPrice(rentalPrice);
		    e.setName(name);
				
		} catch (Exception ex) {
			response.put("status", "error");
	        response.put("message", ex.getMessage());
	        return ResponseEntity.badRequest().body(response);
		}
       	    
	        Equipment updatedEquipment = equipmentServiceImpl.updateEquipment(id, e);
	        response.put("status", "success");
	        response.put("message", "Equipment updated successfully");
	        response.put("Equipment", updatedEquipment);
	        return ResponseEntity.ok(response);
	   
	}

	@DeleteMapping("/equipment/{id}")
	public ResponseEntity<Map<String,Object>> deleteEquipment(@PathVariable UUID id){
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
	
}
