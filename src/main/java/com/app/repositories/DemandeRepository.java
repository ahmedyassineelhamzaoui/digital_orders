package com.app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, UUID>{

}
