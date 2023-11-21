package com.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id // pour spécifier la clé primaire
	// pour spécifier la maniéere de géneartion de la clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Name is required")
	@Size(max=100,message="Name must be at most 100 characters")
	private String name;
	@NotBlank(message="Email is required")
	@Email(message="Invalid email format")
	@Size(max=255,message="email must be at most 255 characters")
	@Column(unique = true)
	private String email;
}
