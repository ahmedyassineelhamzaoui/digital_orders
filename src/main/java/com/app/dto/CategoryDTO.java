package com.app.dto;

import com.app.models.Category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public class CategoryDTO {
    
	@NotEmpty(message = "name is required")
    private String name;
	
	public Category mapToEntity() {
		return Category.builder()
				.name(name)
				.build();
	}
}