package com.app.dto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;


import com.app.models.Category;
import com.app.models.Equipment;
import com.app.models.enums.EquipmentStatus;
import com.app.services.impl.CategoryServiceImpl;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class EquipmentDTO {
		@Nullable
		private UUID id;
	    @NotBlank(message = "Registration number is required")
	    @Column(unique=true)
	    private String registrationNumber;

	    @NotNull(message="Price is required")
	    @Positive(message = "Price must be positive")
	    private Double rentalPrice;

	    @NotBlank(message = "Name is required")
	    @Size(max = 100, message = "Name must be at most 100 characters")
	    private String name;

	    @NotNull(message ="Status is required")
	    private EquipmentStatus equipmentStatus;


	    private String category;
	    private MultipartFile image;
	    
	    public Equipment toEntity() {
	    	return Equipment.builder()
					.id(id)
	    			.name(name)
	    			.registrationNumber(registrationNumber)
	    			.rentalPrice(rentalPrice)
	    			.image(image.getOriginalFilename())
	    			.build();
	    }

}