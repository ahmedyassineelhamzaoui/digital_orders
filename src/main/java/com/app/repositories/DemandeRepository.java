package com.app.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.models.Demande;
import com.app.models.User;
import java.util.UUID;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, UUID> {
    User getUserByName(String user);
}
