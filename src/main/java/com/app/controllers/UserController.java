package com.app.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDTO;
import com.app.models.User;
import com.app.services.impl.UserServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/users")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody @Valid UserDTO user) {
        Map<String, Object> response = new HashMap<>();
        try {
            User createdUser = userService.saveUser(user.mapToEntity());

            response.put("status", "success");
            response.put("message", "User created successfully");
            response.put("user", createdUser);

            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            response.put("status", "error");
            response.put("message", "Email already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

	@PutMapping("/users/{id}")
    public ResponseEntity<Map<String,Object>> updateUser(@PathVariable UUID id,@RequestBody @Valid UserDTO user) {
           User updatedUser = userService.updateUser(id,user.mapToEntity()); 
           Map<String,Object> response = new HashMap<>();
           response.put("status", "success");
           response.put("message", "user updated successfuly");
           response.put("user", updatedUser);
           return ResponseEntity.ok(response);
    }
}
