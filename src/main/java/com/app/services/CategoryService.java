package com.app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.app.models.Category;

public interface CategoryService {

	List<Category> getAllCategories();
	
	Optional<Category> getCategoryById(UUID categoryId);
	
	Category addCategory(Category category);
	
	Category updateCategory(UUID categoryId, Category category);
	
	void deleteCategory(UUID categoryId);
}
