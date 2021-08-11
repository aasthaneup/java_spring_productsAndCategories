package com.assignment.productscategories.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.productscategories.models.Category;
import com.assignment.productscategories.models.CategoryProduct;
import com.assignment.productscategories.models.Product;
import com.assignment.productscategories.services.CategoryService;
import com.assignment.productscategories.services.ProductService;

@Controller
public class MainController {
	private final ProductService productServ;
	private final CategoryService categoryServ;

	public MainController(ProductService productServ, CategoryService categoryServ) {
		this.categoryServ = categoryServ;
		this.productServ = productServ;
	}
	
	//	get request: create new product page
	//	-------------------------------------
	@RequestMapping("/products/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "newproduct.jsp";
	}
	
	//	post request: create new product route
	//	---------------------------------------
	@RequestMapping(value="/products/new", method=RequestMethod.POST)
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if(result.hasErrors()){
			return "newproduct.jsp";
		} else {
			productServ.saveProduct(product);
			return "redirect:/products/new";
		}
	}

	//	get request: create new category page
	//	--------------------------------------
	@RequestMapping("/categories/new")
	public String newCategory(Model model) {
		model.addAttribute("category", new Category());
		return "newcategory.jsp";
	}

	//	post request: create new category route
	//	----------------------------------------
	@RequestMapping(value="/categories/new", method=RequestMethod.POST)
	public String createCategories(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if(result.hasErrors()){
			return "newcategory.jsp";
		} else {
			categoryServ.saveCategory(category);
			return "redirect:/categories/new";
		}
	}

	//	get request: show product page
	//	--------------------------------------
	@RequestMapping("/products/{id}")
	public String showProduct(@PathVariable("id") long id, Model model) {
		
//		***was testing for future implementation of certain features***
//		if(productServ.allProducts().contains(productServ.findProduct(id))) {
//			System.out.println("The product exists");
//		}
//		else {
//			System.out.println("The product does not exist!");
//		}
		
		List<Category> allCat = categoryServ.allCategories();
		List<Category> prodCategories = productServ.findProduct(id).getCategories();
		List<Category> remCategories = new ArrayList<Category>();

		model.addAttribute("product", productServ.findProduct(id));
		model.addAttribute("categories", allCat);

		for(int i=0; i<allCat.size();i++) {
			if(!prodCategories.contains(allCat.get(i))) {
				remCategories.add(allCat.get(i));
			}
		}
		model.addAttribute("prodCategories", remCategories);

		return "showproduct.jsp";
	}


	//	post request: join product to category route
	//	----------------------------------------
	@RequestMapping(value="/products/{id}/addcategory", method=RequestMethod.POST)
	public String addCategory(@RequestParam(value="product_id") Long product_id, @RequestParam(value="category_id") Long category_id) {
		Product p = productServ.findProduct(product_id);
		Category c = categoryServ.findCategory(category_id);

		CategoryProduct cp = new CategoryProduct(p,c);

		productServ.saveRelationship(cp);

		return "redirect:/products/"+product_id;
	}


	//	get request: show category page
	//	--------------------------------------
	@RequestMapping("/categories/{id}")
	public String showCategories(@PathVariable("id") long id, Model model) {
		List<Product> allProd = productServ.allProducts();
		List<Product> catProducts = categoryServ.findCategory(id).getProducts();
		List<Product> remProducts = new ArrayList<Product>();

		model.addAttribute("category", categoryServ.findCategory(id));
		model.addAttribute("products", allProd);

		for(int i = 0; i<allProd.size();i++) {
			if(!catProducts.contains(allProd.get(i))) {
				remProducts.add(allProd.get(i));
			}
		}
		model.addAttribute("catProducts", remProducts);

		return "showcategory.jsp";
	}


	//	post request: join category to product route
	//	----------------------------------------
	@RequestMapping(value="/categories/{id}/addproduct", method=RequestMethod.POST)
	public String addProduct(@RequestParam(value="product_id") Long product_id, @RequestParam(value="category_id") Long category_id) {
		Product p = productServ.findProduct(product_id);
		Category c = categoryServ.findCategory(category_id);

		CategoryProduct cp = new CategoryProduct(p,c);

		productServ.saveRelationship(cp);

		return "redirect:/categories/"+category_id;
	}	



}
