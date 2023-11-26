package com.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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
import com.app.models.Category;
import com.app.services.impl.CategoryServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl; 
	
	
	@PostMapping("/categories")
	public ResponseEntity<Map<String,Object>> addCategory(@Valid @RequestBody Category category,BindingResult bindingResult){
		Map<String,Object> response = new HashMap<String, Object>();
		if(bindingResult.hasErrors()) {
			response.put("status", "error");
			response.put("message", "validation errrors");
			Map<String,Object> errors = new HashMap<String, Object>();
			for(ObjectError error:bindingResult.getAllErrors()) {
				errors.put(error.getObjectName(), error.getDefaultMessage());
			}
			response.put("errors", errors);
			return ResponseEntity.badRequest().body(response);
		}
	    try {
		    Category categoryToAdd = categoryServiceImpl.addCategory(category);
            response.put("status", "success");
            response.put("message", "category added successfuly");
            response.put("Category", categoryToAdd);
            return ResponseEntity.ok(response);
	    }catch(DataIntegrityViolationException e) {
	    	response.put("status", "error");
	    	response.put("message", "name already exist");
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	    }
	}
	@PutMapping("/categories/{id}")
	public ResponseEntity<Map<String, Object>> updateCategory(
	        @PathVariable UUID id,
	        @RequestParam String category_name) {

	    Map<String, Object> response = new HashMap<>();

	    Optional<Category> categoryFound = categoryServiceImpl.getCategoryById(id);
	    if (categoryFound.isEmpty()) {
	        response.put("status", "error");
	        response.put("message", "Category not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    if (category_name == null || category_name.trim().isEmpty()) {
	        response.put("status", "error");
	        response.put("message", "Please provide a non-empty name for the category");
	        return ResponseEntity.badRequest().body(response);
	    }

	    
	    try {
	    	categoryFound.get().setName(category_name);
	    	Category updatedCategory = categoryServiceImpl.updateCategory(id, categoryFound.get());
		    response.put("status", "success");
		    response.put("message", "Category updated successfully");
		    response.put("category", updatedCategory);
		    return ResponseEntity.ok(response);
	    }catch(DataIntegrityViolationException e) {
	    	response.put("status", "error");
		    response.put("message", "category already exist");
		    return ResponseEntity.badRequest().body(response);
	    }
	    
	}
    @GetMapping("/categories")
	public ResponseEntity<Map<String,Object>> getAllCategories(){
		Map<String,Object> response = new HashMap<String, Object>();
		List<Category> categories = categoryServiceImpl.getAllCategories();
		if(categories.isEmpty()) {
			response.put("status", "info");
			response.put("message", "no category founded");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		response.put("status", "success");
		response.put("categories", categories);
		return ResponseEntity.ok(response);
	}
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Map<String,Object>> deleteCategory(@PathVariable UUID id){
    	Map<String,Object> response = new HashMap<String, Object>();
    	Optional<Category> optionalCategory = categoryServiceImpl.getCategoryById(id);
    	if(optionalCategory.isEmpty()) {
    		response.put("status","error");
    		response.put("message", "category not found");
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    	}
    	categoryServiceImpl.deleteCategory(id);
    	response.put("status", "success");
    	response.put("message", "category deleted successfuly");
    	return ResponseEntity.ok(response);
    }
}
