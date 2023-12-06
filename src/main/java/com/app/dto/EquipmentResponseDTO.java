package com.app.dto;

import com.app.models.Category;
import com.app.models.Demande;
import com.app.models.Equipment;
import com.app.models.enums.EquipmentStatus;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class EquipmentResponseDTO {
    private UUID id;
    private String registrationNumber;
    private Double rentalPrice;
    private String name;
    private EquipmentStatus equipmentStatus;
    private String category;
    private String image;

    public Equipment toEntity() {
        return Equipment.builder()
                .id(id)
                .name(name)
                .registrationNumber(registrationNumber)
                .rentalPrice(rentalPrice)
                .image(image)
                .build();
    }
}
