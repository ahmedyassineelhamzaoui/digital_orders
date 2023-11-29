package com.app.repositories;

import com.app.models.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface DevisRepository extends JpaRepository<Devis,Long> {

    }

