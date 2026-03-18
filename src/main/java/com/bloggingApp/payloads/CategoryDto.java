package com.bloggingApp.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int categoryId;
	@NotNull
	@Size(min=4, message="Category must be min of 4 character")
	private String categoryTitle;
	@NotNull
	@Size(min=10, message="Category Description must be min of 10 character")
	private String categoryDescription;
	

}
