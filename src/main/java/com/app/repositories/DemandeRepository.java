package com.app.repositories;

import com.app.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, UUID> {
}
