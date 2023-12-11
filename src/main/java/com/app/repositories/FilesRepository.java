package com.app.repositories;

import com.app.models.ContractFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilesRepository extends JpaRepository<ContractFile, UUID> {
}
