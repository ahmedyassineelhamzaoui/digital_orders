package com.app.dto;


import lombok.Data;

@Data
public class EquipmentDTO {
    private Long id;
    private String registrationNumber;
    private Double rentalPrice;
    private String name;
    private CategoryDTO category;
}