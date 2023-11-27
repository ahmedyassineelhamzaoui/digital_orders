package com.app.repositories;

import com.app.models.Contract;
import com.app.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContractRepository extends JpaRepository<Contract, UUID> {
}
