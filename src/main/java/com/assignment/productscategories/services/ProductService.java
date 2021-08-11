package com.assignment.productscategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignment.productscategories.models.CategoryProduct;
import com.assignment.productscategories.models.Product;
import com.assignment.productscategories.repositories.CategoryProductRepository;
import com.assignment.productscategories.repositories.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepo;
	private final CategoryProductRepository catProdRepo;
	
	public ProductService(ProductRepository productRepo, CategoryProductRepository catProdRepo) {
		this.productRepo = productRepo;
		this.catProdRepo = catProdRepo;
	}
	
	public List<Product> allProducts(){
		return productRepo.findAll();
	}
	
	public Product findProduct(Long id) {
		Optional<Product> optProd = productRepo.findById(id);
		if(optProd.isPresent()) {
			return optProd.get();
		} else {
			return null;
		}
	}
	
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
	
	public CategoryProduct saveRelationship(CategoryProduct cp) {
		return catProdRepo.save(cp);
	}
	
	
}
