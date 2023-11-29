package com.app.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="documents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

	@Id
    @GeneratedValue(generator = "uuid2")
	private UUID id;
	
	@OneToOne
	@JoinColumn(name ="contract_id")
	private Contract contract;
}
