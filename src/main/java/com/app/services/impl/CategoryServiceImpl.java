package com.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Category;
import com.app.repositories.CategoryRepository;
import com.app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId);
	}

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Long categoryId, Category category) {		
	      Category categoryFinded = getCategoryById(categoryId).get();
	      categoryFinded.setName(category.getName());
	      return categoryRepository.save(categoryFinded);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}
	
}
