package com.app.models;

import java.util.List;
import java.util.UUID;

import com.app.dto.CategoryDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

	@Id
    @GeneratedValue(generator = "uuid2")
	private UUID id;
	
    @Column(unique=true)
	private String name;
	
	@OneToMany
	private List<Equipment> equipments;
	
	public CategoryDTO mapToDto() {
		return CategoryDTO.builder()
				.name(name)
				.build();
	}
}
