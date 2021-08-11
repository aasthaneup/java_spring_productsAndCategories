package com.assignment.productscategories.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.productscategories.models.CategoryProduct;

@Repository
public interface CategoryProductRepository extends CrudRepository<CategoryProduct, Long> {
	CategoryProduct save(CategoryProduct cp);
}