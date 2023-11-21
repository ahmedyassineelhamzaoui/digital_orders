package com.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="reservations")
@Data
@AllArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date reservationDate;
	private Date returnDate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@ManyToMany
	@JoinTable(
			name ="reservation_equipment",
			joinColumns = @JoinColumn(name ="reservation_id"),
			inverseJoinColumns = @JoinColumn(name="equipment_id")
			
    )
	private List<Equipment> equipments;
	
}
