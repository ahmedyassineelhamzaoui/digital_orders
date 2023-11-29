package com.app.repositories;

import java.util.UUID;


import com.app.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
