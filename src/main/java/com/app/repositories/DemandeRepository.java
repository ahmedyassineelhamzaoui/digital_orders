package com.app.repositories;


import com.app.models.Demande;
import com.app.models.enums.DemandeStatus;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import java.util.UUID;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, UUID> {
    @Query(value = "SELECT d FROM Demande d  WHERE d.equipment.id = :equipmentnId AND d.demandeStatus =:demandeStatus AND (:endDate>d.startDate AND :startDate< d.endDate)  ")
    List<Demande> getRentedEquipment(UUID equipmentnId, Date startDate, Date endDate , DemandeStatus demandeStatus);
}
