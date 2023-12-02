package com.app.models;

import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.context.support.BeanDefinitionDsl.Role;

import com.app.dto.UserDTO;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id 
    @GeneratedValue(generator = "uuid2")
	private UUID id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private Role role;
	
	public UserDTO maptoDto() {
		return UserDTO.builder()
				.name(name)
				.email(email)
				.build();
	}
}
