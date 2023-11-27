package com.app.models;


import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.app.dto.EquipmentDTO;
import com.app.models.enums.EquipmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipment {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(unique=true)
    private String registrationNumber;

    private Double rentalPrice;

    private String name;

    private EquipmentStatus equipmentStatus;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    
    private String image;
    
    public EquipmentDTO toDto() {
    	return EquipmentDTO.builder()
    			.name(name)
    			.registrationNumber(registrationNumber)
    			.rentalPrice(rentalPrice)
    			.build();
    }

}
