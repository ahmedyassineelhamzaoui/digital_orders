package com.app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity
@Table(name="files")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractFile {


    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String path;

    @OneToOne
    @JoinColumn(name ="contract_id")
    private Contract contract;
}
