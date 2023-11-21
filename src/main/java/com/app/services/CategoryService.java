package com.app.services;

import java.util.List;
import java.util.Optional;

import com.app.models.Category;

public interface CategoryService {

	List<Category> getAllCategories();
	
	Optional<Category> getCategoryById(Long categoryId);
	
	Category addCategory(Category category);
	
	Category updateCategory(Long categoryId, Category category);
	
	void deleteCategory(Long categoryId);
}
