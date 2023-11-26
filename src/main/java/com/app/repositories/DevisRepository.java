package com.app.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.models.Devis;

@Repository
public interface DevisRepository extends JpaRepository<Devis, UUID> {

}
