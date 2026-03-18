package com.bloggingApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.payloads.ApiResponse;
import com.bloggingApp.payloads.CategoryDto;
import com.bloggingApp.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catDto ){
		CategoryDto createCategory=this.categoryService.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto catDto, @PathVariable Integer catId){
		CategoryDto updateCategory=this.categoryService.updateCategory(catDto, catId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}
	
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
    	this.categoryService.DeleteCategory(catId);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successful", true),HttpStatus.OK);
    }
    
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
    	CategoryDto getCategory=this.categoryService.getCategory(catId);
    	return new ResponseEntity<CategoryDto>(getCategory, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAll(){
    	List<CategoryDto> getAllCat=this.categoryService.getAllCategory();
    	return ResponseEntity.ok(getAllCat);
    }

}
