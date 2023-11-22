package com.app.models;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.context.support.BeanDefinitionDsl.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@Id 
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
	@NotNull(message="role is required")
	
	@NotNull(message="role is required")
	private Role role;
}
