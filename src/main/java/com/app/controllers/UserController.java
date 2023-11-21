package com.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.User;
import com.app.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            response.put("status", "error");
            response.put("message", "Validation failed");
            Map<String, String> errors = new HashMap<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
            response.put("errors", 422);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            User createdUser = userService.addUser(user);

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

	@PutMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
    }
}
