package com.app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
