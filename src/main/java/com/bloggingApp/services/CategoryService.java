package com.bloggingApp.services;

import java.util.List;



import com.bloggingApp.payloads.CategoryDto;

public interface CategoryService {
	//create
	CategoryDto createCategory(CategoryDto category);
	
	//update
	CategoryDto updateCategory(CategoryDto category, Integer categoryId);
	
	//delete
	public void DeleteCategory( Integer categorId);
	
	//get
	CategoryDto getCategory( Integer categorId);
	
	//getAll
	List<CategoryDto> getAllCategory();


}
