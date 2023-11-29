package com.app.dto;

import org.springframework.context.support.BeanDefinitionDsl.Role;
import com.app.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public class UserDTO {

	@NotBlank(message="Name is required")
	@Size(max=100,message="Name must be at most 100 characters")
	private String name;
	
	@NotBlank(message="Email is required")
	@Email(message="Invalid email format")
	@Size(max=255,message="email must be at most 255 characters")
	private String email;

	private Role role;
	
	public User mapToEntity() {
		return User.builder()
				.name(name)
				.email(email)
				.role(role)
				.build();
	}
}
