package com.assignment.productscategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignment.productscategories.models.Category;
import com.assignment.productscategories.repositories.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepo;
	public CategoryService(CategoryRepository categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
	public List<Category> allCategories(){
		return categoryRepo.findAll();
	}
	public Category findCategory(Long id) {
		Optional<Category> optCat = categoryRepo.findById(id);
		if(optCat.isPresent()) {
			return optCat.get();
		} else {
			return null;
		}
	}
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}
	public void deleteCategory(Long id) {
		categoryRepo.deleteById(id);
	}
}